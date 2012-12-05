package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.accessManagement.classificationManagement.JDBCClassificationManager;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementor of IRegistrationChildManager with JDBC to a Relational DataBase
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class JDBCRegistrationChildManager implements IRegistrationChildManager {
    private static JDBCRegistrationChildManager manager;

    private JDBCRegistrationChildManager(){
    }

    public static synchronized JDBCRegistrationChildManager getInstance() {
        if(manager == null) {
            manager = new JDBCRegistrationChildManager();
        }
        return manager;
    }

    public synchronized RegistrationChild insert(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO " + DBNames.TABLE_REGISTRATIONCHILD + " (" +
                      DBNames.ATT_REGISTRATIONCHILD_ID + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_NAME + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_USERRANGE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_SICKNESS + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_SECTIONID + ") " +
                      "VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?);"; // escludendo l'id i campi da inserire sono 12		

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, child.getSurname());
            pstmt.setString(2, child.getName());
            Date tmp;
            if(child.getBirthDate() != null)
                tmp = new Date(child.getBirthDate().getTimeInMillis());
            else
                tmp = null;
            
            pstmt.setDate(3, tmp);
            pstmt.setString(4, child.getBirthPlace());
            pstmt.setString(5, child.getFiscalCode());
            pstmt.setString(6, child.getCitizenship());
            pstmt.setString(7, child.getUserRange());
            if(child.getRegistrationDate() != null)
                tmp = new Date(child.getRegistrationDate().getTimeInMillis());
            else
                tmp = null;
            
            pstmt.setDate(8, tmp);
            pstmt.setString(9, child.getSickness());
            pstmt.setString(10, child.getRegistrationPhase());
            pstmt.setInt(11, child.getParentId());
            pstmt.setInt(12, child.getSectionId());
            
            pstmt.executeUpdate();
            con.commit();
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }	
        return child;
    }
	  
    public synchronized RegistrationChild delete(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM " + DBNames.TABLE_REGISTRATIONCHILD + " " +
                            "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, child.getId());
            /* Test della query
            System.out.println(query);
            //*/
            pstmt.executeUpdate();
            con.commit();
            
            // fase di notify (Observe Pattern) alla graduatoria
            JDBCClassificationManager.getInstance().deleteResult(child);
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }	

        return child;
    }
	  
    public synchronized List<RegistrationChild> search(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<RegistrationChild> listOfChildReg = new ArrayList<RegistrationChild>();		
        StringBuffer query = new StringBuffer("SELECT * " +
                        "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " " + 
                        "WHERE ");				
        boolean andState = false;   // Necessario per controllare se all'interno della query va inserito AND
        
        // PREPARAZIONE DELLA QUERY E DEI CAMPI CHE BISOGNA RICERCARE
        if(child.getId() > 0) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_ID + "=?");
            andState = true;
        }
        if(child.getSurname() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SURNAME + "=?");
            andState = true;
        }
        if(child.getName() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_NAME + "=?");
            andState = true;
        }
        if(child.getBirthDate() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + "=?");
            andState = true;
        }
        if(child.getBirthPlace() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + "=?");
            andState = true;
        }
        if(child.getFiscalCode() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + "=?");
            andState = true;
        }
        if(child.getCitizenship() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + "=?");
            andState = true;
        } 
        if(child.getSickness() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=?");
            andState = true;
        }
        if(child.getRegistrationDate() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + "=?");
            andState = true;
        }
        if(child.getUserRange() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_USERRANGE + "=?");
            andState = true;
        }
        if(child.getRegistrationPhase() != null) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=?");
            andState = true;
        }
        if(child.getParentId() > 0) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?");
            andState = true;
        }
        if(child.getSectionId() > 0) {
            query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=?");
            andState = true;
        }
        if(!andState) {  // nel caso tutti i parametri sono null
            query.append("1");
        }
        query.append(";");
        /* Test della query
        System.out.println(query);
        //*/
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query.toString());
            
            // INSERIMENTO DEI PARAMETRI
            // E' IMPORTANTE NON MODIFICARE L'ORDINE, deve essere uguale alla prima fase di preparazione
            int paramNum = 1;
            if(child.getId() > 0) {
                pstmt.setInt(paramNum, child.getId());
                paramNum++;
            }
            if(child.getSurname() != null) {
                pstmt.setString(paramNum, child.getSurname());
                paramNum++;
            }
            if(child.getName() != null) {
                pstmt.setString(paramNum, child.getName());
                paramNum++;
            }
            if(child.getBirthDate() != null) {
                pstmt.setDate(paramNum, new Date(child.getBirthDate().getTimeInMillis()));
                paramNum++;
            }
            if(child.getBirthPlace() != null) {
                pstmt.setString(paramNum, child.getBirthPlace());
                paramNum++;
            }
            if(child.getFiscalCode() != null) {
                pstmt.setString(paramNum, child.getFiscalCode());
                paramNum++;
            }
            if(child.getCitizenship() != null) {
                pstmt.setString(paramNum, child.getCitizenship());
            } 
            if(child.getSickness() != null) {
                pstmt.setString(paramNum, child.getSickness());
                paramNum++;
            }
            if(child.getRegistrationDate() != null) {
                pstmt.setDate(paramNum, new Date(child.getRegistrationDate().getTimeInMillis()));
                paramNum++;
            }
            if(child.getUserRange() != null) {
                pstmt.setString(paramNum, child.getUserRange());
                paramNum++;
            }
            if(child.getRegistrationPhase() != null) {
                pstmt.setString(paramNum, child.getRegistrationPhase());
                paramNum++;
            }
            if(child.getParentId() > 0) {
                pstmt.setInt(paramNum, child.getParentId());
                paramNum++;
            }
            if(child.getSectionId() > 0) {
                pstmt.setInt(paramNum, child.getSectionId());
            }
            
            result = pstmt.executeQuery();
            con.commit();
            
            while(result.next()) {
                RegistrationChild tmpRegChild = new RegistrationChild();		  
                tmpRegChild.setId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_ID));
                tmpRegChild.setSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                tmpRegChild.setName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
                GregorianCalendar tmpBDGC;
                if(result.getDate(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE) != null) {
                    tmpBDGC = new GregorianCalendar();
                    tmpBDGC.setTime(result.getDate(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE));
                } else {
                    tmpBDGC = null;
                }
                tmpRegChild.setBirthDate(tmpBDGC);
                
                tmpRegChild.setBirthPlace(result.getString(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE));
                tmpRegChild.setCitizenship(result.getString(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP));
                tmpRegChild.setFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                tmpRegChild.setSickness(result.getString(DBNames.ATT_REGISTRATIONCHILD_SICKNESS));
                
                GregorianCalendar tmpRDGC = new GregorianCalendar();
                tmpRDGC.setTime(result.getDate(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE));
                tmpRegChild.setRegistrationDate(tmpRDGC);
                
                tmpRegChild.setUserRange(result.getString(DBNames.ATT_REGISTRATIONCHILD_USERRANGE));
                tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE));

                tmpRegChild.setParentId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID));
                tmpRegChild.setSectionId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_SECTIONID));

                listOfChildReg.add(tmpRegChild);
            }
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }
        return listOfChildReg;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public synchronized RegistrationChild update(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " " + 
                        "SET " + DBNames.ATT_REGISTRATIONCHILD_SURNAME + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_NAME + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_USERRANGE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?, " +
                                DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=? " +
                        "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;"; 
			
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, child.getSurname());
            pstmt.setString(2, child.getName());
            Date tmp;
            if(child.getBirthDate() != null)
                tmp = new Date(child.getBirthDate().getTimeInMillis());
            else
                tmp = null;
            pstmt.setDate(3, tmp);
            pstmt.setString(4, child.getBirthPlace());
            pstmt.setString(5, child.getFiscalCode());
            pstmt.setString(6, child.getCitizenship());
            pstmt.setString(7, child.getSickness());
            if(child.getRegistrationDate() != null)
                tmp = new Date(child.getRegistrationDate().getTimeInMillis());
            else
                tmp = null;
            pstmt.setDate(8, tmp);
            pstmt.setString(9, child.getUserRange());
            pstmt.setString(10, child.getRegistrationPhase());
            pstmt.setInt(11, child.getParentId());
            pstmt.setInt(12, child.getSectionId());

            // parameters of where
            pstmt.setInt(13, child.getId());
            
            /* Test della query
            System.out.println(query);
            //*/
            pstmt.executeUpdate();
            con.commit();
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }	

        return child;
    }
          
    /**
     * Get the number of child that have registered the Parent
     * 
     * @param parentId - id of Parent
     * @return number of child
     * @throws SQLException 
     */
    public int getNumberChildren(int parentId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        int num = 0;
        
        try {
            con = DBConnectionPool.getConnection();
            // constructing query string
            query = "SELECT count(*) as NumberOfChild " +
                            "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " " +
                            "WHERE " + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?;";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, parentId);
            
            rs = pstmt.executeQuery();
            con.commit();
            
            while(rs.next()) {
                num = rs.getInt("NumberOfChild");
            }
        } finally {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
        return num;
    }
    
    private boolean changeRegistrationPhase(RegistrationChild child, String phase) throws SQLException {
        boolean toReturn;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " " + 
                        "SET " + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=? " +
                        "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;"; 
			
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, phase);
            pstmt.setInt(2, child.getId());
            
            if(pstmt.executeUpdate() >= 1) // executeUpdate ritorna il numero di righe modificate
                toReturn = true;
            else
                toReturn = false;
            con.commit();
        } catch(SQLException se) {
            toReturn = false;
        } finally {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
        return toReturn;
    }
    
    /**
     * Set the registrationphase of the registrationchild to confirme
     * 
     * @param registrationChildId registrationchild's id to confirm
     * @return true if confirmed correctly, false otherwise
     * @throws SQLException 
     */
    public boolean confirmRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_CONFIRMED);
    }
    
    /**
     * Set the registrationphase of the registrationchild to submitted
     * 
     * @param registrationChildId registrationchild's id to submit
     * @return true if submitted correctly, false otherwise
     * @throws SQLException 
     */
    public boolean submitRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED);
    }
    
    public boolean removeRegistrationChild(RegistrationChild child) throws SQLException {
        // fase di notify (Observe Pattern) alla graduatoria
        JDBCClassificationManager.getInstance().unapproveResult(child);
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DELETED);
    }
}