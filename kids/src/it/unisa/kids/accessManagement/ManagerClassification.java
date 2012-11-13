package it.unisa.kids.accessManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import sun.util.calendar.Gregorian;

public class ManagerClassification 
{

	private static ManagerClassification manager;
	private Classification classification;

	// Singleton Design Pattern's implementation
	public ManagerClassification()
	{
	}

	public static ManagerClassification getInstance(){
		if (manager!=null)
			return manager;
		else
			return manager=new ManagerClassification();
	}
	// end of Singleton Design Pattern's implementation



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



	}

	public void modifyClassification(Classification pClassification, ChildRegistration pChild,Result pResult) throws SQLException
	{

		Connection con = null;
		Statement stmt=null;
		try
		{
			con=DBConnectionPool.getConnection();
			stmt = con.createStatement();
			boolean value = Boolean.parseBoolean(DBNames.ATT_RESULT_RESULT);
			
			String query=(
					"UPDATE " + DBNames.TABLE_CLASSIFICATION + 
					"SET " + DBNames.ATT_RESULT_RESULT + "=" + !value
					);

					stmt.executeUpdate(query);
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(con);//connection of DB
		}


	}


	public void defineDataTerm(Classification pClassification, GregorianCalendar dateTerm) throws SQLException
	{
		Connection con = null;
		Statement stmt=null;
		try
		{
			con=DBConnectionPool.getConnection();
			stmt = con.createStatement();

			String query=(
					"Update " + DBNames.TABLE_CLASSIFICATION + 
					"SET " + DBNames.ATT_CLASSIFICATION_DATA_TERM + "=" + dateTerm
					);

			stmt.executeUpdate(query);

		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);//connection of DB
		}


	}
}


