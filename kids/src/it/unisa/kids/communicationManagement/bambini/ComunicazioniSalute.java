package it.unisa.kids.communicationManagement.bambini;

public class ComunicazioniSalute {
	
	private int id;
	private Object bambino;
	private String pranzo;
	private String quantità;
	private String oreSonno;
	private String statoAnimo;
	private String malori;

	public ComunicazioniSalute (int id, Object bambino, String pranzo, String quantità, String oreSonno, String statoAnimo, String malori){
		this.id=id;
		this.bambino=bambino;
		this.pranzo=pranzo;
		this.quantità=quantità;
		this.oreSonno=oreSonno;
		this.statoAnimo=statoAnimo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getBambino() {
		return bambino;
	}

	public void setBambino(Object bambino) {
		this.bambino=bambino;
	}

	public String getPranzo() {
		return pranzo;
	}

	public void setPranzo(String pranzo) {
		this.pranzo=pranzo;
	}

	public String getQuantità() {
		return quantità;
	}

	public void setQuantità(String quantità) {
		this.quantità=quantità;
	}

	public String getOreSonno() {
		return oreSonno;
	}

	public void setOreSonno(String oreSonno) {
		this.oreSonno=oreSonno;
	}

	public String getStatoAnimo() {
		return statoAnimo;
	}

	public void setStatoAnimo(String statoAnimo) {
		this.statoAnimo=statoAnimo;
	}	
}
