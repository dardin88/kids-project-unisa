package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp
 */
public class JDBCDailyActivity implements IDailyActivitySection {

    private Connection con;
    private PreparedStatement pstmt;

    JDBCDailyActivity() {
    }

    public void insertDailyActivitySection(DailyActivitySection pProject) throws SQLException {
        try {
            con = DBConnectionPool.getConnection();
            String query = "INSERT INTO " + DBNames.TABLE_ACTIVITYSECTIONDAILY + " ("
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_ID + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID + ", "
                    + DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES
                    + ") VALUES(pProject, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pProject.getId());
            pstmt.setInt(2, pProject.getIdActivity());
            pstmt.setInt(3, pProject.getIdEducator());
            Date tempdata = new Date(pProject.getData().getTimeInMillis());
            pstmt.setDate(4, tempdata);
            pstmt.setInt(5, pProject.getIdSection());
            pstmt.setString(6, pProject.getNotes());
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

    public HashMap<String, Value> showRegister(GregorianCalendar data, int idSection) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null, rs2 = null;
        String query = null, query2 = null;
        HashMap list = null;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_REGISTRATIONCHILD + " WHERE " + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=" + idSection;
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            con.commit();
            list = new HashMap<String, Object>();
            while (rs.next()) {
                query2 = "SELECT * FROM " + DBNames.TABLE_REGISTER + " WHERE '" + DBNames.ATT_REGISTER_CHILDID + "'='" + rs.getString(DBNames.ATT_REGISTER_CHILDID) + "'";
                pstmt = con.prepareStatement(query2);
                rs2 = pstmt.executeQuery();
                con.commit();
                list.put(rs.getString(DBNames.ATT_REGISTRATIONCHILD_NAME), new Value(rs2.getInt(DBNames.ATT_REGISTER_CHILDID), rs2.getBoolean(DBNames.ATT_REGISTER_PRESENCE)));
                return list;
            }


        } catch (Exception e) {
        } finally {
            DBConnectionPool.releaseConnection(con);
            return list;
        }

    }

    public DailyActivitySection showDailyActivitySection(GregorianCalendar data, int idSection) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        DailyActivitySection toReturn = null;
        Date sqldate = new Date(data.getTimeInMillis());
        query = "SELECT * FROM " + DBNames.TABLE_DAILY_SECTION_ACT + " WHERE '" + DBNames.ATT_ACTIVITYSECTTIONDAILY_DATE + "'=" + "'" + sqldate.toString() + "' AND " + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID + "=" + idSection;
        con = DBConnectionPool.getConnection();
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            toReturn.setIdActivity(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY));
            toReturn.setIdEducator(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE));
            toReturn.setNotes(rs.getString(DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES));
            toReturn.setId(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_ID));


        }
        return toReturn;
    }

    public Boolean updateDailyActivity(DailyActivitySection toSave) throws SQLException {
        Connection con = null;
        con = DBConnectionPool.getConnection();
        String query;

        // constructing query string
        query = "UPDATE " + DBNames.TABLE_ACTIVITYSECTIONDAILY + " SET "
                + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY + " = ?, "
                + DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE + " = ?, "
                + DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES + " = ? "
                + "WHERE " + DBNames.ATT_ACTIVITYSECTTIONDAILY_ID + " = ?";

        pstmt = con.prepareStatement(query);
        // setting pstmt's parameters

        pstmt.setInt(1, toSave.getIdActivity());
        pstmt.setInt(2, toSave.getIdEducator());
        pstmt.setString(3, toSave.getNotes());
        pstmt.setInt(3, toSave.getId());

        pstmt.executeUpdate();
        con.commit();

        if (con != null) {
            DBConnectionPool.releaseConnection(con);
            return true;
        }
        else
            return false;


    }

    public Boolean updateRegister(HashMap<String, Value> newRegister, int dailyActId) throws SQLException {
        con = DBConnectionPool.getConnection();
        Set temp = newRegister.entrySet();
        Entry<String, Value> child;
        Iterator<Entry<String, Value>> cursore = temp.iterator();
        while (cursore.hasNext()) {


            String query;

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_REGISTER + " SET "
                    + DBNames.ATT_REGISTER_PRESENCE + " = ? "
                    + "WHERE " + DBNames.ATT_REGISTER_DAILYACTIVITYID + " = ?"
                    + "AND " + DBNames.ATT_REGISTER_CHILDID + " = ?";

            pstmt = con.prepareStatement(query);
            // setting pstmt's parameters
            child = cursore.next();
            boolean presence = child.getValue().isPresence();

            int value;
            if (presence == false) {
                value = 0;
            } else {
                value = 1;
            }
            pstmt.setInt(1, value);
            pstmt.setInt(2, dailyActId);
            pstmt.setString(3, "" + child.getValue().getChildId());


            pstmt.executeUpdate();
            con.commit();
        }

        if (con != null) {
            DBConnectionPool.releaseConnection(con);
            return true;
        }
        return false;

    }
}
