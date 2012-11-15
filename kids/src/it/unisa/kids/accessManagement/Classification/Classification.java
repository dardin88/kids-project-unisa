package it.unisa.kids.accessManagement.Classification;

import java.util.GregorianCalendar;

public class Classification 
{
	public Classification(int id,GregorianCalendar date,GregorianCalendar dateTerm) 
	{
		this.id=id;
		this.date=date;
		this.dateTerm=dateTerm;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public GregorianCalendar getDate() 
	{
		return this.date;
	}

	public void setDate(GregorianCalendar date) 
	{
		this.date = date;
	}

	public GregorianCalendar getDateTerm()
	{
		return this.dateTerm;
	}

	public void setDateTerm(GregorianCalendar dateTerm)
	{
		this.dateTerm = dateTerm;

	}
	
	private int id;
	private GregorianCalendar date;
	private GregorianCalendar dateTerm;
}
