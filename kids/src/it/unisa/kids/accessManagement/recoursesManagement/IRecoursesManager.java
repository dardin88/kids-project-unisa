package it.unisa.kids.accessManagement.recoursesManagement;

import java.sql.SQLException;
import java.util.List;



public interface IRecoursesManager 
{
	void insert(Recourse pRecourse) throws SQLException;
	void modify(Recourse pRecourse) throws SQLException;
	void delete(Recourse pRecourse) throws SQLException;
	Recourse accept(Recourse pRecourse) throws SQLException;
	Recourse refuse(Recourse pRecourse) throws SQLException;
	List<Recourse> search(Recourse pRecourse) throws SQLException;
	
}
