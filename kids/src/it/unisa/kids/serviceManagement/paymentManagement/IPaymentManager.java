package it.unisa.kids.serviceManagement.paymentManagement;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentManager {

	public void insert(Payment pPayment) throws SQLException;
	public void update(Payment pPayment) throws SQLException;
	public void delete(Payment pPayment) throws SQLException;
	
	public List<Payment> getPaymentsByObject(Payment pObjectPayment) throws SQLException;
	public List<Payment> getPaymentList() throws SQLException;
}
