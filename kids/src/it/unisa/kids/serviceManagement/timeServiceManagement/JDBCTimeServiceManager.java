package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.facade.CommunicationFacade;
import it.unisa.kids.common.facade.ICommunicationFacade;
import it.unisa.kids.communicationManagement.newsManagement.News;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
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
                    + DBNames.ATT_TIMESERVREQ_PARENTID
                    + ") VALUES(?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setString(1, pTimeServReq.getDayRequested());
            pstmt.setString(2, pTimeServReq.getServiceType());
            pstmt.setInt(3, pTimeServReq.getParentId());

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
            query = "UPDATE " + DBNames.TABLE_TIMESERV_REQUEST + " SET ";

            int i = 0;
            if (pTimeServReq.getDayRequested() != null) {

                query += DBNames.ATT_TIMESERVREQ_DAYREQ + "=?";
                i++;
            }
            if (pTimeServReq.getServiceType() != null) {
                if (i == 0) {
                    query += DBNames.ATT_TIMESERVREQ_SERVTYPE + "=?";
                } else {
                    query += "," + DBNames.ATT_TIMESERVREQ_SERVTYPE + "=?";
                }
                i++;
            }
            if (pTimeServReq.getParentId() > 0) {
                if (i == 0) {
                    query += DBNames.ATT_TIMESERVREQ_PARENTID + "=?";
                } else {
                    query += "," + DBNames.ATT_TIMESERVREQ_PARENTID + "=?";
                }
                i++;
            }
            if (i == 0) {
                query += DBNames.ATT_TIMESERVREQ_CONFIRMED + "=?";
            } else {
                query += "," + DBNames.ATT_TIMESERVREQ_CONFIRMED + "=?";
            }

            query += " WHERE " + DBNames.ATT_TIMESERVREQ_ID + "=?";
            i = 0;
            pstmt = con.prepareStatement(query);
            if (pTimeServReq.getDayRequested() != null) {


                i++;
                pstmt.setString(i, pTimeServReq.getDayRequested());
            }
            if (pTimeServReq.getServiceType() != null) {

                i++;
                pstmt.setString(i, pTimeServReq.getServiceType());
            }
            if (pTimeServReq.getParentId() > 0) {

                i++;
                pstmt.setInt(i, pTimeServReq.getParentId());
            }


            i++;
            pstmt.setInt(i, pTimeServReq.getConfirmed());
            i++;
            pstmt.setInt(i, pTimeServReq.getId());


            pstmt.executeUpdate();
            con.commit();
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
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
                    + " WHERE " + DBNames.ATT_TIMESERVREQ_ID + " = " + pTimeServReq.getId();

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
                ts.setConfirmed(rs.getInt(DBNames.ATT_TIMESERVREQ_CONFIRMED));
                //getting Date from ResultSet and converting it to GregorianCalendar

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
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<TimeServiceRequest> timeServReqs = null;
        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_TIMESERV_REQUEST;
            pstmt = con.prepareStatement(query);
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

    public void insert(ModifyTimeServiceRequest pModifyTimeServiceRequest) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_MODIFYTIMESERVICEREQUEST + " ("
                    + DBNames.ATT_MODIFYTIMESERVICEREQUEST_CHILDID + ", "
                    + DBNames.ATT_MODIFYTIMESERVICEREQUEST_MOTIVATION + ", "
                    + DBNames.ATT_MODIFYTIMESERVICEREQUEST_PARENTID + ","
                    + DBNames.ATT_MODIFYTIMESERVICEREQUEST_RANGEUSER
                    + ") VALUES(?, ?, ?,?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setInt(1, pModifyTimeServiceRequest.getIdChild());
            pstmt.setString(2, pModifyTimeServiceRequest.getMotivation());
            pstmt.setInt(3, pModifyTimeServiceRequest.getIdParent());
            pstmt.setString(4, pModifyTimeServiceRequest.getUserRange());

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

    public List<ModifyTimeServiceRequest> search(ModifyTimeServiceRequest pModifyTimeServiceRequest) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<ModifyTimeServiceRequest> modifyTimeServiceRequests = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_MODIFYTIMESERVICEREQUEST + " WHERE";
            if (pModifyTimeServiceRequest.getId() > 0) {		// or >= 0 ???
                query += " " + DBNames.ATT_MODIFYTIMESERVICEREQUEST_ID + " = ?";
                andState = true;
            }

            if (pModifyTimeServiceRequest.getIdChild() > 0) {
                query += useAnd(andState) + DBNames.ATT_MODIFYTIMESERVICEREQUEST_CHILDID + " = ?";
                andState = true;
            }

            if (pModifyTimeServiceRequest.getIdParent() > 0) {
                query += useAnd(andState) + DBNames.ATT_MODIFYTIMESERVICEREQUEST_PARENTID + " = ?";
                andState = true;
            }

            if (pModifyTimeServiceRequest.getMotivation() != null) {
                query += useAnd(andState) + DBNames.ATT_MODIFYTIMESERVICEREQUEST_MOTIVATION + " = ?";
                andState = true;
            }
            if (pModifyTimeServiceRequest.getUserRange() != null) {
                query += useAnd(andState) + DBNames.ATT_MODIFYTIMESERVICEREQUEST_RANGEUSER + " = ?";
                andState = true;
            }
            if (pModifyTimeServiceRequest.getState() != null) {
                query += useAnd(andState) + DBNames.ATT_MODIFYTIMESERVICEREQUEST_STATE + " = ?";
                andState = true;
            }

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if (pModifyTimeServiceRequest.getId() > 0) {		// or >= 0 ???
                pstmt.setInt(i, pModifyTimeServiceRequest.getId());
                i++;
            }

            if (pModifyTimeServiceRequest.getIdChild() > 0) {
                pstmt.setInt(i, pModifyTimeServiceRequest.getIdChild());
                i++;
            }

            if (pModifyTimeServiceRequest.getIdParent() > 0) {
                pstmt.setInt(i, pModifyTimeServiceRequest.getIdParent());
                i++;
            }

            if (pModifyTimeServiceRequest.getMotivation() != null) {
                pstmt.setString(i, pModifyTimeServiceRequest.getMotivation());
                i++;
            }
            if (pModifyTimeServiceRequest.getUserRange() != null) {
                pstmt.setString(i, pModifyTimeServiceRequest.getUserRange());
                i++;
            }
            if (pModifyTimeServiceRequest.getState() != null) {
                pstmt.setString(i, pModifyTimeServiceRequest.getState());
                i++;
            }


            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing timeServReq list
            modifyTimeServiceRequests= new ArrayList<ModifyTimeServiceRequest>();
            while (rs.next()) {
                ModifyTimeServiceRequest mtsr = new ModifyTimeServiceRequest();
                mtsr.setId(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_ID));
                mtsr.setIdChild(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_CHILDID));
                mtsr.setIdParent(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_PARENTID));
                mtsr.setMotivation(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_MOTIVATION));
                mtsr.setState(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_STATE));
                mtsr.setUserRange(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_RANGEUSER));
                modifyTimeServiceRequests.add(mtsr);
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
        return modifyTimeServiceRequests;
    }
    
        public List<ModifyTimeServiceRequest> getRequestModifyTimeServiceList() throws SQLException{
            Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        List<ModifyTimeServiceRequest> modifyTimeServiceRequests = null;

        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query = "SELECT * FROM " + DBNames.TABLE_MODIFYTIMESERVICEREQUEST ;

            pstmt = con.prepareStatement(query);

            
            rs = pstmt.executeQuery();
            con.commit();

            // constructing timeServReq list
            modifyTimeServiceRequests= new ArrayList<ModifyTimeServiceRequest>();
            while (rs.next()) {
                ModifyTimeServiceRequest mtsr = new ModifyTimeServiceRequest();
                mtsr.setId(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_ID));
                mtsr.setIdChild(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_CHILDID));
                mtsr.setIdParent(rs.getInt(DBNames.ATT_MODIFYTIMESERVICEREQUEST_PARENTID));
                mtsr.setMotivation(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_MOTIVATION));
                mtsr.setState(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_STATE));
                mtsr.setUserRange(rs.getString(DBNames.ATT_MODIFYTIMESERVICEREQUEST_RANGEUSER));
                modifyTimeServiceRequests.add(mtsr);
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
        return modifyTimeServiceRequests;
        }

}
