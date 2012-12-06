package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IRegistrationChildManager extends IManager {
	public RegistrationChild insert(RegistrationChild childToInsert) throws SQLException;
	public RegistrationChild update(RegistrationChild childToUpdate) throws SQLException;
	public RegistrationChild delete(RegistrationChild childToDelete) throws SQLException;
	List<RegistrationChild> search(RegistrationChild childToSearch) throws SQLException;
        public int getNumberChildren(int parentId) throws SQLException;
        public boolean confirmRegistrationChild(RegistrationChild child) throws SQLException;
        public boolean submitRegistrationChild(RegistrationChild child) throws SQLException;
        public boolean removeRegistrationChild(RegistrationChild child) throws SQLException;
}
