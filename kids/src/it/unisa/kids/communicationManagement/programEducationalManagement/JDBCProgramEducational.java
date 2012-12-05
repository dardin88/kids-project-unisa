package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCProgramEducational implements IProgramEducational
{

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
            String query = "INSERT INTO " + DBNames.TABLE_COMMENT + " ("
                    + DBNames.ATT_COMMENT_DATE + ", "
                    + DBNames.ATT_COMMENT_DESCRIPTION + ", "
                    + DBNames.ATT_COMMENT_ID + ", "
                    + DBNames.ATT_COMMENT_IDAUTHOR + ", "
                    + DBNames.ATT_COMMENT_IDSECTION + ", "
                    + DBNames.ATT_COMMENT_IDYEAR + ", "
                    + DBNames.ATT_COMMENT_TYPEMODIFY
                    + ") VALUES(pProject, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(query);
            pstmt.setDate(1, new Date(toAdd.getDate().getTimeInMillis()));
            pstmt.setString(2, toAdd.getContenuto());
            pstmt.setInt(3, toAdd.getId());
            pstmt.setInt(4, toAdd.getIdAutore());
            pstmt.setInt(5, toAdd.getIdSezione());
            pstmt.setInt(6, toAdd.getIdAnnuale());
            pstmt.setString(7, toAdd.getTipoModifica());
            pstmt.executeUpdate();
            con.commit();
    }

}
