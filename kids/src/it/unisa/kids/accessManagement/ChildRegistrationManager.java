package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class ChildRegistrationManager {
	
	private static ChildRegistration manager;

	  private ChildRegistrationManager()
	  {
	  }

	  public static ChildRegistration getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new ChildRegistration();
	    }

	    return manager;
	  }
	  
	  public ChildRegistration create(ChildRegistration aChildReg)
	  {
		  String query="INSERT INTO 'nomedb'.'iscrizioneBambino' (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+		//cambiare i nomi del db
				  					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
				  					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.getSickness()+"', '"+aChildReg.getFaseIscrizione()/*da implementare*/+"' );"; 			
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aChildReg;
	  }
	  
	  public ChildRegistration delete(ChildRegistration aChildReg)
	  {
		  String query="DELETE FROM 'nomedb'.'iscrizioneBambino' WHERE 'iscrizioneBambino'.'nomeBambino' = '"+aChildReg.getName()+"' AND 'idIscrizione'='"+aChildReg.getRegistrationId()+"';";		//cambiare i nome del db
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aChildReg;
	  }
	  
	  
	  public List<Classe> search(Classe unaClasse)
	  {
		  List<Classe> elencoClassi=new ArrayList<Classe>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'iscrizioneBambino' WHERE ";				//cambiare i nome del db
		  
		  /*if (unaClasse.getIdClasse()!=null)
			  query=query+"'idClasse'='"+unaClasse.getIdClasse()+"'";
		  if (unaClasse.getClassName()!=null)
			  query=query+"'nomeClassi'='"+unaClasse.getClassName()+"'";*/
		  
		  
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return elencoClassi;
	  }

}
