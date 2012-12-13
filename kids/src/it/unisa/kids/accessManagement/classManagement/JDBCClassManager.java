package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
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

    @Override
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

    @Override
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

    @Override
    public synchronized List<ClassBean> search(ClassBean pClass) throws SQLException {
        Connection con;
        ResultSet result;
        PreparedStatement stmt;
        boolean andState = false;
        ClassBean tmpClassBean;
        List<ClassBean> listOfClassBean = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM " + DBNames.TABLE_CLASS + " WHERE ");
        if (pClass.getIdClasse() > 0) {
            query.append(DBNames.ATT_CLASS_ID + "=?");
            andState = true;
        }
        if (pClass.getClassName() != null) {
            if (!pClass.getClassName().equals("")) {
                query.append(useAnd(andState)).append(DBNames.ATT_CLASS_NAME + " like \'%").append(pClass.getClassName()).append("%\'");
                andState = true;
            }
        }
        if (pClass.getState() != null) {
            if (!pClass.getState().equals("")) {
                query.append(useAnd(andState)).append(DBNames.ATT_CLASS_STATE + "=?");
                andState = true;
            }
        }
        if (andState == false) {
            query.append("1");
        }
        query.append(";");
        con = DBConnectionPool.getConnection();
        stmt = con.prepareStatement(query.toString());
        try {
            int paramNum = 1;
            if (pClass.getIdClasse() > 0) {
                stmt.setInt(paramNum, pClass.getIdClasse());
                paramNum++;
            }
            if (pClass.getState() != null) {
                if (!pClass.getState().equals("")) {
                    stmt.setString(paramNum, pClass.getState());
                    paramNum++;
                }
            }
            result = stmt.executeQuery();
            con.commit();
            while (result.next()) {
                tmpClassBean = new ClassBean();
                tmpClassBean.setIdClasse(result.getInt(DBNames.ATT_CLASS_ID));
                tmpClassBean.setClassName(result.getString(DBNames.ATT_CLASS_NAME));
                tmpClassBean.setState(result.getString(DBNames.ATT_CLASS_STATE));

                JDBCRegistrationChildManager regMan = JDBCRegistrationChildManager.getInstance();
                RegistrationChild pRegChild = new RegistrationChild();
                pRegChild.setSectionId(result.getInt(DBNames.ATT_CLASS_ID));
                List<RegistrationChild> tmpChild = regMan.search(pRegChild);
                tmpClassBean.setBambini(tmpChild);

                IAccountManager accMan = JDBCAccountManager.getInstance();
                tmpClassBean.setEducatori(accMan.searchEducatorByClass(pClass));

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

    @Override
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

    @Override
    public List<ClassBean> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ClassBean> classi = new ArrayList<>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_CLASS;
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                ClassBean tmpClass = new ClassBean();
                tmpClass.setClassName(rs.getString(DBNames.ATT_CLASS_NAME));
                tmpClass.setState(rs.getString(DBNames.ATT_CLASS_STATE));
                tmpClass.setIdClasse(rs.getInt(DBNames.ATT_CLASS_ID));

                classi.add(tmpClass);
            }
        } finally {
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
