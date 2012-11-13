package it.unisa.kids.communicationManagement.bambini;

public class ManagerComunicazioniSalute 
{
	private static ManagerComunicazioniSalute manager; 
	
	private ManagerComunicazioniSalute()
	{
	}
	
	public ManagerComunicazioniSalute getInstance()
	{
		if(manager==null)
			return (manager=new ManagerComunicazioniSalute());
		else
			return manager;
	}
	
	public ComunicazioniSalute insertComunicazioniSalute (int id, Object bambino, String pranzo, String quantità, String oreSonno, String statoAnimo, String malori)
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
		ComunicazioniSalute n=new ComunicazioniSalute (id, bambino, pranzo, quantità, oreSonno, statoAnimo, malori);
		return n;
	}

}
