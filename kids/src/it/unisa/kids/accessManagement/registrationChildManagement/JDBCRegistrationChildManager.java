package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRegistrationChildManager {
	
	private static JDBCRegistrationChildManager manager;

	private JDBCRegistrationChildManager(){
	}

	public static synchronized JDBCRegistrationChildManager getInstance()
	  {
	    if (manager == null)
	    {
	      manager = new JDBCRegistrationChildManager();
	    }
	    return manager;
	  }
	  
	  public synchronized RegistrationChild create(RegistrationChild pChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  
		  String query="INSERT INTO '"+DBNames.TABLE_CHILD+"' (" +		//spero sia questa la tabella per l'scrizione
				DBNames.ATT_CHILD_SURNAME+", "+
		  		DBNames.ATT_CHILD_NAME+", "+
				DBNames.ATT_CHILD_BORNDATE+", "+
		  		DBNames.ATT_CHILD_COMMONBORN+", "+
				DBNames.ATT_CHILD_FISCALCODE+", "+
		  		DBNames.ATT_CHILD_CITIZENSHIP+", "+
				DBNames.ATT_CHILD_USERSECTION+", "+
		  		DBNames.ATT_CHILD_REGISTRATIONDATE+", "+
				DBNames.ATT_CHILD_SICK+", "+
		  		DBNames.ATT_CHILD_REGISTRATIONPHASE+") VALUES"+		//anche accountgenitore e classe? (nell'SDD non c'erano come campi il motivo è perchè sono chiavi esterne?)
				  					"('"+pChildReg.getSurname()+"', '"+
				  						 pChildReg.getName()+"', '"+
				  						 pChildReg.getBornDate()+"', '"+
				  						 pChildReg.getCommuneBorn()+"', '"+
				  						 pChildReg.getFiscalCode()+"', '" +
				  						 pChildReg.getCitizenship()+"', '"+
				  						 pChildReg.getUserSection()+"', '"+
				  						 pChildReg.getRegistrationDate()+"', '"+
				  						 pChildReg.isSick()+"', '"+
				  						 pChildReg.getRegistrationPhase()+"' );"; 			
		 
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
		  
		  return pChildReg;
	  }
	  
	  public synchronized RegistrationChild delete(RegistrationChild pChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="DELETE FROM '"+DBNames.TABLE_CHILD+
				  			"' WHERE '"+DBNames.ATT_CHILD_ID+"'='"+pChildReg.getRegistrationId()+"';";
		  
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
		  
		  return pChildReg;
	  }
	  
	  
	  public synchronized List<RegistrationChild> search(RegistrationChild pChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  ResultSet result=null;
		  RegistrationChild tmpRegChild=new RegistrationChild();
		  List<RegistrationChild> listOfChildReg=new ArrayList<RegistrationChild>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM '"+DBNames.TABLE_CHILD+"' WHERE ";				
		  
		  if (pChildReg.getBornDate()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_BORNDATE+"'='"+pChildReg.getBornDate()+"'";
		  if (pChildReg.getCitizenship()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_CITIZENSHIP+"'='"+pChildReg.getCitizenship()+"'"; 
		  if (pChildReg.getCommuneBorn()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_COMMONBORN+"'='"+pChildReg.getCommuneBorn()+"'";
		  if (pChildReg.getFiscalCode()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_FISCALCODE+"'='"+pChildReg.getFiscalCode()+"'";
		  if (pChildReg.getName()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_NAME+"'='"+pChildReg.getName()+"'";
		  if (pChildReg.getRegistrationDate()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_REGISTRATIONDATE+"'='"+pChildReg.getRegistrationDate()+"'";
		  if (pChildReg.getRegistrationId()>0)
			  query=query+"'"+DBNames.ATT_CHILD_ID+"'='"+pChildReg.getRegistrationId()+"'";
		  if (pChildReg.isSick()!=true)							//chiedo conferma per la gestione del boolean
			  query=query+"'"+DBNames.ATT_CHILD_SICK+"'='"+pChildReg.isSick()+"'";
		  if (pChildReg.getSurname()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_SURNAME+"'='"+pChildReg.getSurname()+"'";
		  if (pChildReg.getUserSection()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_USERSECTION+"'='"+pChildReg.getUserSection()+"'";
		  if (pChildReg.getRegistrationPhase()!=null)
			  query=query+"'"+DBNames.ATT_CHILD_REGISTRATIONPHASE+"'='"+pChildReg.getRegistrationPhase()+"'";
		  
		  //ci vogliono gli and, e forse ci manca if: accountgenitore, classe
		  
		  query=query+";";
		  		  
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  result=stmt.executeQuery(query);
			  
			  while(result.next())
			  {
				  tmpRegChild.setBornDate(result.getString(DBNames.ATT_CHILD_BORNDATE));
				  tmpRegChild.setCitizenship(result.getString(DBNames.ATT_CHILD_CITIZENSHIP));
				  tmpRegChild.setCommuneBorn(result.getString(DBNames.ATT_CHILD_COMMONBORN));
				  tmpRegChild.setFiscalCode(result.getString(DBNames.ATT_CHILD_FISCALCODE));
				  tmpRegChild.setName(result.getString(DBNames.ATT_CHILD_NAME));
				  tmpRegChild.setRegistrationDate(result.getString(DBNames.ATT_CHILD_REGISTRATIONDATE));
				  tmpRegChild.setRegistrationId(result.getInt(DBNames.ATT_CHILD_ID));
				  tmpRegChild.setSick(result.getBoolean(DBNames.ATT_CHILD_SICK));
				  tmpRegChild.setSurname(result.getString(DBNames.ATT_CHILD_SURNAME));
				  tmpRegChild.setUserSection(result.getString(DBNames.ATT_CHILD_USERSECTION));
				  tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_CHILD_REGISTRATIONPHASE));
				  
				  //account genitore e classe
				  
				  listOfChildReg.add(tmpRegChild);
			  }
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }
		  
		  return listOfChildReg;
	  }
	  
	  public synchronized RegistrationChild update(RegistrationChild pChildReg) throws SQLException
	  {
		  	Connection con = null;
		  	Statement stmt=null;
			String query="UPDATE '"+DBNames.TABLE_CHILD+"' " + 
							"SET ("+DBNames.ATT_CHILD_SURNAME+", "+
									DBNames.ATT_CHILD_NAME+", "+
									DBNames.ATT_CHILD_BORNDATE+", "+
							  		DBNames.ATT_CHILD_COMMONBORN+", "+
									DBNames.ATT_CHILD_FISCALCODE+", "+
							  		DBNames.ATT_CHILD_CITIZENSHIP+", "+
									DBNames.ATT_CHILD_USERSECTION+", "+
							  		DBNames.ATT_CHILD_REGISTRATIONDATE+", "+
									DBNames.ATT_CHILD_SICK+", "+
							  		DBNames.ATT_CHILD_REGISTRATIONPHASE+") VALUES ('"+			//anche accountgenitore e classe? 
							  				 pChildReg.getSurname()+"', '"+
											 pChildReg.getName()+"', '"+
											 pChildReg.getBornDate()+"', '"+
											 pChildReg.getCommuneBorn()+"', '"+
											 pChildReg.getFiscalCode()+"', '" +
											 pChildReg.getCitizenship()+"', '"+
											 pChildReg.getUserSection()+"', '"+
											 pChildReg.getRegistrationDate()+"', '"+
											 pChildReg.isSick()+"', '"+
											 pChildReg.getRegistrationPhase()+"') "+ 
					 	"WHERE '"+DBNames.ATT_CHILD_ID+"'="+pChildReg.getRegistrationId()+";"; 
			
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
			
			 return pChildReg;
		}
}

