package it.unisa.kids.communicationManagement.newsManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface INewsManager
{
	abstract void insertNews(News pNews) throws SQLException;
	abstract ArrayList<News> showNews()throws SQLException ;
	abstract void deleteNews(News pNews) throws SQLException;
	abstract void modifyNews(News pNews)throws SQLException;
	abstract ArrayList<News> searchNews(String word)throws SQLException;

}
