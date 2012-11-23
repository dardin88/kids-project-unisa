package it.unisa.kids.accessManagement.accountManagement;


public class Parent extends Account
{
	private String parentType;
	private String type;

	public Parent()
	{
	}

	protected String getType() {
		return this.type;
	}

	protected void setType(String pType) {
		this.type = pType;
	}

	protected String getParentType() {
		return this.parentType;
	}


	protected void setParentType(String pParentType) {
		this.parentType = pParentType;
	}
}
