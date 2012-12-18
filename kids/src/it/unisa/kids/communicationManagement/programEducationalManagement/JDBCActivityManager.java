/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<DailyActivitySection> toReturn = null;
        query = "SELECT * FROM " + DBNames.TABLE_DAILY_SECTION_ACT + " WHERE '"   + DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID + "='" + pDailyActivitySection.getId()+"'";
        con = DBConnectionPool.getConnection();
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DailyActivitySection dailyActivitySection=new DailyActivitySection();
            dailyActivitySection.setId(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_ID));
            dailyActivitySection.setIdActivity(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY));
            dailyActivitySection.setIdEducator(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE));
            dailyActivitySection.setNotes(rs.getString(DBNames.ATT_ACTIVITYSECTTIONDAILY_NOTES));
            dailyActivitySection.setIdSection(rs.getInt(DBNames.ATT_ACTIVITYSECTTIONDAILY_SECTIONID));

        }
        return toReturn;
    }
}
