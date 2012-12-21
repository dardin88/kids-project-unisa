package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public class JDBCRecoursesManager implements IRecoursesManager {
    private static JDBCRecoursesManager manager;

    private JDBCRecoursesManager(){
    }

    public static JDBCRecoursesManager getInstance() {
        if(manager != null) {
            return manager;
        } else {
            return manager = new JDBCRecoursesManager();
        }
    }

    public synchronized boolean insert(Recourse ricorso) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            query = "INSERT INTO " + DBNames.TABLE_RECOURSE + " (" +
                            DBNames.ATT_RECOURSE_ID + ", " +
                            DBNames.ATT_RECOURSE_DATA + ", " +
                            DBNames.ATT_RECOURSE_REASON + ", " + 
                            DBNames.ATT_RECOURSE_VALUTATION + ", " +
                            DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + ") " +
                            "VALUES(NULL,?,?,?,?);"; 
            
            pstmt = con.prepareCall(query);
            
            //setting pstmt's parameters
            pstmt.setDate(1, CommonMethod.parseDate(ricorso.getDate()));
            pstmt.setString (2, ricorso.getReason());
            pstmt.setString(3, ricorso.getValutation());
            pstmt.setInt(4, ricorso.getRegistrationChildId());

            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if(numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized boolean update(Recourse recourse) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt=null;
        StringBuilder query = new StringBuilder();
        int numEditedRow;
        boolean toReturn = false;
        boolean useComma = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string

            query.append("UPDATE " + DBNames.TABLE_RECOURSE + " SET ");
            
            if(recourse.getDate() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_RECOURSE_DATA + "=?");
                useComma = true;
            }
            if(recourse.getReason() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_RECOURSE_REASON + "=?");
                useComma = true;
            }
            if(recourse.getValutation() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_RECOURSE_VALUTATION + "=?");
                useComma = true;
            }
            if(recourse.getRegistrationChildId() > 0) {
                query.append(useSeparator(useComma) + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=?");
                useComma = true;
            }
            if(useComma) {
                query.append("WHERE " + DBNames.ATT_RECOURSE_ID + "=?;");

                pstmt = con.prepareStatement(query.toString());

                // setting pstmt's parameters

                int param = 1;
                if(recourse.getDate() != null) {
                    pstmt.setDate(param, CommonMethod.parseDate(recourse.getDate()));
                    param++;
                }
                if(recourse.getReason() != null) {
                    pstmt.setString(param, recourse.getReason());
                    param++;
                }
                if(recourse.getValutation() != null) {
                    pstmt.setString(param, recourse.getValutation());
                    param++;
                }
                if(recourse.getRegistrationChildId() > 0) {
                    pstmt.setInt(param, recourse.getRegistrationChildId());
                    param++;
                }
                pstmt.setInt(param, recourse.getId());

                numEditedRow = pstmt.executeUpdate();
                con.commit();
                if(numEditedRow > 0) {
                    toReturn = true;
                }
            }
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public boolean delete(Recourse recourse) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        int numEditedRow;
        boolean toReturn = false;

        try {
            con = DBConnectionPool.getConnection();

            String query = "DELETE FROM " + DBNames.TABLE_RECOURSE + " " +
                            "WHERE " + DBNames.ATT_RECOURSE_ID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, recourse.getId());
            numEditedRow = stmt.executeUpdate();
            con.commit();
            if(numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    private synchronized boolean valuta(Recourse recourse, boolean valutazione) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        int numEditedRow;
        boolean toReturn = false;

        try {
            con = DBConnectionPool.getConnection();

            String query = "UPDATE " + DBNames.TABLE_RECOURSE + " " +
                            "SET " + DBNames.ATT_RECOURSE_VALUTATION + "=? " +
                            "WHERE " + DBNames.ATT_RECOURSE_ID + "=?;";

            stmt = con.prepareStatement(query);
            if(valutazione) {
                stmt.setString(1, DBNames.ATT_RECOURSE_VALUTATION_ACCEPTED);
            } else {
                stmt.setString(1, DBNames.ATT_RECOURSE_VALUTATION_REFUSED);
            }
            stmt.setInt(2, recourse.getId());
            
            numEditedRow = stmt.executeUpdate();
            con.commit();
            if(numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }

        return toReturn;
    }

    public boolean accept(Recourse ricorso) throws SQLException {
        return valuta(ricorso, true);
    }
    
    public boolean refuse(Recourse ricorso) throws SQLException {
        return valuta(ricorso, false);
    }

    public synchronized List<Recourse> search(Recourse ricorso) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder();
        List<Recourse> listRecourse = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", " + 
                    "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " + 
                    "FROM " + DBNames.TABLE_RECOURSE + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " + 
                    "WHERE RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID);
            
            boolean andState = true;
            
            if(ricorso.getId() > 0) {		
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_ID + "=?");
                andState = true;
            }
            if(ricorso.getDate() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_DATA + "=?");
                andState = true;
            }

            if(ricorso.getReason() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_REASON + "=?");
                andState = true;
            }
            if(ricorso.getValutation() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_VALUTATION + "=?");
                andState = true;
            }
            if(ricorso.getRegistrationChildId() > 0) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=?");
                andState = true;
            }
            if(!andState) {
                query.append("1");
            }
            query.append(";");
            
            pstmt = con.prepareStatement(query.toString());

            // setting pstmt's parameters
            int numParam = 1;		// index of pstmt first argument
            if(ricorso.getId() > 0) {
                pstmt.setInt(numParam, ricorso.getId());
                numParam++;
            }
            if(ricorso.getDate() != null) {
                pstmt.setDate(numParam, CommonMethod.parseDate(ricorso.getDate()));
                numParam++;
            }
            if(ricorso.getReason() != null) {
                pstmt.setString(numParam, ricorso.getReason());
                numParam++;
            }
            if(ricorso.getValutation() != null) {
                pstmt.setString(numParam, ricorso.getValutation());
                numParam++;
            }
            if(ricorso.getRegistrationChildId() > 0) {
                pstmt.setInt(numParam, ricorso.getRegistrationChildId());
            }
            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing payment list
            listRecourse = new ArrayList<Recourse>();
            while(rs.next()) {
                Recourse tmpRecourse = new Recourse();
                tmpRecourse.setId(rs.getInt("RE." + DBNames.ATT_RECOURSE_ID));
                tmpRecourse.setDate(CommonMethod.parseGregorianCalendar(rs.getDate("RE." + DBNames.ATT_RECOURSE_DATA)));
                tmpRecourse.setReason(rs.getString("RE." + DBNames.ATT_RECOURSE_REASON));
                tmpRecourse.setValutation(rs.getString("RE." + DBNames.ATT_RECOURSE_VALUTATION));

                tmpRecourse.setRegistrationChildId(rs.getInt("RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID));
                
                tmpRecourse.setRegistrationChildFiscalCode(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                tmpRecourse.setRegistrationChildSurname(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                tmpRecourse.setRegistrationChildName(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRecourse.add(tmpRecourse);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return listRecourse;
    }

    public synchronized List<Recourse> search(Recourse ricorso, String advanceStringToSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder();
        List<Recourse> listRecourse = null;

        boolean andState = true;
        boolean orState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", " + 
                    "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " + 
                    "FROM " + DBNames.TABLE_RECOURSE + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " + 
                    "WHERE RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID);
            
            if(ricorso.getId() > 0) {		
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_ID + "=?");
                andState = true;
            } 
            if(ricorso.getDate() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_DATA + "=?");
                andState = true;
            } 
            if(ricorso.getReason() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_REASON + "=?");
                andState = true;
            } 
            if(ricorso.getValutation() != null) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_VALUTATION + "=?");
                andState = true;
            } 
            if(ricorso.getRegistrationChildId() > 0) {
                query.append(useAnd(andState) + "RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=?");
                andState = true;
            } 
            // Le condizioni else sono posizionate qui di seguito in modo da effettuare prima 
            // tutti i controlli obbligatori (AND) e solo dopo gli or
            if(andState) {
                query.append(" AND (");
            }
            if(ricorso.getId() <= 0) {
                query.append(useOr(orState) + "RE." + DBNames.ATT_RECOURSE_ID + " LIKE '%" + advanceStringToSearch + "%'");
                orState = true;
            }
            if(ricorso.getDate() == null) {
                query.append(useOr(orState) + "RE." + DBNames.ATT_RECOURSE_DATA + " LIKE '%" + advanceStringToSearch + "%'");
                orState = true;
            }
            if(ricorso.getReason() == null) {
                query.append(useOr(orState) + "RE." + DBNames.ATT_RECOURSE_REASON + " LIKE '%" + advanceStringToSearch + "%'");
                orState = true;
            }
            if(ricorso.getRegistrationChildId() <= 0) {
                query.append(useOr(orState) + "RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + " LIKE '%" + advanceStringToSearch + "%'");
                orState = true;
            }
            if(!advanceStringToSearch.equals("")) {
                query.append(useOr(orState) + "RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + " LIKE '%" + advanceStringToSearch + "%' OR " + 
                        "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + " LIKE '%" + advanceStringToSearch + "%' OR " +
                        "RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " LIKE '%" + advanceStringToSearch + "%'");
            }
            if(andState) {
                query.append(")");
            }
            query.append(";");
            
            pstmt = con.prepareStatement(query.toString());

            // setting pstmt's parameters
            int numParam = 1;		// index of pstmt first argument
            if(ricorso.getId() > 0) {
                pstmt.setInt(numParam, ricorso.getId());
                numParam++;
            }
            if(ricorso.getDate() != null) {
                pstmt.setDate(numParam, CommonMethod.parseDate(ricorso.getDate()));
                numParam++;
            }
            if(ricorso.getReason() != null) {
                pstmt.setString(numParam, ricorso.getReason());
                numParam++;
            }
            if(ricorso.getValutation() != null) {
                pstmt.setString(numParam, ricorso.getValutation());
                numParam++;
            }
            if(ricorso.getRegistrationChildId() > 0) {
                pstmt.setInt(numParam, ricorso.getRegistrationChildId());
            }
            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            // constructing payment list
            listRecourse = new ArrayList<Recourse>();
            while(rs.next()) {
                Recourse tmpRecourse = new Recourse();
                tmpRecourse.setId(rs.getInt("RE." + DBNames.ATT_RECOURSE_ID));
                tmpRecourse.setDate(CommonMethod.parseGregorianCalendar(rs.getDate("RE." + DBNames.ATT_RECOURSE_DATA)));
                tmpRecourse.setReason(rs.getString("RE." + DBNames.ATT_RECOURSE_VALUTATION));
                tmpRecourse.setValutation(rs.getString("RE." + DBNames.ATT_RECOURSE_VALUTATION));

                tmpRecourse.setRegistrationChildId(rs.getInt("RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID));
                
                tmpRecourse.setRegistrationChildFiscalCode(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                tmpRecourse.setRegistrationChildSurname(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                tmpRecourse.setRegistrationChildName(rs.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRecourse.add(tmpRecourse);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return listRecourse;
    }
    
    private String useAnd(boolean pEnableAnd) {
            return pEnableAnd ? " AND " : " ";
    }
    
    private String useOr(boolean pEnableAnd) {
            return pEnableAnd ? " OR " : " ";
    }
      
    private String useSeparator(boolean useSeparator) {
        return useSeparator ? ", " : " ";
    }
    
    public int getNumberRecourseToEvaluate() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder();
        int toReturn = 0;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query.append("SELECT COUNT(*) AS NumberRecourseToEvaluate " + 
                    "FROM " + DBNames.TABLE_RECOURSE + " " + 
                    "WHERE " + DBNames.ATT_RECOURSE_VALUTATION + "=?;");
            
            pstmt = con.prepareStatement(query.toString());
            
            pstmt.setString(1, DBNames.ATT_RECOURSE_VALUTATION_TOEVALUATE);
            
            // executing select query
            rs = pstmt.executeQuery();
            con.commit();

            rs.next();
            toReturn = rs.getInt("NumberRecourseToEvaluate");
            
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }
    
    public List<Recourse> getListFromParent(int parentAccountId, String advanceStringToSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Recourse> listRecourse = new ArrayList<Recourse>();
        StringBuilder query = new StringBuilder();

        try {
            con = DBConnectionPool.getConnection();
            
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", " + 
                            "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", " +
                            "RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                        "FROM " + DBNames.TABLE_RECOURSE + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                        "WHERE RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID + " AND " +
                            DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?");
            if(advanceStringToSearch != null) {
                // ADVANCED FIELD SEARCH
                query.append(" AND (RE." + DBNames.ATT_RECOURSE_DATA + " LIKE '%" + advanceStringToSearch + "%' OR " + 
                        "RE." + DBNames.ATT_RECOURSE_VALUTATION + " LIKE '%" + advanceStringToSearch + "%' OR " + 
                        "RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + " LIKE '%" + advanceStringToSearch + "%' OR " + 
                        "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + " LIKE '%" + advanceStringToSearch + "%' OR " +
                        "RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " LIKE '%" + advanceStringToSearch + "%')");
            }
            query.append(";");
            pstmt = con.prepareStatement(query.toString());
            
            pstmt.setInt(1, parentAccountId);
            result = pstmt.executeQuery();
            con.commit();
            
            while(result.next()) {
                Recourse p = new Recourse();
                p.setId(result.getInt("RE." + DBNames.ATT_RECOURSE_ID));
                p.setDate(CommonMethod.parseGregorianCalendar(result.getDate("RE." + DBNames.ATT_RECOURSE_DATA)));
                p.setValutation(result.getString("RE." + DBNames.ATT_RECOURSE_VALUTATION));
                p.setReason(result.getString("RE." + DBNames.ATT_RECOURSE_REASON));
                
                p.setRegistrationChildId(result.getInt("RE." + DBNames.ATT_RECOURSE_REGISTRATIONCHILDID));
                
                p.setRegistrationChildFiscalCode(result.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                p.setRegistrationChildSurname(result.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                p.setRegistrationChildName(result.getString("RC." + DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRecourse.add(p);
            }
        } finally {
            if(result != null) {
                result.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }

        return listRecourse;
    }
}
