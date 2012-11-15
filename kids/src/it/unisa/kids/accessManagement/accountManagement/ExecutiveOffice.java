package it.unisa.kids.accessManagement.accountManagement;


public class ExecutiveOffice extends Account
{
    public ExecutiveOffice()
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
