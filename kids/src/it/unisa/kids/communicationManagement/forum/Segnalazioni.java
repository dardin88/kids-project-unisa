package it.unisa.kids.communicationManagement.forum;

import java.util.GregorianCalendar;

public class Segnalazioni {
	
	private int id;
	private GregorianCalendar data;
	private String descrizione;
	private Discussioni discussione;
	private Commenti commento;
	private Utenti utente;
	
	public Segnalazioni (int id, GregorianCalendar data, String descrizione, Discussioni discussione, Commenti commento, Utenti utente){
		this.id=id;
		this.data=data;
		this.descrizione=descrizione;
		this.discussione=discussione;
		this.commento=commento;
		this.utente=utente;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public GregorianCalendar getData() {
		return data;
	}
	
	public void setData(GregorianCalendar data) {
		this.data=data;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	
	public Discussioni getDiscussione() {
		return discussione;
	}
	
	public void setDiscussione(Discussioni discussione) {
		this.discussione=discussione;
	}
	
	public Commenti getCommento() {
		return commento;
	}
	
	public void setCommento(Commenti commento) {
		this.commento=commento;
	}
	
	public Utenti getUtente() {
		return utente;
	}
	
	public void setUtente(Utenti utente) {
		this.utente = utente;
	}
	
}