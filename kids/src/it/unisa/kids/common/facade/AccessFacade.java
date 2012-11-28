package it.unisa.kids.common.facade;

import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.accessManagement.accountManagement.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.kids.accessManagement.accountManagement.Parent;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.util.List;

public class AccessFacade implements IAccessFacade
{

	// Stato di Standby
	public int getParentId(int childId)
	{
		JDBCRegistrationChildManager c;
		RegistrationChild child = new RegistrationChild();
		child.setIdParent(childId);
		
		return child.getIdParent();
	}

	public int getNumberOfChildren(int parentId) throws SQLException 
	{
            JDBCRegistrationChildManager c;
        }

	public boolean isSick(RegistrationChild c) {
		return c.isSick();
	}

    @Override
    public Account insert(Account pAccount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Account update(Account pAccount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Account> search(Account pAccount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Account delete(Account pAccount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
