package it.unisa.kids.accessManagement.accountManagement;


public class PPCoordinator extends Account
{
	private String type;
	
	public PPCoordinator()
	{
	}

	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}
}
