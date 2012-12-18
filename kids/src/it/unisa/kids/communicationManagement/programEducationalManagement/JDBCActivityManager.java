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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        List<DailyActivitySection> toReturn = new ArrayList<DailyActivitySection>();
        query = "SELECT * FROM " + DBNames.TABLE_DAILY_SECTION_ACT + " WHERE "   + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID + "='" + pDailyActivitySection.getId()+"'";
        con = DBConnectionPool.getConnection();
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DailyActivitySection dailyActivitySection=new DailyActivitySection();
            Date dateSql=rs.getDate(DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE);
            GregorianCalendar date=new GregorianCalendar();
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
            if (pActivity.getIdClass()>0) {
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
            if(pActivity.getIdClass()>0){
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
}
