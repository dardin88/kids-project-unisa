package it.unisa.kids.communicationManagement.valutazioni;


import java.util.GregorianCalendar;

public class ManagerValutazioni {
	
	private static ManagerValutazioni manager;
	
	private ManagerValutazioni(){
	
	}
	
	public ManagerValutazioni getIstance(){
		if(manager==null)
			return manager=new ManagerValutazioni();
		else 
			return manager;
	}
	
	public Valutazioni insertValutazione (int id, Object listaDomande){
				
		return new Valutazioni(id, listaDomande);
	}

}
