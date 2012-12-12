package Stub;

import it.unisa.kids.serviceManagement.paymentManagement.IPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import it.unisa.kids.serviceManagement.paymentManagement.RefundBean;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PaymentManagerStub implements IPaymentManager {
    private PaymentBean payment;
    private RefundBean refund;
    
    @Override
    public void insert(PaymentBean pPayment) throws SQLException {
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
    public void update(PaymentBean pPayment) throws SQLException {
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
    public void delete(PaymentBean pPayment) throws SQLException {
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
    public List<PaymentBean> search(PaymentBean pPayment) throws SQLException {
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
        
        LinkedList<PaymentBean> list=new LinkedList<PaymentBean>();
        list.add(payment);
        
        return list;
    }

    @Override
    public List<PaymentBean> getPaymentList() throws SQLException {
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
        
        LinkedList<PaymentBean> list=new LinkedList<PaymentBean>();
        list.add(payment);
        
        return list;
    }

    @Override
    public void insert(RefundBean pRefund) throws SQLException {
        this.refund=new RefundBean();
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
    }

    @Override
    public void update(RefundBean pRefund) throws SQLException {
        this.refund=new RefundBean();
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
    }

    @Override
    public void delete(RefundBean pRefund) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RefundBean> search(RefundBean pRefund) throws SQLException {
        this.refund=new RefundBean();
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
        
        LinkedList<RefundBean> list=new LinkedList<RefundBean>();
        list.add(refund);
        
        return list;
    }

    @Override
    public List<RefundBean> getRefundList() throws SQLException {
        this.refund=new RefundBean();
        this.refund.setAmount(0.0); 
        this.refund.setId(1);
        this.refund.setDescription("Description");
        this.refund.setParentId(1);
        
        LinkedList<RefundBean> list=new LinkedList<RefundBean>();
        list.add(refund);
        
        return list;
    }   
}