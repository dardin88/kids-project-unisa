package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IClassificationManager extends IManager {
	public void insert(Classification pClassification) throws SQLException;
	public void modify(Classification pClassification) throws SQLException;
	public void delete(Classification pClassification) throws SQLException;
	
	public List<Classification> search(Classification pClassification) throws SQLException;
	public List<Classification> getClassificationList() throws SQLException;

}
