package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCMeetingManager implements IMeetingManager {

    private static JDBCMeetingManager manager;
    private static int id;

    /**
     * the constructor empty
     */
    private JDBCMeetingManager() {
    }

    /**
     * this method implements the design pattern "singleton"
     *
     * @return manager
     */
    public static JDBCMeetingManager getInstance() {
        if (manager == null) {
            return manager = new JDBCMeetingManager();
        } else {
            return manager;
        }
    }

    /**
     * This method insert the meeting in the database.
     *
     * @param Meeting reunion
     *
     */
    public void insert(Meeting reunion) throws SQLException {
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
                    + DBNames.ATT_REUNION_TYPE + ","
                    + DBNames.ATT_REUNION_STATE;
            query2 = "VALUES (?,?,?,?,?,?,?,?";

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
            pStmt.setString(8, reunion.getState());

            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con); //connection of DB
        }
    }

    /**
     * This method modify the meeting in the database.
     *
     * @param Meeting changedReunion
     *
     */
    public void update(Meeting changedReunion) throws SQLException {
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
                    + DBNames.ATT_REUNION_TYPE + "=? ,"
                    + DBNames.ATT_REUNION_STATE + "=?";

            query1 += "WHERE " + DBNames.ATT_REUNION_ID + "=?";

            pStmt = con.prepareStatement(query1);
            pStmt.setString(1, changedReunion.getTitle());
            pStmt.setString(2, changedReunion.getDescription());
            pStmt.setString(3, changedReunion.getDate());
            pStmt.setString(4, changedReunion.getFirstTime());
            pStmt.setString(5, changedReunion.getSecondTime());
            pStmt.setString(6, changedReunion.getType());
            pStmt.setString(7, changedReunion.getState());
            pStmt.setInt(8, changedReunion.getId());


            pStmt.executeUpdate();
            con.commit();

        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con); //connection of DB
        }
    }

    /**
     * This method delete the meeting in the database.
     *
     * @param Meeting deletedReunion
     *
     */
    public void delete(Meeting deleteReunion) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            query = "DELETE FROM " + DBNames.TABLE_REUNION + " WHERE " + DBNames.ATT_REUNION_ID + "=?";
            pStmt = con.prepareStatement(query);
            pStmt.setInt(1, deleteReunion.getId());

            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    /**
     * This method return all meetings present in the database.
     *
     * @return ArrayList<Meeting>
     *
     */
    public ArrayList<Meeting> getMeetingList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query;
        ArrayList<Meeting> listMeeting = new ArrayList<Meeting>();
        try {

            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_REUNION;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                Meeting meeting = new Meeting();
                int id = rs.getInt(DBNames.ATT_REUNION_ID);
                String title = rs.getString(DBNames.ATT_REUNION_TITLE);
                String description = rs.getString(DBNames.ATT_REUNION_DESCRIPTION);
                String date = rs.getString(DBNames.ATT_REUNION_DATA);
                String firstTime = rs.getString(DBNames.ATT_REUNION_FIRST_TIME);
                String secondTime = rs.getString(DBNames.ATT_REUNION_SECOND_TIME);
                String type = rs.getString(DBNames.ATT_REUNION_TYPE);
                String state = rs.getString(DBNames.ATT_REUNION_STATE);


                meeting.setId(id);
                meeting.setTitle(title);
                meeting.setDescription(description);
                meeting.setDate(date);
                meeting.setFirstTime(firstTime);
                meeting.setSecondTime(secondTime);
                meeting.setType(type);
                meeting.setState(state);


                listMeeting.add(meeting);
            }
            return listMeeting;

        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
}
