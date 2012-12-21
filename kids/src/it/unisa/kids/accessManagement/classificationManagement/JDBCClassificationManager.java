package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public class JDBCClassificationManager implements IClassificationManager {
    private static JDBCClassificationManager manager;

    // Singleton Design Pattern's implementation
    private JDBCClassificationManager() {
    }

    public static synchronized JDBCClassificationManager getInstance() {
        if(manager != null) {
            return manager;
        } else {
            return manager = new JDBCClassificationManager();
        }
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
            query = "INSERT INTO " + DBNames.TABLE_CLASSIFICATION + " (" +
                            DBNames.ATT_CLASSIFICATION_ID + ", " +
                            DBNames.ATT_CLASSIFICATION_NAME + ", " +
                            DBNames.ATT_CLASSIFICATION_DATA + ", " +
                            DBNames.ATT_CLASSIFICATION_STATUS + ") " +
                      "VALUES(NULL,?,?,?);";
            
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
        StringBuilder query = new StringBuilder();
        int numEditedRow;
        boolean toReturn = false;
        
        try {
            con = DBConnectionPool.getConnection();
            boolean useSeparator = false;
            // constructing query string
            query.append("UPDATE " + DBNames.TABLE_CLASSIFICATION + " SET ");
            if(classification.getName() != null) {
                query.append(useSeparator(useSeparator) + DBNames.ATT_CLASSIFICATION_NAME + "=? ");
                useSeparator = true;
            }
            if(classification.getDate() != null) {
                query.append(useSeparator(useSeparator) + DBNames.ATT_CLASSIFICATION_DATA + "=? ");
                useSeparator = true;
            }
            if(classification.getStatus() != null) {
                query.append(useSeparator(useSeparator) + DBNames.ATT_CLASSIFICATION_STATUS + "=? ");
                useSeparator = true;
            }
            
            if(useSeparator) { // se non è inizializzato nessun campo non è necessario fare update
                query.append("WHERE " + DBNames.ATT_CLASSIFICATION_ID + "=?;");
                
                pstmt = con.prepareStatement(query.toString());

                // setting pstmt's parameters
                int count = 1;
                if(classification.getName() != null) {
                    pstmt.setString(count, classification.getName());
                    count++;
                }
                
                if(classification.getDate() != null) {
                    Date dateToSet;
                    dateToSet = new Date(classification.getDate().getTimeInMillis());
                    pstmt.setDate(count, dateToSet);
                    count++;
                }
                
                if(classification.getStatus() != null) {
                    pstmt.setString(count, classification.getStatus());
                    count++;
                }
                
                pstmt.setInt(count, classification.getId());

                numEditedRow = pstmt.executeUpdate();
                con.commit();

                if(numEditedRow > 0) {
                    toReturn = true;
                }
                // aggiornamento dei risultati
                List<Result> listResult = classification.getResults();
                if(listResult != null) {
                    for(Result tmpResult : listResult) {
                        if(!updateResult(tmpResult)) {
                            toReturn &= insertResult(tmpResult);
                        }
                    }
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
        StringBuilder query = new StringBuilder();
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
            query.append(" ORDER BY " + DBNames.ATT_CLASSIFICATION_STATUS + ", " + DBNames.ATT_CLASSIFICATION_ID + ";");
            
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
                    dateToSet = CommonMethod.parseGregorianCalendar(resultSet.getDate(DBNames.ATT_CLASSIFICATION_DATA));
                } else {
                    dateToSet = null;
                }
                tmpClassification.setDate(dateToSet);
                tmpClassification.setStatus(resultSet.getString(DBNames.ATT_CLASSIFICATION_STATUS));
                
                // ricerca dei risultati associati a questa graduatoria
                Result tmpResult = new Result();
                tmpResult.setClassificationId(tmpClassification.getId());
                tmpClassification.setResults(searchResult(tmpResult));
                
                // Aggiungo nella lista di ritorno
                classificationList.add(tmpClassification);
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

    
    public synchronized List<Classification> search(Classification classification, String toSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        List<Classification> classificationList = null;
        boolean andState = false;
        boolean orState = false;
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
            // Le condizioni else sono posizionate qui di seguito in modo da effettuare prima 
            // tutti i controlli obbligatori (AND) e solo dopo gli or
            if(andState) {
                query.append(" AND (");
            }
            if(classification.getName() == null) {
                query.append(useOr(orState) + DBNames.ATT_CLASSIFICATION_NAME + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(classification.getDate() == null) {
                query.append(useOr(orState) + DBNames.ATT_CLASSIFICATION_DATA + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(classification.getStatus() == null) {
                query.append(useOr(orState) + DBNames.ATT_CLASSIFICATION_STATUS + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(!orState && andState) {
                query.append("1");
            }
            if(andState) {
                query.append(")");
            }
            // chiusura della query, le graduatorie vanno messe prima in ordine di stato bozza > provvisoria > definitiva
            query.append(" ORDER BY " + DBNames.ATT_CLASSIFICATION_STATUS + ", " + DBNames.ATT_CLASSIFICATION_ID + ";");
            
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
                    pstmt.setDate(i, CommonMethod.parseDate(classification.getDate()));
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
                    dateToSet = CommonMethod.parseGregorianCalendar(resultSet.getDate(DBNames.ATT_CLASSIFICATION_DATA));
                } else {
                    dateToSet = null;
                }
                tmpClassification.setDate(dateToSet);
                tmpClassification.setStatus(resultSet.getString(DBNames.ATT_CLASSIFICATION_STATUS));
                
                // ricerca dei risultati associati a questa graduatoria
                Result tmpResult = new Result();
                tmpResult.setClassificationId(tmpClassification.getId());
                tmpClassification.setResults(searchResult(tmpResult));
                
                // Aggiungo nella lista di ritorno
                classificationList.add(tmpClassification);
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
                        DBNames.ATT_RESULT_RESULT + " " +
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
        StringBuilder query = new StringBuilder();
        boolean toReturn = false;
        int numEditedRow;
        boolean useSep = false;
        
        try {
            con = DBConnectionPool.getConnection();
            query.append("UPDATE " + DBNames.TABLE_RESULT + " SET ");
            if(result.getScore() >= 0) {
                query.append(DBNames.ATT_RESULT_SCORE + "=? ");
                useSep = true;
            }
            if(result.isSetResult()) {
                query.append(useSeparator(useSep) + DBNames.ATT_RESULT_RESULT + "=? ");
                useSep = true;
            }
            query.append("WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=? AND " +
                    DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;");
            
            if(useSep) {
                pstmt = con.prepareStatement(query.toString());

                int count = 1;
                if(result.getScore() >= 0) {
                    pstmt.setDouble(count, result.getScore());
                    count++;
                }
                if(result.isSetResult()) { 
                    pstmt.setBoolean(count, result.getResult());
                    count++;
                }
                pstmt.setInt(count, result.getRegistrationChildId());
                count++;
                pstmt.setInt(count, result.getClassificationId());

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
                    "WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=? AND " +
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
        StringBuilder query = new StringBuilder();
        List<Result> resultList = null;
        boolean andState = true;

        try {
            con = DBConnectionPool.getConnection();
            query.append("SELECT R.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + 
                                    ", RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                        "FROM " + DBNames.TABLE_RESULT + " AS R, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                        "WHERE R." + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID);
            if(result.getClassificationId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?");
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
            query.append(" ORDER BY " + DBNames.ATT_RESULT_RESULT + " DESC, " + DBNames.ATT_RESULT_SCORE + " DESC, " + 
                        DBNames.ATT_RESULT_CLASSIFICATIONID + ", " + 
                        DBNames.ATT_RESULT_REGISTRATIONCHILDID + ";");
            
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
                
                rTmp.setRegistrationChildFiscalCode(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                rTmp.setRegistrationChildSurname(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                rTmp.setRegistrationChildName(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
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
    
    public synchronized List<Result> searchResult(Result result, String toSearch) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        List<Result> resultList = null;
        boolean andState = true;    // perchè come minimo ci sono le condizione della join
        boolean orState = false;

        try {
            con = DBConnectionPool.getConnection();
            query.append("SELECT R.*, RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + 
                                    ", RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " " +
                        "FROM " + DBNames.TABLE_RESULT + " AS R, " + DBNames.TABLE_REGISTRATIONCHILD + " AS RC " +
                        "WHERE R." + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=RC." + DBNames.ATT_REGISTRATIONCHILD_ID);
            if(result.getClassificationId() > 0) {
                query.append(useAnd(andState) + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?");
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
            
            // Le condizioni else sono posizionate qui di seguito in modo da effettuare prima 
            // tutti i controlli obbligatori (AND) e solo dopo gli or
            if(andState) {
                query.append(" AND (");
            }
            //if(result.getRegistrationChildId() <= 0) {
                query.append(useOr(orState) + "RC." + DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + " LIKE '%" + toSearch + "%'");
                orState = true;
            //}
            //if(result.getRegistrationChildId() <= 0) {
                query.append(useOr(orState) + "RC." + DBNames.ATT_REGISTRATIONCHILD_SURNAME + " LIKE '%" + toSearch + "%'");
                orState = true;
            //}
            //if(result.getRegistrationChildId() <= 0) {
                query.append(useOr(orState) + "RC." + DBNames.ATT_REGISTRATIONCHILD_NAME + " LIKE '%" + toSearch + "%'");
                orState = true;
            //}
            if(result.getScore() < 0) {
                query.append(useOr(orState) + DBNames.ATT_RESULT_SCORE + " LIKE '%" + toSearch + "%'");
                orState = true;
            }
            if(!orState && andState) {
                query.append("1");
            }
            if(andState) {
                query.append(")");
            }
            
            // ordinamento
            query.append(" ORDER BY " + DBNames.ATT_RESULT_RESULT + " DESC, " + DBNames.ATT_RESULT_SCORE + " DESC, " + 
                        DBNames.ATT_RESULT_CLASSIFICATIONID + ", " + 
                        DBNames.ATT_RESULT_REGISTRATIONCHILDID + ";");
            
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
                
                rTmp.setRegistrationChildFiscalCode(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE));
                rTmp.setRegistrationChildSurname(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
                rTmp.setRegistrationChildName(resultSet.getString(DBNames.ATT_REGISTRATIONCHILD_NAME));
                
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
            query = "DELETE FROM " + DBNames.TABLE_RESULT + " " +
                            "WHERE " + DBNames.ATT_RESULT_CLASSIFICATIONID + "=?;";

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
            query = "DELETE FROM " + DBNames.TABLE_RESULT + " " +
                            "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=?;";

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
    
    public synchronized int deleteAllResultFromDifferentClassification(int registrationChildId, int classificationId) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        String query;
        int numEditedRow;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string for classification
            query = "DELETE FROM " + DBNames.TABLE_RESULT + " " +
                            "WHERE " + DBNames.ATT_RESULT_REGISTRATIONCHILDID + "=? AND " + DBNames.ATT_RESULT_CLASSIFICATIONID + "<>?;";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, registrationChildId);
            stmt.setInt(2, classificationId);
            
            numEditedRow = stmt.executeUpdate();
            con.commit();
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            if(con != null) {
                DBConnectionPool.releaseConnection(con);
            }
        }
        return numEditedRow;
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
                            + DBNames.ATT_CRITERIA_DBFIELDSTABLE + ", "
                            + DBNames.ATT_CRITERIA_DBFIELD + ", "
                            + DBNames.ATT_CRITERIA_COMPARATOR + ", "
                            + DBNames.ATT_CRITERIA_CONDITION + ", "
                            + DBNames.ATT_CRITERIA_WEIGHT + ", "
                            + DBNames.ATT_CRITERIA_ACTIVE + " "
                            + ") VALUES(NULL,?,?,?,?,?,?,?)";
            
            pstmt = con.prepareStatement(query);

            //setting pstmt's parameters
            pstmt.setString(1, criterion.getDescription());
            pstmt.setString(2, criterion.getDbFieldsTable());
            pstmt.setString(3, criterion.getDbField());
            pstmt.setString(4, criterion.getComparator());
            pstmt.setString(5, criterion.getCondition());
            pstmt.setDouble(6, criterion.getWeight());
            pstmt.setBoolean(7, criterion.getActive());
            
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

    public synchronized boolean updateCriterion(Criterion criterion) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        StringBuilder query = new StringBuilder();
        boolean toReturn = false;
        int numEditedRow;
        boolean useComma = false;
        
        try {
            con = DBConnectionPool.getConnection();

            // constructing query string
            query.append("UPDATE " + DBNames.TABLE_CRITERIA + " SET ");
            
            if(criterion.getDescription() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_DESCRIPTION + "=?");
                useComma = true;
            }
            if(criterion.getDbFieldsTable() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_DBFIELDSTABLE + "=?");
                useComma = true;
            }
            if(criterion.getDbField() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_DBFIELD + "=?");
                useComma = true;
            }
            if(criterion.getComparator() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_COMPARATOR + "=?");
                useComma = true;
            }
            if(criterion.getCondition() != null) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_CONDITION + "=?");
                useComma = true;
            }
            if(criterion.isSetWeight()) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_WEIGHT + "=?");
                useComma = true;
            }
            if(criterion.isSetActive()) {
                query.append(useSeparator(useComma) + DBNames.ATT_CRITERIA_ACTIVE + "=?");
                useComma = true;
            }
            if(useComma) {
                query.append(" WHERE " + DBNames.ATT_CRITERIA_ID + "=?;");

                pstmt = con.prepareStatement(query.toString());

                // setting pstmt's parameters
                int param = 1;
                if(criterion.getDescription() != null) {
                    pstmt.setString(param, criterion.getDescription());
                    param++;
                }
                if(criterion.getDbFieldsTable() != null) {
                    pstmt.setString(param, criterion.getDbFieldsTable());
                    param++;
                }
                if(criterion.getDbField() != null) {
                    pstmt.setString(param, criterion.getDbField());
                    param++;
                }
                if(criterion.getComparator() != null) {
                    pstmt.setString(param, criterion.getComparator());
                    param++;
                }
                if(criterion.getCondition() != null) {
                    pstmt.setString(param, criterion.getCondition());
                    param++;
                }
                if(criterion.isSetWeight()) {
                    pstmt.setDouble(param, criterion.getWeight());
                    param++;
                }
                if(criterion.isSetActive()) {
                    pstmt.setBoolean(param, criterion.getActive());
                    param++;
                }
                pstmt.setInt(param, criterion.getId());

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
        StringBuilder query = new StringBuilder();
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
            query.append(" ORDER BY " + DBNames.ATT_CRITERIA_DESCRIPTION + ";");
            
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
                cTmp.setDbFieldsTable(resultSet.getString(DBNames.ATT_CRITERIA_DBFIELDSTABLE));
                cTmp.setDbField(resultSet.getString(DBNames.ATT_CRITERIA_DBFIELD));
                cTmp.setComparator(resultSet.getString(DBNames.ATT_CRITERIA_COMPARATOR));
                cTmp.setCondition(resultSet.getString(DBNames.ATT_CRITERIA_CONDITION));
                cTmp.setWeight(resultSet.getDouble(DBNames.ATT_CRITERIA_WEIGHT));
                cTmp.setActive(resultSet.getBoolean(DBNames.ATT_CRITERIA_ACTIVE));
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
    
    public synchronized List<Criterion> getAllActiveCriteria() throws SQLException {
        Criterion tmpCriteria = new Criterion();
        tmpCriteria.setActive(true);
        return searchCriterion(tmpCriteria);
    }

    public synchronized List<Classification> getAllClassification() throws SQLException {
        Classification classification = new Classification();
        return search(classification);
    }
    
    public int calculateScore(Result result, List<Criterion> listCriteria) {
        int score = 0;
        
        /*
         * NOT IMPLEMENTED
         */
        
        return score;
    }
    
    private String useAnd(boolean pEnableAnd) {
            return pEnableAnd ? " AND " : " ";
    }

    private String useOr(boolean pEnableAnd) {
            return pEnableAnd ? " OR " : " ";
    }
      
    private String useSeparator(boolean pEnableAnd) {
            return pEnableAnd ? ", " : " ";
    }
}
