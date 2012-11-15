package it.unisa.kids.communicationManagement.childrenManagement;

public class ManagerComunicazioniBisogno 
{
	private static ManagerComunicazioniBisogno manager; 
	
	private ManagerComunicazioniBisogno()
	{
	}
	
	public ManagerComunicazioniBisogno getInstance()
	{
		if(manager==null)
			return (manager=new ManagerComunicazioniBisogno());
		else
			return manager;
	}
	
	public ComunicazioniBisogno insertComunicazioniBisogno (int id, Object bambino, String tipo, String bisogni)
	{
		if (tipo.length()>10)
		{
			//errore
		}
		if(bisogni.length()>200)
		{
			//errore
		}
		ComunicazioniBisogno n=new ComunicazioniBisogno (id, bambino, tipo, bisogni);
		return n;
	}

}
