package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class ManagerSegnalazioni {
	
	private static ManagerSegnalazioni manager;
	
	private ManagerSegnalazioni(){
	
	}
	
	public ManagerSegnalazioni getIstance(){
		if(manager==null)
			return manager=new ManagerSegnalazioni();
		else 
			return manager;
	}
	
	public Segnalazioni insertSegnalazione (int id, GregorianCalendar data, String descrizione, Discussioni discussione, Commenti commento, Utenti utente){
		if(descrizione.length()>500){
			//errore
		}
		
		return new Segnalazioni(id, data, descrizione, discussione, commento, utente);
	}

}
