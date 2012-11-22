package it.unisa.kids.common;

import java.sql.SQLException;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;

public interface IAccessFacade 
{
	public int getParentId(int childId);
	public int getNumberOfChildren(int parentId) throws SQLException;
	public boolean isSick(RegistrationChild c);	

}
