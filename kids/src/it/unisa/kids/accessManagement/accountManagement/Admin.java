package it.unisa.kids.accessManagement.accountManagement;

/**
 * 
 * @author Michele Nappo
 *
 */

public class Admin extends Account
{
	private String type;

	public Admin()
	{
	}

	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}
}
