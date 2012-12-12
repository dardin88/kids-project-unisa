package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.common.IManager;
import it.unisa.kids.communicationManagement.newsManagement.News;

import java.sql.SQLException;
import java.util.List;

public interface ITimeServiceManager extends IManager {
    
    public void insert(ModifyTimeServiceRequest pModifyTimeServiceRequest) throws SQLException;
    
    public List<ModifyTimeServiceRequest> search(ModifyTimeServiceRequest pModifyTimeServiceRequest) throws SQLException;
    public void insert(News pTimeServ) throws SQLException;

    public void update(News pTimeServ) throws SQLException;

    public void delete(News pTimeServ) throws SQLException;

    public List<News> search(News pTimeServ) throws SQLException;

    public List<News> getTimeServiceList() throws SQLException;

    public List<News> getTimeServiceList(String pTimeServType) throws SQLException;

    public void insert(TimeServiceRequest pTimeServReq) throws SQLException;

    public void update(TimeServiceRequest pTimeServReq) throws SQLException;

    public void delete(TimeServiceRequest pTimeServReq) throws SQLException;

    public List<TimeServiceRequest> search(TimeServiceRequest pTimeServReq) throws SQLException;

    public List<TimeServiceRequest> getTimeServRequestList() throws SQLException;
}
