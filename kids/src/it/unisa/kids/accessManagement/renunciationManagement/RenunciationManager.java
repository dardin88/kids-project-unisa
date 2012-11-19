package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RenunciationManager {
	
	private static RenunciationManager manager;

	  private RenunciationManager()
	  {
	  }

	  public static RenunciationManager getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new RenunciationManager();
	    }

	    return manager;
	  }
	  
	  public RenunciationBean create(RenunciationBean aWaiver) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
			
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  String query="INSERT INTO 'rinunce' (Data , Conferma) VALUES"+		//l'id deve essere inserito manualmente?
	  					"('"+aWaiver.getDate()+"', '"+aWaiver.isConfirmation()+"' );"; 			
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  }
		  finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		  }
		  
		  return aWaiver;
	  }
	  
	  public RenunciationBean delete(RenunciationBean aWaiver) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  String query="DELETE FROM 'rinunce' WHERE 'Id'='"+aWaiver.getId()+"';";
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }
		  
		  return aWaiver;
	  }
	  
	  
	  public List<RenunciationBean> search(RenunciationBean aWaiver) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  ResultSet result=null;
		  List<RenunciationBean> listOfWaiver=new ArrayList<RenunciationBean>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'rinunce' WHERE ";				
		  
		  if (aWaiver.getId()!=null)
			  query=query+"'Id'='"+aWaiver.getId()+"'";
		  if (aWaiver.getDate()!=null)
			  query=query+"'Data'='"+aWaiver.getDate()+"'"; 
		  if (aWaiver.isConfirmation()==true)									//Chiedo conferma :D
			  query=query+"'Conferma'='"+aWaiver.isConfirmation()+"'";
		  
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
		  
		  return listOfWaiver;
	  }
	  
	  public RenunciationBean modify(RenunciationBean aWaiver)throws SQLException
	  {
		  	Connection con = null;
		  	Statement stmt=null;
			String query="UPDATE 'rinunce' " + 			//l'id deve essere modificato manualmente?
					"SET (Data , Conferma) VALUES"+ 
					"('"+aWaiver.getDate()+"', '"+aWaiver.isConfirmation()+"' )"+ 
					"WHERE 'Id'="+aWaiver.getId()+";"; 
			
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
			
			return aWaiver;
		}
}
