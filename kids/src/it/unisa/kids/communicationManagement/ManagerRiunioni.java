package it.unisa.kids.communicationManagement;

import java.util.GregorianCalendar;

public class ManagerRiunioni {
	
	private static ManagerRiunioni manager;
	
	private ManagerRiunioni(){
	
	}
	
	public ManagerRiunioni getIstance(){
		if(manager==null)
			return manager=new ManagerRiunioni();
		else 
			return manager;
	}
	
	public Riunioni insertRiunione (int id, String title, String description, GregorianCalendar date, GregorianCalendar hour, String type){
		if(title.length()>50){
			//errore
		}
		if(description.length()>500){
			//errore
		}
		
		return new Riunioni(id, title, description, date, hour, type);
	}

}
