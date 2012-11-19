package it.unisa.kids.accessManagement.classificationManagement;

import java.util.GregorianCalendar;

public class Classification 
{
	public Classification() 
	{
		
	}
	/*
	 * aggiungere la lista di bambini appartenenti a questa graduatoria
	 */
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
