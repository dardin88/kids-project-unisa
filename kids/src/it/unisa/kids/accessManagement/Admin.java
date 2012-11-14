package it.unisa.kids.accessManagement;

public class Admin extends Account
{
    public Admin()
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
