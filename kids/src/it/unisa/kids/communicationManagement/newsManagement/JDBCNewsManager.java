package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.accessManagement.Account;
import it.unisa.kids.accessManagement.ManagerAccount;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public void insertNews(News pNews) throws SQLException 
	{
		Connection connection = null;
		Statement stmt=null;
		String query1;
		String query2;
		try
		{
			connection=DBConnectionPool.getConnection();
			query1="insert into "+DBNames.TABLE_NEWS+"(" +
					DBNames.ATT_NEWS_TITLE+","+
					DBNames.ATT_NEWS_DESCRIPTION+","+
					DBNames.ATT_NEWS_TYPE+","+
					DBNames.ATT_NEWS_DATE+","+
					DBNames.ATT_NEWS_TIME+","+
					DBNames.ATT_NEWS_DATACREATION+","+
					DBNames.ATT_NEWS_ATTACHED+","+
					DBNames.ATT_NEWS_DELEGATEACCOUNT+")";
			query2=" values ('"+pNews.getTitle()+","+
					pNews.getDescription()+","+
					pNews.getType()+","+
					pNews.getDate()+","+
					pNews.getTime()+","+
					pNews.getDataCreation()+","+
					pNews.getAttached()+","+
					pNews.getDelegate()+"')";

			stmt=connection.createStatement();
			stmt.executeQuery(query1+query2);
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
				//non mi prendo la data di creazione
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				Date ore=rsNews.getDate(DBNames.ATT_NEWS_TIME);
				int hours=ore.getHours();
				int minutes=ore.getMinutes();
				int second=ore.getSeconds();
				GregorianCalendar ora=new GregorianCalendar(hours,minutes,second);
				Object attached=rsNews.getString(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				Account delegate=new Account();
				delegate.setId(idDelegate);
				ManagerAccount manager=ManagerAccount.getInstance();
				ArrayList<Account> listAccount= manager.Search(delegate);
				delegate=listAccount.get(0);
				News news=new News();
				news.setTitle(title);
				news.setDescription(description);
				news.setType(type);
				news.setDate(data);
				news.setTime(ora);
				news.setAttached(attached);
				news.setDelegate(delegate);
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
			//delete in base at the dataCreation
			query="delete from "+DBNames.TABLE_NEWS+" where "+DBNames.ATT_NEWS_DATE+"="+pNews.getDataCreation();
			stmt=connection.createStatement();
			stmt.executeQuery(query);
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
			//controllare uguaglianze date
			query="select * from "+DBNames.TABLE_NEWS+" where "+DBNames.ATT_NEWS_DATACREATION+"="+pNews.getDataCreation().getTimeInMillis();
			stmt=connection.createStatement();
			rsNews=stmt.executeQuery(query);
			while(rsNews.next())
			{	//non mi prendo la data di creazione
				String title=rsNews.getString(DBNames.ATT_NEWS_TITLE);
				String description=rsNews.getString(DBNames.ATT_NEWS_DESCRIPTION);
				String type=rsNews.getString(DBNames.ATT_NEWS_TYPE);
				Date date=rsNews.getDate(DBNames.ATT_NEWS_DATE);
				GregorianCalendar data=new GregorianCalendar();
				data.setTime(date);
				Date ore=rsNews.getDate(DBNames.ATT_NEWS_TIME);
				int hours=ore.getHours();
				int minutes=ore.getMinutes();
				int second=ore.getSeconds();
				GregorianCalendar ora=new GregorianCalendar(hours,minutes,second);
				Object attached=rsNews.getString(DBNames.ATT_NEWS_ATTACHED);
				int idDelegate=rsNews.getInt(DBNames.ATT_NEWS_DELEGATEACCOUNT);
				Account delegate=new Account();
				delegate.setId(idDelegate);
				ManagerAccount manager=ManagerAccount.getInstance();
				ArrayList<Account> listAccount= manager.Search(delegate);
				delegate=listAccount.get(0);
				News news=new News();
				news.setTitle(title);
				news.setDescription(description);
				news.setType(type);
				news.setDate(data);
				news.setTime(ora);
				news.setAttached(attached);
				news.setDelegate(delegate);
				//ps cancellando e inserendo la news vado a modificare la data di creazione?
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
		ArrayList<News> listSearchNews=new ArrayList<News>();
		ArrayList<News> listNews=showNews();
		for(News n:listNews)
		{
			if((n.getTitle()).indexOf(word)>0)
				listSearchNews.add(n);
		}
		return listSearchNews;
	}



}
