package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

/**This class models the communication manager
 * @author Elena Sollai
 *
 */
public class CommunicationManager {
	
	private static CommunicationManager  manager; 
	
	/**
	 * Empty constructor
	 */
	private CommunicationManager (){
	}
	
	/**
	 * this method implements the design pattern "singleton"
	 * @return manager
	 */
	public CommunicationManager getIstance(){
		if(manager==null)
			return manager=new CommunicationManager();
		else 
			return manager;
	}
	
	/**
	 * this method insert the communication in the database.
	 * @param Communication communication
	 * 
	 */
	public void insert (Communication communication) throws ErroreNeiDati{
		Connection con = null;
		Statement stmt = null;
		
		if(communication.getDescription().length()>500)
			throw new ErroreNeiDati();
		else {
			try{
				con=DBConnectionPool.getConnection();

				stmt = con.createStatement();

				String query = "Insert  into" + DBNames.TABLE_COMUNICATION + "values("+ 
				communication.getId() + "," + communication.getType() + "," + communication.getIdEducator() + 
				"," + communication.getIdParent() + "," + communication.getDescription() + 
				"," + communication.getDate() + ")";
				
				stmt.executeUpdate(query);

			}
			finally{
				stmt.close();
				DBConnectionPool.releaseConnection(con); //connection of DB
			}
		}
	}

	/**
	 * this method modify the communication in the database.
	 * @param Communication changedCommunication
	 * 
	 */
	public void modify (Communication changedCommunication) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		
		try{
			con=DBConnectionPool.getConnection();
			
			String query="Update table_communication " +
					"SET "+ DBNames.ATT_COMMUNICATION_TYPE + "=" + changedCommunication.getType() + "," + 
					DBNames.ATT_COMMUNICATION_IDEDUCATOR + "=" + changedCommunication.getIdEducator() + 
					"," + DBNames.ATT_COMMUNICATION_IDPARENT + "=" + changedCommunication.getIdParent() + 
					"," + DBNames.ATT_COMMUNICATION_DESCRIPTION + "=" + changedCommunication.getDescription() + 
					"," + DBNames.ATT_COMMUNICATION_DATE + "=" + changedCommunication.getDate() +
					"WHERE "+ DBNames.ATT_COMMUNICATION_ID + "=" + changedCommunication.getId(); 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	
	/**
	 * this method delete the communication in the database.
	 * @param Communication deletedCommunication
	 * 
	 */
	public void delete (Communication deletedCommunication) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		
		try{
			con=DBConnectionPool.getConnection();
			
			String query = "Delete From " + DBNames.TABLE_COMUNICATION + 
					" Where "+DBNames.ATT_COMMUNICATION_ID + "='" + deletedCommunication.getId() + "'";
			
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
}

