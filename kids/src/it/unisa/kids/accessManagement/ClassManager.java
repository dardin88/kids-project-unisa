package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class ClassManager { 
	
	  private static ClassManager manager;

	  private ClassManager()
	  {
	  }

	  public static ClassManager getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new ClassManager();
	    }

	    return manager;
	  }
	  
	  public Class create(Class aClass)
	  {
		  String query="INSERT INTO 'nomedb'.'classi' ('nomeClasse', 'idClasse') VALUES ('"+aClass.getClassName()+"', '"+aClass.getIdClasse()+"');"; 			//cambiare i nomi del db, come inserire educatori e bambini
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aClass;
	  }
	  
	  public Class delete(Class unaClasse)
	  {
		  String query="DELETE FROM 'nomedb'.'classi' WHERE 'classi'.'nomeClasse' = '"+unaClasse.getClassName()+"' AND 'idClasse'='"+unaClasse.getIdClasse()+"'";		//cambiare i nome del db
		  
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return unaClasse;
	  }
	  
	  
	  public List<Class> search(Class unaClasse)
	  {
		  List<Class> elencoClassi=new ArrayList<Class>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'classi' WHERE ";				//cambiare i nome del db
		  
		  if (unaClasse.getIdClasse()!=null)
			  query=query+"'idClasse'='"+unaClasse.getIdClasse()+"'";
		  if (unaClasse.getClassName()!=null)
			  query=query+"'nomeClassi'='"+unaClasse.getClassName()+"'";
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return elencoClassi;
	  }
}
