package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class Commenti {
	
	private int id;
	private Utenti utente;
	private Discussioni discussione;
	private GregorianCalendar data;
	private String corpo;
	
	public Commenti (int id, Utenti utente, Discussioni discussione, GregorianCalendar data,String corpo){
		this.id=id;
		this.utente=utente;
		this.discussione=discussione;
		this.data=data;
		this.corpo=corpo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Utenti getUtente() {
		return utente;
	}
	
	public void setUtente(Utenti utente) {
		this.utente = utente;
	}
	
	public Discussioni getDiscussione() {
		return discussione;
	}
	
	public void setDiscussione(Discussioni discussione) {
		this.discussione=discussione;
	}
	
	public GregorianCalendar getData() {
		return data;
	}
	
	public void setData(GregorianCalendar data) {
		this.data=data;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo=corpo;
	}
	
}