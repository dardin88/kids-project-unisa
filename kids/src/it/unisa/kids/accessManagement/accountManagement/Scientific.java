package it.unisa.kids.accessManagement.accountManagement;


public class Scientific extends Account
{
    public Scientific()
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
