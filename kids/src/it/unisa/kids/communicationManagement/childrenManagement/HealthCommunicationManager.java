package it.unisa.kids.communicationManagement.childrenManagement;

public class ManagerComunicazioneSalute 
{
	private static ManagerComunicazioneSalute  manager; 
	
	private ManagerComunicazioneSalute ()
	{
	}
	
	public ManagerComunicazioneSalute  getInstance()
	{
		if(manager==null)
			return (manager=new ManagerComunicazioneSalute ());
		else
			return manager;
	}
	
	public ComunicazioneSalute  insertComunicazioneSalute  (int id, Object bambino, String pranzo, String quantità, String oreSonno, String statoAnimo, String malori)
	{
		if (pranzo.length()>50)
		{
			//errore
		}
		if(quantità.length()>20)
		{
			//errore
		}
		if (oreSonno.length()>20)
		{
			//errore
		}
		if(statoAnimo.length()>50)
		{
			//errore
		}	
		if(malori.length()>200)
		{
			//errore
		}
		ComunicazioneSalute  n=new ComunicazioneSalute (id, bambino, pranzo, quantità, oreSonno, statoAnimo, malori);
		return n;
	}

}
