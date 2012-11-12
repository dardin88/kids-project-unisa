package it.unisa.kids.communicationManagement.valutazioni;

import java.util.GregorianCalendar;

public class Valutazioni {
	
	private int id;
	private Object listaDomande;
	
	public Valutazioni (int id, Object listaDomande){
		this.id=id;
		this.listaDomande=listaDomande;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Object getListaDomande() {
		return listaDomande;
	}
	
	public void setListaDomande(Object listaDomande) {
		this.listaDomande=listaDomande;
	}

}
