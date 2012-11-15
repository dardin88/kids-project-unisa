package it.unisa.kids.accessManagement.classification;

import it.unisa.kids.accessManagement.childRegistration.ChildRegistration;
import it.unisa.kids.accessManagement.result.Result;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.*;
import java.util.GregorianCalendar;

/**
 * 
 * @author Michele Nappo
 *
 */

public class ClassificationManager 
{
	private static ClassificationManager manager;

	// Singleton Design Pattern's implementation
	private ClassificationManager()
	{
	}

	public static ClassificationManager getInstance(){
		if (manager!=null)
			return manager;
		else
			return manager=new ClassificationManager();
	}
	// end of Singleton Design Pattern's implementation


	public void createClassification (Classification pClassification) throws SQLException
	{
		Connection con = null;
		PreparedStatement pStmt=null;

		try
		{
			con=DBConnectionPool.getConnection();
			String query1, query2;
			query1="Insert  into" + DBNames.TABLE_CLASSIFICATION + "values("+ 
					DBNames.ATT_CLASSIFICATION_ID + "," + DBNames.ATT_CLASSIFICATION_DATA 
					+ "," + DBNames.ATT_CLASSIFICATION_DATA_TERM  + ")";
			
			query2="VALUES (?,?,?)";
			
			pStmt = con.prepareStatement(query1+query2);
			
			pStmt.setInt(1, pClassification.getId());
			pStmt.setDate (2,new Date(pClassification.getDate().getTimeInMillis()));
			pStmt.setDate (3,new Date(pClassification.getDateTerm().getTimeInMillis()));
			
			
			pStmt.executeUpdate();
			con.commit();
		}
		finally
		{
			pStmt.close();
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
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(con);//connection of DB
		}
	}
}