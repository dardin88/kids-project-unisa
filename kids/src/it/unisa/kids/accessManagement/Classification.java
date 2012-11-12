package it.unisa.kids.accessManagement;

import java.util.GregorianCalendar;

public class Classification 
{
	private String id;
	private GregorianCalendar date;
	private GregorianCalendar dateTerm;

	public Classification(String id,GregorianCalendar date,GregorianCalendar dateTerm) 
	{
		this.id=id;
		this.date=date;
		this.dateTerm=dateTerm;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public GregorianCalendar getDate() 
	{
		return date;
	}

	public void setDate(GregorianCalendar date) 
	{
		this.date = date;
	}

	public GregorianCalendar getDateTerm()
	{
		return dateTerm;
	}

	public void setDateTerm(GregorianCalendar dateTerm)
	{
		this.dateTerm = dateTerm;

	}

}
