package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class ManagerClassi {
	
	  private static ManagerClassi manager;

	  private ManagerClassi()
	  {
	  }

	  public static ManagerClassi getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new ManagerClassi();
	    }

	    return manager;
	  }
	  
	  public Classe crea(Classe unaClasse)
	  {
		  String query="INSERT INTO 'nomedb'.'classi' ('nomeClasse', 'idClasse') VALUES ('"+unaClasse.getNomeClasse()+"', '"+unaClasse.getIdClasse()+"');"; 			//cambiare i nomi del db
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return unaClasse;
	  }
	  
	  public Classe elimina(Classe unaClasse)
	  {
		  String query="DELETE FROM 'nomedb'.'classi' WHERE 'classi'.'nomeClasse' = '"+unaClasse.getNomeClasse()+"' AND 'idClasse'='"+unaClasse.getIdClasse()+"'";		//cambiare i nome del db
		  
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return unaClasse;
	  }
	  
	  
	  public List<Classe> ricerca(Classe unaClasse)
	  {
		  List<Classe> elencoClassi=new ArrayList<Classe>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'classi' WHERE ";				//cambiare i nome del db
		  
		  if (unaClasse.getIdClasse()!=null)
			  query=query+"'idClasse'='"+unaClasse.getIdClasse()+"'";
		  if (unaClasse.getNomeClasse()!=null)
			  query=query+"'nomeClassi'='"+unaClasse.getNomeClasse()+"'";
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return elencoClassi;
	  }
}
