package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
	
	public synchronized void delete(Payment pPayment) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "DELETE FROM " + DBNames.TABLE_PAYMENT
					+ "WHERE " + DBNames.ATT_PAYMENT_ID + " = " + pPayment.getId();
			
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized List<Payment> getPaymentsByObject(Payment pPayment) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<Payment> paymentList = null;
		
		boolean and = false;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing search query string
			query = "SELECT * FROM " + DBNames.TABLE_PAYMENT + " WHERE";
			if (pPayment.getId() > 0) {		// or >= 0 ???
				query += " " + DBNames.ATT_PAYMENT_ID + " = ?";
				and = true;
			}
			
			if (pPayment.getExpDate() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_EXPDATE + " = ?";
				and = true;
			}
			
			query += enableAnd(and) + DBNames.ATT_PAYMENT_CHARGE + " = ?";
			and = true;
			
			if (pPayment.getPaymentDescription() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_DESCRIPTION + " = ?";
				and = true;
			}
			
			if (pPayment.getAmount() >= 0) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_AMOUNT + " = ?";
				and = true;
			}
			
			if (pPayment.getAmountDue() >= 0) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_AMOUNTDUE + " = ?";
				and = true;
			}
			
			query += enableAnd(and) + DBNames.ATT_PAYMENT_PAID + " = ?";
			and = true;
			
			if (pPayment.getDiscount() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_DISCOUNT + " = ?";
				and = true;
			}
			
			if (pPayment.getDiscountDescription() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_DISCDESCRIPTION + " = ?";
				and = true;
			}
			
			if (pPayment.getOriginAccount() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_ORIGINACCOUNT + " = ?";
				and = true;
			}
			
			if (pPayment.getPayee() != null) {
				query += enableAnd(and) + DBNames.ATT_PAYMENT_PAYEE + " = ?";
				and = true;
			}
			
			if (pPayment.getParentId() > 0) {		// or >= 0 ???
				query += enableAnd(and) + DBNames.ATT_PAYMENT_PARENTID + " = ?";
				and = true;
			}
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pPayment.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pPayment.getId());
				i++;
			}
			
			if (pPayment.getExpDate() != null) {
				pstmt.setDate(i, new java.sql.Date(pPayment.getExpDate().getTimeInMillis()));
				i++;
			}
			
			pstmt.setBoolean(i, pPayment.isCharge());
			i++;
			
			if (pPayment.getPaymentDescription() != null) {
				pstmt.setString(i, pPayment.getPaymentDescription());
				i++;
			}
			
			if (pPayment.getAmount() >= 0) {
				pstmt.setDouble(i, pPayment.getAmount());
				i++;
			}
			
			if (pPayment.getAmountDue() >= 0) {
				pstmt.setDouble(i, pPayment.getAmountDue());
				i++;
			}
			
			pstmt.setBoolean(i, pPayment.isPaid());
			i++;
			
			if (pPayment.getDiscount() != null) {
				pstmt.setString(i, pPayment.getDiscount());
				i++;
			}
			
			if (pPayment.getDiscountDescription() != null) {
				pstmt.setString(i, pPayment.getDiscountDescription());
				i++;
			}
			
			if (pPayment.getOriginAccount() != null) {
				pstmt.setString(i, pPayment.getOriginAccount());
				i++;
			}
			
			if (pPayment.getPayee() != null) {
				pstmt.setString(i, pPayment.getPayee());
				i++;
			}
			
			if (pPayment.getParentId() > 0) {		// or >= 0 ???
				pstmt.setInt(i, pPayment.getParentId());
				i++;
			}
			
			// executing select query
			rs = pstmt.executeQuery();
			con.commit();
			
			// constructing payment list
			paymentList = new ArrayList<Payment>();
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));
				
				GregorianCalendar expDate = new GregorianCalendar();
				expDate.setTime(rs.getDate(DBNames.ATT_PAYMENT_EXPDATE));
				p.setExpDate(expDate);
				
				p.setCharge(rs.getBoolean(DBNames.ATT_PAYMENT_CHARGE));
				p.setPaymentDescription(rs.getString(DBNames.ATT_PAYMENT_DESCRIPTION));
				p.setAmount(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNT));
				p.setAmountDue(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNTDUE));
				p.setPaid(rs.getBoolean(DBNames.ATT_PAYMENT_PAID));
				p.setDiscount(rs.getString(DBNames.ATT_PAYMENT_DISCOUNT));
				p.setDiscountDescription(rs.getString(DBNames.ATT_PAYMENT_DISCDESCRIPTION));
				p.setOriginAccount(rs.getString(DBNames.ATT_PAYMENT_ORIGINACCOUNT));
				p.setPayee(rs.getString(DBNames.ATT_PAYMENT_PAYEE));
				p.setParentId(rs.getInt(DBNames.ATT_PAYMENT_PARENTID));
				
				paymentList.add(p);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return paymentList;
	}
	
	public synchronized List<Payment> getPaymentList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		List<Payment> paymentList = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM " + DBNames.TABLE_PAYMENT;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			// constructing payment list
			paymentList = new ArrayList<Payment>();
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));
				
				GregorianCalendar expDate = new GregorianCalendar();
				expDate.setTime(rs.getDate(DBNames.ATT_PAYMENT_EXPDATE));
				p.setExpDate(expDate);
				
				p.setCharge(rs.getBoolean(DBNames.ATT_PAYMENT_CHARGE));
				p.setPaymentDescription(rs.getString(DBNames.ATT_PAYMENT_DESCRIPTION));
				p.setAmount(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNT));
				p.setAmountDue(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNTDUE));
				p.setPaid(rs.getBoolean(DBNames.ATT_PAYMENT_PAID));
				p.setDiscount(rs.getString(DBNames.ATT_PAYMENT_DISCOUNT));
				p.setDiscountDescription(rs.getString(DBNames.ATT_PAYMENT_DISCDESCRIPTION));
				p.setOriginAccount(rs.getString(DBNames.ATT_PAYMENT_ORIGINACCOUNT));
				p.setPayee(rs.getString(DBNames.ATT_PAYMENT_PAYEE));
				p.setParentId(rs.getInt(DBNames.ATT_PAYMENT_PARENTID));
				
				paymentList.add(p);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return paymentList;
	}
	
	
	private String enableAnd(boolean enableAnd) {
		return enableAnd ? " AND " : " ";
	}
}
