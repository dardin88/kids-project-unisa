package it.unisa.kids.accessManagement;

public class Recourse {
	private String id;
	private String data;
	private String reason;
	private String valutation;
	private String id_registration;

	public void setId_iscrizione(String id_iscrizione) {
		this.id_registration = id_iscrizione;
	}

	public String getId_iscrizione() {
		return id_registration;
	}

	public Recourse() {
	}

	public String getId() {
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

	public void setId(String id) {
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
