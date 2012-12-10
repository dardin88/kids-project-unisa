package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.Educator;
import it.unisa.kids.accessManagement.accountManagement.JDBCAccountManager;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCClassManager implements IClassManager {

    private static JDBCClassManager manager;

    private JDBCClassManager() {
    }

    public static JDBCClassManager getInstance() {
        if (manager == null) {
            manager = new JDBCClassManager();
        }

        return manager;
    }

    public synchronized ClassBean insert(ClassBean pClass) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = "INSERT INTO " + DBNames.TABLE_CLASS + " (" + DBNames.ATT_CLASS_NAME + ", " + DBNames.ATT_CLASS_STATE + ") "
                + "VALUES ('" + pClass.getClassName() + "', '"
                + pClass.getState() + "');"; 			//come inserire educatori e bambini


        try {
            con = DBConnectionPool.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }

        return pClass;
    }

    public synchronized ClassBean delete(ClassBean pClass) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = "DELETE FROM " + DBNames.TABLE_CLASS 
                + " WHERE " + DBNames.ATT_CLASS_ID + "=" + pClass.getIdClasse() + ";";


        try {
            con = DBConnectionPool.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }

        return pClass;
    }

    public synchronized List<ClassBean> search(ClassBean pClass) throws SQLException {
        Connection con = null;
        ResultSet result;
        PreparedStatement stmt = null;
        boolean andState = false;
        ClassBean tmpClassBean = new ClassBean();

        List<ClassBean> listOfClassBean = new ArrayList<ClassBean>();		//deve essere riempito con il risultato della query
        StringBuffer query = new StringBuffer("SELECT * FROM " + DBNames.TABLE_CLASS + " WHERE ");

        if (pClass.getIdClasse() > 0) {
            query.append(DBNames.ATT_CLASS_ID + "=?");
            andState = true;
        }
        if (pClass.getClassName() != null  ) {
            query.append(useAnd(andState) + DBNames.ATT_CLASS_NAME + "=?");
            andState = true;
        }
        if (pClass.getState() != null  ) {
            query.append(useAnd(andState) + DBNames.ATT_CLASS_STATE + "=?s");
            andState = true;
        }
        if (andState == false) {
            query.append("1");
        }

        query.append(";");

        System.out.println("****query:" + query);
        con = DBConnectionPool.getConnection();
        stmt = con.prepareStatement(query.toString());

        try {


            int paramNum = 1;
            if (pClass.getIdClasse() > 0) {
                stmt.setInt(paramNum, pClass.getIdClasse());
                paramNum++;
            }
            if (pClass.getClassName() != null ) {
                stmt.setString(paramNum, pClass.getClassName());
                paramNum++;
            }
            if (pClass.getState() != null ) {
                stmt.setString(paramNum, pClass.getState());
                paramNum++;
            }

            result = stmt.executeQuery();
            con.commit();

            while (result.next()) {
                /*  ho provato a riempire gli array list bambini e educatori
                 JDBCRegistrationChildManager regMan=JDBCRegistrationChildManager.getInstance();			  
                 RegistrationChild pRegChild=new RegistrationChild();
                 regChild.set;
                 List<RegistrationChild> tmpChild=regMan.search(pRegChild);
                 tmpClassBean.setBambini(tmpChild);
                                
                 JDBCAccountManager accMan=JDBCAccountManager.getInstance();
                 Account pAcc=new Account();
                 pAcc.set;
                 List<Account> tmpEducator=accMan.search(pAcc);
                 tmpClassBean.setEducatori(tmpEducator);*/
                System.out.println(tmpClassBean.getClassName());
                tmpClassBean = new ClassBean();

                tmpClassBean.setIdClasse(result.getInt(DBNames.ATT_CLASS_ID));
                tmpClassBean.setClassName(result.getString(DBNames.ATT_CLASS_NAME));
                tmpClassBean.setState(result.getString(DBNames.ATT_CLASS_STATE));

                listOfClassBean.add(tmpClassBean);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }

        return listOfClassBean;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public synchronized ClassBean update(ClassBean pClass) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = "UPDATE " + DBNames.TABLE_CLASS + " "
                + "SET " + DBNames.ATT_CLASS_NAME + "='" + pClass.getClassName()
                + "', " + DBNames.ATT_CLASS_STATE + "='" + pClass.getState()
                + "' WHERE " + DBNames.ATT_CLASS_ID + "=" + pClass.getIdClasse() + ";";

        System.out.println(query);
        try {
            con = DBConnectionPool.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }

        return pClass;
    }
 
    public List<ClassBean> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ClassBean> classi = new ArrayList<ClassBean>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_CLASS;
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(query);
            con.commit();
            while(rs.next())
            {
                ClassBean tmpClass=new ClassBean();
                tmpClass.setClassName(rs.getString(DBNames.ATT_CLASS_NAME));
                tmpClass.setState(rs.getString(DBNames.ATT_CLASS_STATE));
                tmpClass.setIdClasse(rs.getInt(DBNames.ATT_CLASS_ID));
                
                classi.add(tmpClass);
            }
        } 
        finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return classi;
    }
}
