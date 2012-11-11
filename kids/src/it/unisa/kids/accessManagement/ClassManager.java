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
		  String query="INSERT INTO 'classe' ('Nome', 'Id') VALUES ('"+aClass.getClassName()+"', '"+aClass.getIdClasse()+"');"; 			//come inserire educatori e bambini, l'id deve essere inserito manualmente?
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aClass;
	  }
	  
	  public Class delete(Class aClass)
	  {
		  String query="DELETE FROM 'classe' WHERE 'Id'='"+aClass.getIdClasse()+"'";	
		  
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aClass;
	  }
	  
	  
	  public List<Class> search(Class aClass)
	  {
		  List<Class> listOfClass=new ArrayList<Class>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'classe' WHERE ";			
		  
		  if (aClass.getIdClasse()!=null)
			  query=query+"'Id'='"+aClass.getIdClasse()+"'";
		  if (aClass.getClassName()!=null)
			  query=query+"'Nome'='"+aClass.getClassName()+"'";
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return listOfClass;
	  }
	  
	  public Class modify(Class aClass){
			
			String query="UPDATE 'classe' " +
					"SET 'Nome'="+aClass.getClassName()+	//l'id non deve essere toccato giusto?
					"WHERE 'Id'="+aClass.getIdClasse(); 
			
			 //connessione al db
			  
			 //esecuzione della query	
			
			return aClass;
	  }
}
