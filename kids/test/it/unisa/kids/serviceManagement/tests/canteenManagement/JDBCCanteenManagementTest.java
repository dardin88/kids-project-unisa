package it.unisa.kids.serviceManagement.tests.canteenManagement;

import it.unisa.kids.serviceManagement.canteenManagement.JDBCCanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import it.unisa.kids.serviceManagement.tests.paymentManagement.JDBCPaymentManagerTest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCCanteenManagementTest extends TestCase {
    private MenuBean bean;
    private JDBCCanteenManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new MenuBean();
        this.bean.setFirst("First");
        this.bean.setFruit("Fruit");
        this.bean.setSecond("Second");
        this.bean.setSideDish("Side");
        this.bean.setType("Type");
        this.bean.setId(1);
        this.managerTest=JDBCCanteenManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<MenuBean> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPaymentManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCCanteenManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        MenuBean test = new MenuBean();
        test.setFirst("First");
        test.setFruit("Fruit");
        test.setSecond("Second");
        test.setSideDish("Side");
        test.setType("Type");
        test.setId(2);
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", "First", this.managerTest.search(test).get(0).getFirst());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPaymentManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPaymentManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        MenuBean toSearch;
        
        try {
            List<MenuBean> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setFirst("NewFirst");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewFirst", this.managerTest.search(toSearch).get(0).getFirst());
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(this.bean);
            
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.search(this.bean).size());
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }
}