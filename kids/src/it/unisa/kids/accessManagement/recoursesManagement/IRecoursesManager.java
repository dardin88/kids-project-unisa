package it.unisa.kids.accessManagement.recoursesManagement;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public interface IRecoursesManager {
    public boolean insert(Recourse pRecourse) throws SQLException;
    public boolean modify(Recourse pRecourse) throws SQLException;
    public boolean delete(Recourse pRecourse) throws SQLException;
    public boolean accept(Recourse pRecourse) throws SQLException;
    public boolean refuse(Recourse pRecourse) throws SQLException;
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
    
    public int getNumberRecourseToEvaluate() throws SQLException;
}
