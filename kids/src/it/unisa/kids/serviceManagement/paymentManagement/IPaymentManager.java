package it.unisa.kids.serviceManagement.paymentManagement;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentManager {

	public void insert(PaymentBean pPayment) throws SQLException;
	public void update(PaymentBean pPayment) throws SQLException;
	public void delete(PaymentBean pPayment) throws SQLException;
	
	public List<PaymentBean> search(PaymentBean pPayment) throws SQLException;
	public List<PaymentBean> getPaymentList() throws SQLException;
}
