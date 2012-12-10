package it.unisa.kids.common.facade;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.classManagement.IClassManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccessFacade implements IAccessFacade {

    // C'è bisogno che venga implementato il bridge per Classe
    public int getParentId(int childId) {
        JDBCRegistrationChildManager registrationChildManager = JDBCRegistrationChildManager.getInstance();
        RegistrationChild child = new RegistrationChild();
        child.setParentId(childId);

        try {
            List<RegistrationChild> resultList = registrationChildManager.search(child);
            if (resultList.size() == 1) {
                child = resultList.get(0);
            }
        } catch (SQLException ex) {
        }
        return child.getParentId();
    }

    // Questo metodo deve essere trasferito nel ClassManager non appena quest'ultimo è stato corretto
    public int getNumberOfChildren(int parentId) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        int num = 0;

        try {
            con = DBConnectionPool.getConnection();
            // constructing query string
            query = "SELECT count(*) as NumberOfChild"
                    + "FROM" + DBNames.TABLE_REGISTRATIONCHILD + ","
                    + "WHERE" + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, parentId);

            rs = stmt.executeQuery();
            con.commit();

            while (rs.next()) {
                num = rs.getInt("NumberOfChild");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return num;
    }

    public boolean isSick(RegistrationChild c) {
        boolean toReturn;
        if (c.getSickness() != null) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    public Account insert(Account pAccount) {
        RefinedAbstractManager refinedAbstractAccountManager = RefinedAbstractManager.getInstance();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.insert(pAccount);
        } catch (SQLException ex) {
            return null;
        }
        return pAccount;
    }

    public Account update(Account pAccount) {
        RefinedAbstractManager refinedAbstractAccountManager = RefinedAbstractManager.getInstance();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.update(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pAccount;
    }

    public List<Account> search(Account pAccount) {
        RefinedAbstractManager refinedAbstractAccountManager = RefinedAbstractManager.getInstance();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        List<Account> listAccount = null;
        try {
            listAccount = accountManager.search(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAccount;
    }

    public Account delete(Account pAccount) {
        RefinedAbstractManager refinedAbstractAccountManager = RefinedAbstractManager.getInstance();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        try {
            accountManager.delete(pAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pAccount;
    }

    public Account getParentById(int pParentId) throws SQLException {
        RefinedAbstractManager refinedAbstractAccountManager = RefinedAbstractManager.getInstance();
        IAccountManager accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
        List<Account> listAccount;
        Account account = new Account();
        account.setId(pParentId);
        listAccount = accountManager.search(account);
        if (listAccount != null) {
            return listAccount.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ClassBean> getClasses() throws SQLException {
        IClassManager classManager = (IClassManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_CLASS);
        
        return classManager.getAll();
    }

    @Override
    public List<RegistrationChild> search(RegistrationChild pRegistrationChild) throws SQLException {
        IRegistrationChildManager regChildManager = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
        
        return regChildManager.search(pRegistrationChild);
    }
}
