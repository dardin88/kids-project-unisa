package it.unisa.kids.communicationManagement.riunioni;

import java.util.GregorianCalendar;

public class Riunioni {
	
	private int id;
	private String title;
	private String description;
	private GregorianCalendar date;
	private GregorianCalendar hour;
	private String type;
	
	public Riunioni (int id, String title, String description, GregorianCalendar date, GregorianCalendar hour, String type){
		this.id=id;
		this.title=title;
		this.description=description;
		this.date=date;
		this.hour=hour;
		this.type=type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public GregorianCalendar getDate() {
		return date;
	}
	
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	public GregorianCalendar getHour() {
		return hour;
	}
	
	public void setHour(GregorianCalendar hour) {
		this.hour = hour;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}	
}
