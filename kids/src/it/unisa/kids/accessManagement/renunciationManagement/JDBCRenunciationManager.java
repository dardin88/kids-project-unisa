package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public class JDBCRenunciationManager implements IRenunciationManager {
    private static JDBCRenunciationManager manager;

    private JDBCRenunciationManager() {
    }

    public static synchronized JDBCRenunciationManager getInstance() {
        if(manager == null) {
            manager = new JDBCRenunciationManager();
        }

        return manager;
    }

    public synchronized boolean insert(Renunciation renunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_RENUNCIATION + " ("
                    + DBNames.ATT_RENUNCIATION_ID + ", "
                    + DBNames.ATT_RENUNCIATION_DATE + ", "
                    + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + ", "
                    + DBNames.ATT_RENUNCIATION_REASON + ", "
                    + DBNames.ATT_RENUNCIATION_ISCONFIRMED + " "
                    + ") VALUES(NULL, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setDate(1, CommonMethod.parseDate(renunciation.getDate()));
            pstmt.setInt(2, renunciation.getRegistrationChildId());
            pstmt.setString(3, renunciation.getReason());
            pstmt.setBoolean(4, renunciation.getIsConfirmed());

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

    public synchronized boolean delete(Renunciation renunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();
            
            String query = "DELETE " +
                            "FROM " + DBNames.TABLE_RENUNCIATION + " " +
                            "WHERE " + DBNames.ATT_RENUNCIATION_ID + "=?;";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, renunciation.getId());
            
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

    public List<Renunciation> search(Renunciation renunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Renunciation> listRenunciation = new ArrayList<Renunciation>();
        StringBuilder query = new StringBuilder();

        try {
            con = DBConnectionPool.getConnection();
            
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", RC." + 
                    DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                            "FROM " + DBNames.TABLE_RENUNCIATION + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                            "WHERE RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID + " ");

            boolean andState = true;

            if (renunciation.getId() > 0) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_ID + "=?");
                andState = true;
            }
            if (renunciation.getDate() != null) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_DATE + "=?");
                andState = true;
            }
            if (renunciation.getRegistrationChildId() > 0) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=?");
                andState = true;
            }
            if (renunciation.isSetConfirmed()) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_ISCONFIRMED + "=? ");
                andState = true;
            }
            if (renunciation.getReason() != null) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_REASON + "=? ");
                andState = true;
            }
            
            query.append(";");
            
            pstmt = con.prepareStatement(query.toString());

            int paramNum = 1;

            if(renunciation.getId() > 0) {
                pstmt.setInt(paramNum, renunciation.getId());
                paramNum++;
            }

            if(renunciation.getDate() != null) {
                pstmt.setDate(paramNum, CommonMethod.parseDate(renunciation.getDate()));
                paramNum++;
            }

            if(renunciation.getRegistrationChildId() > 0) {
                pstmt.setInt(paramNum, renunciation.getRegistrationChildId());
                paramNum++;
            }
            if(renunciation.isSetConfirmed()) {
                pstmt.setBoolean(paramNum, renunciation.getIsConfirmed());
                paramNum++;
            }
            if(renunciation.getReason() != null) {
                pstmt.setString(paramNum, renunciation.getReason());
                paramNum++;
            }

            result = pstmt.executeQuery();
            con.commit();
            while(result.next()) {
                Renunciation p = new Renunciation();
                p.setId(result.getInt(DBNames.ATT_RENUNCIATION_ID));
                GregorianCalendar date;
                if(result.getDate(DBNames.ATT_RENUNCIATION_DATE) != null) {
                    date = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_RENUNCIATION_DATE));
                } else {
                    date = null;
                }
                p.setDate(date);
                p.setIsConfirmed(result.getBoolean(DBNames.ATT_RENUNCIATION_ISCONFIRMED));
                p.setRegistrationChildId(result.getInt(DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID));
                p.setReason(result.getString(DBNames.ATT_RENUNCIATION_REASON));
                
                p.setRegistrationChildFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                p.setRegistrationChildSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                p.setRegistrationChildName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRenunciation.add(p);
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

        return listRenunciation;
    }

    public List<Renunciation> search(Renunciation renunciation, String toSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Renunciation> listRenunciation = new ArrayList<Renunciation>();
        StringBuilder query = new StringBuilder();

        try {
            con = DBConnectionPool.getConnection();
            
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", RC." + 
                    DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                            "FROM " + DBNames.TABLE_RENUNCIATION + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                            "WHERE RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID + " ");

            boolean andState = true;
            boolean orState = false;

            if(renunciation.getId() > 0) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_ID + "=?");
                andState = true;
            } 
            if(renunciation.getDate() != null) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_DATE + "=?");
                andState = true;
            } 
            if (renunciation.getRegistrationChildId() > 0) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=?");
                andState = true;
            } 
            if (renunciation.isSetConfirmed()) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_ISCONFIRMED + "=? ");
                andState = true;
            } 
            if (renunciation.getReason() != null) {
                query.append(useAnd(andState) + " RE." + DBNames.ATT_RENUNCIATION_REASON + "=? ");
                andState = true;
            }
            // Le condizioni else sono posizionate qui di seguito in modo da effettuare prima 
            // tutti i controlli obbligatori (AND) e solo dopo gli or
            if(andState) {
                query.append(" AND (");
            }
            if(renunciation.getId() <= 0) {
                query.append(useOr(orState) + " RE." + DBNames.ATT_RENUNCIATION_ID + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(renunciation.getDate() == null) {
                query.append(useOr(orState) + " RE." + DBNames.ATT_RENUNCIATION_DATE + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if (renunciation.getRegistrationChildId() <= 0) {
                query.append(useOr(orState) + " RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if (!renunciation.isSetConfirmed()) {
                query.append(useOr(orState) + " RE." + DBNames.ATT_RENUNCIATION_ISCONFIRMED + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if (renunciation.getReason() == null) {
                query.append(useOr(orState) + " RE." + DBNames.ATT_RENUNCIATION_REASON + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(!orState && andState) {
                query.append("1");
            }
            if(andState) {
                query.append(")");
            }
            query.append(";");
            
            pstmt = con.prepareStatement(query.toString());

            int paramNum = 1;

            if(renunciation.getId() > 0) {
                pstmt.setInt(paramNum, renunciation.getId());
                paramNum++;
            }

            if(renunciation.getDate() != null) {
                pstmt.setDate(paramNum, CommonMethod.parseDate(renunciation.getDate()));
                paramNum++;
            }

            if(renunciation.getRegistrationChildId() > 0) {
                pstmt.setInt(paramNum, renunciation.getRegistrationChildId());
                paramNum++;
            }
            if(renunciation.isSetConfirmed()) {
                pstmt.setBoolean(paramNum, renunciation.getIsConfirmed());
                paramNum++;
            }
            if(renunciation.getReason() != null) {
                pstmt.setString(paramNum, renunciation.getReason());
                paramNum++;
            }

            result = pstmt.executeQuery();
            con.commit();
            while(result.next()) {
                Renunciation p = new Renunciation();
                p.setId(result.getInt(DBNames.ATT_RENUNCIATION_ID));
                GregorianCalendar date;
                if(result.getDate(DBNames.ATT_RENUNCIATION_DATE) != null) {
                    date = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_RENUNCIATION_DATE));
                } else {
                    date = null;
                }
                p.setDate(date);
                p.setIsConfirmed(result.getBoolean(DBNames.ATT_RENUNCIATION_ISCONFIRMED));
                p.setRegistrationChildId(result.getInt(DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID));
                p.setReason(result.getString(DBNames.ATT_RENUNCIATION_REASON));
                
                p.setRegistrationChildFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                p.setRegistrationChildSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                p.setRegistrationChildName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRenunciation.add(p);
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

        return listRenunciation;
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
    public boolean update(Renunciation pRenunciation) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        StringBuffer query = new StringBuffer();

        boolean commaState = false;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing update query string
            query.append("UPDATE " + DBNames.TABLE_RENUNCIATION + " SET ");

            if(pRenunciation.getRegistrationChildId() > 0) {
                query.append(DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=?");
                commaState = true;
            }

            if(pRenunciation.getDate() != null) {
                query.append(useSeparator(commaState) + DBNames.ATT_RENUNCIATION_DATE + "=?");
                commaState = true;
            }

            if(pRenunciation.getReason() != null) {
                query.append(useSeparator(commaState) + DBNames.ATT_RENUNCIATION_REASON + "=?");
                commaState = true;
            }

            if(pRenunciation.isSetConfirmed()) {
                query.append(useSeparator(commaState) + DBNames.ATT_RENUNCIATION_ISCONFIRMED + "=?");
                commaState = true;
            }

            if(commaState) {
                query.append(" WHERE " + DBNames.ATT_RENUNCIATION_ID + "=?;");
                
                pstmt = con.prepareStatement(query.toString());

                // setting pstmt's parameters
                int i = 1;		// index of pstmt first argument

                if(pRenunciation.getRegistrationChildId() > 0) {
                    pstmt.setInt(i, pRenunciation.getRegistrationChildId());
                    i++;
                }
                if(pRenunciation.getDate() != null) {
                    pstmt.setDate(i, CommonMethod.parseDate(pRenunciation.getDate()));
                    i++;
                }
                if(pRenunciation.getReason() != null) {
                    pstmt.setString(i, pRenunciation.getReason());
                    i++;
                }
                if(pRenunciation.isSetConfirmed()) {
                    pstmt.setBoolean(i, pRenunciation.getIsConfirmed());
                    i++;
                }
                pstmt.setInt(i, pRenunciation.getId());
                
                // executing update query
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
    
    public synchronized List<Renunciation> getListFromParent(int parentAccountId, String advanceStringToSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Renunciation> listRenunciation = new ArrayList<Renunciation>();
        StringBuilder query = new StringBuilder();

        try {
            con = DBConnectionPool.getConnection();
            
            query.append("SELECT RE.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", RC." + 
                    DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                            "FROM " + DBNames.TABLE_RENUNCIATION + " AS RE, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                            "WHERE RE." + DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID + " AND " +
                            DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?");
            if(advanceStringToSearch != null) {
                    // ADVANCED FIELD SEARCH
                query.append(" AND (RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + " LIKE '%" + advanceStringToSearch + "%' OR RC." + 
                    DBNames.ATT_REGISTRATIONCHILD_SURNAME + " LIKE '%" + advanceStringToSearch + "%' OR RC." + 
                    DBNames.ATT_REGISTRATIONCHILD_NAME + " LIKE '%" + advanceStringToSearch + "%')");
            }
            query.append(";");
            pstmt = con.prepareStatement(query.toString());
            
            pstmt.setInt(1, parentAccountId);
            result = pstmt.executeQuery();
            con.commit();
            
            while(result.next()) {
                Renunciation p = new Renunciation();
                p.setId(result.getInt(DBNames.ATT_RENUNCIATION_ID));
                GregorianCalendar date;
                if(result.getDate(DBNames.ATT_RENUNCIATION_DATE) != null) {
                    date = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_RENUNCIATION_DATE));
                } else {
                    date = null;
                }
                p.setDate(date);
                p.setIsConfirmed(result.getBoolean(DBNames.ATT_RENUNCIATION_ISCONFIRMED));
                p.setRegistrationChildId(result.getInt(DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID));
                p.setReason(result.getString(DBNames.ATT_RENUNCIATION_REASON));
                
                p.setRegistrationChildFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                p.setRegistrationChildSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                p.setRegistrationChildName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                listRenunciation.add(p);
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

        return listRenunciation;
    }
}
