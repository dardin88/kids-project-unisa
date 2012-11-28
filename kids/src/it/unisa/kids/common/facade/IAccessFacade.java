package it.unisa.kids.common.facade;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.sql.SQLException;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import java.util.List;

public interface IAccessFacade 
{
	public int getParentId(int childId);
	public int getNumberOfChildren(int parentId) throws SQLException;
	public boolean isSick(RegistrationChild c);
        public Account insert(Account pAccount);
        public Account update(Account pAccount);
        public List<Account> search(Account pAccount);
        public Account delete(Account pAccount);
        
        public Account getParentById(int pParentId) throws SQLException;

}
