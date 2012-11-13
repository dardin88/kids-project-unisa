package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCPaymentManager implements IPaymentManager {
	
	// Singleton Design Pattern's implementation
	private static JDBCPaymentManager manager;
	
	private JDBCPaymentManager() {
		
	}
	
	public static synchronized JDBCPaymentManager getInstance() {
		if (manager == null)
			manager = new JDBCPaymentManager();
		return manager;
	}
	// end of Singleton Design Pattern's implementation
	
	public synchronized void insert(Payment pPayment) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query1;
		String query2;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
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
			query2 = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?";
			
			// constructing query string for optional parameters
			if (pPayment.getPaymentDescription() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DESCRIPTION;
				query2 += ", ?";
			}
			if (pPayment.getDiscount() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DISCOUNT;
				query2 += ", ?";
			}
			if (pPayment.getDiscountDescription() != null) {
				query1 += ", " + DBNames.ATT_PAYMENT_DISCDESCRIPTION;
				query2 += ", ?";
			}
			query1 += ") ";
			query2  += ")";
			
			pstmt = con.prepareStatement(query1 + query2);
			
			//setting pstmt's parameters
			pstmt.setInt(1, pPayment.getId());
			pstmt.setDate(2, new java.sql.Date(pPayment.getExpDate().getTimeInMillis()));
			pstmt.setDouble(3, pPayment.getAmount());
			pstmt.setDouble(4, pPayment.getAmountDue());
			pstmt.setBoolean(5, pPayment.isPaid());
			pstmt.setString(6, pPayment.getOriginAccount());
			pstmt.setString(7, pPayment.getPayee());
			pstmt.setInt(8, pPayment.getParentId());
			pstmt.setBoolean(9, pPayment.isCharge());
			
			//setting pstmt's optional parameters
			if (pPayment.getPaymentDescription() != null)
				pstmt.setString(10, pPayment.getPaymentDescription());
			if (pPayment.getDiscount() != null)
				pstmt.setString(11, pPayment.getDiscount());
			if (pPayment.getDiscountDescription() != null)
				pstmt.setString(12, pPayment.getDiscountDescription());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized void update(Payment pPayment) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "UPDATE " + DBNames.TABLE_PAYMENT + " SET "
					+ DBNames.ATT_PAYMENT_EXPDATE + " = ?, "
					+ DBNames.ATT_PAYMENT_CHARGE + " = ?, "
					+ DBNames.ATT_PAYMENT_DESCRIPTION + " = ?, "
					+ DBNames.ATT_PAYMENT_AMOUNT + " = ?, "
					+ DBNames.ATT_PAYMENT_AMOUNTDUE + " = ?, "
					+ DBNames.ATT_PAYMENT_PAID + " = ?, "
					+ DBNames.ATT_PAYMENT_DISCOUNT + " = ?, "
					+ DBNames.ATT_PAYMENT_DISCDESCRIPTION + " = ?, "
					+ DBNames.ATT_PAYMENT_ORIGINACCOUNT + " = ?, "
					+ DBNames.ATT_PAYMENT_PAYEE + " = ?, "
					+ DBNames.ATT_PAYMENT_PARENTID + " = ? "
					+ "WHERE " + DBNames.ATT_PAYMENT_ID + " = ?";
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			pstmt.setDate(1, new java.sql.Date(pPayment.getExpDate().getTimeInMillis()));
			pstmt.setBoolean(2, pPayment.isCharge());
			pstmt.setString(3, pPayment.getPaymentDescription());
			pstmt.setDouble(4, pPayment.getAmount());
			pstmt.setDouble(5, pPayment.getAmountDue());
			pstmt.setBoolean(6, pPayment.isPaid());
			pstmt.setString(7, pPayment.getDiscount());
			pstmt.setString(8, pPayment.getDiscountDescription());
			pstmt.setString(9, pPayment.getOriginAccount());
			pstmt.setString(10, pPayment.getPayee());
			pstmt.setInt(11, pPayment.getParentId());
			pstmt.setInt(12, pPayment.getId());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
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
