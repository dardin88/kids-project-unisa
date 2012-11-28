package it.unisa.kids.common.facade;

import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.accessManagement.accountManagement.JDBCAccountManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.kids.accessManagement.accountManagement.Parent;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccessFacade implements IAccessFacade
{

	// C'è bisogno che venga implementato il bridge per Classe
	public int getParentId(int childId)
	{
		JDBCRegistrationChildManager c;
		RegistrationChild child = new RegistrationChild();
		child.setIdParent(childId);
		
		return child.getIdParent();
	}
        
        // Questo metodo deve essere trasferito nel ClassManager non appena quest'ultimo è stato corretto
	public int getNumberOfChildren(int parentId) throws SQLException 
	{
            int num=0;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs=null;
            String query="Select count(*)  as 'NumChildren' from"+DBNames.TABLE_REGISTRATION+"where"+DBNames.ATT_REGISTRATION_ACCOUNT_PARENT+"='"+parentId;
            try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  rs=stmt.executeQuery(query);
                          while(rs.next())
                          {
                              num=rs.getInt("NumChildren");
                          }  
                          return num;
		  }
            finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }	
            
        }
        
	public boolean isSick(RegistrationChild c) {
		return c.isSick();
	}

    
    public Account insert(Account pAccount) {
       RefinedAbstractManager refinedAbstractAccountManager = new RefinedAbstractManager();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.insert(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pAccount;
    }

    
    public Account update(Account pAccount) {
         RefinedAbstractManager refinedAbstractAccountManager = new RefinedAbstractManager();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.update(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pAccount;
    }

    
    public List<Account> search(Account pAccount) {
         RefinedAbstractManager refinedAbstractAccountManager = new RefinedAbstractManager();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        List<Account> listAccount=null;
        try {
            listAccount=accountManager.search(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAccount;
    }

   
    public Account delete(Account pAccount) {
       RefinedAbstractManager refinedAbstractAccountManager = new RefinedAbstractManager();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.delete(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pAccount;
    }


    public Account getParentById(int pParentId) throws SQLException {
        RefinedAbstractManager refinedAbstractAccountManager = new RefinedAbstractManager();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        List<Account> listAccount;
        Account account= new Account();
        account.setId(pParentId);
        listAccount= accountManager.search(account);
        if(listAccount!=null){
            return listAccount.get(0);
        }
        else{
            return null;
        }
    }

    

   


}
