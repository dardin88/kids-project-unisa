package it.unisa.kids.communicationManagement.reunion;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class ReunionManager {

	private static ReunionManager manager;
	
	/**
	 * the constructor empty
	 */
	private ReunionManager(){
	}

	/**
	 * this method implements the design pattern "singleton"
	 * @return manager
	 */
	public ReunionManager getIstance(){
		if(manager==null)
			return manager=new ReunionManager();
		else 
			return manager;
	}

	/**
	 * this method insert the reunion in the database.
	 * @param Reunion reunion
	 * 
	 */
	public void insert (Reunion reunion) throws ErroreNeiDati{
		Connection con = null;
		Statement stmt = null;
		
		if(reunion.getTitle().length()>50 && reunion.getDescription().length()>500)
			throw new ErroreNeiDati();
		else {
			try{
				con=DBConnectionPool.getConnection();

				stmt = con.createStatement();

				String query = "Insert  into" + DBNames.TABLE_REUNION + "values("+ 
						reunion.getId() + "," + reunion.getTitle() + "," + reunion.getDescription() + "," + reunion.getDate() + "," + reunion.getHour() + "," + reunion.getType();

				stmt.executeUpdate(query);

			}
			finally{
				stmt.close();
				DBConnectionPool.releaseConnection(con); //connection of DB
			}
		}
	}

	/**
	 * this method modify the reunion in the database.
	 * @param Reunion changedReunion
	 * 
	 */
	public void modify (Reunion changedReunion) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		
		try{
			con=DBConnectionPool.getConnection();
			
			String query="Update table_riunion " +
					"SET "+ DBNames.ATT_REUNION_TITLE + "=" + changedReunion.getTitle() + "," + DBNames.ATT_REUNION_DESCRIPTION + "=" + changedReunion.getDescription() + "," + DBNames.ATT_REUNION_DATA + "=" + changedReunion.getDate() + "," + DBNames.ATT_REUNION_HOUR + "=" + changedReunion.getHour() + "," + DBNames.ATT_REUNION_TYPE + "=" + changedReunion.getType() +
					"WHERE "+ DBNames.ATT_REUNION_ID + "=" + changedReunion.getId(); 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	
	/**
	 * this method delete the reunion in the database.
	 * @param Reunion deletedReunion
	 * 
	 */
	public void delete (Reunion deletedReunion) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		
		try{
			con=DBConnectionPool.getConnection();
			
			String query = "Delete From " + DBNames.TABLE_REUNION + " Where "+DBNames.ATT_REUNION_ID + "='" + deletedReunion.getId() + "'";
			
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
}
