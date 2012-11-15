package it.unisa.kids.accessManagement.classification;

import java.sql.SQLException;
import java.util.List;

public interface IClassificationManager 
{
	public void insert(ClassificationBean pClassification) throws SQLException;
	public void update(ClassificationBean pClassification) throws SQLException;
	public void delete(ClassificationBean pClassification) throws SQLException;
	
	public List<ClassificationBean> search(ClassificationBean pClassification) throws SQLException;
	public List<ClassificationBean> getClassificationList() throws SQLException;

}
