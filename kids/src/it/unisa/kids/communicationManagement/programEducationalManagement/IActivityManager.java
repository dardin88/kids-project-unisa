/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public interface IActivityManager extends IManager {

    public void insert(DailyActivitySection pProject) throws SQLException;
}
