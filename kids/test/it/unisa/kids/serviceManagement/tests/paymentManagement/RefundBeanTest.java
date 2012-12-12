package it.unisa.kids.serviceManagement.tests.paymentManagement;

import it.unisa.kids.serviceManagement.paymentManagement.RefundBean;
import junit.framework.TestCase;

public class RefundBeanTest extends TestCase {
    private RefundBean refund;
    
    public RefundBeanTest(){}
    
    @Override
    public void setUp(){
        this.refund=new RefundBean();
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
    }
    
    @Override
    public void tearDown(){
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.refund.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.refund.getId());
    }
    
    private void testGetAmount(double pOracle){
        assertEquals(pOracle, this.refund.getAmount());
    }
    
    public void testGetAmount(){
        assertEquals(0.0, this.refund.getAmount());
    }
    
    public void testGetPaymentDescription(){
        assertEquals("Description", this.refund.getDescription());
    }
    
    private void testGetPaymentDescription(String pOracle){
        assertEquals(pOracle, this.refund.getDescription());
    }
    
    private void testGetParentId(int pOracle){
        assertEquals(pOracle, this.refund.getParentId());
    }
    
    public void testGetParentId(){
        assertEquals(1, this.refund.getParentId());
    }
    
    public void testSetId(){
        this.refund.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetAmount(){
        this.refund.setAmount(1.0);
        this.testGetAmount(1.0);
    }   
    
    public void testSetDescription(){
        this.refund.setDescription("New");
        this.testGetPaymentDescription("New");
    }
    
    public void testSetParentId(){
        this.refund.setParentId(2);
        this.testGetParentId(2);
    }
}