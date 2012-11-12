package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.exception.MandatoryFieldException;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCPaymentManager implements IPaymentManager {
	
	private static final int DESCRIPTION_MAXLENGTH = 200;
	private static final int ORIGIN_ACCOUNT_MAXLENGTH = 30;
	private static final int PAYEE_MAXLENGTH = 30;
	private static final int DISCOUNT_MAXLENGTH = 10;
	private static final int DISCDESCRIPTION_MAXLENGTH = 200;
	
	private static JDBCPaymentManager manager;
	
	private JDBCPaymentManager() {
		
	}
	
	public static synchronized JDBCPaymentManager getInstance() {
		if (manager == null) {
			manager = new JDBCPaymentManager();
		}
		return manager;
	}
	
	public synchronized void insert(Payment pPayment) throws SQLException, MandatoryFieldException {
		Connection con = null;
		Statement stmt = null;
		String query1;
		String query2;
		
		try {
			con = DBConnectionPool.getConnection();
			
			query1 = "INSERT INTO " + DBNames.TABLE_PAYMENT + " ("
					+ DBNames.ATT_PAYMENT_ID + ", "
					+ DBNames.ATT_PAYMENT_EXPDATE + ", "
					+ DBNames.ATT_PAYMENT_AMOUNT + ", "
					+ DBNames.ATT_PAYMENT_AMOUNTDUE + ", "
					+ DBNames.ATT_PAYMENT_PAID + ", "
					+ DBNames.ATT_PAYMENT_ORIGINACCOUNT + ", "
					+ DBNames.ATT_PAYMENT_PAYEE + ", "
					+ DBNames.ATT_PAYMENT_PARENTID + ", "
					+ DBNames.ATT_PAYMENT_CHARGE;
			query2 = "VALUES ("
					+ pPayment.getId() + ", "
					+ pPayment.getExpDate() + ", "
					+ pPayment.getAmount() + ", "
					+ pPayment.getAmountDue() + ", "
					+ pPayment.isPaid() + ", "
					+ pPayment.getOriginAccount() + ", "
					+ pPayment.getPayee() + ", "
					+ pPayment.getParentId() + ", "
					+ pPayment.isCharge();
			
			if (pPayment.getPaymentDescription() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DESCRIPTION;
				query2 += ", " + pPayment.getPaymentDescription();
			}
			if (pPayment.getDiscount() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DISCOUNT;
				query2 += ", " + pPayment.getDiscount();
			}
			if (pPayment.getDiscountDescription() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DISCDESCRIPTION;
				query2 += ", " + pPayment.getDiscountDescription();
			}
			query1 += ") ";
			query2  += ")";
			
			stmt = con.createStatement();
			stmt.executeUpdate(query1 + query2);
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized void update(Payment pPayment) {
		
	}
	
	public synchronized void delete(Payment pPayment) {
		
	}
	
	public synchronized List<Payment> getPaymentsByObject(Payment pObjectPayment) {
		return null;
	}
	
	public synchronized List<Payment> getPaymentList() {
		return null;
	}
}
