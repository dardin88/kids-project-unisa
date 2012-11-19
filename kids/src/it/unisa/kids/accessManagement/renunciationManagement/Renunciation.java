package it.unisa.kids.accessManagement.renunciationManagement;

import java.util.GregorianCalendar;

public class Renunciation {
	
	String id; 
	GregorianCalendar date; 	//DATA 
	boolean confirmation; 		//CONFERMA	
	//devo inserire anche il campo iscrizione?
	
	public Renunciation() {  }
	
	public Renunciation(GregorianCalendar date, boolean confirmation) 		
	{																			//e poi altra cosa che avevo pensato potrebbe essere inserita solo la data tanto se uno richiama questa funzione è ovvio che vuole rifuitare giusto?
		this.date = date;
		this.confirmation = confirmation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		String tmp="";
		return tmp=tmp+date.YEAR+"-"+date.MONTH+"-"+date.DATE;
	}
	public void setDate(String date) {
		this.date.set(Integer.parseInt(date.substring(0, 3)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
	}
	public boolean isConfirmation() {
		return confirmation;
	}
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
}
