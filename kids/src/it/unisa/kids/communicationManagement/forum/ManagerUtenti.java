package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class ManagerUtenti {
	
	private static ManagerUtenti manager;
	
	private ManagerUtenti(){
	
	}
	
	public ManagerUtenti getIstance(){
		if(manager==null)
			return manager=new ManagerUtenti();
		else 
			return manager;
	}
	
	public Utenti insertUtenti (int id, String username, String password){
		if(username.length()>10){
			//errore
		}
		if(password.length()>10){
			//errore
		}
		
		return new Utenti(id, username, password);
	}

}
