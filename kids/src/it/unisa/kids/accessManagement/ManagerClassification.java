package it.unisa.kids.accessManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerClassification 
{

	private static ManagerClassification manager;
	private Classification classification;

	public ManagerClassification()
	{
	}

	public static ManagerClassification getInstance(){
		if (manager!=null)
			return manager;
		else
			return manager=new ManagerClassification();
	}



	public void createClassification (Classification pClassification) throws SQLException
	{
		Connection con = null;
		Statement stmt=null;
		try
		{
			con=DBConnectionPool.getConnection();

			stmt = con.createStatement();

			String query="Insert  into" + DBNames.TABLE_CLASSIFICATION + "values("+ 
			pClassification.getId() + "," + pClassification.getDate() + "," + pClassification.getDateTerm();
			
			stmt.executeUpdate(query);

		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);//connection of DB
		}

		//execute of query		

	}

	public void modifyClassification()
	{
		
	}

	
	public void defineDataTerm()
	{
		
	}
}


