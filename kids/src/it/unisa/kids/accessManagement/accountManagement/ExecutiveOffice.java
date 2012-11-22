package it.unisa.kids.accessManagement.accountManagement;


public class ExecutiveOffice extends Account
{
	private String type;
	
	public ExecutiveOffice()
    {
    }
   
	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}
}
