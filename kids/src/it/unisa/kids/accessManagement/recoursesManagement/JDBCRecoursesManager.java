package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
public class JDBCRecoursesManager implements IRecoursesManager {

	private static JDBCRecoursesManager manager;
	private Account parent;

	private JDBCRecoursesManager(){
		parent=new Account();
	}

	public static JDBCRecoursesManager getInstace(){
		if (manager!=null)
			return manager;
		else
			return manager=new JDBCRecoursesManager();
	}

	public synchronized void insert (RecourseBean pRecourse) throws SQLException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String query;

		try {
			con=DBConnectionPool.getConnection();

			query="INSERT INTO "+DBNames.TABLE_RECOURSE+" ("
					+DBNames.ATT_RECOURSE_ID +
					","+DBNames.ATT_RECOURSE_DATA +
					","+DBNames.ATT_RECOURSE_REASON +
					","+ DBNames.ATT_RECOURSE_VALUTATION +
					") VALUES (?,?,?,?)"; 

			//setting pstmt's parameters
			pstmt.setInt(1, pRecourse.getId());
			pstmt.setDate(2, new java.sql.Date(pRecourse.getDate().getTimeInMillis()));
			pstmt.setString (3, pRecourse.getReason());
			pstmt.setBoolean(4, pRecourse.getValutation());

			pstmt.executeUpdate(query);
			con.commit();
		} 
		finally{
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}


	}

	public synchronized void update(RecourseBean pRecourse) throws SQLException
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		String query;

		try
		{
			con=DBConnectionPool.getConnection();

			// constructing query string

			query = "UPDATE " + DBNames.TABLE_RECOURSE +
					" SET "
					+ DBNames.ATT_RECOURSE_DATA + " = ?, " + 
					","+DBNames.ATT_RECOURSE_REASON + " = ?, " +
					","+ DBNames.ATT_RECOURSE_VALUTATION + " = ?, " +
					"WHERE " + DBNames.ATT_RECOURSE_ID + " = ?";

			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters


			pstmt.setDate(1, new Date(pRecourse.getDate().getTimeInMillis()));
			pstmt.setString (2, pRecourse.getReason());
			pstmt.setBoolean(3, pRecourse.getValutation());
			pstmt.setInt(4, pRecourse.getId());

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

	public void delete(RecourseBean recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		try {
			con=DBConnectionPool.getConnection();

			String query="delete from"+DBNames.TABLE_RECOURSE+
					" where "+DBNames.ATT_RECOURSE_ID+"=  "+recourse.getId();

			stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.commit();
		} 
		finally{
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}

	public RecourseBean accept(RecourseBean recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;

		try {
			con=DBConnectionPool.getConnection();

			String query="Update "+DBNames.TABLE_RECOURSE+"" +
					"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
					"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return recourse;
	}

	public RecourseBean refuse(RecourseBean recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;

		try {
			con=DBConnectionPool.getConnection();

			String query="Update "+DBNames.TABLE_RECOURSE+"" +
					"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
					"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";


			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return recourse;
	}

	public synchronized List<RecourseBean> search(RecourseBean pRecourse) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<RecourseBean> recourse = null;

		boolean andState = false;

		try {
			con = DBConnectionPool.getConnection();

			// constructing search query string
			query = "SELECT * FROM " + DBNames.TABLE_CLASSIFICATION + " WHERE";
			if (pRecourse.getId() > 0) {		
				query += " " + DBNames.ATT_RECOURSE_ID+ " = ?";
				andState = true;
			}

			if (pRecourse.getDate() != null) {
				query += useAnd(andState) + DBNames.ATT_RECOURSE_DATA+ " = ?";
				andState = true;
			}

			query += useAnd(andState) + DBNames.ATT_RECOURSE_REASON+ " = ?";
			andState = true;

			query += useAnd(andState) + DBNames.ATT_RECOURSE_VALUTATION+ " = ?";
			andState = true;

			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pRecourse.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pRecourse.getId());
				i++;
			}

			if (pRecourse.getDate() != null) {
				pstmt.setDate(i, new java.sql.Date(pRecourse.getDate().getTimeInMillis()));
				i++;
			}

			// executing select query
			rs = pstmt.executeQuery();
			con.commit();

			// constructing payment list
			recourse = new ArrayList<RecourseBean>();
			while (rs.next()) 
			{
				RecourseBean p = new RecourseBean();
				p.setId(rs.getInt(DBNames.ATT_RECOURSE_ID));

				//getting Date from ResultSet and converting it to GregorianCalendar
				GregorianCalendar date = new GregorianCalendar();
				date.setTime(rs.getDate(DBNames.ATT_RECOURSE_DATA));
				p.setDate(date);
				
				p.setReason(rs.getString(DBNames.ATT_RECOURSE_VALUTATION));
				p.setValutation(rs.getBoolean(DBNames.ATT_RECOURSE_VALUTATION));

				recourse.add(p);
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

		return recourse;
	}

	private String useAnd(boolean pEnableAnd) {
		return pEnableAnd ? " AND " : " ";
	}




}
