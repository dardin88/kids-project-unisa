package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.EmailObserver;
import it.unisa.kids.common.Email;
import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.facade.IAccessFacade;
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
        if (manager == null) {
            manager = new JDBCPaymentManager();
        }
        return manager;
    }
    // end of Singleton Design Pattern's implementation

    public synchronized void insert(PaymentBean pPayment) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_PAYMENT + " ("
                    + DBNames.ATT_PAYMENT_ID + ", "
                    + DBNames.ATT_PAYMENT_EXPDATE + ", "
                    + DBNames.ATT_PAYMENT_AMOUNT + ", "
                    + DBNames.ATT_PAYMENT_PAID + ", "
                    + DBNames.ATT_PAYMENT_ORIGINACCOUNT + ", "
                    + DBNames.ATT_PAYMENT_PAYEE + ", "
                    + DBNames.ATT_PAYMENT_PARENTID + ", "
                    + DBNames.ATT_PAYMENT_CHARGE + ", "
                    + DBNames.ATT_PAYMENT_DESCRIPTION + ", "
                    + DBNames.ATT_PAYMENT_DISCOUNT + ", "
                    + DBNames.ATT_PAYMENT_DISCDESCRIPTION
                    + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pPayment.getId());
            pstmt.setDate(2, new java.sql.Date(pPayment.getExpDate().getTimeInMillis()));
            pstmt.setDouble(3, pPayment.getAmount());
            pstmt.setBoolean(4, pPayment.isPaid());
            pstmt.setString(5, pPayment.getOriginAccount());
            pstmt.setString(6, pPayment.getPayee());
            pstmt.setInt(7, pPayment.getParentId());
            pstmt.setBoolean(8, pPayment.isCharge());
            pstmt.setString(9, pPayment.getPaymentDescription());
            pstmt.setDouble(10, pPayment.getDiscount());
            pstmt.setString(11, pPayment.getDiscountDescription());

            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized void update(PaymentBean pPayment) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        boolean commaState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing update query string
            query = "UPDATE " + DBNames.TABLE_PAYMENT + " SET ";
            if (pPayment.getExpDate() != null) {
                query += DBNames.ATT_PAYMENT_EXPDATE + " = ?";
                commaState = true;
            }

            if (pPayment.isChargeUsable()) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_CHARGE + " = ?";
                commaState = true;
            }

            if (pPayment.getPaymentDescription() != null) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_DESCRIPTION + " = ?";
                commaState = true;
            }

            if (pPayment.getAmount() >= 0) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_AMOUNT + " = ?";
                commaState = true;
            }

            if (pPayment.isPaidUsable()) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_PAID + " = ?";
                commaState = true;
            }

            if (pPayment.getDiscount() >= 0) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_DISCOUNT + " = ?";
                commaState = true;
            }

            if (pPayment.getDiscountDescription() != null) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_DISCDESCRIPTION + " = ?";
                commaState = true;
            }

            if (pPayment.getOriginAccount() != null) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_ORIGINACCOUNT + " = ?";
                commaState = true;
            }

            if (pPayment.getPayee() != null) {
                query += useComma(commaState) + DBNames.ATT_PAYMENT_PAYEE + " = ?";
                commaState = true;
            }

            if (pPayment.getParentId() > 0) {		// or >= 0 ???
                query += useComma(commaState) + DBNames.ATT_PAYMENT_PARENTID + " = ?";
                commaState = true;
            }

            query += " WHERE " + DBNames.ATT_PAYMENT_ID + " = ?";
            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument

            if (pPayment.getExpDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pPayment.getExpDate().getTimeInMillis()));
                i++;
            }

            if (pPayment.isChargeUsable()) {
                pstmt.setBoolean(i, pPayment.isCharge());
                i++;
            }

            if (pPayment.getPaymentDescription() != null) {
                pstmt.setString(i, pPayment.getPaymentDescription());
                i++;
            }

            if (pPayment.getAmount() >= 0) {
                pstmt.setDouble(i, pPayment.getAmount());
                i++;
            }

            if (pPayment.isPaidUsable()) {
                pstmt.setBoolean(i, pPayment.isPaid());
                i++;
            }

            if (pPayment.getDiscount() >= 0) {
                pstmt.setDouble(i, pPayment.getDiscount());
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

            pstmt.setInt(i, pPayment.getId());

            // executing update query
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    private String useComma(boolean pEnableComma) {
        return pEnableComma ? ", " : "";
    }

    public synchronized void delete(PaymentBean pPayment) throws SQLException {
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
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized List<PaymentBean> search(PaymentBean pPayment) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<PaymentBean> payments = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_PAYMENT + " WHERE";
            if (pPayment.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_PAYMENT_ID + " = ?";
                andState = true;
            }

            if (pPayment.getExpDate() != null) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_EXPDATE + " = ?";
                andState = true;
            }

            if (pPayment.isChargeUsable()) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_CHARGE + " = ?";
                andState = true;
            }

            if (pPayment.getPaymentDescription() != null) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_DESCRIPTION + " = ?";
                andState = true;
            }

            if (pPayment.getAmount() >= 0) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_AMOUNT + " = ?";
                andState = true;
            }

            if (pPayment.isPaidUsable()) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_PAID + " = ?";
                andState = true;
            }

            if (pPayment.getDiscount() >= 0) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_DISCOUNT + " = ?";
                andState = true;
            }

            if (pPayment.getDiscountDescription() != null) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_DISCDESCRIPTION + " = ?";
                andState = true;
            }

            if (pPayment.getOriginAccount() != null) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_ORIGINACCOUNT + " = ?";
                andState = true;
            }

            if (pPayment.getPayee() != null) {
                query += useAnd(andState) + DBNames.ATT_PAYMENT_PAYEE + " = ?";
                andState = true;
            }

            if (pPayment.getParentId() > 0) {		// or >= 0 ???
                query += useAnd(andState) + DBNames.ATT_PAYMENT_PARENTID + " = ?";
                andState = true;
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

            if (pPayment.isChargeUsable()) {
                pstmt.setBoolean(i, pPayment.isCharge());
                i++;
            }

            if (pPayment.getPaymentDescription() != null) {
                pstmt.setString(i, pPayment.getPaymentDescription());
                i++;
            }

            if (pPayment.getAmount() >= 0) {
                pstmt.setDouble(i, pPayment.getAmount());
                i++;
            }

            if (pPayment.isPaidUsable()) {
                pstmt.setBoolean(i, pPayment.isPaid());
                i++;
            }

            if (pPayment.getDiscount() >= 0) {
                pstmt.setDouble(i, pPayment.getDiscount());
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
            payments = new ArrayList<PaymentBean>();
            while (rs.next()) {
                PaymentBean p = new PaymentBean();
                p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar expDate = new GregorianCalendar();
                expDate.setTime(rs.getDate(DBNames.ATT_PAYMENT_EXPDATE));   // check di valori nulli non eseguito perche' expDate è NOT NULL nel DB
                p.setExpDate(expDate);

                p.setCharge(rs.getBoolean(DBNames.ATT_PAYMENT_CHARGE));
                p.setPaymentDescription(rs.getString(DBNames.ATT_PAYMENT_DESCRIPTION));
                p.setAmount(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNT));
                p.setPaid(rs.getBoolean(DBNames.ATT_PAYMENT_PAID));
                p.setDiscount(rs.getDouble(DBNames.ATT_PAYMENT_DISCOUNT));
                p.setDiscountDescription(rs.getString(DBNames.ATT_PAYMENT_DISCDESCRIPTION));
                p.setOriginAccount(rs.getString(DBNames.ATT_PAYMENT_ORIGINACCOUNT));
                p.setPayee(rs.getString(DBNames.ATT_PAYMENT_PAYEE));
                p.setParentId(rs.getInt(DBNames.ATT_PAYMENT_PARENTID));

                payments.add(p);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return payments;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public synchronized List<PaymentBean> getPaymentList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<PaymentBean> payments = null;

        try {
            con = DBConnectionPool.getConnection();

            query = "SELECT * FROM " + DBNames.TABLE_PAYMENT;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // constructing payment list
            payments = new ArrayList<PaymentBean>();
            while (rs.next()) {
                PaymentBean p = new PaymentBean();
                p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar expDate = new GregorianCalendar();
                expDate.setTime(rs.getDate(DBNames.ATT_PAYMENT_EXPDATE));
                p.setExpDate(expDate);

                p.setCharge(rs.getBoolean(DBNames.ATT_PAYMENT_CHARGE));
                p.setPaymentDescription(rs.getString(DBNames.ATT_PAYMENT_DESCRIPTION));
                p.setAmount(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNT));
                p.setPaid(rs.getBoolean(DBNames.ATT_PAYMENT_PAID));
                p.setDiscount(rs.getDouble(DBNames.ATT_PAYMENT_DISCOUNT));
                p.setDiscountDescription(rs.getString(DBNames.ATT_PAYMENT_DISCDESCRIPTION));
                p.setOriginAccount(rs.getString(DBNames.ATT_PAYMENT_ORIGINACCOUNT));
                p.setPayee(rs.getString(DBNames.ATT_PAYMENT_PAYEE));
                p.setParentId(rs.getInt(DBNames.ATT_PAYMENT_PARENTID));

                payments.add(p);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return payments;
    }

    public synchronized void addCharge(PaymentBean pPayment) throws SQLException {
        IAccessFacade accessFacade = new AccessFacade();

        Account parentAccount = accessFacade.getParentById(pPayment.getParentId());

        String email = parentAccount.getEmail();
        String name = parentAccount.getNameUser();
        String surname = parentAccount.getSurnameUser();
        String body = "Salve " + name + " " + surname + ".\n";
        body += "La informiamo che le è stato addebitato il seguente pagamento dell'importo "
                + pPayment.getAmount() + ": " + pPayment.getPaymentDescription();

        insert(pPayment);

        Email e = new Email();
        e.setEmail(email);
        e.setBody(body);

        pPayment.addObserver(new EmailObserver());
        pPayment.notifyObservers(e);
    }

    public synchronized void insert(RefundBean pRefund) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_REFUND + " ("
                    + DBNames.ATT_REFUND_ID + ", "
                    + DBNames.ATT_REFUND_DESCRIPTION + ", "
                    + DBNames.ATT_REFUND_AMOUNT + ", "
                    + DBNames.ATT_REFUND_PARENTID
                    + ") VALUES(?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pRefund.getId());
            pstmt.setString(2, pRefund.getDescription());
            pstmt.setDouble(3, pRefund.getAmount());
            pstmt.setInt(4, pRefund.getParentId());

            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized void update(RefundBean pRefund) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_REFUND + " SET "
                    + DBNames.ATT_REFUND_DESCRIPTION + " = ?, "
                    + DBNames.ATT_REFUND_AMOUNT + " = ?, "
                    + DBNames.ATT_REFUND_PARENTID + " = ? "
                    + "WHERE " + DBNames.ATT_REFUND_ID + " = ?";

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            pstmt.setString(1, pRefund.getDescription());
            pstmt.setDouble(2, pRefund.getAmount());
            pstmt.setInt(3, pRefund.getParentId());
            pstmt.setInt(4, pRefund.getId());

            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized void delete(RefundBean pRefund) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "DELETE FROM " + DBNames.TABLE_REFUND
                    + "WHERE " + DBNames.ATT_REFUND_ID + " = " + pRefund.getId();

            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized List<RefundBean> search(RefundBean pRefund) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<RefundBean> refunds = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_REFUND + " WHERE";
            if (pRefund.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_REFUND_ID + " = ?";
                andState = true;
            }

            if (pRefund.getDescription() != null) {
                query += useAnd(andState) + DBNames.ATT_REFUND_DESCRIPTION + " = ?";
                andState = true;
            }

            if (pRefund.getAmount() >= 0) {
                query += useAnd(andState) + DBNames.ATT_REFUND_AMOUNT + " = ?";
                andState = true;
            }

            if (pRefund.getParentId() > 0) {		// or >= 0 ???
                query += useAnd(andState) + DBNames.ATT_REFUND_PARENTID + " = ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pRefund.getId() > 0) {		// >= 0 ??
                pstmt.setInt(i, pRefund.getId());
                i++;
            }

            if (pRefund.getDescription() != null) {
                pstmt.setString(i, pRefund.getDescription());
                i++;
            }

            if (pRefund.getAmount() >= 0) {
                pstmt.setDouble(i, pRefund.getAmount());
                i++;
            }

            if (pRefund.getParentId() > 0) {		// or >= 0 ???
                pstmt.setInt(i, pRefund.getParentId());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing refund list
            refunds = new ArrayList<RefundBean>();
            while (rs.next()) {
                RefundBean rp = new RefundBean();
                rp.setId(rs.getInt(DBNames.ATT_REFUND_ID));
                rp.setDescription(rs.getString(DBNames.ATT_REFUND_DESCRIPTION));
                rp.setAmount(rs.getDouble(DBNames.ATT_REFUND_AMOUNT));
                rp.setParentId(rs.getInt(DBNames.ATT_REFUND_PARENTID));

                refunds.add(rp);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return refunds;
    }

    public synchronized List<RefundBean> getRefundList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<RefundBean> refunds = null;

        try {
            con = DBConnectionPool.getConnection();

            query = "SELECT * FROM " + DBNames.TABLE_REFUND;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // constructing refund list
            refunds = new ArrayList<RefundBean>();
            while (rs.next()) {
                RefundBean rp = new RefundBean();
                rp.setId(rs.getInt(DBNames.ATT_REFUND_ID));
                rp.setDescription(rs.getString(DBNames.ATT_REFUND_DESCRIPTION));
                rp.setAmount(rs.getDouble(DBNames.ATT_REFUND_AMOUNT));
                rp.setParentId(rs.getInt(DBNames.ATT_REFUND_PARENTID));

                refunds.add(rp);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return refunds;
    }
}
