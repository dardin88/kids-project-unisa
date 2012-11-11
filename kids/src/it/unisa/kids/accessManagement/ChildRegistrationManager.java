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
		  String query="INSERT INTO 'iscrizionebambino' (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+		//anche accountgenitore e classe? (nell'SDD non c'erano come campi il motivo è perchè sono chiavi esterne?) l'id deve essere inserito manualmente?
				  					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
				  					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.getSickness()+"', '"+aChildReg.getFaseIscrizione()/*da implementare*/+"' );"; 			
		 
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aChildReg;
	  }
	  
	  public ChildRegistration delete(ChildRegistration aChildReg)
	  {
		  String query="DELETE FROM 'iscrizioneBambino' WHERE 'Id'='"+aChildReg.getRegistrationId()+"';";
		  
		  //connessione al db
		  
		  //esecuzione della query
		  
		  return aChildReg;
	  }
	  
	  
	  public List<ChildRegistration> search(ChildRegistration aChildReg)
	  {
		  List<ChildRegistration> listOfChildReg=new ArrayList<ChildRegistration>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'iscrizionebambino' WHERE ";				
		  
		  if (aChildReg.getBornDate()!=null)
			  query=query+"'DataNascita'='"+aChildReg.getBornDate()+"'";
		  if (aChildReg.getCitizenship()!=null)
			  query=query+"'Cittadinanza'='"+aChildReg.getCitizenship()+"'"; 
		  if (aChildReg.getCommuneBorn()!=null)
			  query=query+"'ComuneNascita'='"+aChildReg.getCommuneBorn()+"'";
		  if (aChildReg.getFiscalCode()!=null)
			  query=query+"'CodiceFiscale'='"+aChildReg.getFiscalCode()+"'";
		  if (aChildReg.getName()!=null)
			  query=query+"'Nome'='"+aChildReg.getName()+"'";
		  if (aChildReg.getRegistrationDate()!=null)
			  query=query+"'DataIscrizione'='"+aChildReg.getRegistrationDate()+"'";
		  if (aChildReg.getRegistrationId()!=null)
			  query=query+"'Id'='"+aChildReg.getRegistrationId()+"'";
		  if (aChildReg.getSickness()!=null)
			  query=query+"'Malattia'='"+aChildReg.getSickness()+"'";
		  if (aChildReg.getSurname()!=null)
			  query=query+"'Cognome'='"+aChildReg.getSurname()+"'";
		  
		  //ci manca qualche if: fasciautenza, faseiscrizione, accountgenitore, classe
		  
		  query=query+";";
		  		  
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return listOfChildReg;
	  }
	  
	  public ChildRegistration modify(ChildRegistration aChildReg){
			
			String query="UPDATE 'iscrizionebambino' " + //l'id deve essere modificato manualmente?
					"SET (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+//			anche accountgenitore e classe? 
					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
 					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.getSickness()+"', '"+aChildReg.getFaseIscrizione()/*da implementare*/+"' )"+ 
					"WHERE 'Id'="+aChildReg.getRegistrationId()+";"; 
			
			 //connessione al db
			  
			 //esecuzione della query	
			
			return aChildReg;
		}

}
