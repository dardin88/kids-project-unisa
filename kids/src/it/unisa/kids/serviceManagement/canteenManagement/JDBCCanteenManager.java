package it.unisa.kids.serviceManagement.canteenManagement;

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

public class JDBCCanteenManager implements ICanteenManager {

    // Singleton Design Pattern's implementation
    private static JDBCCanteenManager manager;

    private JDBCCanteenManager() {
    }

    public static synchronized JDBCCanteenManager getInstance() {
        if (manager == null) {
            manager = new JDBCCanteenManager();
        }
        return manager;
    }
    // end of Singleton Design Pattern's implementation

    @Override
    public synchronized void insert(MenuBean pMenu) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_MENU + " ("
                    + DBNames.ATT_MENU_ID + ", "
                    + DBNames.ATT_MENU_TYPE + ", "
                    + DBNames.ATT_MENU_DATE + ", "
                    + DBNames.ATT_MENU_FIRST + ", "
                    + DBNames.ATT_MENU_SECOND + ", "
                    + DBNames.ATT_MENU_SIDEDISH + ", "
                    + DBNames.ATT_MENU_FRUIT + ", "
                    + DBNames.ATT_MENU_CHILDINSCID
                    + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pMenu.getId());
            pstmt.setString(2, pMenu.getType());
            pstmt.setDate(3, new java.sql.Date(pMenu.getDate().getTimeInMillis()));
            pstmt.setString(4, pMenu.getFirst());
            pstmt.setString(5, pMenu.getSecond());
            pstmt.setString(6, pMenu.getSideDish());
            pstmt.setString(7, pMenu.getFruit());
            pstmt.setInt(8, pMenu.getChildInscriptionId());

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

    @Override
    public synchronized void update(MenuBean pMenu) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        boolean commaState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_MENU + " SET ";
            if (pMenu.getType() != null) {
                query += DBNames.ATT_MENU_TYPE + " = ?";
                commaState = true;
            }
            
            if (pMenu.getDate() != null) {
                query += DBNames.ATT_MENU_DATE + " = ?";
                commaState = true;
            }

            if (pMenu.getFirst() != null) {
                query += useComma(commaState) + DBNames.ATT_MENU_FIRST + " = ?";
                commaState = true;
            }
            
            if (pMenu.getSecond() != null) {
                query += useComma(commaState) + DBNames.ATT_MENU_SECOND + " = ?";
                commaState = true;
            }
            
            if (pMenu.getSideDish() != null) {
                query += useComma(commaState) + DBNames.ATT_MENU_SIDEDISH + " = ?";
                commaState = true;
            }
            
            if (pMenu.getFruit() != null) {
                query += useComma(commaState) + DBNames.ATT_MENU_FRUIT + " = ?";
                commaState = true;
            }

            if (pMenu.getChildInscriptionId() >= 0) {
                query += useComma(commaState) + DBNames.ATT_MENU_CHILDINSCID + " = ?";
                commaState = true;
            }
            query += " WHERE " + DBNames.ATT_MENU_ID + " = ?";
            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            
            if (pMenu.getType() != null) {
                pstmt.setString(i, pMenu.getType());
                i++;
            }
            if (pMenu.getDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pMenu.getDate().getTimeInMillis()));
                i++;
            }

            if (pMenu.getFirst() != null) {
                pstmt.setString(i, pMenu.getFirst());
                i++;
            }
            
            if (pMenu.getSecond() != null) {
                pstmt.setString(i, pMenu.getSecond());
                i++;
            }
            
            if (pMenu.getSideDish() != null) {
                pstmt.setString(i, pMenu.getSideDish());
                i++;
            }
            
            if (pMenu.getFruit() != null) {
                pstmt.setString(i, pMenu.getFruit());
                i++;
            }

            if (pMenu.getChildInscriptionId() >= 0) {
                pstmt.setInt(i, pMenu.getChildInscriptionId());
                i++;
            }

            pstmt.setInt(i, pMenu.getId());

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

    // da rivedere, per ora non dovrebbne servire
    public synchronized void delete(MenuBean pMenu) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "DELETE FROM " + DBNames.TABLE_MENU
                    + "WHERE " + DBNames.ATT_MENU_ID + " = " + pMenu.getId();

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

    public synchronized List<MenuBean> search(MenuBean pMenu)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<MenuBean> menus = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_MENU + " WHERE";
            if (pMenu.getId() > 0) {
                query += " " + DBNames.ATT_MENU_ID + " = ?";
                andState = true;
            }

            if (pMenu.getType() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_TYPE + " = ?";
                andState = true;
            }

            if (pMenu.getDate() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_DATE + " = ?";
                andState = true;
            }

            if (pMenu.getFirst() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_FIRST + " = ?";
                andState = true;
            }

            if (pMenu.getSecond() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_SECOND + " = ?";
                andState = true;
            }

            if (pMenu.getSideDish() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_SIDEDISH + " = ?";
                andState = true;
            }

            if (pMenu.getFruit() != null) {
                query += useAnd(andState) + DBNames.ATT_MENU_FRUIT + " = ?";
                andState = true;
            }

            if (pMenu.getChildInscriptionId() >= 0) {  // il valore 0 identifica i menu giornalieri
                query += useAnd(andState) + DBNames.ATT_MENU_CHILDINSCID + " = ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pMenu.getId() > 0) {
                pstmt.setInt(i, pMenu.getId());
                i++;
            }

            if (pMenu.getType() != null) {
                pstmt.setString(i, pMenu.getType());
                i++;
            }

            if (pMenu.getDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pMenu.getDate().getTimeInMillis()));
                i++;
            }

            if (pMenu.getFirst() != null) {
                pstmt.setString(i, pMenu.getFirst());
                i++;
            }

            if (pMenu.getSecond() != null) {
                pstmt.setString(i, pMenu.getSecond());
                i++;
            }

            if (pMenu.getSideDish() != null) {
                pstmt.setString(i, pMenu.getSideDish());
                i++;
            }

            if (pMenu.getFruit() != null) {
                pstmt.setString(i, pMenu.getFruit());
                i++;
            }

            if (pMenu.getChildInscriptionId() >= 0) {  // il valore 0 identifica i menu giornalieri
                pstmt.setInt(i, pMenu.getChildInscriptionId());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing menu list
            menus = new ArrayList<MenuBean>();
            while (rs.next()) {
                MenuBean m = new MenuBean();
                m.setId(rs.getInt(DBNames.ATT_MENU_ID));
                m.setType(rs.getString(DBNames.ATT_MENU_TYPE));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar menuDate = new GregorianCalendar();
                menuDate.setTime(rs.getDate(DBNames.ATT_MENU_DATE));   // check di valori nulli non eseguito perche' menuDate è NOT NULL nel DB
                m.setDate(menuDate);

                m.setFirst(rs.getString(DBNames.ATT_MENU_FIRST));
                m.setSecond(rs.getString(DBNames.ATT_MENU_SECOND));
                m.setSideDish(rs.getString(DBNames.ATT_MENU_SIDEDISH));
                m.setFruit(rs.getString(DBNames.ATT_MENU_FRUIT));
                m.setChildInscriptionId(rs.getInt(DBNames.ATT_MENU_CHILDINSCID));

                menus.add(m);
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
        return menus;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    @Override
    public List<MenuBean> getLastMenu(int pNumOfMenu, String pMenuType, boolean pOnlyDaily) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<MenuBean> menus = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "SELECT * FROM " + DBNames.TABLE_MENU;
            if (pMenuType != null && pMenuType.equals(MenuBean.DIFF_MENU)) {
                query += " WHERE " + DBNames.ATT_MENU_TYPE + " = '" + MenuBean.DIFF_MENU + "'";
            } else if (pMenuType != null && pMenuType.equals(MenuBean.DAILY_MENU)) {
                query += " WHERE " + DBNames.ATT_MENU_TYPE + " = '" + MenuBean.DAILY_MENU + "'";
            }

            if (pOnlyDaily) {
                query += " AND " + DBNames.ATT_MENU_CHILDINSCID + " = 0";
            } else {
                query += " AND " + DBNames.ATT_MENU_CHILDINSCID + " > 0";
            }
            query += " ORDER BY " + DBNames.ATT_MENU_ID + " desc" + " LIMIT " + pNumOfMenu;

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            con.commit();

            // constructing menu list
            menus = new ArrayList<MenuBean>();
            while (rs.next()) {
                MenuBean m = new MenuBean();
                m.setId(rs.getInt(DBNames.ATT_MENU_ID));
                m.setType(rs.getString(DBNames.ATT_MENU_TYPE));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar menuDate = new GregorianCalendar();
                menuDate.setTime(rs.getDate(DBNames.ATT_MENU_DATE));   // check di valori nulli non eseguito perche' menuDate è NOT NULL nel DB
                m.setDate(menuDate);

                m.setFirst(rs.getString(DBNames.ATT_MENU_FIRST));
                m.setSecond(rs.getString(DBNames.ATT_MENU_SECOND));
                m.setSideDish(rs.getString(DBNames.ATT_MENU_SIDEDISH));
                m.setFruit(rs.getString(DBNames.ATT_MENU_FRUIT));
                m.setChildInscriptionId(rs.getInt(DBNames.ATT_MENU_CHILDINSCID));

                menus.add(m);
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
        return menus;
    }

    public synchronized void insert(MealRequestBean pMealReq) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_MEAL_REQUEST + " ("
                    + DBNames.ATT_MEALREQ_ID + ", "
                    + DBNames.ATT_MEALREQ_DATE + ", "
                    + DBNames.ATT_MEALREQ_FULFILLED + ", "
                    + DBNames.ATT_MEALREQ_PARENTID
                    + ") VALUES(?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pMealReq.getId());
            pstmt.setDate(2, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
            pstmt.setBoolean(3, pMealReq.isFulfilled());
            pstmt.setInt(4, pMealReq.getParentId());

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

        // adding payment for this meal request DA FARE/RIVEDERE/RIVALUTARE
        /*IPaymentManager paymentManager = (IPaymentManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PAYMENT);
         PaymentBean chargePayment = new PaymentBean();
         chargePayment.setAmount(MealRequestBean.PRICE_MEALREQ);
         chargePayment.setPayee(PaymentBean.DEFAULT_PAYEE);
         chargePayment.setPaymentDescription("");
         chargePayment.setParentId(0);	// serve un metodo getParentId(Child c);*/
        //paymentManager.addCharge(chargePayment);
    }

    public synchronized void update(MealRequestBean pMealReq) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        boolean commaState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_MEAL_REQUEST + " SET ";
            if (pMealReq.getDate() != null) {
                query += DBNames.ATT_MEALREQ_DATE + " = ?";
                commaState = true;
            }

            if (pMealReq.isFulfilledUsable()) {
                query += useComma(commaState) + DBNames.ATT_MEALREQ_FULFILLED + " = ?";
                commaState = true;
            }

            if (pMealReq.getParentId() > 0) {
                query += useComma(commaState) + DBNames.ATT_MEALREQ_PARENTID + " = ?";
                commaState = true;
            }
            query += " WHERE " + DBNames.ATT_MEALREQ_ID + " = ?";
            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument

            if (pMealReq.getDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
                i++;
            }

            if (pMealReq.isFulfilledUsable()) {
                pstmt.setBoolean(i, pMealReq.isFulfilled());
                i++;
            }

            if (pMealReq.getParentId() > 0) {
                pstmt.setInt(i, pMealReq.getParentId());
                i++;
            }

            pstmt.setInt(i, pMealReq.getId());

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

    // da rivalutare - dovrebbe non servire più
    public synchronized void delete(MealRequestBean pMealReq) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "DELETE FROM " + DBNames.TABLE_MEAL_REQUEST
                    + " WHERE " + DBNames.ATT_MEALREQ_ID + " = " + pMealReq.getId();

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

    public synchronized List<MealRequestBean> search(MealRequestBean pMealReq) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<MealRequestBean> mealReqs = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_MEAL_REQUEST + " WHERE";
            if (pMealReq.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_MEALREQ_ID + " = ?";
                andState = true;
            }

            if (pMealReq.getDate() != null) {
                query += useAnd(andState) + DBNames.ATT_MEALREQ_DATE + " = ?";
                andState = true;
            }

            if (pMealReq.isFulfilledUsable()) {
                query += useAnd(andState) + DBNames.ATT_MEALREQ_FULFILLED + "= ?";
                andState = true;
            }

            if (pMealReq.getParentId() > 0) {		// or >= 0 ???
                query += useAnd(andState) + DBNames.ATT_MEALREQ_PARENTID + " = ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pMealReq.getId() > 0) {		// >= 0 ??
                pstmt.setInt(i, pMealReq.getId());
                i++;
            }

            if (pMealReq.getDate() != null) {
                pstmt.setDate(i, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
                i++;
            }

            if (pMealReq.isFulfilledUsable()) {
                pstmt.setBoolean(i, pMealReq.isFulfilled());
                i++;
            }

            if (pMealReq.getParentId() > 0) {
                pstmt.setInt(i, pMealReq.getParentId());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing mealReq list
            mealReqs = new ArrayList<MealRequestBean>();
            while (rs.next()) {
                MealRequestBean mr = new MealRequestBean();
                mr.setId(rs.getInt(DBNames.ATT_MEALREQ_ID));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar mealReqDate = new GregorianCalendar();
                mealReqDate.setTime(rs.getDate(DBNames.ATT_MEALREQ_DATE));
                mr.setDate(mealReqDate);

                mr.setFulfilled(rs.getBoolean(DBNames.ATT_MEALREQ_FULFILLED));
                mr.setParentId(rs.getInt(DBNames.ATT_MEALREQ_PARENTID));

                mealReqs.add(mr);
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
        return mealReqs;
    }

    public synchronized List<MealRequestBean> getMealReqList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<MealRequestBean> mealReqs = null;

        try {
            con = DBConnectionPool.getConnection();

            query = "SELECT * FROM " + DBNames.TABLE_MEAL_REQUEST;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // constructing payment list
            mealReqs = new ArrayList<MealRequestBean>();
            while (rs.next()) {
                MealRequestBean mr = new MealRequestBean();
                mr.setId(rs.getInt(DBNames.ATT_MEALREQ_ID));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar mealReqDate = new GregorianCalendar();
                mealReqDate.setTime(rs.getDate(DBNames.ATT_MEALREQ_DATE));
                mr.setDate(mealReqDate);

                mr.setFulfilled(rs.getBoolean(DBNames.ATT_MEALREQ_FULFILLED));
                mr.setParentId(rs.getInt(DBNames.ATT_MEALREQ_PARENTID));

                mealReqs.add(mr);
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
        return mealReqs;
    }
}
