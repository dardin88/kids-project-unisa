package it.unisa.kids.accessManagement.Class;

import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	  
	  public Class create(Class aClass) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="INSERT INTO 'classe' ('Nome', 'Id') VALUES ('"+aClass.getClassName()+"', '"+aClass.getIdClasse()+"');"; 			//come inserire educatori e bambini, l'id deve essere inserito manualmente?
		  
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
		  
		  return aClass;
	  }
	  
	  public Class delete(Class aClass) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="DELETE FROM 'classe' WHERE 'Id'='"+aClass.getIdClasse()+"'";	
		  
		  
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
		  
		  return aClass;
	  }
	  
	  
	  public List<Class> search(Class aClass) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  ResultSet result=null;
		  List<Class> listOfClass=new ArrayList<Class>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM 'classe' WHERE ";			
		  
		  if (aClass.getIdClasse()!=null)
			  query=query+"'Id'='"+aClass.getIdClasse()+"'";
		  if (aClass.getClassName()!=null)
			  query=query+"'Nome'='"+aClass.getClassName()+"'";
		  
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
		  
		  return listOfClass;
	  }
	  
	  public Class modify(Class aClass) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="UPDATE 'classe' " +
					"SET 'Nome'="+aClass.getClassName()+	//l'id non deve essere toccato giusto?
					"WHERE 'Id'="+aClass.getIdClasse(); 
			
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
		  
		  return aClass;
	  }
}
