package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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

    public static synchronized JDBCClassificationManager getInstance() {
        if(manager != null)
            return manager;
        else
            return manager = new JDBCClassificationManager();
    }
    // end of Singleton Design Pattern's implementation

    public synchronized boolean insert(Classification classification) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_CLASSIFICATION + " ("
                            + DBNames.ATT_CLASSIFICATION_ID + ", "
                            + DBNames.ATT_CLASSIFICATION_NAME + ", "
                            + DBNames.ATT_CLASSIFICATION_DATA+ ", "
                            + DBNames.ATT_CLASSIFICATION_STATUS + ", "
                            + ") VALUES(NULL, ?, ?, ?);";
            
            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setString(1, classification.getName());
            Date dateToSet;
            if(classification.getDate() != null) {
                dateToSet = new Date(classification.getDate().getTimeInMillis());
            } else {
                dateToSet = null;
            }
            pstmt.setDate(2, dateToSet);
            pstmt.setString(3, classification.getStatus());

            numEditedRow = pstmt.executeUpdate();
            con.commit();
            
            if(numEditedRow > 0) {
                toReturn = true;
            }
            /* Come è stato progettato il sistema, una nuova graduatoria non può avere già risultati
             * In caso di modifiche, decommentare
            // inserimento dei risultati
            List<Result> listResult = classification.getResults();

            for(Result tmpResult : listResult) {
                if(!insertResult(tmpResult)) {
                    // se non è stato possibile inserirla, significa che è già presente
                    // quindi bisogna aggiornare la tupla presente nel db
                    toReturn &= updateResult(tmpResult);
                }
            }
            //*/
            
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

    public synchronized boolean update(Classification classification) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_CLASSIFICATION + " " +
                    "SET " +
                        DBNames.ATT_CLASSIFICATION_NAME + "=?, " +
                        DBNames.ATT_CLASSIFICATION_DATA + "=?, " +
                        DBNames.ATT_CLASSIFICATION_STATUS + "=?, " +
                    "WHERE " + DBNames.ATT_CLASSIFICATION_ID + "=?;";

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters

            pstmt.setString(1, classification.getName());
            Date dateToSet;
            if(classification.getDate() != null) {
                dateToSet = new Date(classification.getDate().getTimeInMillis());
            } else {
                dateToSet = null;
            }
            pstmt.setDate(2, dateToSet);
            
            pstmt.setString(3, classification.getStatus());
            pstmt.setInt(4, classification.getId());

            numEditedRow = pstmt.executeUpdate();
            con.commit();
            
            if(numEditedRow > 0) {
                toReturn = true;
            }
            // aggiornamento dei risultati
            List<Result> listResult = classification.getResults();
            for(Result tmpResult : listResult) {
                if(!updateResult(tmpResult)) {
                    toReturn &= insertResult(tmpResult);
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

    public synchronized boolean delete(Classification classification) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE " +
                    "FROM " + DBNames.TABLE_CLASSIFICATION + " " +
                    "WHERE " + DBNames.ATT_CLASSIFICATION_ID + "=?;";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, classification.getId());

            numEditedRow = pstmt.executeUpdate();
            con.commit();
            
            if(numEditedRow > 0) {
                toReturn = true;
            }
            // cancellazione dei risultati per evitare incongruenze nel db
            deleteAllResult(classification);
        } finally {
            if(pstmt != null) {
                pstmt.close();
            } if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return toReturn;
    }

    public synchronized List<Classification> search(Classification classification) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        List<Classification> classificationList = null;
        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();

            // constructing search query string
            query.append("SELECT * " +
                            "FROM " + DBNames.TABLE_CLASSIFICATION + " " +
                            "WHERE ");
            if(classification.getId() > 0) {		
                query.append(DBNames.ATT_CLASSIFICATION_ID + "=?");
                andState = true;
            }
            if(classification.getName() != null) {
                query.append(useAnd(andState) + DBNames.ATT_CLASSIFICATION_NAME + "=?");
                andState = true;
            }
            if(classification.getDate() != null) {
                query.append(useAnd(andState) + DBNames.ATT_CLASSIFICATION_DATA + "=?");
                andState = true;
            }
            if(classification.getStatus() != null) {
                query.append(useAnd(andState) + DBNames.ATT_CLASSIFICATION_STATUS + "=?");
                andState = true;
            }

            if(andState == false) {
                query.append("1");
            }
            // chiusura della query, le graduatorie vanno messe prima in ordine di stato bozza > provvisoria > definitiva
            query.append(" ORDER BY (" + DBNames.ATT_CLASSIFICATION_STATUS + ", " + DBNames.ATT_CLASSIFICATION_ID + ");");
            
            pstmt = con.prepareStatement(query.toString());

            // setting pstmt's parameters
            int i = 1;		// index of pstmt first argument
            if(classification.getId() > 0) {
                    pstmt.setInt(i, classification.getId());
                    i++;
            }
            if(classification.getName() != null) {
                    pstmt.setString(i, classification.getName());
                    i++;
            }
            if(classification.getDate() != null) {
                    pstmt.setDate(i, new Date(classification.getDate().getTimeInMillis()));
                    i++;
            }
            if(classification.getStatus() != null) {
                    pstmt.setString(i, classification.getStatus());
                    i++;
            }

            // executing select query
            resultSet = pstmt.executeQuery();
            con.commit();

            // constructing payment list
            classificationList = new ArrayList<Classification>();
            while(resultSet.next()) {
                Classification tmpClassification =  new Classification();
                tmpClassification.setId(resultSet.getInt(DBNames.ATT_CLASSIFICATION_ID));
                tmpClassification.setName(resultSet.getString(DBNames.ATT_CLASSIFICATION_NAME));

                //getting Date from ResultSet and converting it to GregorianCalendar
                GregorianCalendar dateToSet;
                if(resultSet.getDate(DBNames.ATT_CLASSIFICATION_DATA) != null) {
                    dateToSet = new GregorianCalendar();
                    dateToSet.setTime(resultSet.getDate(DBNames.ATT_CLASSIFICATION_DATA));
                } else {
                    dateToSet = null;
                }
                tmpClassification.setDate(dateToSet);
                tmpClassification.setStatus(resultSet.getString(DBNames.ATT_CLASSIFICATION_STATUS));
                
                // ricerca dei risultati associati a questa graduatoria
                Result tmpResult = new Result();
                tmpResult.setClassificationId(tmpClassification.getId());
                tmpClassification.setResults(searchResult(tmpResult));
            }
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return classificationList;
    }

    public synchronized boolean insertResult(Result result) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();
            query = "INSERT INTO " + DBNames.TABLE_RESULT + " (" +
                        DBNames.ATT_RESULT_REGISTRATIONCHILDID + ", " +
                        DBNames.ATT_RESULT_CLASSIFICATIONID + ", " +
                        DBNames.ATT_RESULT_SCORE + ", " +
                        DBNames.ATT_RESULT_RESULT + ", " +
                    ") VALUES(?, ?, ?, ?);";
                
            pstmt = con.prepareStatement(query);
                
            pstmt.setInt(1, result.getRegistrationChildId());
            pstmt.setInt(2, result.getClassificationId());
            pstmt.setDouble(3, result.getScore());
            pstmt.setBoolean(4, result.getResult());

            numEditedRow = pstmt.executeUpdate();
            con.commit();
            pstmt.close();
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
    
    public synchronized boolean updateResult(Result result) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();
            query = "UPDATE" + DBNames.TABLE_RESULT + " " +
                    "SET " +
                        DBNames.ATT_RESULT_SCORE + "=?, " +
                        DBNames.ATT_RESULT_RESULT + "=?, " +
                    "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=? AND " +
                    DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;";
            pstmt = con.prepareStatement(query);

            pstmt.setDouble(1, result.getScore());
            pstmt.setBoolean(2, result.getResult());
            pstmt.setInt(3, result.getRegistrationChildId());
            pstmt.setInt(4, result.getClassificationId());

            numEditedRow = pstmt.executeUpdate();
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
    
    public synchronized boolean deleteResult(Result result) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE " +
                    "FROM " + DBNames.TABLE_RESULT + " " +
                    "WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?; AND " +
                        DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, result.getClassificationId());
            stmt.setInt(2, result.getRegistrationChildId());
            stmt.executeUpdate();
            con.commit();
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
    
    public synchronized List<Result> searchResult(Result result) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        List<Result> resultList = null;
        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();
            query.append("SELECT * " +
                        "FROM " + DBNames.TABLE_RESULT + " " +
                        "WHERE ");
            if(result.getClassificationId() > 0) {
                query.append(DBNames.ATT_RESULT_CLASSIFICATIONID + "=?");
                andState = true;
            }
            if(result.getRegistrationChildId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?");
                andState = true;
            }
            if(result.getScore() >= 0) {
                query.append(useAnd(andState) + DBNames.ATT_RESULT_SCORE + "=?");
                andState = true;
            }
            if(andState != true) {
                query.append("1");
            }
            // ordinamento
            query.append(" ORDER BY (" + DBNames.ATT_RESULT_SCORE + ", " + 
                        DBNames.ATT_RESULT_CLASSIFICATIONID + ", " + 
                        DBNames.ATT_RESULT_REGISTRATIONCHILDID + ") DESC;");
            
            pstmt = con.prepareStatement(query.toString());
            int i = 1;
            if(result.getClassificationId() > 0) {
                pstmt.setInt(i, result.getClassificationId());
                i++;
            }
            if(result.getRegistrationChildId() > 0) {
                pstmt.setInt(i, result.getRegistrationChildId());
                i++;
            }
            if(result.getScore() >= 0) {
                pstmt.setDouble(i, result.getScore());
                i++;
            }
            
            resultSet = pstmt.executeQuery();

            resultList = new ArrayList<Result>();
            while(resultSet.next()) {
                Result rTmp = new Result();
                rTmp.setClassificationId(resultSet.getInt(DBNames.ATT_RESULT_CLASSIFICATIONID));
                rTmp.setRegistrationChildId(resultSet.getInt(DBNames.ATT_RESULT_REGISTRATIONCHILDID));
                rTmp.setScore(resultSet.getDouble(DBNames.ATT_RESULT_SCORE));
                rTmp.setResult(resultSet.getBoolean(DBNames.ATT_RESULT_RESULT));
                resultList.add(rTmp);
            }
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return resultList;
    }
    
    public synchronized boolean deleteAllResult(Classification classification) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE FROM " + DBNames.TABLE_RESULT
                            + "WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, classification.getId());
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
    
    public synchronized boolean deleteAllResult(RegistrationChild child) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE FROM " + DBNames.TABLE_RESULT
                            + "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, child.getId());
            
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
    
    // metodo utilizzato dal registrationchildmanager in caso di eliminazione di una domanda di iscrizione
    // observe patter
    public synchronized boolean unapproveResult(RegistrationChild registrationChildId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();
            
            query = "UPDATE " + DBNames.TABLE_RESULT + " " +
                        "SET " + DBNames.ATT_RESULT_RESULT + "=? " +
                        "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";
            
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, registrationChildId.getId());
            
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
    
    public synchronized boolean insertCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "INSERT INTO " + DBNames.TABLE_CRITERIA + " ("
                            + DBNames.ATT_CRITERIA_ID + ", "
                            + DBNames.ATT_CRITERIA_DESCRIPTION + ", "
                            + DBNames.ATT_CRITERIA_WEIGHT + ", "
                            + ") VALUES(NULL, ?, ?)";
            
            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setString(1, criterion.getDescription());
            pstmt.setDouble(2, criterion.getWeight());
            
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

    public synchronized boolean modifyCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query = "UPDATE " + DBNames.TABLE_CRITERIA + " " +
                    "SET " +
                    DBNames.ATT_CRITERIA_ID + "=?, " +
                    DBNames.ATT_CRITERIA_WEIGHT + "=?, " +
                    "WHERE " + DBNames.ATT_CRITERIA_ID + "=?;";

            pstmt = con.prepareStatement(query);

            // setting pstmt's parameters

            pstmt.setString(1, criterion.getDescription());
            pstmt.setDouble(2,criterion.getWeight());
            pstmt.setInt(3, criterion.getId());

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

    public synchronized boolean deleteCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query;
        boolean toReturn = false;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE " + 
                    "FROM " + DBNames.TABLE_CRITERIA + " " +
                    "WHERE " + DBNames.ATT_CRITERIA_ID + "=?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, criterion.getId());
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

    public synchronized List<Criterion> searchCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        List<Criterion> criteriaList = null;
        boolean andState = false;

        try {
            con = DBConnectionPool.getConnection();
            query.append("SELECT * " +
                        "FROM " + DBNames.TABLE_CRITERIA + " " +
                        "WHERE ");
            if(criterion.getId() > 0) {
                query.append(DBNames.ATT_CRITERIA_ID + "=?");
                andState = true;
            }
            if(criterion.getDescription() != null) {
                query.append(useAnd(andState) + DBNames.ATT_CRITERIA_DESCRIPTION + "=?");
                andState = true;
            }
            if(andState != true) {
                query.append("1");
            }
            // ordinamento
            query.append(" ORDER BY (" + DBNames.ATT_CRITERIA_DESCRIPTION + ");");
            
            pstmt = con.prepareStatement(query.toString());
            int i = 1;
            if(criterion.getId() > 0) {
                pstmt.setInt(i, criterion.getId());
                i++;
            }
            if(criterion.getDescription() != null) {
                pstmt.setString(i, criterion.getDescription());
                i++;
            }
            
            resultSet = pstmt.executeQuery();

            criteriaList = new ArrayList<Criterion>();
            while(resultSet.next()) {
                Criterion cTmp = new Criterion();
                cTmp.setId(resultSet.getInt(DBNames.ATT_CRITERIA_ID));
                cTmp.setDescription(resultSet.getString(DBNames.ATT_CRITERIA_DESCRIPTION));
                cTmp.setWeight(resultSet.getDouble(DBNames.ATT_CRITERIA_WEIGHT));
                criteriaList.add(cTmp);
            }
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return criteriaList;
    }
    
    public synchronized List<Criterion> getAllCriteria() throws SQLException {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String query;
            List<Criterion> criteriaList = null;
            
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
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                if(con != null) {
                    DBConnectionPool.releaseConnection(con);
                }
            }
            return criteriaList;
    }

    public synchronized List<Classification> getAllClassification() throws SQLException {
        Classification classification = new Classification();
        return search(classification);
    }
    
    private String useAnd(boolean pEnableAnd) {
            return pEnableAnd ? " AND " : " ";
    }

}
