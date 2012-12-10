package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import it.unisa.kids.common.RefinedAbstractManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRenunciationManager implements IRenunciationManager {

    private static JDBCRenunciationManager manager;

    private JDBCRenunciationManager() {
    }

    public static synchronized JDBCRenunciationManager getInstance() {
        if (manager == null) {
            manager = new JDBCRenunciationManager();
        }

        return manager;
    }

    public synchronized void insert(Renunciation pRenunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_RENUNCIATION + " ("
                    + DBNames.ATT_RENUNCIATION_ID + ", "
                    + DBNames.ATT_RENUNCIATION_ID_CHILD + ", "
                    + DBNames.ATT_RENUNCIATION_MOTIVATION + ", "
                    + DBNames.ATT_RENUNCIATION_CONFIRM + ", "
                    + ") VALUES(?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pRenunciation.getId());
            pstmt.setInt(2, pRenunciation.getIdBambino());
            pstmt.setString(3, pRenunciation.getMotivazione());
            pstmt.setInt(4, pRenunciation.isConferma());

            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public void delete(Renunciation pRenunciation) throws SQLException {
        Connection con = null;
        Statement stmt = null;

        try {
            con = DBConnectionPool.getConnection();
            String query = "DELETE FROM '" + DBNames.TABLE_RENUNCIATION + "' WHERE '" + DBNames.ATT_RENUNCIATION_ID + "'='" + pRenunciation.getId() + "';";

            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    public List<Renunciation> search(Renunciation pRenunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Renunciation> listRenunciation = new ArrayList<Renunciation>();
        StringBuffer query = new StringBuffer("SELECT * "
                + "FROM " + DBNames.TABLE_RENUNCIATION
                + "WHERE ");

        boolean andState = false;

        if (pRenunciation.getId() > 0) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_ID + "=? ,");
            andState = true;
        }
        if (pRenunciation.getIdBambino() >= 0) {
            query.append(useAnd(andState) + DBNames.ATT_RENUNCIATION_ID_CHILD + " = ? ,");
            andState = true;
        }
        if (pRenunciation.isConferma() == 1) {
            query.append(useAnd(andState) + DBNames.ATT_RENUNCIATION_CONFIRM + " = ? ");
            andState = true;
        }
        if (!andState) {  // nel caso tutti i parametri sono null
            query.append("1");
        }
        System.out.println(query);

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query.toString());

            int paramNum = 1;

            if (pRenunciation.getIdBambino() > 0) {
                pstmt.setInt(paramNum, pRenunciation.getId());
                paramNum++;
            }

            if (pRenunciation.getIdBambino() > 0) {
                pstmt.setInt(paramNum, pRenunciation.getIdBambino());
                paramNum++;
            }
            if (pRenunciation.isConferma() == 1) {
                pstmt.setInt(paramNum, pRenunciation.isConferma());
                paramNum++;
            }

            result = pstmt.executeQuery();
            con.commit();
            while (result.next()) {
                Renunciation p = new Renunciation();
                p.setId(result.getInt(DBNames.ATT_RENUNCIATION_ID));
                p.setConferma(result.getInt(DBNames.ATT_RENUNCIATION_CONFIRM));
                p.setIdBambino(result.getInt(DBNames.ATT_RENUNCIATION_ID_CHILD));
                p.setMotivazione(result.getString(DBNames.ATT_RENUNCIATION_MOTIVATION));
                listRenunciation.add(p);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }

        return listRenunciation;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public void update(Renunciation pRenunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        boolean commaState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing update query string
            query = "UPDATE " + DBNames.TABLE_RENUNCIATION + " SET ";

            if (pRenunciation.getIdBambino() != 0) {
                query += DBNames.ATT_RENUNCIATION_ID_CHILD + "=?";
                commaState = true;
            }

            if (pRenunciation.getMotivazione() != null) {
                query += DBNames.ATT_RENUNCIATION_MOTIVATION + "=?";
                commaState = true;
            }

            query += DBNames.ATT_RENUNCIATION_CONFIRM + "=?";
            commaState = true;


            query += " WHERE " + DBNames.ATT_RENUNCIATION_ID + " = ?";
            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument

            if (pRenunciation.getIdBambino() != 0) {
                pstmt.setInt(i, pRenunciation.getIdBambino());
                i++;
            }
            if (pRenunciation.getMotivazione() != null) {
                pstmt.setString(i, pRenunciation.getMotivazione());
                i++;
            }

            pstmt.setInt(i, pRenunciation.isConferma());
            i++;

            pstmt.setInt(i, pRenunciation.getId());

            // executing update query
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }
}
