package it.unisa.kids.accessManagement.accountManagement;


public class Educator extends Account
{
    public Educator()
    {
    }
    
   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	 private String type;

}
