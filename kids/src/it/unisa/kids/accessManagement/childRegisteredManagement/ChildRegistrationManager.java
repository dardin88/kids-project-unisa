package it.unisa.kids.accessManagement.childRegisteredManagement;

import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChildRegistrationManager {
	
	private static ChildRegistrationBean manager;

	  private ChildRegistrationManager()
	  {
	  }

	  public static ChildRegistrationBean getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new ChildRegistrationBean();
	    }

	    return manager;
	  }
	  
	  public ChildRegistrationBean create(ChildRegistrationBean aChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="INSERT INTO 'iscrizionebambino' (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+		//anche accountgenitore e classe? (nell'SDD non c'erano come campi il motivo è perchè sono chiavi esterne?) l'id deve essere inserito manualmente?
				  					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
				  					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.getSickness()+"', '"+aChildReg.getFaseIscrizione()/*da implementare*/+"' );"; 			
		 
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }	
		  
		  return aChildReg;
	  }
	  
	  public ChildRegistrationBean delete(ChildRegistrationBean aChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="DELETE FROM 'iscrizioneBambino' WHERE 'Id'='"+aChildReg.getRegistrationId()+"';";
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }	
		  
		  return aChildReg;
	  }
	  
	  
	  public List<ChildRegistrationBean> search(ChildRegistrationBean aChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  ResultSet result=null;
		  List<ChildRegistrationBean> listOfChildReg=new ArrayList<ChildRegistrationBean>();		//deve essere riempito con il risultato della query
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
		  		  
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  result=stmt.executeQuery(query);
			  
			  //organizza il risultato
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }
		  
		  return listOfChildReg;
	  }
	  
	  public ChildRegistrationBean modify(ChildRegistrationBean aChildReg) throws SQLException
	  {
		  	Connection con = null;
		  	Statement stmt=null;
			String query="UPDATE 'iscrizionebambino' " + //l'id deve essere modificato manualmente?
					"SET (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+//			anche accountgenitore e classe? 
					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
 					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.getSickness()+"', '"+aChildReg.getFaseIscrizione()/*da implementare*/+"' )"+ 
					"WHERE 'Id'="+aChildReg.getRegistrationId()+";"; 
			
			try
			{
				  con=DBConnectionPool.getConnection();
				  stmt = con.createStatement();
				  stmt.executeUpdate(query);
			}
			finally{
				  stmt.close();
				  DBConnectionPool.releaseConnection(con);
			}	
			
			 return aChildReg;
		}
}
