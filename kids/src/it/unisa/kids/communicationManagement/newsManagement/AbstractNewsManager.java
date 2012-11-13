package it.unisa.kids.communicationManagement.newsManagement;

public abstract class AbstractNewsManager
{
	protected INewsManager imp;
	public abstract  INewsManager getNewsManagerImplementator();

}
