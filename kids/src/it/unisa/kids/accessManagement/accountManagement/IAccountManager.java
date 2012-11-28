package it.unisa.kids.accessManagement.accountManagement;


import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IAccountManager extends IManager {	
	public Account insert(Account pAccount) throws SQLException;
	public List<Account> search(Account pAccount) throws SQLException;
	public Account delete(Account pAccount) throws SQLException;
	public Account update(Account pAccount) throws SQLException;
}
