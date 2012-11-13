package it.unisa.kids.accessManagement;

public class Result 
{
	
	private int registration;
	private int classification;
	private boolean result;
	
	public Result(int registration, int classification,boolean result) 
	{																	
		this.setRegistration(registration);
		this.setClassification(classification);
		this.setResult(result);
	}

	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
