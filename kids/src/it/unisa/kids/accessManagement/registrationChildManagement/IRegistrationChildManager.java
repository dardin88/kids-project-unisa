package it.unisa.kids.accessManagement.registrationChildManagement;

import java.sql.SQLException;
import java.util.List;

public interface IRegistrationChildManager {
	public RegistrationChild create(RegistrationChild pChildReg) throws SQLException;
	public RegistrationChild update(RegistrationChild pChildReg) throws SQLException;
	public RegistrationChild delete(RegistrationChild pChildReg) throws SQLException;
	List<RegistrationChild> search(RegistrationChild pChildReg) throws SQLException;
}
