package it.unisa.kids.accessManagement;

public class PPCoordinator extends Account
{
    public PPCoordinator()
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