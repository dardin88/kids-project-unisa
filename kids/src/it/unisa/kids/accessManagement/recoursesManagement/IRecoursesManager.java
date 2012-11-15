package it.unisa.kids.accessManagement.recoursesManagement;

import java.sql.SQLException;
import java.util.List;



public interface IRecoursesManager 
{
	void insert (RecourseBean pRecourse) throws SQLException;
	void update(RecourseBean pRecourse) throws SQLException;
	void delete(RecourseBean pRecourse) throws SQLException;
	RecourseBean accept(RecourseBean pRecourse) throws SQLException;
	RecourseBean refuse(RecourseBean pRecourse) throws SQLException;
	List<RecourseBean> search(RecourseBean pRecourse) throws SQLException;
	
}
