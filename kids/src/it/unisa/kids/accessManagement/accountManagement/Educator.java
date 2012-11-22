package it.unisa.kids.accessManagement.accountManagement;


public class Educator extends Account
{
	private String type;

	public Educator()
	{
	}

	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}
}
