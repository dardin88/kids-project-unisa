package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class ManagerDiscussioni {
	
	private static ManagerDiscussioni manager;
	
	private ManagerDiscussioni(){
	
	}
	
	public ManagerDiscussioni getIstance(){
		if(manager==null)
			return manager=new ManagerDiscussioni();
		else 
			return manager;
	}
	
	public Discussioni insertDiscussione (int id, String title, String testo){
		if(title.length()>50){
			//errore
		}
		if(testo.length()>500){
			//errore
		}
		
		return new Discussioni(id, title, testo);
	}

}
