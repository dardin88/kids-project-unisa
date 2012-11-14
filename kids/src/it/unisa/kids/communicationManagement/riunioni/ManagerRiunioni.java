package it.unisa.kids.communicationManagement.riunioni;

import it.unisa.kids.accessManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class ManagerRiunioni {

	private static ManagerRiunioni manager;

	private ManagerRiunioni(){
	}

	public ManagerRiunioni getIstance(){
		if(manager==null)
			return manager=new ManagerRiunioni();
		else 
			return manager;
	}

	public Riunioni insert (Riunioni riunion) throws ErroreNeiDati{
		Connection con = null;
		Statement stmt = null;
		if(riunion.getTitle().length()>50 && riunion.getDescription().length()>500)
			throw new ErroreNeiDati();
		else {
			try{
				con=DBConnectionPool.getConnection();

				stmt = con.createStatement();

				String query = "Insert  into" + DBNames.TABLE_REUNION + "values("+ 
						riunion.getId() + "," + riunion.getTitle() + "," + riunion.getDescription() + "," + riunion.getDate() + "," + riunion.getHour() + "," + riunion.getType();

				stmt.executeUpdate(query);

			}
			finally{
				stmt.close();
				DBConnectionPool.releaseConnection(con); //connection of DB
			}
		}
		return riunion;
	}

	public Riunioni modify (Riunioni changedRiunion) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		try{
			con=DBConnectionPool.getConnection();
			
			String query="Update table_riunion " +
					"SET "+ DBNames.ATT_REUNION_TITLE + "=" + changedRiunion.getTitle() + "," + DBNames.ATT_REUNION_DESCRIPTION + "=" + changedRiunion.getDescription() + "," + DBNames.ATT_REUNION_DATA + "=" + changedRiunion.getDate() + "," + DBNames.ATT_REUNION_HOUR + "=" + changedRiunion.getHour() + "," + DBNames.ATT_REUNION_TYPE + "=" + changedRiunion.getType() +
					"WHERE "+ DBNames.ATT_REUNION_ID + "=" + changedRiunion.getId(); 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return changedRiunion;
	}
	
	public Riunioni delete (Riunioni deletedRiunion) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		try{
			con=DBConnectionPool.getConnection();
			
			String query = "Delete From " + DBNames.TABLE_REUNION + " Where "+DBNames.ATT_REUNION_ID + "='" + deletedRiunion.getId() + "'";
			
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return deletedRiunion;
	}
}
