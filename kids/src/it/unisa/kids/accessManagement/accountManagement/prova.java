/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.sql.SQLException;

/**
 *
 * @author Gianmarco
 */
public class prova {
    public static void main(String args[]) throws SQLException{
    Account account=new Account();
    account.setId(5);
    IAccountManager accountManager =(IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
    
    if(accountManager.insert(account)!=null){
    System.out.println("inserito con successo");
    }
    else{
    System.out.println("non inserito");
    }
    
    }
}
