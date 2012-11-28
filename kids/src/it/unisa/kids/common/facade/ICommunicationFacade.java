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
public interface ICommunicationFacade {

    public void insert(News pNews) throws SQLException;

    public void update(News pNews) throws SQLException;

    public void delete(News pNews) throws SQLException;

    public List<News> search(News pNews) throws SQLException;

    public int getNumberOfAbsences(int pChildId) throws SQLException;
}
