package it.unisa.kids.communicationManagement;

import java.util.GregorianCalendar;


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
	
	public News insertNews(String title,String description,Object attached,GregorianCalendar date,GregorianCalendar time)
	{
		if (title.length()>50)
		{
			//errore
		}
		if(description.length()>200)
		{
			//errore
		}
		News n=new News(title, description, attached, date, time);
		return n;
	}

}
