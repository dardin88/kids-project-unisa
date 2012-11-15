package it.unisa.kids.accessManagement.renunciation;

import java.util.GregorianCalendar;

public class RenunciationBean {
	
	String id; 
	GregorianCalendar date; 	//DATA 
	boolean confirmation; 		//CONFERMA	
	//devo inserire anche il campo iscrizione?
	
	public RenunciationBean(GregorianCalendar date, boolean confirmation) 		//l'id dovrebbe essere autoincrementale e non l'ho inserito nel costruttore giusto?
	{																		//e poi altra cosa che avevo pensato potrebbe essere inserita solo la data tanto se uno richiama questa funzione è ovvio che vuole rifuitare giusto?
		this.date = date;
		this.confirmation = confirmation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GregorianCalendar getDate() {
		return date;
	}
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	public boolean isConfirmation() {
		return confirmation;
	}
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	
	

}
