package it.unisa.kids.common;

import java.sql.SQLException;

import it.unisa.kids.accessManagement.accountManagement.Parent;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;

public interface IAccessFacade 
{
	public int getParentId(RegistrationChild c);
	public int getNumberOfChildren(Parent p) throws SQLException;
	public boolean isSick(RegistrationChild c);	

}
