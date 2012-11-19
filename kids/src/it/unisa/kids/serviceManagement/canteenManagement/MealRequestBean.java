package it.unisa.kids.serviceManagement.canteenManagement;

import java.util.GregorianCalendar;

public class MealRequestBean {
	
	public static final double PRICE_MEALREQ = 5.00;
	
	private int id;
	private String requestedMenuType;		// one of: MenuBean.MAIN_MENU, MenuBean.ALTERNATIVE_MENU
	private GregorianCalendar date;
	private int childInscriptionId;
	
	public MealRequestBean() {
		
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int pId) {
		this.id = pId;
	}
	
	public String getRequestedMenuType() {
		return this.requestedMenuType;
	}
	
	public void setRequestedMenuType(String pReqMenuType) {
		this.requestedMenuType = pReqMenuType;
	}
	
	public GregorianCalendar getDate() {
		return this.date;
	}
	
	public void setDate(GregorianCalendar pDate) {
		this.date = pDate;
	}
	
	public int getChildInscriptionId() {
		return this.childInscriptionId;
	}
	
	public void setChildInscriptionId(int pChildInscriptionId) {
		this.childInscriptionId = pChildInscriptionId;
	}
}
