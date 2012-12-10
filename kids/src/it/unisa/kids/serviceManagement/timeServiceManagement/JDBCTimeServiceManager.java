package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.facade.CommunicationFacade;
import it.unisa.kids.common.facade.ICommunicationFacade;
import it.unisa.kids.communicationManagement.newsManagement.News;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class JDBCTimeServiceManager implements ITimeServiceManager {

    // Singleton Design Pattern's implementation
    private static JDBCTimeServiceManager manager;

    private JDBCTimeServiceManager() {
    }

    public static JDBCTimeServiceManager getInstance() {
        if (manager == null) {
            manager = new JDBCTimeServiceManager();
        }
        return manager;
    }
    // end of Singleton Design Pattern's implementation

    public synchronized void insert(News pTimeServ) throws SQLException {
        ICommunicationFacade commFacade = new CommunicationFacade();

        commFacade.insert(pTimeServ);
    }

    public synchronized void update(News pTimeServ) throws SQLException {
        ICommunicationFacade commFacade = new CommunicationFacade();

        commFacade.update(pTimeServ);
    }

    public void delete(News pTimeServ) throws SQLException {
        ICommunicationFacade commFacade = new CommunicationFacade();

        commFacade.delete(pTimeServ);
    }

    public List<News> search(News pTimeServ) throws SQLException {
        ICommunicationFacade commFacade = new CommunicationFacade();

        List<News> timeServices = commFacade.search(pTimeServ);
        return timeServices;
    }

    public List<News> getTimeServiceList() throws SQLException {
        News nb = new News();

        nb.setType(News.OPENING_TIMESERV);
        List<News> timeServices1 = search(nb);

        nb.setType(News.CLOSING_TIMESERV);
        List<News> timeServices2 = search(nb);

        timeServices1.addAll(timeServices2);
        return timeServices1;
    }

    public List<News> getTimeServiceList(String pTimeServType) throws SQLException {
        News nb = new News();

        if (pTimeServType.equals(News.CLOSING_TIMESERV)) {
            nb.setType(News.CLOSING_TIMESERV);
        } else {
            nb.setType(News.OPENING_TIMESERV);
        }

        return search(nb);
    }

    public void insert(TimeServiceRequest pTimeServReq) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_TIMESERV_REQUEST + " ("
                    + DBNames.ATT_TIMESERVREQ_DAYREQ + ", "
                    + DBNames.ATT_TIMESERVREQ_SERVTYPE + ", "
                    + DBNames.ATT_TIMESERVREQ_REQTIME + ", "
                    + DBNames.ATT_TIMESERVREQ_PARENTID
                    + ") VALUES(?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setString(1, pTimeServReq.getDayRequested());
            pstmt.setString(2, pTimeServReq.getServiceType());
            pstmt.setTime(3, pTimeServReq.getRequestTime());
            pstmt.setInt(4, pTimeServReq.getParentId());

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

    public void update(TimeServiceRequest pTimeServReq) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_TIMESERV_REQUEST + " SET "
                    + DBNames.ATT_TIMESERVREQ_DAYREQ + " = ?, "
                    + DBNames.ATT_TIMESERVREQ_SERVTYPE + " = ?, "
                    + DBNames.ATT_TIMESERVREQ_DATE + " = ?, "
                    + DBNames.ATT_TIMESERVREQ_REQTIME + " = ?, "
                    + DBNames.ATT_TIMESERVREQ_PARENTID + " = ? "
                    + "WHERE " + DBNames.ATT_TIMESERVREQ_ID + " = ?";

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            pstmt.setString(1, pTimeServReq.getDayRequested());
            pstmt.setString(2, pTimeServReq.getServiceType());
            pstmt.setDate(3, new java.sql.Date(pTimeServReq.getDate().getTimeInMillis()));
            pstmt.setTime(4, pTimeServReq.getRequestTime());
            pstmt.setInt(5, pTimeServReq.getParentId());
            pstmt.setInt(6, pTimeServReq.getId());

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

    public void delete(TimeServiceRequest pTimeServReq) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "DELETE FROM " + DBNames.TABLE_TIMESERV_REQUEST
                    + "WHERE " + DBNames.ATT_TIMESERVREQ_ID + " = " + pTimeServReq.getId();

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

    public List<TimeServiceRequest> search(TimeServiceRequest pTimeServReq)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<TimeServiceRequest> timeServReqs = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_TIMESERV_REQUEST + " WHERE";
            if (pTimeServReq.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_TIMESERVREQ_ID + " = ?";
                andState = true;
            }

            if (pTimeServReq.getDayRequested() != null) {
                query += useAnd(andState) + DBNames.ATT_TIMESERVREQ_DAYREQ + " = ?";
                andState = true;
            }

            if (pTimeServReq.getServiceType() != null) {
                query += useAnd(andState) + DBNames.ATT_TIMESERVREQ_SERVTYPE + " = ?";
                andState = true;
            }

           
            
            if (pTimeServReq.getRequestTime() != null) {
                query += useAnd(andState) + DBNames.ATT_TIMESERVREQ_REQTIME + " = ?";
            }

            if (pTimeServReq.getParentId() > 0) {		// or >= 0 ???
                query += useAnd(andState) + DBNames.ATT_TIMESERVREQ_PARENTID + " = ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pTimeServReq.getId() > 0) {		// >= 0 ??
                pstmt.setInt(i, pTimeServReq.getId());
                i++;
            }

            if (pTimeServReq.getDayRequested() != null) {
                pstmt.setString(i, pTimeServReq.getDayRequested());
                i++;
            }

            if (pTimeServReq.getServiceType() != null) {
                pstmt.setString(i, pTimeServReq.getServiceType());
                i++;
            }

            
            
            if (pTimeServReq.getRequestTime() != null) {
                pstmt.setTime(i, pTimeServReq.getRequestTime());
                i++;
            }

            if (pTimeServReq.getParentId() > 0) {
                pstmt.setInt(i, pTimeServReq.getParentId());
                i++;
            }

            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing timeServReq list
            timeServReqs = new ArrayList<TimeServiceRequest>();
            while (rs.next()) {
                TimeServiceRequest ts = new TimeServiceRequest();
                ts.setId(rs.getInt(DBNames.ATT_TIMESERVREQ_ID));
                ts.setDayRequested(rs.getString(DBNames.ATT_TIMESERVREQ_DAYREQ));
                ts.setServiceType(rs.getString(DBNames.ATT_TIMESERVREQ_SERVTYPE));

                //getting Date from ResultSet and converting it to GregorianCalendar
                
                ts.setRequestTime(rs.getTime(DBNames.ATT_TIMESERVREQ_REQTIME));
                ts.setParentId(rs.getInt(DBNames.ATT_TIMESERVREQ_PARENTID));

                timeServReqs.add(ts);
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
        return timeServReqs;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    @Override
    public List<TimeServiceRequest> getTimeServRequestList()
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}
