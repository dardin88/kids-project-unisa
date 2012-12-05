package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.communicationManagement.newsManagement.INewsManager;
import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class JDBCProgramEducational implements IProgramEducational {

    private static IProgramEducational manager;

    public static IProgramEducational getInstance() {
        if (manager == null) {
            return (manager = new JDBCProgramEducational());
        } else {
            return manager;
        }
    }

    @Override
    public void modifySubstantialProject(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modifyLittleProject(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String approveProject(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyExpirationPlan(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> showNotifyExpirationPlan(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void approvedChangesProposed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void rejectChangedProposed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void insertComment(CommentoBean toAdd) throws SQLException {
            Connection con = DBConnectionPool.getConnection();
            PreparedStatement pstmt=null;       

        try {

            String query = "INSERT INTO " + DBNames.TABLE_COMMENT + " ("
                    + DBNames.ATT_COMMENT_DATE + ", "
                    + DBNames.ATT_COMMENT_DESCRIPTION + ", "
                    // + DBNames.ATT_COMMENT_ID + ", "
                    + DBNames.ATT_COMMENT_IDAUTHOR + ", "
                    + DBNames.ATT_COMMENT_IDSECTION + ", "
                    + DBNames.ATT_COMMENT_IDYEAR + ", "
                    + DBNames.ATT_COMMENT_TYPEMODIFY
                    + ") VALUES(?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);
            pstmt.setDate(1, new Date(toAdd.getDate().getTimeInMillis()));
            pstmt.setString(2, toAdd.getContenuto());
            //pstmt.setInt(3, toAdd.getId());
            pstmt.setInt(3, toAdd.getIdAutore());
            pstmt.setInt(4, toAdd.getIdSezione());
            pstmt.setInt(5, toAdd.getIdAnnuale());
            pstmt.setString(6, toAdd.getTipoModifica());
            pstmt.executeUpdate();
            con.commit();
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    public ArrayList<CommentoBean> getComments(AnnualProjectSection toView) throws SQLException {
        ResultSet rs = null;
        ArrayList<CommentoBean> toReturn=new ArrayList<CommentoBean>();
        String query = "SELECT * FROM " + DBNames.TABLE_COMMENT
                + " WHERE " + DBNames.ATT_COMMENT_ID + " = " + toView.getId();
        PreparedStatement pstm;
        Connection con;
        con = DBConnectionPool.getConnection();
        pstm = con.prepareStatement(query);
        rs = pstm.executeQuery();
        while(rs.next()){
            CommentoBean toAdd=new CommentoBean();
            GregorianCalendar todata=new GregorianCalendar();
            toAdd.setContenuto(rs.getString(DBNames.ATT_COMMENT_DESCRIPTION));
                    todata.setTimeInMillis(rs.getDate(DBNames.ATT_COMMENT_DATE).getTime());
            toAdd.setDate(todata);
            toAdd.setId(rs.getInt(DBNames.ATT_COMMENT_ID));
            toAdd.setIdAnnuale(rs.getInt(DBNames.ATT_COMMENT_IDYEAR));
            toAdd.setIdAutore(rs.getInt(DBNames.ATT_COMMENT_IDAUTHOR));
            toAdd.setIdSezione(rs.getInt(DBNames.ATT_COMMENT_IDSECTION));
            toAdd.setTipoModifica(rs.getString(DBNames.ATT_COMMENT_TYPEMODIFY));
            toReturn.add(toAdd);
            
        }
        con.commit();
        return toReturn;
    }
}
