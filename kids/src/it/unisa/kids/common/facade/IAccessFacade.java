package it.unisa.kids.common.facade;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.classManagement.ClassBean;
import java.sql.SQLException;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import java.util.List;

public interface IAccessFacade {

    public int getParentId(int childId);

    public Account insert(Account pAccount);

    public Account update(Account pAccount);

    public List<Account> search(Account pAccount);

    public Account delete(Account pAccount);

    public Account getParentById(int pParentId) throws SQLException;

    public List<ClassBean> getClasses() throws SQLException;
    
    public List<ClassBean> search(ClassBean pClass) throws SQLException;
    
    public void update(ClassBean pClass) throws SQLException;

    public List<RegistrationChild> search(RegistrationChild pRegistrationChild) throws SQLException;

    public boolean modifySickness(int registrationChildId, String sickness) throws SQLException;

    public boolean modifyVaccination(int registrationChildId, String vaccination) throws SQLException;

    public boolean modifyAdditionalNotes(int registrationChildId, String additionalNotes) throws SQLException;
}
