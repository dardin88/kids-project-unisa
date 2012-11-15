package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.common.AbstractManager;

public class RefinedAbstractNewsManager extends AbstractManager<INewsManager> 
{
	public INewsManager getManagerImplementor() 
	{
		this.imp= JDBCNewsManager.getInstance();
		return imp;
	}
}
