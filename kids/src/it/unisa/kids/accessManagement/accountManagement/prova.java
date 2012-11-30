/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gianmarco
 */
public class prova {
    public static void main(String args[]) throws SQLException{
    Account account=new Account();
    List<Account> list;
    account.setNameUser("Gianmarco");
    account.setSurnameUser("DelPozzo");
    IAccountManager accountManager =(IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
    
    if((list=accountManager.search(account))!=null){
    System.out.println("eliminato con successo");
    System.out.println(""+list.get(0).getNameUser());
    }
    else{
    System.out.println("non inserito");
    }
    
    }
}
