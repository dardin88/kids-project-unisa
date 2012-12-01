package it.unisa.kids.communicationManagement.reunion;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class JDBCReunionManager implements IReunionManager {

    private static JDBCReunionManager manager;
    private static int id;

    /**
     * the constructor empty
     */
    private JDBCReunionManager() {
    }

    /**
     * this method implements the design pattern "singleton"
     *
     * @return manager
     */
    public static JDBCReunionManager getInstance() {
        if (manager == null) {
            return manager = new JDBCReunionManager();
        } else {
            return manager;
        }
    }

    /**
     * this method insert the reunion in the database.
     *
     * @param Reunion reunion
     *
     */
    public void insert(Reunion reunion) throws ErroreNeiDati, SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query1;
        String query2;

        try {
            con = DBConnectionPool.getConnection();
            query1 = "INSERT INTO " + DBNames.TABLE_REUNION + "("
                    + DBNames.ATT_REUNION_ID + ","
                    + DBNames.ATT_REUNION_TITLE + ","
                    + DBNames.ATT_REUNION_DESCRIPTION + ","
                    + DBNames.ATT_REUNION_DATA + ","
                    + DBNames.ATT_REUNION_FIRST_TIME + ","
                    + DBNames.ATT_REUNION_SECOND_TIME + ","
                    + DBNames.ATT_REUNION_TYPE;
            query2 = "VALUES (?,?,?,?,?,?,?";

            query1 += ")";
            query2 += ")";
            pStmt = con.prepareStatement(query1 + query2);

            pStmt.setInt(1, id);
            pStmt.setString(2, reunion.getTitle());
            pStmt.setString(3, reunion.getDescription());
            pStmt.setString(4, reunion.getDate());
            pStmt.setString(5, reunion.getFirstTime());
            pStmt.setString(6, reunion.getSecondTime());
            pStmt.setString(7, reunion.getType());

            System.out.println(pStmt);
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con); //connection of DB
        }
    }

    /**
     * this method modify the reunion in the database.
     *
     * @param Reunion changedReunion
     *
     */
    public void update(Reunion changedReunion) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query1;


        try {
            con = DBConnectionPool.getConnection();
            query1 = "UPDATE " + DBNames.TABLE_REUNION + " SET "
                    + DBNames.ATT_REUNION_TITLE + "=? ,"
                    + DBNames.ATT_REUNION_DESCRIPTION + "=? ,"
                    + DBNames.ATT_REUNION_DATA + "=? ,"
                    + DBNames.ATT_REUNION_FIRST_TIME + "=? ,"
                    + DBNames.ATT_REUNION_SECOND_TIME + "=? ,"
                    + DBNames.ATT_REUNION_TYPE + "=?";

            query1 += "WHERE " + DBNames.ATT_REUNION_ID + "=?";

            pStmt = con.prepareStatement(query1);
            pStmt.setString(1, changedReunion.getTitle());
            pStmt.setString(2, changedReunion.getDescription());
            pStmt.setString(3, changedReunion.getDate());
            pStmt.setString(4, changedReunion.getFirstTime());
            pStmt.setString(5, changedReunion.getSecondTime());
            pStmt.setString(6, changedReunion.getType());
            pStmt.setInt(7, changedReunion.getId());

            pStmt.executeUpdate();
            con.commit();

        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con); //connection of DB
        }
    }

    /**
     * this method delete the reunion in the database.
     *
     * @param Reunion deletedReunion
     *
     */
    public void delete(Reunion deleteReunion) throws SQLException {
        Connection con = null;
        Statement pStmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            query = "DELETE FROM " + DBNames.TABLE_REUNION + " WHERE " + DBNames.ATT_REUNION_ID + "='"+deleteReunion.getId()+"'";

            System.out.println(query);
            
            pStmt.executeQuery(query);
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    public ArrayList<Reunion> getMeetingList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query;
        ArrayList<Reunion> listMeeting = new ArrayList<Reunion>();
        try {

            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_REUNION;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                Reunion meeting = new Reunion();
                int id = rs.getInt(DBNames.ATT_REUNION_ID);
                String title = rs.getString(DBNames.ATT_REUNION_TITLE);
                String description = rs.getString(DBNames.ATT_REUNION_DESCRIPTION);
                String date = rs.getString(DBNames.ATT_REUNION_DATA);
                String firstTime = rs.getString(DBNames.ATT_REUNION_FIRST_TIME);
                String secondTime = rs.getString(DBNames.ATT_REUNION_SECOND_TIME);
                String type = rs.getString(DBNames.ATT_REUNION_TYPE);

                meeting.setId(id);
                meeting.setTitle(title);
                meeting.setDescription(description);
                meeting.setDate(date);
                meeting.setFirstTime(firstTime);
                meeting.setSecondTime(secondTime);
                meeting.setType(type);

                listMeeting.add(meeting);
            }
            return listMeeting;

        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
}
