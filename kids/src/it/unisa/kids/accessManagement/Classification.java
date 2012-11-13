package it.unisa.kids.accessManagement;

import java.util.GregorianCalendar;

public class Classification 
{
	private int id;
	private GregorianCalendar date;
	private GregorianCalendar dateTerm;

	public Classification(int id,GregorianCalendar date,GregorianCalendar dateTerm) 
	{
		this.id=id;
		this.date=date;
		this.dateTerm=dateTerm;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id) 
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
