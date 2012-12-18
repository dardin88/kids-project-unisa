/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;

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
}
