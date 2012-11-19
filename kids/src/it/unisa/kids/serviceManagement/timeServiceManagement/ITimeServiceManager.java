package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.common.IManager;
import it.unisa.kids.common.bean.TimeServiceBean;

import java.sql.SQLException;
import java.util.List;

public interface ITimeServiceManager extends IManager {

	public void insert(TimeServiceBean pTimeServ) throws SQLException;
	public void update(TimeServiceBean pTimeServ) throws SQLException;
	public void delete(TimeServiceBean pTimeServ) throws SQLException;
	
	public List<TimeServiceBean> search(TimeServiceBean pTimeServ) throws SQLException;
	public List<TimeServiceBean> getTimeServiceList() throws SQLException;
	public List<TimeServiceBean> getTimeServiceList(String pTimeServType) throws SQLException;
	
	public void insert(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public void update(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public void delete(TimeServiceRequestBean pTimeServReq) throws SQLException;
	
	public List<TimeServiceRequestBean> search(TimeServiceRequestBean pTimeServReq) throws SQLException;
	public List<TimeServiceRequestBean> getTimeServRequestList() throws SQLException;
}
