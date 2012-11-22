package it.unisa.kids.accessManagement.accountManagement;


import java.sql.SQLException;
import java.util.List;

public interface IAccountManager {	
	public Account create(Account pAccount) throws SQLException;
	public List<Account> search(Account pAccount) throws SQLException;
	public Account delete(Account pAccount) throws SQLException;
	public Account modify(Account pAccount) throws SQLException;
}
