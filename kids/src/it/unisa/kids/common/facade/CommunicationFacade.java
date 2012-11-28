/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common.facade;

import it.unisa.kids.common.facade.ICommunicationFacade;
import it.unisa.kids.communicationManagement.newsManagement.News;
import java.sql.SQLException;

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
    public void search(News pNews) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNumberOfAbsences(int pChildId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
