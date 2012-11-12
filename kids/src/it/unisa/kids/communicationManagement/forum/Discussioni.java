package it.unisa.kids.communicationManagement.forum;

public class Discussioni {
	
	private int id;
	private String titolo;
	private String testo;
	
	public Discussioni (int id, String title, String testo){
		this.id=id;
		this.titolo=titolo;
		this.testo=testo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}