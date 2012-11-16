package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentManager extends IManager {

	public void insert(PaymentBean pPayment) throws SQLException;
	public void update(PaymentBean pPayment) throws SQLException;
	public void delete(PaymentBean pPayment) throws SQLException;
	
	public void insert(RefundBean pRefund) throws SQLException;
	public void update(RefundBean pRefund) throws SQLException;
	public void delete(RefundBean pRefund) throws SQLException;
	
	public List<PaymentBean> search(PaymentBean pPayment) throws SQLException;
	public List<PaymentBean> getPaymentList() throws SQLException;
	
	public List<RefundBean> search(RefundBean pRefund) throws SQLException;
	public List<RefundBean> getRefundList() throws SQLException;
}
