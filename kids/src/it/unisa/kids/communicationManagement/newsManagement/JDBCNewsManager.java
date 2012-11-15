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
import java.util.Calendar;
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

	public void insertNews(News pNews) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement stmt=null;
		String query1;
		try
		{
			connection=DBConnectionPool.getConnection();		
			query1="insert into "+DBNames.TABLE_NEWS+"(" +
					DBNames.ATT_NEWS_TITLE+","+
					DBNames.ATT_NEWS_DESCRIPTION+","+
					DBNames.ATT_NEWS_TYPE+","+
					DBNames.ATT_NEWS_DATE+","+
					DBNames.ATT_NEWS_TIME+","+
					DBNames.ATT_NEWS_ATTACHED+","+
					DBNames.ATT_NEWS_DELEGATEACCOUNT+
					") values (?,?,?,?,?,?,?)";			
			
			stmt=connection.prepareStatement(query1);
			stmt.setString(1, pNews.getTitle());
			stmt.setString(2, pNews.getDescription());
			stmt.setString(3, pNews.getType());
			stmt.setDate(4, new Date(pNews.getDate().getTimeInMillis()));
			stmt.setTime(5, pNews.getTime());
			stmt.setObject(6, pNews.getAttached());
			stmt.setInt(7, pNews.getDelegate());
			
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

	public ArrayList<News> showNews() throws SQLException 
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
				
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				data.set(Calendar.MONTH, (data.get(Calendar.MONTH))+1);
				Time time=rsNews.getTime(DBNames.ATT_NEWS_TIME);
				//Date ora=new Date(time.getTime());
				Object attached=rsNews.getObject(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
			/*	Account delegate=new Account();
				delegate.setId(idDelegate);
				ManagerAccount manager=ManagerAccount.getInstance();
				ArrayList<Account> listAccount= manager.Search(delegate);
				delegate=listAccount.get(0);
			*/	News news=new News();
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

	public void deleteNews(News pNews) throws SQLException 
	{
		Connection connection=null;
		Statement stmt=null;
		String query;
		try
		{
			connection=DBConnectionPool.getConnection();
			query="delete from "+DBNames.TABLE_NEWS+" where "+DBNames.ATT_NEWS_DATE+"='"+pNews.getDate().get(Calendar.YEAR)+"-"+((pNews.getDate().get(Calendar.MONTH))+1)+"-"+pNews.getDate().get(Calendar.DAY_OF_MONTH)+"' && "+DBNames.ATT_NEWS_TIME+" = '"+pNews.getTime()+"'";
			System.out.println(query);
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
	public void modifyNews(News pNews) throws SQLException 
	{
		Connection connection=null;
		Statement stmt=null;
		String query;
		ResultSet rsNews=null;
		try
		{
			connection=DBConnectionPool.getConnection();
			query="select * from "+DBNames.TABLE_NEWS+" where "+DBNames.ATT_NEWS_DATE+"='"+pNews.getDate().get(Calendar.YEAR)+"-"+((pNews.getDate().get(Calendar.MONTH))+1)+"-"+pNews.getDate().get(Calendar.DAY_OF_MONTH)+"'&& "+DBNames.ATT_NEWS_TIME+" = '"+pNews.getTime()+"'";
			stmt=connection.createStatement();
			rsNews=stmt.executeQuery(query);
			while(rsNews.next())
			{	
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
			//	data.set(Calendar.MONTH, (data.get(Calendar.MONTH))+1);
				Time time=rsNews.getTime(DBNames.ATT_NEWS_TIME);
				//Date ora=new Date(time.getTime());
				Object attached=rsNews.getObject(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				News news=new News();
				news.setTitle(title);
				news.setDescription(description);
				news.setType(type);
				news.setDate(data);
				news.setTime(time);
				news.setAttached(attached);
				news.setDelegate(idDelegate);
				deleteNews(news);
				insertNews(pNews);
			}
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
	public ArrayList<News> searchNews(String word) throws SQLException 
	{
		ArrayList<News> listNews=new ArrayList<News>();
		Connection connection=null;
		Statement stmt=null;
		ResultSet rsNews=null;
		try
		{
			connection=DBConnectionPool.getConnection();
			String query="select * from "+DBNames.TABLE_NEWS+" WHERE " + DBNames.ATT_NEWS_DESCRIPTION + " like '_%" +word+ "%'";
			stmt=connection.createStatement();
			rsNews=stmt.executeQuery(query);
			while(rsNews.next())
			{
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				data.set(Calendar.MONTH, (data.get(Calendar.MONTH))+1);
				Time time=rsNews.getTime(DBNames.ATT_NEWS_TIME);
				//Date ora=new Date(time.getTime());
				Object attached=rsNews.getObject(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				News news=new News();
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
