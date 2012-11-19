package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRenunciationManager {
	
	private static JDBCRenunciationManager manager;

	private JDBCRenunciationManager() {
	}

	public static JDBCRenunciationManager getInstance() {
		if (manager == null) {
	      manager = new JDBCRenunciationManager();
	    }
		return manager;
	}
	  
	public Renunciation create(Renunciation pWaiver) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		
		try {
			con=DBConnectionPool.getConnection();
			String query="INSERT INTO '"+DBNames.TABLE_RINUNCIA+"' ("+DBNames.ATT_RINUNCIA_DATE+" , "+DBNames.ATT_RINUNCIA_CONFIRM+") " +
					"VALUES"+		
	  				"('"+pWaiver.getDate()+
	  				"', '"+pWaiver.isConfirmation()+"' );"; 			
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	  
		return pWaiver;
	}
	  
	public Renunciation delete(Renunciation pWaiver) throws SQLException {
		  Connection con = null;
		  Statement stmt=null;
		  
		  try {
			  con = DBConnectionPool.getConnection();
			  String query = "DELETE FROM '"+DBNames.TABLE_RINUNCIA+"' WHERE '"+DBNames.ATT_RINUNCIA_ID+"'='"+pWaiver.getId()+"';";
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  } finally {
			  stmt.close(); // controllare prima se la connessione è avvenuta con successo e solo in tal caso chiuderla
			  DBConnectionPool.releaseConnection(con);
		  }
		  
		  return pWaiver;
	  }
	  
	  
	public List<Renunciation> search(Renunciation pWaiver) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		ResultSet result=null;
		Renunciation tmpWaiver=new Renunciation();
		List<Renunciation> listOfWaiver=new ArrayList<Renunciation>();		
		String query="SELECT * FROM '"+DBNames.TABLE_RINUNCIA+"' WHERE ";				
		  
		if(pWaiver.getId()!=null)
			query=query+"'"+DBNames.ATT_RINUNCIA_ID+"'='"+pWaiver.getId()+"'";
		if(pWaiver.getDate()!=null)
			query=query+"'"+DBNames.ATT_RINUNCIA_DATE+"'='"+pWaiver.getDate()+"'"; 
		if(pWaiver.isConfirmation()==true)									//Chiedo conferma :D
			query=query+"'"+DBNames.ATT_RINUNCIA_CONFIRM+"'='"+pWaiver.isConfirmation()+"'";
		  
		query=query+";";
		  
		  //ci vogliono gli and
		  		
		try {
			con=DBConnectionPool.getConnection();
			stmt = con.createStatement();
			result=stmt.executeQuery(query);
			  
			while(result.next()) {
				tmpWaiver.setConfirmation(result.getBoolean(DBNames.ATT_RINUNCIA_CONFIRM));
				tmpWaiver.setDate(result.getString(DBNames.ATT_RINUNCIA_DATE));
				tmpWaiver.setId(result.getString(DBNames.ATT_RINUNCIA_ID));
			
				listOfWaiver.add(tmpWaiver);
			}
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return listOfWaiver;
	}
	
	public Renunciation modify(Renunciation pWaiver) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		String query="UPDATE '"+DBNames.TABLE_RINUNCIA+"' " + 	
					"SET ("+DBNames.ATT_RINUNCIA_DATE+" , "+DBNames.ATT_RINUNCIA_CONFIRM+") VALUES"+ 
					"('"+pWaiver.getDate()+
  					"', '"+pWaiver.isConfirmation()+"' )"+ 
					"WHERE '"+DBNames.ATT_RINUNCIA_ID+"'="+pWaiver.getId()+";"; 
			
		try {
			con=DBConnectionPool.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}	
		return pWaiver;
	}
}
