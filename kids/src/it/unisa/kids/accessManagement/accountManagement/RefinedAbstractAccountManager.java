package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.AbstractManager;


public class RefinedAbstractAccountManager extends AbstractManager<IAccountManager> {
	public IAccountManager getManagerImplementor() {
		this.imp = JDBCManagerAccount.getInstance();
		return this.imp;
	}

}
