package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.accessManagement.classificationManagement.IClassificationManager;
import it.unisa.kids.accessManagement.classificationManagement.JDBCClassificationManager;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
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

    private JDBCRegistrationChildManager() {
    }

    /**
     * Implementor of Singleton Data Pattern
     *
     * @return an istance of this manager
     */
    public static synchronized JDBCRegistrationChildManager getInstance() {
        if (manager == null) {
            manager = new JDBCRegistrationChildManager();
        }
        return manager;
    }

    public synchronized boolean insert(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "INSERT INTO " + DBNames.TABLE_REGISTRATIONCHILD + " ("
                + DBNames.ATT_REGISTRATIONCHILD_ID + ", "
                + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + ", "
                + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", "
                + DBNames.ATT_REGISTRATIONCHILD_NAME + ", "
                + DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + ", "
                + DBNames.ATT_REGISTRATIONCHILD_USERRANGE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + ", "
                + DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS + ", "
                + DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT + ", "
                + DBNames.ATT_REGISTRATIONCHILD_ISSICKNESSSET + ", "
                + DBNames.ATT_REGISTRATIONCHILD_ISVACCINATIONSSET + ", "
                + DBNames.ATT_REGISTRATIONCHILD_ISPRIVACYSTATEMENTSET + ", "
                + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + ") "
                + "VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, child.getParentId());
            Date dateToSet;
            if (child.getRegistrationDate() != null) {
                dateToSet = CommonMethod.parseDate(child.getRegistrationDate());
            } else {
                dateToSet = null;
            }
            pstmt.setDate(2, dateToSet);
            pstmt.setString(3, child.getRegistrationPhase());

            pstmt.setString(4, child.getSurname());
            pstmt.setString(5, child.getName());
            if (child.getBirthDate() != null) {
                dateToSet = CommonMethod.parseDate(child.getBirthDate());
            } else {
                dateToSet = null;
            }
            pstmt.setDate(6, dateToSet);    // data di nascita
            pstmt.setString(7, child.getBirthPlace());
            pstmt.setString(8, child.getFiscalCode());
            pstmt.setString(9, child.getCitizenship());
            pstmt.setString(10, child.getUserRange());

            pstmt.setString(11, child.getSickness());
            pstmt.setString(12, child.getVaccinations());
            pstmt.setString(13, child.getPrivacyStatement());
            pstmt.setString(14, child.getIsSicknessSet());
            pstmt.setString(15, child.getIsVaccinationsSet());
            pstmt.setString(16, child.getIsPrivacyStatementSet());
            pstmt.setInt(17, child.getSectionId());

            if (pstmt.executeUpdate() > 0) { // executeUpdate restituisce il numero delle righe modificato
                toReturn = true;
            }
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized boolean update(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_SURNAME + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_NAME + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_USERRANGE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_ISSICKNESSSET + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_ISVACCINATIONSSET + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_ISPRIVACYSTATEMENTSET + "=?, "
                + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, child.getSurname());
            pstmt.setString(2, child.getName());
            Date tmp;
            if (child.getBirthDate() != null) {
                tmp = CommonMethod.parseDate(child.getBirthDate());
            } else {
                tmp = null;
            }
            pstmt.setDate(3, tmp);
            pstmt.setString(4, child.getBirthPlace());
            pstmt.setString(5, child.getFiscalCode());
            pstmt.setString(6, child.getCitizenship());
            if (child.getRegistrationDate() != null) {
                tmp = CommonMethod.parseDate(child.getRegistrationDate());
            } else {
                tmp = null;
            }
            pstmt.setDate(7, tmp);
            pstmt.setString(8, child.getUserRange());
            pstmt.setString(9, child.getRegistrationPhase());
            pstmt.setInt(10, child.getParentId());

            pstmt.setString(11, child.getSickness());
            pstmt.setString(12, child.getVaccinations());
            pstmt.setString(13, child.getPrivacyStatement());
            pstmt.setString(14, child.getIsSicknessSet());
            pstmt.setString(15, child.getIsVaccinationsSet());
            pstmt.setString(16, child.getIsPrivacyStatementSet());

            pstmt.setInt(17, child.getSectionId());

            // parameters of where
            pstmt.setInt(18, child.getId());
            if (pstmt.executeUpdate() > 0) {
                toReturn = true;
            }
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized boolean delete(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "DELETE " +
                    "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " " +
                    "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        boolean toReturn = false;
        int numEditedRow;

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, child.getId());
            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();

            if (numEditedRow > 0) {
                toReturn = true;
            }
            // fase di notify (Observe Pattern) alla graduatoria
            RefinedAbstractManager abstractManager = RefinedAbstractManager.getInstance();
            IClassificationManager classificationManager = (IClassificationManager) abstractManager.getManagerImplementor(DBNames.TABLE_CLASSIFICATION);
            classificationManager.deleteAllResult(child);

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized List<RegistrationChild> search(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<RegistrationChild> listOfChildReg = new ArrayList<RegistrationChild>();
        StringBuffer query = new StringBuffer("SELECT * "
                + "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "WHERE ");
        boolean andState = false;   // Necessario per controllare se all'interno della query va inserito AND

        // PREPARAZIONE DELLA QUERY E DEI CAMPI CHE BISOGNA RICERCARE
        if (child != null) {
            if (child.getId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_ID + "=?");
                andState = true;
            }
            if (child.getParentId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?");
                andState = true;
            }
            if (child.getRegistrationDate() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + "=?");
                andState = true;
            }
            if (child.getRegistrationPhase() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=?");
                andState = true;
            }
            if (child.getSurname() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SURNAME + "=?");
                andState = true;
            }
            if (child.getName() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_NAME + "=?");
                andState = true;
            }
            if (child.getBirthDate() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + "=?");
                andState = true;
            }
            if (child.getBirthPlace() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + "=?");
                andState = true;
            }
            if (child.getFiscalCode() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + "=?");
                andState = true;
            }
            if (child.getCitizenship() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + "=?");
                andState = true;
            }
            if (child.getUserRange() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_USERRANGE + "=?");
                andState = true;
            }
            if (child.getSectionId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=?");
                andState = true;
            }
            if (child.getSickness() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=?");
                andState = true;
            }
            if (child.getVaccinations() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS + "=?");
                andState = true;
            }
            if (child.getPrivacyStatement() != null) {
                query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT + "=?");
                andState = true;
            }
        }
        if (!andState) {  // nel caso tutti i parametri sono null
            query.append("1");
        }
        query.append(" ORDER BY " + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + ", "
                + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ";");
        // Test della query
        //
        // System.out.println("Query ricerca: " + query);

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query.toString());

            // INSERIMENTO DEI PARAMETRI
            // E' IMPORTANTE NON MODIFICARE L'ORDINE, deve essere uguale alla prima fase di preparazione
            int paramNum = 1;
            if (child != null) {
                if (child.getId() > 0) {
                    pstmt.setInt(paramNum, child.getId());
                    paramNum++;
                }
                if (child.getParentId() > 0) {
                    pstmt.setInt(paramNum, child.getParentId());
                    paramNum++;
                }
                if (child.getRegistrationDate() != null) {
                    pstmt.setDate(paramNum, CommonMethod.parseDate(child.getRegistrationDate()));
                    paramNum++;
                }
                if (child.getRegistrationPhase() != null) {
                    pstmt.setString(paramNum, child.getRegistrationPhase());
                    paramNum++;
                }
                if (child.getSurname() != null) {
                    pstmt.setString(paramNum, child.getSurname());
                    paramNum++;
                }
                if (child.getName() != null) {
                    pstmt.setString(paramNum, child.getName());
                    paramNum++;
                }
                if (child.getBirthDate() != null) {
                    pstmt.setDate(paramNum, CommonMethod.parseDate(child.getBirthDate()));
                    paramNum++;
                }
                if (child.getBirthPlace() != null) {
                    pstmt.setString(paramNum, child.getBirthPlace());
                    paramNum++;
                }
                if (child.getFiscalCode() != null) {
                    pstmt.setString(paramNum, child.getFiscalCode());
                    paramNum++;
                }
                if (child.getCitizenship() != null) {
                    pstmt.setString(paramNum, child.getCitizenship());
                }
                if (child.getUserRange() != null) {
                    pstmt.setString(paramNum, child.getUserRange());
                    paramNum++;
                }
                if (child.getSectionId() > 0) {
                    pstmt.setInt(paramNum, child.getSectionId());
                    paramNum++;
                }
                if (child.getSickness() != null) {
                    pstmt.setString(paramNum, child.getSickness());
                    paramNum++;
                }
                if (child.getVaccinations() != null) {
                    pstmt.setString(paramNum, child.getVaccinations());
                    paramNum++;
                }
                if (child.getPrivacyStatement() != null) {
                    pstmt.setString(paramNum, child.getPrivacyStatement());
                    paramNum++;
                }
            }

            result = pstmt.executeQuery();
            con.commit();

            while (result.next()) {
                RegistrationChild tmpRegChild = new RegistrationChild();
                tmpRegChild.setId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_ID));
                tmpRegChild.setParentId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID));

                GregorianCalendar tmpRDGC = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE));
                tmpRegChild.setRegistrationDate(tmpRDGC);
                tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE));

                tmpRegChild.setSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                tmpRegChild.setName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));

                GregorianCalendar tmpBDGC = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE));
                tmpRegChild.setBirthDate(tmpBDGC);

                tmpRegChild.setBirthPlace(result.getString(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE));
                tmpRegChild.setCitizenship(result.getString(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP));
                tmpRegChild.setFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                tmpRegChild.setUserRange(result.getString(DBNames.ATT_REGISTRATIONCHILD_USERRANGE));
                tmpRegChild.setSickness(result.getString(DBNames.ATT_REGISTRATIONCHILD_SICKNESS));
                tmpRegChild.setVaccinations(result.getString(DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS));
                tmpRegChild.setPrivacyStatement(result.getString(DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT));
                tmpRegChild.setAdditionalNotes(result.getString(DBNames.ATT_REGISTRATIONCHILD_ADDITIONALNOTES));
                tmpRegChild.setSectionId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_SECTIONID));
                tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE));
                tmpRegChild.setIsSicknessSet(result.getString(DBNames.ATT_REGISTRATIONCHILD_ISSICKNESSSET));
                tmpRegChild.setIsVaccinationsSet(result.getString(DBNames.ATT_REGISTRATIONCHILD_ISVACCINATIONSSET));
                tmpRegChild.setIsPrivacyStatementSet(result.getString(DBNames.ATT_REGISTRATIONCHILD_ISPRIVACYSTATEMENTSET));
                listOfChildReg.add(tmpRegChild);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return listOfChildReg;
    }

    public int getNumberChildren(int parentId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        int num = 0;

        try {
            con = DBConnectionPool.getConnection();
            // constructing query string
            query = "SELECT count(*) as NumberOfChild "
                    + "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " "
                    + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=?;";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, parentId);

            rs = pstmt.executeQuery();
            con.commit();

            while (rs.next()) {
                num = rs.getInt("NumberOfChild");
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
        return num;
    }

    /**
     * Set the registrationphase of the registrationchild to confirme
     *
     * @param registrationChildId registrationchild's id to confirm
     * @return true if confirmed correctly, false otherwise
     * @throws SQLException
     */
    public boolean confirmRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_RECEIPT);
    }

    /**
     * Set the registrationphase of the registrationchild to submitted
     *
     * @param registrationChildId registrationchild's id to submit
     * @return true if submitted correctly, false otherwise
     * @throws SQLException
     */
    public boolean submitRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_SUBMITTED);
    }

    public boolean removeRegistrationChild(RegistrationChild child) throws SQLException {
        // fase di notify (Observe Pattern) alla graduatoria
        JDBCClassificationManager.getInstance().unapproveResult(child);
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_DELETED);
    }

    public boolean acceptRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_ACCEPTED);
    }

    public boolean renounceRegistrationChild(RegistrationChild child) throws SQLException {
        return changeRegistrationPhase(child, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_RENOUNCED);
    }

    public synchronized boolean completeRegistrationChild(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query;
        int numEditedRow;

        try {
            con = DBConnectionPool.getConnection();
            query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                    + "SET "
                    + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=? "
                    + DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS + "=? "
                    + DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT + "=? "
                    + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=?"
                    + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, child.getSickness());
            pstmt.setString(2, child.getVaccinations());
            pstmt.setString(3, child.getPrivacyStatement());
            pstmt.setString(4, DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_COMPLETED);
            pstmt.setInt(5, child.getId());

            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if (numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public boolean setSectionRegistrationChild(RegistrationChild child, int sectionId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        int numEditedRow;
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setInt(1, sectionId);
            pstmt.setInt(2, child.getId());

            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if (numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public boolean modifySickness(int registrationChildId, String sickness) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_SICKNESS + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        int numEditedRow;
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, sickness);
            pstmt.setInt(2, registrationChildId);

            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if (numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public boolean modifyVaccination(int registrationChildId, String vaccination) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        int numEditedRow;
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, vaccination);
            pstmt.setInt(2, registrationChildId);

            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if (numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public boolean modifyAdditionalNotes(int registrationChildId, String additionalNotes) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean toReturn = false;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_ADDITIONALNOTES + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";
        int numEditedRow;
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, additionalNotes);
            pstmt.setInt(2, registrationChildId);

            /* Test della query
             System.out.println(query);
             //*/
            numEditedRow = pstmt.executeUpdate();
            con.commit();
            if (numEditedRow > 0) {
                toReturn = true;
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized List<RegistrationChild> getReceptedRegistrationChild() throws SQLException {
        RegistrationChild tmpChild = new RegistrationChild();
        tmpChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_RECEIPT);
        List<RegistrationChild> toReturn = search(tmpChild);
        
        return toReturn;
    }
    
    private synchronized boolean changeRegistrationPhase(RegistrationChild child, String phase) throws SQLException {
        boolean toReturn;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "SET " + DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + "=? "
                + "WHERE " + DBNames.ATT_REGISTRATIONCHILD_ID + "=?;";

        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);

            // parameters of values
            pstmt.setString(1, phase);
            pstmt.setInt(2, child.getId());

            if (pstmt.executeUpdate() >= 1) { // executeUpdate ritorna il numero di righe modificate
                toReturn = true;
            } else {
                toReturn = false;
            }
            con.commit();
        } catch (SQLException se) {
            toReturn = false;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    private String useAnd(boolean pEnableAnd) {
        return pEnableAnd ? " AND " : " ";
    }

    public synchronized List<RegistrationChild> searchSectionId(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<RegistrationChild> listOfChildReg = new ArrayList<RegistrationChild>();
        StringBuffer query = new StringBuffer("SELECT * "
                + "FROM " + DBNames.TABLE_REGISTRATIONCHILD + " "
                + "WHERE ");
        boolean andState = false;   // Necessario per controllare se all'interno della query va inserito AND

        // PREPARAZIONE DELLA QUERY E DEI CAMPI CHE BISOGNA RICERCARE
        query.append(useAnd(andState) + DBNames.ATT_REGISTRATIONCHILD_SECTIONID + "=?");


        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query.toString());

            // INSERIMENTO DEI PARAMETRI
            // E' IMPORTANTE NON MODIFICARE L'ORDINE, deve essere uguale alla prima fase di preparazione
            pstmt.setInt(1, child.getSectionId());


            result = pstmt.executeQuery();
            con.commit();

            while (result.next()) {
                RegistrationChild tmpRegChild = new RegistrationChild();
                tmpRegChild.setId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_ID));
                tmpRegChild.setParentId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID));

                GregorianCalendar tmpRDGC = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE));
                tmpRegChild.setRegistrationDate(tmpRDGC);
                tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE));

                tmpRegChild.setSurname(result.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                tmpRegChild.setName(result.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));

                GregorianCalendar tmpBDGC = CommonMethod.parseGregorianCalendar(result.getDate(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE));
                tmpRegChild.setBirthDate(tmpBDGC);

                tmpRegChild.setBirthPlace(result.getString(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE));
                tmpRegChild.setCitizenship(result.getString(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP));
                tmpRegChild.setFiscalCode(result.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                tmpRegChild.setUserRange(result.getString(DBNames.ATT_REGISTRATIONCHILD_USERRANGE));

                tmpRegChild.setSickness(result.getString(DBNames.ATT_REGISTRATIONCHILD_SICKNESS));
                tmpRegChild.setVaccinations(result.getString(DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS));
                tmpRegChild.setPrivacyStatement(result.getString(DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT));
                tmpRegChild.setAdditionalNotes(result.getString(DBNames.ATT_REGISTRATIONCHILD_ADDITIONALNOTES));

                tmpRegChild.setSectionId(result.getInt(DBNames.ATT_REGISTRATIONCHILD_SECTIONID));

                listOfChildReg.add(tmpRegChild);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return listOfChildReg;
    }
}