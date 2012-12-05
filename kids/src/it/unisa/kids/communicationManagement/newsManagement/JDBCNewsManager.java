package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class JDBCNewsManager implements INewsManager
{
	private static INewsManager manager;
	/**
	 * the constructor empty
	 */
	private JDBCNewsManager()
	{
	}

	/**
	 * this method implements the design pattern "singleton"
	 * @return manager
	 */
	public static INewsManager getInstance()
	{
		if(manager==null)
			return (manager=new JDBCNewsManager());
		else
			return manager;
	}

	/**
	 * this method insert the news in the database.
	 * @param News pNews
	 * 
	 */

	public void insert(News pNews) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement stmt=null;
		String query1;
		try
		{
			connection=DBConnectionPool.getConnection();		
			query1="insert into "+DBNames.TABLE_NEWS+"(" +
					DBNames.ATT_NEWS_ID+","+
                                        DBNames.ATT_NEWS_TITLE+","+
					DBNames.ATT_NEWS_DESCRIPTION+","+
					DBNames.ATT_NEWS_TYPE+","+
					DBNames.ATT_NEWS_DATE+","+
					DBNames.ATT_NEWS_TIME+","+
					DBNames.ATT_NEWS_ATTACHED+","+
					DBNames.ATT_NEWS_DELEGATEACCOUNT+
					") values (?,?,?,?,?,?,?,?)";			
			
			stmt=connection.prepareStatement(query1);
			stmt.setInt(1,pNews.getId());                       
                        stmt.setString(2, pNews.getTitle());
			stmt.setString(3, pNews.getDescription());
			stmt.setString(4, pNews.getType());
			stmt.setDate(5, new Date(pNews.getDate().getTimeInMillis()));
			stmt.setTime(6, pNews.getTime());
			stmt.setString(7, pNews.getAttached());
			stmt.setInt(8, pNews.getDelegate());
                      
			
			stmt.executeUpdate();
			connection.commit();
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}

	}

	/**
	 * this method show all the news presents in the database
	 * @return ArrayListNews<News> listNews
	 */

	public ArrayList<News> show() throws SQLException 
	{
		Connection connection=null;
		Statement stmt=null;
		ResultSet rsNews=null;
		String query;
		ArrayList<News> listNews=new ArrayList<News>();
		try
		{
			connection=DBConnectionPool.getConnection();
			query="select * from "+DBNames.TABLE_NEWS;
			stmt=connection.createStatement();
			rsNews=stmt.executeQuery(query);
			while(rsNews.next())
			{
				int id=rsNews.getInt(DBNames.ATT_NEWS_ID);
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				
                                GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				 //data.set(Calendar.MONTH, (data.get(Calendar.MONTH))+1);
				Time time=rsNews.getTime(DBNames.ATT_NEWS_TIME);
				//Date ora=new Date(time.getTime());
				String attached=rsNews.getString(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				News news=new News();
                                news.setId(id);
				news.setTitle(title);
				news.setDescription(description);
				news.setType(type);
				news.setDate(data);
				news.setTime(time);
				news.setAttached(attached);
				news.setDelegate(idDelegate);
				listNews.add(news);
			}
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return listNews;
	}

	/**
	 * this method delete news by the database
	 * @param News pNews
	 */

	public void delete(News pNews) throws SQLException 
	{
		Connection connection=null;
		Statement stmt=null;
		String query;
		try
		{
			connection=DBConnectionPool.getConnection();
			query="delete from "+DBNames.TABLE_NEWS+" where "+DBNames.ATT_NEWS_ID+"='"+pNews.getId()+"'";
			stmt=connection.createStatement();
			stmt.executeUpdate(query);
			connection.commit();
		
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
	}

	
	/**
	 * this method modify a news. make the news of the database
	 * and delete it, reinsert the news across the parameter
	 * @param News pNews
	 */
	public void update(News pNews) throws SQLException 
	{
		Connection connection=null;
		Statement stmt=null;
		String query;
		try
		{
			connection=DBConnectionPool.getConnection();
			query="update "+DBNames.TABLE_NEWS+" set "+DBNames.ATT_NEWS_DATE+"='"+new Date(pNews.getDate().getTimeInMillis())+"',"+
					DBNames.ATT_NEWS_ATTACHED+"='"+pNews.getAttached()+"',"+
					DBNames.ATT_NEWS_DELEGATEACCOUNT+"="+pNews.getDelegate()+","+
					DBNames.ATT_NEWS_DESCRIPTION+"='"+pNews.getDescription()+"',"+
					DBNames.ATT_NEWS_TIME+"='"+pNews.getTime()+"',"+DBNames.ATT_NEWS_TITLE+"='"+pNews.getTitle()+"',"+
					DBNames.ATT_NEWS_TYPE+"='"+pNews.getType()+"' where "+DBNames.ATT_NEWS_ID+"="+pNews.getId();
			stmt=connection.createStatement();
			stmt.executeUpdate(query);
			connection.commit();
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
		
	}


	/**
	 * this method researchs news with keyword in the title of the news
	 * @param String word
	 * @return ArrayList<News> listSearchNews
	 */
	public ArrayList<News> search(String word) throws SQLException 
	{
		ArrayList<News> listNews=new ArrayList<News>();
		Connection connection=null;
		Statement stmt=null;
		ResultSet rsNews=null;
		try
		{
	                   connection = DBConnectionPool.getConnection();
                    String query = "select * from " + DBNames.TABLE_NEWS + " WHERE "
                            + DBNames.ATT_NEWS_DESCRIPTION + " like '%" + word + "%'"
                            + " or " + DBNames.ATT_NEWS_DATE + " like '%" + word + "%'"
                            + " or " + DBNames.ATT_NEWS_TITLE + " like '%" + word + "%'"
                            + " or " + DBNames.ATT_NEWS_TYPE + " like '%" + word + "%'"
                            + " or " + DBNames.ATT_NEWS_TIME + " like '%" + word + "%'"
                            + " or " + DBNames.ATT_NEWS_ATTACHED + " like '%" + word + "%'";
                    stmt = connection.createStatement();
			rsNews=stmt.executeQuery(query);
			while(rsNews.next())
			{
                                int id=rsNews.getInt(DBNames.ATT_NEWS_ID);
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);				 
                                Time time=rsNews.getTime(DBNames.ATT_NEWS_TIME);
				//Date ora=new Date(time.getTime());
				String attached=rsNews.getString(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				News news=new News();
                                news.setId(id);
				news.setTitle(title);
				news.setDescription(description);
				news.setType(type);
				news.setDate(data);
				news.setTime(time);
				news.setAttached(attached);
				news.setDelegate(idDelegate);
				listNews.add(news);
			}
		}
		finally
		{
			stmt.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return listNews;
	}



}
