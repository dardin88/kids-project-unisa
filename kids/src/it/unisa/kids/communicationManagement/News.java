package it.unisa.kids.communicationManagement;

import java.util.GregorianCalendar;

public class News 
{
	private String title;
	private String description;
	private Object allegato;
	private GregorianCalendar date,time;
	
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
		return allegato;
	}
	public void setAllegato(Object allegato) {
		this.allegato = allegato;
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
