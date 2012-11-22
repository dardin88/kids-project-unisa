package it.unisa.kids.accessManagement.accountManagement;


public class Scientific extends Account {
	private String type;

	public Scientific() {
	}


	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}



}
