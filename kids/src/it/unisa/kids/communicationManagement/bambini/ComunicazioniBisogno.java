package it.unisa.kids.communicationManagement.bambini;

public class ComunicazioniBisogno {
	
	private int id;
	private Object bambino;
	private String tipo;
	private String bisogni;

	public ComunicazioniBisogno (int id, Object bambino, String tipo, String bisogni){
		this.id=id;
		this.bambino=bambino;
		this.tipo=tipo;
		this.bisogni=bisogni;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo=tipo;
	}

	public String getBisogni() {
		return bisogni;
	}

	public void setBisogni(String bisogni) {
		this.bisogni=bisogni;
	}

}
