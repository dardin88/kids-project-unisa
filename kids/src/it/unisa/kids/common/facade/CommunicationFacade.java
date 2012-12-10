/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common.facade;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.communicationManagement.newsManagement.INewsManager;
import it.unisa.kids.communicationManagement.newsManagement.News;
import it.unisa.kids.serviceManagement.timeServiceManagement.ITimeServiceManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author navi
 */
public class CommunicationFacade implements ICommunicationFacade {

   
    @Override
    public void insert(News pNews) throws SQLException {
        RefinedAbstractManager refinedAbstractNewsManager = RefinedAbstractManager.getInstance();
        INewsManager newsManager = (INewsManager) refinedAbstractNewsManager.getManagerImplementor(DBNames.TABLE_NEWS);
        newsManager.insert(pNews);
    }

    @Override
    public void update(News pNews) throws SQLException {
        RefinedAbstractManager refinedAbstractNewsManager = RefinedAbstractManager.getInstance();
        INewsManager newsManager = (INewsManager) refinedAbstractNewsManager.getManagerImplementor(DBNames.TABLE_NEWS);
         newsManager.update(pNews,false);
    }

    @Override
    public List<News> search(News pNews) throws SQLException {
        RefinedAbstractManager refinedAbstractNewsManager = RefinedAbstractManager.getInstance();
        INewsManager newsManager = (INewsManager) refinedAbstractNewsManager.getManagerImplementor(DBNames.TABLE_NEWS);
        return newsManager.search(pNews.getType());
    }

    @Override
    public int getNumberOfAbsences(int pChildId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(News pNews) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
