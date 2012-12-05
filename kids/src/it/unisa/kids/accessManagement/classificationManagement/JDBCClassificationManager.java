package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Michele Nappo, Lauri Giuseppe Giovanni
 *
 */
public class JDBCClassificationManager implements IClassificationManager {
    private static JDBCClassificationManager manager;

    // Singleton Design Pattern's implementation
    private JDBCClassificationManager() {
    }

    public static synchronized JDBCClassificationManager getInstance(){
        if(manager!=null)
            return manager;
        else
            return manager=new JDBCClassificationManager();
    }
    // end of Singleton Design Pattern's implementation

    public synchronized void insert(Classification pClassification) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_CLASSIFICATION + " ("
                            + DBNames.ATT_CLASSIFICATION_NAME + ", "
                            + DBNames.ATT_CLASSIFICATION_DATA+ ", "
                            + DBNames.ATT_CLASSIFICATION_STATUS + ", "
                            + ") VALUES(?, ?, ?)";


            //setting pstmt's parameters
            pstmt.setString(1, pClassification.getName());
            pstmt.setDate(2, new java.sql.Date(pClassification.getDate().getTimeInMillis()));
            pstmt.setString(3, pClassification.getStatus());

            pstmt.executeUpdate();
            con.commit();

            // inserimento dei risultati
            List<Result> resultList = pClassification.getResults();
            ListIterator<Result> iterList = resultList.listIterator();
            Result tmp;
            while(iterList.hasNext()) {
                tmp = iterList.next();
                query = "INSERT INTO " + DBNames.TABLE_RESULT + " ("
                            + DBNames.ATT_RESULT_REGISTRATIONCHILDID + ", "
                            + DBNames.ATT_RESULT_CLASSIFICATIONID+ ", "
                            + DBNames.ATT_RESULT_RESULT + ", "
                            + ") VALUES(?, ?, ?)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, tmp.getRegistrationChildId());
                pstmt.setInt(2, tmp.getClassificationId());
                pstmt.setBoolean(3, tmp.getResult());

                pstmt.executeUpdate();
                con.commit();

            }
        } finally {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
    }

    public synchronized void modify(Classification pClassification) throws SQLException {
            Connection con = null;
            PreparedStatement pstmt=null;
            String query;

            try {
                    con = DBConnectionPool.getConnection();

                    // constructing query string
                    query = "UPDATE " + DBNames.TABLE_CLASSIFICATION +
                                    " SET "
                                    + DBNames.ATT_CLASSIFICATION_NAME + "=?, "
                                    + DBNames.ATT_CLASSIFICATION_DATA + "=?, "
                                    + DBNames.ATT_CLASSIFICATION_STATUS + "=?, "
                                    + "WHERE " + DBNames.ATT_CLASSIFICATION_ID + "=?;";

                    pstmt = con.prepareStatement(query);

                    // setting pstmt's parameters

                    pstmt.setString(1, pClassification.getName());
                    pstmt.setDate (2,new Date(pClassification.getDate().getTimeInMillis()));
                    pstmt.setString(3, pClassification.getStatus());
                    pstmt.setInt(4, pClassification.getId());
                    
                    pstmt.executeUpdate();
                    con.commit();

            // aggiornamento dei risultati
            List<Result> resultList = pClassification.getResults();
            ListIterator<Result> iterList = resultList.listIterator();
            Result tmp;
            while(iterList.hasNext()) {
                tmp = iterList.next();
                query = "UPDATE " + DBNames.TABLE_RESULT +
                            " SET " + DBNames.ATT_RESULT_RESULT + "=? " + 
                            "WHERE " + DBNames.ATT_CLASSIFICATION_ID + " = ? AND "
                            + DBNames.ATT_CLASSIFICATION_DATA + " = ?, ";
                pstmt = con.prepareStatement(query);
                pstmt.setBoolean(1, tmp.getResult());
                pstmt.setInt(2, tmp.getRegistrationChildId());
                pstmt.setInt(3, tmp.getClassificationId());
                
                int numAggiornati = pstmt.executeUpdate();
                con.commit();
                
                if(numAggiornati == 0) {
                    // Significa che questo elemento non era presente nel db, quindi va inserito
                    query = "INSERT INTO " + DBNames.TABLE_RESULT + " ("
                            + DBNames.ATT_RESULT_REGISTRATIONCHILDID + ", "
                            + DBNames.ATT_RESULT_CLASSIFICATIONID+ ", "
                            + DBNames.ATT_RESULT_RESULT + ", "
                            + ") VALUES(?, ?, ?)";
                    pstmt = con.prepareStatement(query);
                    pstmt.setInt(1, tmp.getRegistrationChildId());
                    pstmt.setInt(2, tmp.getClassificationId());
                    pstmt.setBoolean(3, tmp.getResult());

                    pstmt.executeUpdate();
                    con.commit();
                }

            }
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                DBConnectionPool.releaseConnection(con);
        }
    }

    public synchronized void delete(Classification pClassification) throws SQLException {
            Connection con = null;
            PreparedStatement stmt = null;
            String query = null;

            try {
                    con = DBConnectionPool.getConnection();

                    // constructing query string for classification
                    query = "DELETE FROM " + DBNames.TABLE_CLASSIFICATION
                                    + "WHERE " + DBNames.ATT_CLASSIFICATION_ID + "=?;";

                    stmt = con.prepareStatement(query);
                    stmt.setInt(1, pClassification.getId());
                    stmt.executeUpdate();
                    con.commit();
                    
                    // cancellazione dei risultati per evitare incongruenze nel db
                    query = "DELETE FROM " + DBNames.TABLE_RESULT
                                    + "WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;";

                    stmt = con.prepareStatement(query);
                    stmt.setInt(1, pClassification.getId());
                    stmt.executeUpdate();
                    con.commit();
            } finally {
                    if (stmt != null)
                            stmt.close();
                    if (con != null)
                            DBConnectionPool.releaseConnection(con);
            }
    }

    public synchronized List<Classification> search(Classification pClassification) throws SQLException {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            String query = null;
            List<Classification> classificationList = null;
            List<Result> resultList = null;
            
            boolean andState = false;

            try {
                con = DBConnectionPool.getConnection();

                // constructing search query string
                query = "SELECT * "
                        + "FROM " + DBNames.TABLE_CLASSIFICATION + 
                        " WHERE";
                if (pClassification.getId() > 0) {		
                        query += " " + DBNames.ATT_CLASSIFICATION_ID + " = ?";
                        andState = true;
                }

                if (pClassification.getName() != null) {
                        query += useAnd(andState) + DBNames.ATT_CLASSIFICATION_NAME + " = ?";
                        andState = true;
                }

                if (pClassification.getDate() != null) {
                        query += useAnd(andState) + DBNames.ATT_CLASSIFICATION_DATA + " = ?";
                        andState = true;
                }

                if (pClassification.getStatus() != null) {
                    query += useAnd(andState) + DBNames.ATT_CLASSIFICATION_STATUS + " = ?";
                    andState = true;
                }

                pstmt = con.prepareStatement(query);

                // setting pstmt's parameters
                int i = 1;		// index of pstmt first argument
                if(pClassification.getId() > 0) {		// >= 0 ??
                        pstmt.setInt(i, pClassification.getId());
                        i++;
                }
                if(pClassification.getName() != null) {
                        pstmt.setString(i, pClassification.getName());
                        i++;
                }
                if(pClassification.getDate() != null) {
                        pstmt.setDate(i, new java.sql.Date(pClassification.getDate().getTimeInMillis()));
                        i++;
                }
                if(pClassification.getStatus() != null) {
                        pstmt.setString(i, pClassification.getStatus());
                        i++;
                }

                // executing select query
                rs = pstmt.executeQuery();
                con.commit();

                // constructing payment list
                classificationList = new ArrayList<Classification>();
                while(rs.next()) {
                    Classification cTmp =  new Classification();
                    cTmp.setId(rs.getInt(DBNames.ATT_CLASSIFICATION_ID));

                    cTmp.setName(rs.getString(DBNames.ATT_CLASSIFICATION_NAME));

                    //getting Date from ResultSet and converting it to GregorianCalendar
                    GregorianCalendar date = new GregorianCalendar();
                    date.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA));
                    cTmp.setDate(date);

                    cTmp.setStatus(rs.getString(DBNames.ATT_CLASSIFICATION_STATUS));

                    // ricerca dei risultati associati a questa graduatoria
                    query = "SELECT * " +
                            "FROM " + DBNames.TABLE_RESULT + 
                            " WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;";
                    if(pstmt != null) // chiusura dello stmt precedente
                        pstmt.close();
                    pstmt = con.prepareStatement(query);
                    pstmt.setInt(1, cTmp.getId());
                    rs1 = pstmt.executeQuery();

                    resultList = new ArrayList<Result>();
                    while(rs.next()) {
                        Result rTmp = new Result();
                        rTmp.setClassificationId(rs.getInt(DBNames.ATT_RESULT_CLASSIFICATIONID));
                        rTmp.setRegistrationChildId(rs.getInt(DBNames.ATT_RESULT_REGISTRATIONCHILDID));
                        rTmp.setResult(rs.getBoolean(DBNames.ATT_RESULT_RESULT));
                        resultList.add(rTmp);
                    }
                    cTmp.setResults(resultList);
                    classificationList.add(cTmp);
                }
            } finally {
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
                if(con != null)
                    DBConnectionPool.releaseConnection(con);
            }

            return classificationList;
    }

    private String useAnd(boolean pEnableAnd) {
            return pEnableAnd ? " AND " : " ";
    }

    public synchronized List<Classification> getClassificationList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        List<Classification> classification = null;

        try {
            con = DBConnectionPool.getConnection();

            query = "SELECT * " +
                    "FROM " + DBNames.TABLE_CLASSIFICATION;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // constructing payment list
            classification = new ArrayList<Classification>();
            while(rs.next()) {
                Classification p = new Classification();
                p.setId(rs.getInt(DBNames.ATT_CLASSIFICATION_ID));

                p.setName(rs.getString(DBNames.ATT_CLASSIFICATION_NAME));
                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar date = new GregorianCalendar();
                date.setTime(rs.getDate(DBNames.ATT_CLASSIFICATION_DATA));
                p.setDate(date);

                p.setStatus(rs.getString(DBNames.ATT_CLASSIFICATION_STATUS));
                classification.add(p);
            }
        } finally {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
        return classification;
    }
    
    public synchronized void deleteResult(RegistrationChild toDelete) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query = null;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE FROM " + DBNames.TABLE_RESULT
                            + "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, toDelete.getId());
            stmt.executeUpdate();
            con.commit();
        } finally {
            if(stmt != null)
                stmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
    }
    
    // metodo utilizzato dal registrationchildmanager in caso di eliminazione di una domanda di iscrizione
    // observe patter
    public synchronized void unapproveResult(RegistrationChild registrationChildId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE " + DBNames.TABLE_RESULT + " " +
                        "SET " + DBNames.ATT_RESULT_RESULT + "=? " +
                        "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";
        try {
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, registrationChildId.getId());
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                con.close();
        }
    }
    public synchronized void insertCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_CRITERIA + " ("
                            + DBNames.ATT_CRITERIA_ID + ", "
                            + DBNames.ATT_CRITERIA_DESCRIPTION + ", "
                            + DBNames.ATT_CRITERIA_WEIGHT + ", "
                            + ") VALUES(NULL, ?, ?)";


            //setting pstmt's parameters
            pstmt.setString(1, criterion.getDescription());
            pstmt.setDouble(2, criterion.getWeight());
            
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
    }

    public synchronized void modifyCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt=null;
        String query;

        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_CRITERIA +
                            " SET "
                            + DBNames.ATT_CRITERIA_ID + "=?, "
                            + DBNames.ATT_CRITERIA_WEIGHT + "=?, "
                            + "WHERE " + DBNames.ATT_CRITERIA_ID + "=?;";

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters

            pstmt.setString(1, criterion.getDescription());
            pstmt.setDouble(2,criterion.getWeight());
            pstmt.setInt(3, criterion.getId());

            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                DBConnectionPool.releaseConnection(con);
        }
    }

    public synchronized void deleteCriterion(Criterion criterion) throws SQLException {
            Connection con = null;
            PreparedStatement stmt = null;
            String query = null;

            try {
                con = DBConnectionPool.getConnection();

                // constructing query string for classification
                query = "DELETE FROM " + DBNames.TABLE_CRITERIA + " " +
                                "WHERE " + DBNames.ATT_CRITERIA_ID + "=?;";

                stmt = con.prepareStatement(query);
                stmt.setInt(1, criterion.getId());
                stmt.executeUpdate();
                con.commit();
            } finally {
                    if(stmt != null)
                        stmt.close();
                    if(con != null)
                        DBConnectionPool.releaseConnection(con);
            }
    }

    public synchronized List<Criterion> getAllCriteria() throws SQLException {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            String query = null;
            List<Criterion> criteriaList = null;
            List<Result> resultList = null;
            
            boolean andState = false;

            try {
                con = DBConnectionPool.getConnection();

                // constructing search query string
                query = "SELECT * " +
                        "FROM " + DBNames.TABLE_CRITERIA + " " +
                        "WHERE 1;";
                stmt = con.createStatement();

                // executing select query
                rs = stmt.executeQuery(query);
                con.commit();

                // constructing payment list
                criteriaList = new ArrayList<Criterion>();
                while(rs.next()) {
                    Criterion cTmp =  new Criterion();
                    cTmp.setId(rs.getInt(DBNames.ATT_CRITERIA_ID));
                    cTmp.setDescription(rs.getString(DBNames.ATT_CRITERIA_DESCRIPTION));
                    cTmp.setWeight(rs.getDouble(DBNames.ATT_CRITERIA_WEIGHT));
                    criteriaList.add(cTmp);
                }
            } finally {
                if(rs != null)
                    rs.close();
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    DBConnectionPool.releaseConnection(con);
            }

            return criteriaList;
    }

}
