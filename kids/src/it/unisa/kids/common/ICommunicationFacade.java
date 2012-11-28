/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common;

import it.unisa.kids.communicationManagement.newsManagement.News;
import java.sql.SQLException;

/**
 *
 * @author navi
 */
public interface ICommunicationFacade {

    public void insert(News pNews) throws SQLException;

    public void update(News pNews) throws SQLException;

    public void search(News pNews) throws SQLException;
    
    public int getNumberOfAbsences(int pChildId) throws SQLException;
}