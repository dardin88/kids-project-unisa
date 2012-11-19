package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRegistrationChildManager {
	
	private static RegistrationChild manager;

	private JDBCRegistrationChildManager(){
	}

	public static RegistrationChild getInstance() {
		if(manager == null) {
			manager = new RegistrationChild();
		}
		return manager;
	}
	  
	public RegistrationChild create(RegistrationChild aChildReg) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String query = "INSERT INTO 'iscrizionebambino' " +
				"(Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) " +
				"VALUES"+		//anche accountgenitore e classe? (nell'SDD non c'erano come campi il motivo è perchè sono chiavi esterne?) l'id deve essere inserito manualmente?
				"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
				"'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.isSick()+"', '"+aChildReg.getFaseDellIscrizione()+"' );"; 			
		 
		try {
			con = DBConnectionPool.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}	
		return aChildReg;
	}
	  
	public RegistrationChild delete(RegistrationChild aChildReg) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		String query = "DELETE " +
					"FROM 'iscrizioneBambino' " +
					"WHERE 'Id'='"+aChildReg.getRegistrationId()+"';";
		
		try {
			con=DBConnectionPool.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}	
		return aChildReg;
	}
	
	public List<RegistrationChild> search(RegistrationChild aChildReg) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		List<RegistrationChild> listOfChildReg = new ArrayList<RegistrationChild>();		//deve essere riempito con il risultato della query
		String query="SELECT * FROM 'iscrizionebambino' WHERE ";				
		  
		if(aChildReg.getBornDate() != null)
			query = query + "'DataNascita'='" + aChildReg.getBornDate() + "'";
		if(aChildReg.getCitizenship() != null)
			query = query + "'Cittadinanza'='" + aChildReg.getCitizenship() + "'"; 
		if(aChildReg.getCommuneBorn() != null)
			query = query+"'ComuneNascita'='"+aChildReg.getCommuneBorn()+"'";
		if(aChildReg.getFiscalCode() != null)
			query = query+"'CodiceFiscale'='"+aChildReg.getFiscalCode()+"'";
		if(aChildReg.getName() != null)
			query = query+"'Nome'='"+aChildReg.getName()+"'";
		if(aChildReg.getRegistrationDate() != null)
			query = query+"'DataIscrizione'='"+aChildReg.getRegistrationDate()+"'";
		if(aChildReg.getRegistrationId() != 0)
			query = query+"'Id'='"+aChildReg.getRegistrationId()+"'";
		if(aChildReg.isSick() != null)
			query = query+"'Malattia'='"+aChildReg.isSick()+"'";
		if(aChildReg.getSurname()!=null)
			query=query+"'Cognome'='"+aChildReg.getSurname()+"'";
		  
		  //ci manca qualche if: fasciautenza, faseiscrizione, accountgenitore, classe
		  
		query=query+";";
		  		  
		try {
			con = DBConnectionPool.getConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(query);
			  
			//organizza il risultato
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return listOfChildReg;
	}
	  
	public RegistrationChild modify(RegistrationChild aChildReg) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		String query="UPDATE 'iscrizionebambino' " + //l'id deve essere modificato manualmente?
					"SET (Id, Cognome, Nome, DataNascita, ComuneNascita, CodiceFiscale, Cittadinanza, FasciaUtenza, DataIscrizione, Malattia, FaseDellIscrizione) VALUES"+//			anche accountgenitore e classe? 
					"('"+aChildReg.getRegistrationId()+"', '"+aChildReg.getSurname()+"', '"+aChildReg.getName()+"', '"+aChildReg.getBornDate()+"', '"+aChildReg.getCommuneBorn()+"', '"+aChildReg.getFiscalCode()+"', " +
 					 "'"+aChildReg.getCitizenship()+"', '"+aChildReg.getUserSection()+/* da implementare*/"', '"+aChildReg.getRegistrationDate()+"', '"+aChildReg.isSick()+"', '"+aChildReg.getFaseDellIscrizione()/*da implementare*/+"' )"+ 
					"WHERE 'Id'="+aChildReg.getRegistrationId()+";"; 
			
		try {
			con = DBConnectionPool.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}	
		return aChildReg;
	}
}
