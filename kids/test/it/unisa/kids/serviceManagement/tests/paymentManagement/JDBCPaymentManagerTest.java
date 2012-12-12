package it.unisa.kids.serviceManagement.tests.paymentManagement;

import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCPaymentManagerTest extends TestCase {
    private PaymentBean bean;
    private JDBCPaymentManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new PaymentBean();
        this.bean.setId(1);
        this.bean.setParentId(1);
        this.bean.setAmount(0.0);
        this.bean.setPaid(true);
        this.bean.setPaidUsable(true);
        this.bean.setDiscount(0.0);
        this.managerTest=JDBCPaymentManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<PaymentBean> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPaymentManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCPaymentManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        PaymentBean test=new PaymentBean();
        test.setId(1);
        test.setParentId(1);
        test.setAmount(0.0);
        test.setPaid(true);
        test.setPaidUsable(true);
        test.setDiscount(0.0);
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", 0.0, this.managerTest.search(test).get(0).getAmount());
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
        PaymentBean toSearch;
        
        try {
            List<PaymentBean> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setAmount(1.0);
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", 1.0, this.managerTest.search(toSearch).get(0).getAmount());
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