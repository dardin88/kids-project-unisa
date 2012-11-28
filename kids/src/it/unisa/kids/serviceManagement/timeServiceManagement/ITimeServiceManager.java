package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.common.IManager;
import it.unisa.kids.communicationManagement.newsManagement.News;

import java.sql.SQLException;
import java.util.List;

public interface ITimeServiceManager extends IManager {

	public void insert(News pNews) throws SQLException;
	public void update(News pNews) throws SQLException;
	public void delete(News pNews) throws SQLException;
	
	public List<News> search(News pNews) throws SQLException;
	public List<News> getTimeServiceList() throws SQLException;
	public List<News> getTimeServiceList(String pTimeServType) throws SQLException;
	
	public void insert(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public void update(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public void delete(TimeServiceRequestBean pTimeServReq) throws SQLException;
	
	public List<TimeServiceRequestBean> search(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public List<TimeServiceRequestBean> getTimeServRequestList() throws SQLException;
}
