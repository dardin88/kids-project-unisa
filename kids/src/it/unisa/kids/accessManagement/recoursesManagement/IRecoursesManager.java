package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public interface IRecoursesManager extends IManager {
    public boolean insert(Recourse pRecourse) throws SQLException;
    public boolean update(Recourse pRecourse) throws SQLException;
    public boolean delete(Recourse pRecourse) throws SQLException;
    public List<Recourse> search(Recourse pRecourse) throws SQLException;
    /**
     * Search recourse whith precision parameter setted in pRecourse or with
     * similitudines with 'toSearch'
     * 
     * @param pRecourse  precised parameter to search
     * @param toSearch word to compare with database field
     * @return a list of registrationchild
     * @throws SQLException if occured an SQLException
     */
    public List<Recourse> search(Recourse pRecourse, String toSearch) throws SQLException;
    
    public boolean accept(Recourse pRecourse) throws SQLException;
    public boolean refuse(Recourse pRecourse) throws SQLException;
    
    public int getNumberRecourseToEvaluate() throws SQLException;
    
    /**
     * Get the list of the Recourses submitted from a parent
     * 
     * @param parentAccountId id of parent's account
     * @param advanceStringToSearch if has to search additional field between fiscalcode, surname or name
     * @return the list of recourses from the given parent
     * @throws SQLException if occured an SQLException
     */
    public List<Recourse> getListFromParent(int parentAccountId, String advanceStringToSearch) throws SQLException;
}
