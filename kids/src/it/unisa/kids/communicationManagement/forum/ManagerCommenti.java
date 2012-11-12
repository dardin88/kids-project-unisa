package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class ManagerCommenti {
	
	private static ManagerCommenti manager;
	
	private ManagerCommenti(){
	
	}
	
	public ManagerCommenti getIstance(){
		if(manager==null)
			return manager=new ManagerCommenti();
		else 
			return manager;
	}
	
	public Commenti insertCommento (int id, Utenti utente, Discussioni discussione, GregorianCalendar data,String corpo){
		if(corpo.length()>500){
			//errore
		}
		
		return new Commenti(id, utente, discussione, data, corpo);
	}

}
