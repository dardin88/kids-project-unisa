/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

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
import java.util.List;

/**
 *
 * @author marco
 */
public class JDBCActivityManager implements IActivityManager {

    private static IActivityManager manager;

    public static IActivityManager getInstance() {
        if (manager == null) {
            return (manager = new JDBCActivityManager());
        } else {
            return manager;
        }
    }

    @Override
    public void insert(DailyActivitySection pProject) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_ACTIVITYSECTIONDAILY + " ("
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID
                    + ") VALUES(?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setDate(1, new Date(pProject.getData().getTimeInMillis()));
            pstmt.setInt(2, pProject.getIdActivity());
            pstmt.setInt(3, pProject.getIdEducator());
            pstmt.setString(4, pProject.getNotes());
            pstmt.setInt(5, pProject.getIdSection());

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

    @Override
    public List<DailyActivitySection> getDailyActivitySectionList() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyActivitySection> search(DailyActivitySection pDailyActivitySection) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        boolean andState = false;
        List<DailyActivitySection> toReturn = new ArrayList<DailyActivitySection>();
        query = "SELECT * FROM " + DBNames.TABLE_DAILY_SECTION_ACT + " WHERE";
        if (pDailyActivitySection.getId() > 0) {
            query += " " + useAnd(andState) + DBNames.ATT_ACTIVITYSECTTIONDAILY_ID + " = ?";
            andState = true;
        }
        if (pDailyActivitySection.getIdSection() > 0) {
            query += " " + useAnd(andState) + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID + " = ?";
            andState = true;
        }
        query += " ORDER BY " + DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE;
        System.out.println(query);

        con = DBConnectionPool.getConnection();

        pstmt = con.prepareStatement(query);
        int i = 1;
        if (pDailyActivitySection.getId() > 0) {		// >= 0 ??
            pstmt.setInt(i, pDailyActivitySection.getId());
            i++;
        }

        if (pDailyActivitySection.getIdSection() > 0) {		// >= 0 ??
            pstmt.setInt(i, pDailyActivitySection.getIdSection());
            i++;
        }
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DailyActivitySection dailyActivitySection = new DailyActivitySection();
            Date dateSql = rs.getDate(DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE);
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(dateSql);
            dailyActivitySection.setData(date);
            dailyActivitySection.setId(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_ID));
            dailyActivitySection.setIdActivity(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY));
            dailyActivitySection.setIdEducator(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE));
            dailyActivitySection.setNotes(rs.getString(DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES));
            dailyActivitySection.setIdSection(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID));
            toReturn.add(dailyActivitySection);
        }
        return toReturn;
    }

    @Override
    public List<Activity> search(Activity pActivity) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<Activity> activities = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_ACT + " WHERE";
            if (pActivity.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_ACTIVITY_ID + " = ?";
                andState = true;
            }

            if (pActivity.getName() != null) {
                query += useAnd(andState) + DBNames.ATT_ACTIVITY_NAME + " = ?";
                andState = true;
            }

            if (pActivity.getDescription() != null) {
                query += useAnd(andState) + DBNames.ATT_ACTIVITY_DESCRIPTION + " = ?";
                andState = true;
            }

            if (pActivity.getStartDate() != null) {
                query += useAnd(andState) + DBNames.ATT_ACTIVITY_STARTDATE + "= ?";
                andState = true;
            }

            if (pActivity.getEndDate() != null) {
                query += useAnd(andState) + DBNames.ATT_ACTIVITY_ENDDATE + "= ?";
                andState = true;
            }

            if (pActivity.getIdClass() > 0) {
                query += useAnd(andState) + DBNames.ATT_ACTIVITY_IDCLASS + "= ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pActivity.getId() > 0) {		// >= 0 ??
                pstmt.setInt(i, pActivity.getId());
                i++;
            }

            if (pActivity.getName() != null) {
                pstmt.setString(i, pActivity.getName());
                i++;
            }

            if (pActivity.getDescription() != null) {
                pstmt.setString(i, pActivity.getDescription());
                i++;
            }

            if (pActivity.getStartDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pActivity.getStartDate().getTimeInMillis()));
                i++;
            }

            if (pActivity.getEndDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pActivity.getEndDate().getTimeInMillis()));
                i++;
            }

            if (pActivity.getIdClass() > 0) {
                pstmt.setInt(i, pActivity.getIdClass());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing mealReq list
            activities = new ArrayList<Activity>();
            while (rs.next()) {
                Activity act = new Activity();
                act.setId(rs.getInt(DBNames.ATT_ACTIVITY_ID));
                act.setName(rs.getString(DBNames.ATT_ACTIVITY_NAME));
                act.setDescription(rs.getString(DBNames.ATT_ACTIVITY_DESCRIPTION));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar actDate = new GregorianCalendar();
                actDate.setTime(rs.getDate(DBNames.ATT_ACTIVITY_STARTDATE));
                act.setStartDate(actDate);
                act.setIdClass(rs.getInt(DBNames.ATT_ACTIVITY_IDCLASS));
                actDate = new GregorianCalendar();
                actDate.setTime(rs.getDate(DBNames.ATT_ACTIVITY_ENDDATE));
                act.setEndDate(actDate);

                activities.add(act);
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
        return activities;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public synchronized List<Activity> getActivityList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<Activity> activities = null;

        try {
            con = DBConnectionPool.getConnection();

            query = "SELECT * FROM " + DBNames.TABLE_ACT;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // constructing activities list
            activities = new ArrayList<Activity>();
            while (rs.next()) {
                Activity act = new Activity();
                act.setId(rs.getInt(DBNames.ATT_ACTIVITY_ID));
                act.setName(rs.getString(DBNames.ATT_ACTIVITY_NAME));
                act.setDescription(rs.getString(DBNames.ATT_ACTIVITY_DESCRIPTION));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar actDate = new GregorianCalendar();
                actDate.setTime(rs.getDate(DBNames.ATT_ACTIVITY_STARTDATE));
                act.setStartDate(actDate);
                actDate.setTime(rs.getDate(DBNames.ATT_ACTIVITY_ENDDATE));
                act.setEndDate(actDate);

                activities.add(act);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return activities;
    }

    @Override
    public void insert(Activity pActivity) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_ACT + " ("
                    + DBNames.ATT_ACTIVITY_ID + ", "
                    + DBNames.ATT_ACTIVITY_NAME + ", "
                    + DBNames.ATT_ACTIVITY_DESCRIPTION + ", "
                    + DBNames.ATT_ACTIVITY_STARTDATE + ", "
                    + DBNames.ATT_ACTIVITY_ENDDATE + ", "
                    + DBNames.ATT_ACTIVITY_IDCLASS
                    + ") VALUES(?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pActivity.getId());
            pstmt.setString(2, pActivity.getName());
            pstmt.setString(3, pActivity.getDescription());
            pstmt.setDate(4, new java.sql.Date(pActivity.getStartDate().getTimeInMillis()));
            pstmt.setDate(5, new java.sql.Date(pActivity.getEndDate().getTimeInMillis()));
            pstmt.setInt(6, pActivity.getIdClass());

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

    @Override
    public void insert(CommentBean pComment) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_COMMENT + " ("
                    + DBNames.ATT_COMMENT_ID + ", "
                    + DBNames.ATT_COMMENT_CONTENT + ", "
                    + DBNames.ATT_COMMENT_DATE + ", "
                    + DBNames.ATT_COMMENT_ANNUALID + ", "
                    + DBNames.ATT_COMMENT_CLASSID + ", "
                    + DBNames.ATT_COMMENT_AUTHORID + ", "
                    + DBNames.ATT_COMMENT_TIME
                    + ") VALUES(?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pComment.getId());
            pstmt.setString(2, pComment.getContent());
            pstmt.setDate(3, new java.sql.Date(pComment.getDate().getTimeInMillis()));
            pstmt.setInt(4, pComment.getAnnualId());
            pstmt.setInt(5, pComment.getClassId());
            pstmt.setInt(6, pComment.getAuthorId());
            pstmt.setTime(7, pComment.getTime());

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

    @Override
    public List<CommentBean> search(CommentBean pComment) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<CommentBean> comments = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_COMMENT + " WHERE";
            if (pComment.getId() > 0) {
                query += " " + DBNames.ATT_COMMENT_ID + " = ?";
                andState = true;
            }

            if (pComment.getDate() != null) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_DATE + " = ?";
                andState = true;
            }

            if (pComment.getContent() != null) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_CONTENT + " LIKE CONCAT('%', ?, '%')";
                andState = true;
            }

            if (pComment.getAnnualId() >= 0) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_ANNUALID + "= ?";
                andState = true;
            }

            if (pComment.getClassId() >= 0) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_CLASSID + "= ?";
                andState = true;
            }

            if (pComment.getAuthorId() > 0) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_AUTHORID + "= ?";
                andState = true;
            }

            if (pComment.getTime() != null) {
                query += useAnd(andState) + DBNames.ATT_COMMENT_TIME + "= ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pComment.getId() > 0) {		// >= 0 ??
                pstmt.setInt(i, pComment.getId());
                i++;
            }

            if (pComment.getDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pComment.getDate().getTimeInMillis()));
                i++;
            }

            if (pComment.getContent() != null) {
                pstmt.setString(i, pComment.getContent());
                i++;
            }

            if (pComment.getAnnualId() >= 0) {
                pstmt.setInt(i, pComment.getAnnualId());
                i++;
            }

            if (pComment.getClassId() >= 0) {
                pstmt.setInt(i, pComment.getClassId());
                i++;
            }

            if (pComment.getAuthorId() > 0) {
                pstmt.setInt(i, pComment.getAuthorId());
                i++;
            }

            if (pComment.getTime() != null) {
                pstmt.setTime(i, pComment.getTime());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing comment list
            comments = new ArrayList<CommentBean>();
            while (rs.next()) {
                CommentBean comm = new CommentBean();
                comm.setId(rs.getInt(DBNames.ATT_COMMENT_ID));
                comm.setContent(rs.getString(DBNames.ATT_COMMENT_CONTENT));
                comm.setAnnualId(rs.getInt(DBNames.ATT_COMMENT_ANNUALID));
                comm.setClassId(rs.getInt(DBNames.ATT_COMMENT_CLASSID));
                comm.setAuthorId(rs.getInt(DBNames.ATT_COMMENT_AUTHORID));
                comm.setTime(rs.getTime(DBNames.ATT_COMMENT_TIME));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar commDate = new GregorianCalendar();
                commDate.setTime(rs.getDate(DBNames.ATT_COMMENT_DATE));
                comm.setDate(commDate);

                comments.add(comm);
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
        return comments;
    }

    public void remove(CommentBean pComment) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "delete from " + DBNames.TABLE_COMMENT + " where " + DBNames.ATT_COMMENT_ID + "=" + pComment.getId();
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();

        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
    }

    public void remove(DailyActivitySection pDailyActivitySection) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        try {
            con = DBConnectionPool.getConnection();
            query = "DELETE FROM " + DBNames.TABLE_ACTIVITYSECTIONDAILY + " WHERE " + DBNames.ATT_ACTIVITYSECTTIONDAILY_ID + "='" + pDailyActivitySection.getId() + "'";
            System.out.println(query);
            pstmt = con.prepareStatement(query);
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

    @Override
    public void delete(Activity pActivity) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "DELETE FROM " + DBNames.TABLE_ACT
                    + " WHERE " + DBNames.ATT_ACTIVITY_ID + " = " + pActivity.getId();

            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }
}
