package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface of RegistrationChildManager
 * 
 * @author Lauri Giuseppe Giovanni
 */
public interface IRegistrationChildManager extends IManager {
    
    public boolean insert(RegistrationChild childToInsert) throws SQLException;
    public boolean update(RegistrationChild childToUpdate) throws SQLException;
    public boolean delete(RegistrationChild childToDelete) throws SQLException;
    List<RegistrationChild> search(RegistrationChild childToSearch) throws SQLException;

    /**
     * Set the registrationchild to submitted. 
     * 
     * @param child registrationchild
     * @return true if the registrationchild was correctly submitted, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean submitRegistrationChild(RegistrationChild child) throws SQLException;
    /**
     * Set the registrationchild to confirmed. 
     * 
     * @param child registrationchild
     * @return true if the registrationchild was correctly confirmed, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean confirmRegistrationChild(RegistrationChild child) throws SQLException;
    /**
     * Set the registrationchild to remove. 
     * 
     * @param child registrationchild
     * @return true if the registrationchild was correctly removed, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean removeRegistrationChild(RegistrationChild child) throws SQLException;
    /**
     * Set the registrationchild to accepted. 
     * 
     * @param child registrationchild
     * @return true if the registrationchild was correctly accepted, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean acceptRegistrationChild(RegistrationChild child) throws SQLException;
    /**
     * Store the additional data after the registrationchild is accepted
     * 
     * @param child registrationchild that contains id and the additiona data
     * @return true if the data are correctly storaged, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean completeRegistrationChild(RegistrationChild child) throws SQLException;
    
    /**
     * Set the section of the child
     * 
     * @param child registrationchild to edit
     * @param sectionId id of the section to set in the registrationchild
     * @return true if the registrationchild's section was correctly edited, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean setSectionRegistrationChild(RegistrationChild child, int sectionId) throws SQLException;
    
    /**
     * Get the number of registrationchild insert by parent with parentId
     * 
     * @param parentId id of the parent
     * @return the number of registrationchild isert by parent
     * @throws SQLException if occured an SQLException
     */
    public int getNumberChildren(int parentId) throws SQLException;

    /**
     * Set the registrationchild to renunced. 
     * This method is used by RenunciationManagement
     * 
     * @param child registrationchild
     * @return true if the registrationchild was correctly renunced, false otherweise
     * @throws SQLException if occured an SQLException
     */
    public boolean renounceRegistrationChild(RegistrationChild child) throws SQLException;
    
    public boolean modifySickness(int registrationChildId, String sickness) throws SQLException;
    public boolean modifyVaccination(int registrationChildId, String vaccination) throws SQLException;
    public boolean modifyAdditionalNotes(int registrationChildId, String additionalNotes) throws SQLException;
    public List<RegistrationChild> searchSectionId(RegistrationChild child) throws SQLException;
    
    public List<RegistrationChild> getReceptedRegistrationChild() throws SQLException;
}
