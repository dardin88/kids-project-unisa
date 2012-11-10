package it.unisa.kids.communicationManagement;


public class ManagerNews 
{
	private static ManagerNews manager; 
	
	private ManagerNews()
	{
	}
	
	public ManagerNews getInstance()
	{
		if(manager==null)
			return (manager=new ManagerNews());
		else
			return manager;
	}

}
