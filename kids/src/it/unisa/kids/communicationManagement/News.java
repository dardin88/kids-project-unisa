package it.unisa.kids.communicationManagement;

import java.util.GregorianCalendar;

public class News 
{
	private String title;
	private String description;
	private Object attached;
	private GregorianCalendar date,time;
	
	public News(String title,String description,Object attached,GregorianCalendar date,GregorianCalendar time)
	{
		this.title=title;
		this.description=description;
		this.attached=attached;
		this.date=date;
		this.time=time;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getAllegato() {
		return attached;
	}
	public void setAllegato(Object allegato) {
		this.attached = allegato;
	}
	public GregorianCalendar getDate() {
		return date;
	}
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	public GregorianCalendar getTime() {
		return time;
	}
	public void setTime(GregorianCalendar time) {
		this.time = time;
	}

}
