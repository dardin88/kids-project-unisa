/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common.facade;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marco
 */
public interface IAccountFacade {
    public List<Account> search(Account pAccount) throws SQLException;
}
