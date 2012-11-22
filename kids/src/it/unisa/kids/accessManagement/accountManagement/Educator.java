package it.unisa.kids.accessManagement.accountManagement;


public class Educator extends Account
{
    public Educator()
    {
    }
    
   
	protected String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}
    
	 private String type;

}
