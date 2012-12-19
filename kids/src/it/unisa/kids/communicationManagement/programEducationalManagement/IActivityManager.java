/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marco
 */
public interface IActivityManager extends IManager {

    public void insert(DailyActivitySection pDailyActivitySection) throws SQLException;
    
    public void insert(Activity pActivity) throws SQLException;

    public List<DailyActivitySection> getDailyActivitySectionList() throws SQLException;

    public List<DailyActivitySection> search(DailyActivitySection pDailyActivitySection) throws SQLException;

    public List<Activity> search(Activity pActivity) throws SQLException;

    public List<Activity> getActivityList() throws SQLException;
    
    public void insert(CommentBean pComment) throws SQLException;
    
    public List<CommentBean> search(CommentBean pComment) throws SQLException;
}
