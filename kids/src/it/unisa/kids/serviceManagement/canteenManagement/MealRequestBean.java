package it.unisa.kids.serviceManagement.canteenManagement;

import java.util.GregorianCalendar;

public class MealRequestBean {
	
	public static final double PRICE_MEALREQ = 5.00;
	
	private int id;
	private GregorianCalendar date;
	private int parentId;
	
	public MealRequestBean() {
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int pId) {
		this.id = pId;
	}
	
	public GregorianCalendar getDate() {
		return this.date;
	}
	
	public void setDate(GregorianCalendar pDate) {
		this.date = pDate;
	}
	
	public int getParentId() {
		return this.parentId;
	}
	
	public void setParentId(int pParentId) {
		this.parentId = pParentId;
	}
}
