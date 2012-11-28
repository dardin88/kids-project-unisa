/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common.facade;

import it.unisa.kids.communicationManagement.newsManagement.News;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author navi
 */
public class CommunicationFacade implements ICommunicationFacade {

   
    @Override
    public void insert(News pNews) throws SQLException {
        
    }

    @Override
    public void update(News pNews) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<News> search(News pNews) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
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
