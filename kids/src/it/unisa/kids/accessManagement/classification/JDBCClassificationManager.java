package it.unisa.kids.accessManagement.classification;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Michele Nappo
 *
 */

public class JDBCClassificationManager implements IClassificationManager
{
	private static JDBCClassificationManager manager;

	// Singleton Design Pattern's implementation
	private JDBCClassificationManager()
	{
	}

	public static synchronized JDBCClassificationManager getInstance(){
		if (manager!=null)
			return manager;
		else
			return manager=new JDBCClassificationManager();
	}
	// end of Singleton Design Pattern's implementation


	public synchronized void insert (ClassificationBean pClassification) throws SQLException
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		String query;

		try {
			con = DBConnectionPool.getConnection();

			// constructing query string
			query = "INSERT INTO " + DBNames.TABLE_CLASSIFICATION + " ("
					+ DBNames.ATT_CLASSIFICATION_ID + ", "
					+ DBNames.ATT_CLASSIFICATION_DATA+ ", "
					+ DBNames.ATT_CLASSIFICATION_DATA_TERM + ", "

					+ ") VALUES(?, ?, ?)";

			pstmt = con.prepareStatement(query);

			//setting pstmt's parameters
			pstmt.setInt(1, pClassification.getId());
			pstmt.setDate(2, new java.sql.Date(pClassification.getDate().getTimeInMillis()));
			pstmt.setDate(2, new java.sql.Date(pClassification.getDateTerm().getTimeInMillis()));

			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}



	public synchronized void update(ClassificationBean pClassification) throws SQLException
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		String query;

		try
		{
			con=DBConnectionPool.getConnection();

			// constructing query string

			query = "UPDATE " + DBNames.TABLE_CLASSIFICATION +
					" SET "
					+ DBNames.ATT_CLASSIFICATION_DATA + " = ?, "
					+ DBNames.ATT_CLASSIFICATION_DATA_TERM + " = ?, "
					+ "WHERE " + DBNames.ATT_CLASSIFICATION_ID + " = ?";

			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters

			pstmt.setDate (1,new Date(pClassification.getDate().getTimeInMillis()));
			pstmt.setDate (2,new Date(pClassification.getDateTerm().getTimeInMillis()));
			pstmt.setInt(3, pClassification.getId());
			pstmt.executeUpdate();
			con.commit();

		}
		finally
		{
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}

	public synchronized void delete(ClassificationBean pClassification) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String query = null;

		try {
			con = DBConnectionPool.getConnection();

			// constructing query string
			query = "DELETE FROM " + DBNames.TABLE_CLASSIFICATION
					+ "WHERE " + DBNames.ATT_CLASSIFICATION_ID + "= " + pClassification.getId();

			stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}

	public synchronized List<ClassificationBean> search(ClassificationBean pClassification) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<ClassificationBean> classification = null;

		boolean andState = false;

		try {
			con = DBConnectionPool.getConnection();

			// constructing search query string
			query = "SELECT * FROM " + DBNames.TABLE_CLASSIFICATION + " WHERE";
			if (pClassification.getId() > 0) {		
				query += " " + DBNames.ATT_CLASSIFICATION_ID + " = ?";
				andState = true;
			}

			if (pClassification.getDate() != null) {
				query += useAnd(andState) + DBNames.ATT_CLASSIFICATION_DATA+ " = ?";
				andState = true;
			}

			query += useAnd(andState) + DBNames.ATT_CLASSIFICATION_DATA_TERM + " = ?";
			andState = true;

			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pClassification.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pClassification.getId());
				i++;
			}

			if (pClassification.getDate() != null) {
				pstmt.setDate(i, new java.sql.Date(pClassification.getDate().getTimeInMillis()));
				i++;
			}

			if (pClassification.getDateTerm() != null) {
				pstmt.setDate(i, new java.sql.Date(pClassification.getDateTerm().getTimeInMillis()));
				i++;
			}

			// executing select query
			rs = pstmt.executeQuery();
			con.commit();

			// constructing payment list
			classification = new ArrayList<ClassificationBean>();
			while (rs.next()) 
			{
				ClassificationBean p = new ClassificationBean();
				p.setId(rs.getInt(DBNames.ATT_CLASSIFICATION_ID));

				//getting Date from ResultSet and converting it to GregorianCalendar
				GregorianCalendar date = new GregorianCalendar();
				date.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA));
				p.setDate(date);

				GregorianCalendar dateTerm = new GregorianCalendar();
				dateTerm.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA_TERM));
				p.setDateTerm(dateTerm);

				classification.add(p);
			}
		} 
		finally 
		{
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}

		return classification;
	}

	private String useAnd(boolean pEnableAnd) {
		return pEnableAnd ? " AND " : " ";
	}


	public synchronized List<ClassificationBean> getClassificationList() throws SQLException 
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		List<ClassificationBean> classification = null;

		try {
			con = DBConnectionPool.getConnection();

			query = "SELECT * FROM " + DBNames.TABLE_CLASSIFICATION;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// constructing payment list
			classification = new ArrayList<ClassificationBean>();
			while (rs.next()) 
			{
				ClassificationBean p = new ClassificationBean();
				p.setId(rs.getInt(DBNames.ATT_CLASSIFICATION_ID));

				//getting Date from ResultSet and converting it to GregorianCalendar
				GregorianCalendar date = new GregorianCalendar();
				date.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA));
				p.setDate(date);

				GregorianCalendar dateTerm = new GregorianCalendar();
				dateTerm.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA_TERM));
				p.setDate(dateTerm);
				classification.add(p);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return classification;
	}


}
