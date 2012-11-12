package it.unisa.kids.accessManagement;

public class Recourse {
	private int id;
	private String data;
	private String reason;
	private String valutation;
	private int id_registration;

	public void setId_registration(int id_registration) {
		this.id_registration = id_registration;
	}

	public int getId_registration() {
		return id_registration;
	}

	public Recourse() {
	}

	public int getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getReason() {
		return reason;
	}

	public String getValutation() {
		return valutation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setReason(String motivo) {
		this.reason = motivo;
	}

	public void setValutation(String valutazione) {
		this.valutation = valutazione;
	}

}
