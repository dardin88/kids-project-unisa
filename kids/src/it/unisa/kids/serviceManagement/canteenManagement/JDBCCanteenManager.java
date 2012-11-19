package it.unisa.kids.serviceManagement.canteenManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.bean.MenuBean;
import it.unisa.kids.serviceManagement.paymentManagement.IPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
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
		if (manager == null)
			manager = new JDBCCanteenManager();
		return manager;
	}
	// end of Singleton Design Pattern's implementation
	
	public synchronized void insert(DifferentiatedMenuBean pDiffMenu) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "INSERT INTO " + DBNames.TABLE_DIFFMENU + " ("
					+ DBNames.ATT_DIFFMENU_ID + ", "
					+ DBNames.ATT_DIFFMENU_MOTIVATION + ", "
					+ DBNames.ATT_DIFFMENU_FIRST + ", "
					+ DBNames.ATT_DIFFMENU_SECOND + ", "
					+ DBNames.ATT_DIFFMENU_SIDEDISH + ", "
					+ DBNames.ATT_DIFFMENU_FRUIT + ", "
					+ DBNames.ATT_DIFFMENU_CHILDINSCID
					+ ") VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			//setting pstmt's parameters
			pstmt.setInt(1, pDiffMenu.getId());
			pstmt.setString(2, pDiffMenu.getMotivation());
			pstmt.setString(3, pDiffMenu.getFirst());
			pstmt.setString(4, pDiffMenu.getSecond());
			pstmt.setString(5, pDiffMenu.getSideDish());
			pstmt.setString(6, pDiffMenu.getFruit());
			pstmt.setInt(7, pDiffMenu.getChildInscriptionId());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized void update(DifferentiatedMenuBean pDiffMenu) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "UPDATE " + DBNames.TABLE_DIFFMENU + " SET "
					+ DBNames.ATT_DIFFMENU_MOTIVATION + " = ?, "
					+ DBNames.ATT_DIFFMENU_FIRST + " = ?, "
					+ DBNames.ATT_DIFFMENU_SECOND + " = ?, "
					+ DBNames.ATT_DIFFMENU_SIDEDISH + " = ?, "
					+ DBNames.ATT_DIFFMENU_FRUIT + " = ?, "
					+ DBNames.ATT_DIFFMENU_CHILDINSCID + " = ? "
					+ "WHERE " + DBNames.ATT_DIFFMENU_ID + " = ?";
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			pstmt.setString(1, pDiffMenu.getMotivation());
			pstmt.setString(2, pDiffMenu.getFirst());
			pstmt.setString(3, pDiffMenu.getSecond());
			pstmt.setString(4, pDiffMenu.getSideDish());
			pstmt.setString(5, pDiffMenu.getFruit());
			pstmt.setInt(6, pDiffMenu.getChildInscriptionId());
			pstmt.setInt(7, pDiffMenu.getId());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized void delete(DifferentiatedMenuBean pDiffMenu) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "DELETE FROM " + DBNames.TABLE_DIFFMENU
					+ "WHERE " + DBNames.ATT_DIFFMENU_ID + " = " + pDiffMenu.getId();
			
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
	
	public synchronized List<DifferentiatedMenuBean> search(DifferentiatedMenuBean pDiffMenu)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<DifferentiatedMenuBean> diffMenus = null;
		
		boolean andState = false;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing search query string
			query = "SELECT * FROM " + DBNames.TABLE_DIFFMENU + " WHERE";
			if (pDiffMenu.getId() > 0) {		// or >= 0 ???
				query += " " + DBNames.ATT_DIFFMENU_ID + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getMotivation() != null) {
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_MOTIVATION + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getFirst() != null) {
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_FIRST + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getSecond() != null) {
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_SECOND + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getSideDish() != null) {
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_SIDEDISH + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getFruit() != null) {
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_FRUIT + " = ?";
				andState = true;
			}
			
			if (pDiffMenu.getChildInscriptionId() > 0) {	// or >= 0 ?
				query += useAnd(andState) + DBNames.ATT_DIFFMENU_CHILDINSCID + " = ?";
				andState = true;
			}
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pDiffMenu.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pDiffMenu.getId());
				i++;
			}
			
			if (pDiffMenu.getMotivation() != null) {
				pstmt.setString(i, pDiffMenu.getMotivation());
				i++;
			}
			
			if (pDiffMenu.getFirst() != null) {
				pstmt.setString(i, pDiffMenu.getFirst());
				i++;
			}
			
			if (pDiffMenu.getSecond() != null) {
				pstmt.setString(i, pDiffMenu.getSecond());
				i++;
			}
			
			if (pDiffMenu.getSideDish() != null) {
				pstmt.setString(i, pDiffMenu.getSideDish());
				i++;
			}
			
			if (pDiffMenu.getFruit() != null) {
				pstmt.setString(i, pDiffMenu.getFruit());
				i++;
			}
			
			if (pDiffMenu.getChildInscriptionId() > 0) {
				pstmt.setInt(i, pDiffMenu.getChildInscriptionId());
				i++;
			}
			
			// executing select query
			rs = pstmt.executeQuery();
			con.commit();
			
			// constructing diffmenu list
			diffMenus = new ArrayList<DifferentiatedMenuBean>();
			while (rs.next()) {
				DifferentiatedMenuBean dm = new DifferentiatedMenuBean();
				dm.setId(rs.getInt(DBNames.ATT_DIFFMENU_ID));
				dm.setMotivation(rs.getString(DBNames.ATT_DIFFMENU_MOTIVATION));
				dm.setFirst(rs.getString(DBNames.ATT_DIFFMENU_FIRST));
				dm.setSecond(rs.getString(DBNames.ATT_DIFFMENU_SECOND));
				dm.setSideDish(rs.getString(DBNames.ATT_DIFFMENU_SIDEDISH));
				dm.setFruit(rs.getString(DBNames.ATT_DIFFMENU_FRUIT));
				dm.setChildInscriptionId(rs.getInt(DBNames.ATT_DIFFMENU_CHILDINSCID));
				
				diffMenus.add(dm);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return diffMenus;
	}
	
	private String useAnd(boolean pEnableAnd) {
		return pEnableAnd ? " AND " : " ";
	}
	
	public synchronized List<DifferentiatedMenuBean> getDiffMenuList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		List<DifferentiatedMenuBean> diffMenus = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM " + DBNames.TABLE_DIFFMENU;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			// constructing diffmenu list
			diffMenus = new ArrayList<DifferentiatedMenuBean>();
			while (rs.next()) {
				DifferentiatedMenuBean dm = new DifferentiatedMenuBean();
				dm.setId(rs.getInt(DBNames.ATT_DIFFMENU_ID));
				dm.setMotivation(rs.getString(DBNames.ATT_DIFFMENU_MOTIVATION));
				dm.setFirst(rs.getString(DBNames.ATT_DIFFMENU_FIRST));
				dm.setSecond(rs.getString(DBNames.ATT_DIFFMENU_SECOND));
				dm.setSideDish(rs.getString(DBNames.ATT_DIFFMENU_SIDEDISH));
				dm.setFruit(rs.getString(DBNames.ATT_DIFFMENU_FRUIT));
				dm.setChildInscriptionId(rs.getInt(DBNames.ATT_DIFFMENU_CHILDINSCID));
				
				diffMenus.add(dm);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return diffMenus;
	}
	
	public synchronized void insert(MenuBean pMenu) throws SQLException {
		/* TO-DO: get CommunicationFacade object */
		
		commFacade.insertMenu(pMenu);
	}
	
	public synchronized void update(MenuBean pMenu) throws SQLException {
		/* TO-DO: get CommunicationFacade object */
		
		commFacade.updateMenu(pMenu);
	}
	
	public synchronized void delete(MenuBean pMenu) throws SQLException {
		/* TO-DO: get CommunicationFacade object */
		
		commFacade.deleteMenu(pMenu);
	}
	
	public synchronized List<MenuBean> search(MenuBean pMenu) throws SQLException {
		/* TO-DO: get CommunicationFacade object */
		
		// if-block: move to CommunicationFacade
		if (pMenu.getFirst() != null && pMenu.getSecond() != null
				&& pMenu.getSideDish() != null && pMenu.getFruit() != null)
			pMenu.setDescription(unparseMenu(pMenu));
		
		List<MenuBean> menus = commFacade.searchMenu(pMenu);
		
		for (MenuBean mb : menus) {
			String[] menuStrings = parseMenu(mb.getDescription());
			mb.setFirst(menuStrings[0]);
			mb.setSecond(menuStrings[1]);
			mb.setSideDish(menuStrings[2]);
			mb.setFruit(menuStrings[3]);
		}
		return menus;
	}
	
	public synchronized List<MenuBean> getMenuList() throws SQLException {		
		MenuBean mb = new MenuBean();
		
		mb.setType(MenuBean.MAIN_MENU);
		List<MenuBean> menus1 = search(mb);
		
		mb.setType(MenuBean.ALTERNATIVE_MENU);
		List<MenuBean> menus2 = search(mb);
		
		menus1.addAll(menus2);
		return menus1;
	}
	
	public synchronized List<MenuBean> getMenuList(String pMenuType) throws SQLException {
		MenuBean mb = new MenuBean();
		
		if (pMenuType.equals(MenuBean.ALTERNATIVE_MENU))
			mb.setType(MenuBean.ALTERNATIVE_MENU);
		else
			mb.setType(MenuBean.MAIN_MENU);
		
		return search(mb);
	}
	
	// move to CommunicationFacade
	private String unparseMenu(MenuBean pMenu) {
		return pMenu.getFirst() + MenuBean.DELIMITER
				+ pMenu.getSecond() + MenuBean.DELIMITER
				+ pMenu.getSideDish() + MenuBean.DELIMITER
				+ pMenu.getFruit();
	}
	
	// move to CommunicationFacade
	private String[] parseMenu(String pMenuStr) {
		String menuStr = pMenuStr;
		String[] menuList = new String[4];
		
		for (int i = 3; i >= 0; i--) {
			while (menuStr.lastIndexOf(MenuBean.DELIMITER) == menuStr.length() - 1)
				menuStr = menuStr.substring(0, menuStr.length() - 1);
			menuList[i] = menuStr.substring(menuStr.lastIndexOf(MenuBean.DELIMITER) + 1);
			menuStr = menuStr.substring(0, menuStr.lastIndexOf(MenuBean.DELIMITER));
		}
		return menuList;
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
					+ DBNames.ATT_MEALREQ_MENUTYPE + ", "
					+ DBNames.ATT_MEALREQ_DATE + ", "
					+ DBNames.ATT_MEALREQ_CHILDINSCID
					+ ") VALUES(?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			//setting pstmt's parameters
			pstmt.setInt(1, pMealReq.getId());
			pstmt.setString(2, pMealReq.getRequestedMenuType());
			pstmt.setDate(3, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
			pstmt.setInt(4, pMealReq.getChildInscriptionId());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		
		// adding charge for this meal request
		// da valutare se spostarlo nella servlet
		IPaymentManager paymentManager = JDBCPaymentManager.getInstance();
		PaymentBean chargePayment = new PaymentBean();
		chargePayment.setCharge(true);
		chargePayment.setAmount(MealRequestBean.PRICE_MEALREQ);
		chargePayment.setAmountDue(MealRequestBean.PRICE_MEALREQ);
		chargePayment.setPayee("");
		chargePayment.setPaymentDescription("");
		chargePayment.setParentId(0);	// serve un metodo getParentId(Child c);
		paymentManager.addCharge(chargePayment);
	}
	
	public synchronized void update(MealRequestBean pMealReq) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "UPDATE " + DBNames.TABLE_MEAL_REQUEST + " SET "
					+ DBNames.ATT_MEALREQ_MENUTYPE + " = ?, "
					+ DBNames.ATT_MEALREQ_DATE + " = ?, "
					+ DBNames.ATT_MEALREQ_CHILDINSCID + " = ? "
					+ "WHERE " + DBNames.ATT_MEALREQ_ID + " = ?";
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			pstmt.setString(1, pMealReq.getRequestedMenuType());
			pstmt.setDate(2, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
			pstmt.setInt(3, pMealReq.getChildInscriptionId());
			pstmt.setInt(4, pMealReq.getId());
			
			pstmt.executeUpdate();
			con.commit();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
	}
	
	public synchronized void delete(MealRequestBean pMealReq) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String query = null;
		
		try {
			con = DBConnectionPool.getConnection();
			
			// constructing query string
			query = "DELETE FROM " + DBNames.TABLE_MEAL_REQUEST
					+ "WHERE " + DBNames.ATT_MEALREQ_ID + " = " + pMealReq.getId();
			
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
			
			if (pMealReq.getRequestedMenuType() != null) {
				query += useAnd(andState) + DBNames.ATT_MEALREQ_MENUTYPE + " = ?";
				andState = true;
			}
			
			if (pMealReq.getDate() != null) {
				query += useAnd(andState) + DBNames.ATT_MEALREQ_DATE + " = ?";
				andState = true;
			}
			
			if (pMealReq.getChildInscriptionId() > 0) {		// or >= 0 ???
				query += useAnd(andState) + DBNames.ATT_MEALREQ_CHILDINSCID + " = ?";
				andState = true;
			}
			
			pstmt = con.prepareStatement(query);
			
			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pMealReq.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pMealReq.getId());
				i++;
			}
			
			if (pMealReq.getRequestedMenuType() != null) {
				pstmt.setString(i, pMealReq.getRequestedMenuType());
				i++;
			}
			
			if (pMealReq.getDate() != null) {
				pstmt.setDate(i, new java.sql.Date(pMealReq.getDate().getTimeInMillis()));
				i++;
			}
			
			if (pMealReq.getChildInscriptionId() > 0) {
				pstmt.setInt(i, pMealReq.getChildInscriptionId());
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
				mr.setRequestedMenuType(rs.getString(DBNames.ATT_MEALREQ_MENUTYPE));
				
				//getting Date from ResultSet and converting it to GregorianCalendar
				GregorianCalendar sqlDate = new GregorianCalendar();
				sqlDate.setTime(rs.getDate(DBNames.ATT_MEALREQ_DATE));
				mr.setDate(sqlDate);
				
				mr.setChildInscriptionId(rs.getInt(DBNames.ATT_MEALREQ_CHILDINSCID));
				
				mealReqs.add(mr);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return mealReqs;
	}
	
	public synchronized List<MealRequestBean> getMealReqList() throws SQLException {
		MealRequestBean mr = new MealRequestBean();

		mr.setRequestedMenuType(MenuBean.MAIN_MENU);
		List<MealRequestBean> mealReqs1 = search(mr);

		mr.setRequestedMenuType(MenuBean.ALTERNATIVE_MENU);
		List<MealRequestBean> mealReqs2 = search(mr);

		mealReqs1.addAll(mealReqs2);
		return mealReqs1;
	}

	public synchronized List<MealRequestBean> getMealReqList(String pMealType) throws SQLException {
		MealRequestBean mr = new MealRequestBean();
		
		if (pMealType.equals(MenuBean.ALTERNATIVE_MENU))
			mr.setRequestedMenuType(MenuBean.ALTERNATIVE_MENU);
		else
			mr.setRequestedMenuType(MenuBean.MAIN_MENU);
		
		return search(mr);
	}
}