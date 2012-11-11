package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.exception.MandatoryFieldException;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentManager {

	public void insert(Payment pPayment) throws SQLException, MandatoryFieldException;
	public void update(Payment pPayment) throws SQLException, MandatoryFieldException;
	public void delete(Payment pPayment) throws SQLException, MandatoryFieldException;
	
	public List<Payment> getPaymentsByObject(Payment pObjectPayment) throws SQLException, MandatoryFieldException;
	public List<Payment> getPaymentList() throws SQLException, MandatoryFieldException;
}
