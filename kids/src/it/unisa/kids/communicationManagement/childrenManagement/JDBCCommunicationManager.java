package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**This class models the communication manager
 * @author Elena Sollai
 *
 */
public class JDBCCommunicationManager {
	
	private static JDBCCommunicationManager  manager; 
	
	/**
	 * Empty constructor
	 */
	private JDBCCommunicationManager (){
	}
	
	/**
	 * this method implements the design pattern "singleton"
	 * @return manager
	 */
	public JDBCCommunicationManager getIstance(){
		if(manager==null)
			return manager=new JDBCCommunicationManager();
		else 
			return manager;
	}
	
	/**
	 * this method implements the design pattern "singleton"
	 * @return manager
	 */
	public static ICommunicationManager getInstance()
	{
		if(manager==null)
			return (manager=new JDBCCommunicationManager());
		else
			return manager;
	}
	
	/**
	 * this method insert the communication in the database.
	 * @param Communication pCommunication
	 * 
	 */
	public void insertCommunication (Communication pCommunication) throws SQLException{
		
		Connection connection = null;
		PreparedStatement stmt=null;
		String query1;
		try {
			connection=DBConnectionPool.getConnection();		
			query1="insert into "+DBNames.TABLE_COMUNICATION+"(" +
					DBNames.ATT_COMMUNICATION_ID+","+
					DBNames.ATT_COMMUNICATION_TYPE+","+
					DBNames.ATT_COMMUNICATION_IDEDUCATOR+","+
					DBNames.ATT_COMMUNICATION_IDCHILD+","+
					DBNames.ATT_COMMUNICATION_DESCRIPTION+","+
					DBNames.ATT_COMMUNICATION_DATE+
					") values (?,?,?,?,?,?,?)";
		
			stmt=connection.prepareStatement(query1);
			stmt.setInt(1, pCommunication.getId());
			stmt.setInt(2, pCommunication.getType());
			stmt.setInt(3, pCommunication.getIdEducator());
			stmt.setInt(4, pCommunication.getIdChild());
			stmt.setString(5, pCommunication.getDescription());
			stmt.setDate(6, new Date(pCommunication.getDate().getTimeInMillis()));
			
			stmt.executeUpdate();
			connection.commit();
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
	}
	
	/**
	 * this method show all the Communication presents in the database
	 * @return ArrayListCommunication<Communication> listCommunication
	 */

	public ArrayList<Communication> showCommunication() throws SQLException {
		Connection connection=null;
		Statement stmt=null;
		ResultSet rsCommunication=null;
		String query;
		ArrayList<Communication> listCommunication=new ArrayList<Communication>();
		try{
			connection=DBConnectionPool.getConnection();
			query="select * from "+DBNames.TABLE_COMUNICATION;
			stmt=connection.createStatement();
			rsCommunication=stmt.executeQuery(query);
			while(rsCommunication.next()){
				int id=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_ID);		
				int type=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_TYPE);
				int idEducator=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDEDUCATOR);
				int idChild=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDCHILD);
				String description=rsCommunication.getString(DBNames.ATT_COMMUNICATION_DESCRIPTION);
				Date date=rsCommunication.getDate(DBNames.ATT_COMMUNICATION_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				data.set(Calendar.MONTH, (data.get(Calendar.MONTH))+1);

				Communication communication = new Communication();
				communication.setId(id);
				communication.setType(type);
				communication.setIdEducator(idEducator);
				communication.setIdChild(idChild);
				communication.setDescription(description);
				communication.setDate(data);
				listCommunication.add(communication);
			}
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return listCommunication;
	}
	
	/**
	 * this method delete communication by the database
	 * @param Communication pCommunication
	 */
	public void deleteCommunication(Communication pCommunication) throws SQLException {
		Connection connection=null;
		Statement stmt=null;
		String query;
		try{
			connection=DBConnectionPool.getConnection();
			query="delete from "+DBNames.TABLE_COMUNICATION+" where "+DBNames.ATT_COMMUNICATION_ID+"='"+pCommunication.getId()+"'";
			System.out.println(query);
			stmt=connection.createStatement();
			stmt.executeUpdate(query);
			connection.commit();
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
	}
	
	/**
	 * this method modify a communication. make the communication of the database
	 * and delete it, reinsert the communication across the parameter
	 * @param Communication pCommunication
	 */
	public void modifyCommunication(Communication pCommunication) throws SQLException {
		Connection connection=null;
		Statement stmt=null;
		String query;
		ResultSet rsCommunication=null;
		try{
			connection=DBConnectionPool.getConnection();
			query="select * from "+DBNames.TABLE_COMUNICATION+" where "+DBNames.ATT_COMMUNICATION_ID+"='"+pCommunication.getId()+"'";
			stmt=connection.createStatement();
			rsCommunication=stmt.executeQuery(query);
			
			while(rsCommunication.next()){	
				int id=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_ID);
				int type=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_TYPE);
				int idEducator=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDEDUCATOR);
				int idChild=rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDCHILD);
				String description=rsCommunication.getString(DBNames.ATT_COMMUNICATION_DESCRIPTION);				
				Date date=rsCommunication.getDate(DBNames.ATT_COMMUNICATION_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);

				Communication communication = new Communication();
				communication.setId(id);
				communication.setType(type);
				communication.setIdEducator(idEducator);
				communication.setIdChild(idChild);
				communication.setDescription(description);
				communication.setDate(data);
		
				deleteCommunication(communication);
				insertCommunication(pCommunication);
			}
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
		
	}
	
}




