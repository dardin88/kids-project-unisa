package it.unisa.kids.accessManagement;
/**
 * 
 * @author Michele Nappo
 *
 */

public class Result 
{
	public Result(int registration, int classification,boolean result) 
	{																	
		this.setRegistration(registration);
		this.setClassification(classification);
		this.setResult(result);
	}

	public int getRegistration() 
	{
		return this.registration;
	}

	public void setRegistration(int registration) 
	{
		this.registration = registration;
	}

	public int getClassification() 
	{
		return this.classification;
	}

	public void setClassification(int classification) 
	{
		this.classification = classification;
	}

	public boolean getResult()
	{
		return this.result;
	}

	public void setResult(boolean result)
	{
		this.result = result;
	}
	
	private int registration;
	private int classification;
	private boolean result;
}
