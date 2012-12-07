package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface INewsManager extends IManager
{
	abstract void insert(News pNews) throws SQLException;
	abstract ArrayList<News> show()throws SQLException ;
	abstract void delete(News pNews) throws SQLException;
	abstract void update(News pNews,boolean flag)throws SQLException;
	abstract ArrayList<News> search(String word)throws SQLException;

}
