package it.unisa.kids.serviceManagement.tests.paymentManagement;

import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import junit.framework.TestCase;

public class PaymentBeanTest extends TestCase {
    private PaymentBean payment;
    
    public PaymentBeanTest(){}
    
    @Override
    public void setUp(){
        this.payment=new PaymentBean();
        this.payment.setAmount(0.0);
        this.payment.setPaid(true);
        this.payment.setPaidUsable(true);
        this.payment.setDiscount(0.0);
        this.payment.setDiscountDescription("TestDescription");
        this.payment.setOriginAccount("Account");
        this.payment.setPaid(true);
        this.payment.setPayee("Payee");
        this.payment.setPaymentDescription("Description");
        this.payment.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.payment.setAmount(0.0);
        this.payment.setPaid(true);
        this.payment.setPaidUsable(true);
        this.payment.setDiscount(0.0);
        this.payment.setDiscountDescription("TestDescription");
        this.payment.setOriginAccount("Account");
        this.payment.setPaid(true);
        this.payment.setPayee("Payee");
        this.payment.setPaymentDescription("Description");
        this.payment.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.payment.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.payment.getId());
    }
    
    private void testGetAmount(double pOracle){
        assertEquals(pOracle, this.payment.getAmount());
    }
    
    public void testGetAmount(){
        assertEquals(0.0, this.payment.getAmount());
    }
    
    private void testGetDiscount(double pOracle){
        assertEquals(pOracle, this.payment.getDiscount());
    }
    
    public void testGetDiscount(){
        assertEquals(0.0, this.payment.getDiscount());
    }
    
    public void testGetCharge(){
        assertEquals(true, this.payment.isPaid());
    }
    
    private void testGetCharge(boolean pOracle){
        assertEquals(pOracle, this.payment.isPaid());
    }
    
    public void testGetChargeUsable(){
        assertEquals(true, this.payment.isPaid());
    }
    
    private void testGetChargeUsable(boolean pOracle){
        assertEquals(pOracle, this.payment.isPaidUsable());
    }
    
    public void testGetPaymentDescription(){
        assertEquals("Description", this.payment.getPaymentDescription());
    }
    
    private void testGetPaymentDescription(String pOracle){
        assertEquals(pOracle, this.payment.getPaymentDescription());
    }
    
    public void testSetId(){
        this.payment.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetAmount(){
        this.payment.setAmount(1.0);
        this.testGetAmount(1.0);
    }
    
    public void testSetCharge(){
        this.payment.setPaid(false);
        this.testGetCharge(false);
    }
    
    public void testSetChargeUsable(){
        this.payment.setPaidUsable(false);
        this.testGetChargeUsable(false);
    }
    
    public void testSetDescription(){
        this.payment.setPaymentDescription("New");
        this.testGetPaymentDescription("New");
    }
}