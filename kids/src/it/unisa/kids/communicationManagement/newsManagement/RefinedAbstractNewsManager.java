package it.unisa.kids.communicationManagement.newsManagement;

public class RefinedAbstractNewsManager extends AbstractNewsManager 
{
	public INewsManager getNewsManagerImplementator() 
	{
		this.imp= JDBCNewsManager.getInstance();
		return imp;
	}

}
