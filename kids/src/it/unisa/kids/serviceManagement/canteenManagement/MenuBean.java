package it.unisa.kids.serviceManagement.canteenManagement;

import it.unisa.kids.communicationManagement.newsManagement.News;

public class MenuBean extends News {

	public static final String MAIN_MENU 		= "Main menu";
	public static final String ALTERNATIVE_MENU = "Alternative menu";
	
	private String first;
	private String second;
	private String sideDish;
	private String fruit;
	
	public MenuBean() {
		
	}
	
	public String getFirst() {
		return this.first;
	}
	
	public void setFirst(String pFirst) {
		this.first = pFirst;
	}
	
	public String getSecond() {
		return this.second;
	}
	
	public void setSecond(String pSecond) {
		this.second = pSecond;
	}
	
	public String getSideDish() {
		return this.sideDish;
	}
	
	public void setSideDish(String pSideDish) {
		this.sideDish = pSideDish;
	}
	
	public String getFruit() {
		return this.fruit;
	}
	
	public void setFruit(String pFruit) {
		this.fruit = pFruit;
	}
}
