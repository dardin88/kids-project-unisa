package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;


 /**
 *
 * @author felice
 */
public class JDBCSurveyManager implements ISurveyManager{

    private static ISurveyManager manager;
    
    /**Constructor is empty. */
    private JDBCSurveyManager() {}
    
    /**
     * Method who implements Singleton pattern.
     * @return manager
     */
    public static ISurveyManager getInstance() {  
        if(manager == null) 
            return (manager = new JDBCSurveyManager());
        else
            return manager;
    }
    
    /**
     *Insert surveys in the table.
     * @param pSurvey
     */
    public void insert(Survey pSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "INSERT INTO" + DBNames.TABLE_SURVEY + "("+
                    DBNames.ATT_SURVEY_SURVEYID + "," +
                    DBNames.ATT_SURVEY_SURVEYLINK + ") VALUES (?,?)";
        
            pStmt = connection.prepareStatement(query);
            
            pStmt.setInt(1, pSurvey.getId());
            pStmt.setString(2, pSurvey.getLink());
            
            pStmt.executeUpdate();
            connection.commit();
        }
        finally {
            if(pStmt != null) {
                connection.close();        
            } 
              if (connection != null) {
                  DBConnectionPool.releaseConnection(connection); 
              }
        }              
    }

    /**
     *Update/Modify a survey in database. 
     * @param pSurvey
     */
    public void update(Survey pChangedSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;
        
        try {
            connection = DBConnectionPool.getConnection();
            query = "UPDATE" + DBNames.TABLE_SURVEY + "SET" + 
                    DBNames.ATT_SURVEY_SURVEYLINK + "= ?";
            
            query += "WHERE" + DBNames.ATT_SURVEY_SURVEYID + "= ?";
            
            pStmt = connection.prepareStatement(query);
            pStmt.setString(1, pChangedSurvey.getLink());
            pStmt.setInt(2, pChangedSurvey.getId());
            
            pStmt.executeUpdate();
            connection.commit();
       } 
        finally{
            if(pStmt != null) { pStmt.close(); }
            if(connection != null){ DBConnectionPool.releaseConnection(connection);}
            
        }
        
    }

    /**
     *Shows a list of surveys.
     * @param 
     */
    public List<Survey> search() throws SQLException {
      Connection connection = null;
      Statement pStmt = null;
      ResultSet rsSurvey = null;
      String query;
      List<Survey> listSurvey = null;
      
      try {
         connection = DBConnectionPool.getConnection();
         query = "SELECT * FROM" + DBNames.TABLE_SURVEY;
         pStmt = connection.createStatement();
         rsSurvey = pStmt.executeQuery(query);
         connection.commit();
         
         while(rsSurvey.next()){
             int id = rsSurvey.getInt(DBNames.ATT_SURVEY_SURVEYID);
             String link = rsSurvey.getString(DBNames.ATT_SURVEY_SURVEYLINK);
             
             Survey survey = new Survey();
             survey.setId(id);
             survey.setLink(link);
             listSurvey.add(survey);
         }
         
         return listSurvey;
      } 
      finally {
          pStmt.close();
          DBConnectionPool.releaseConnection(connection);
      }
        
    }
    
    /**
     *Delete a survey from the database.
     * @param pSurvey
     */
    public void delete(Survey pSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;
        
        try {
            connection = DBConnectionPool.getConnection();
            query = "DELETE FROM" + DBNames.TABLE_SURVEY +
                    "WHERE" + DBNames.ATT_SURVEY_SURVEYID + "= '" + pSurvey.getId() + "'";
            pStmt = connection.prepareStatement(query);
            pStmt.executeUpdate();
            connection.commit();
        } 
        finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
    }

    
    public void insert(Survey pAvailableSurvey, Account pParent) throws SQLException {
       Connection connection = null;
       PreparedStatement pStmt = null;
       String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "INSERT INTO" + DBNames.TABLE_SURVEYCOMPILED + "(" +
                    DBNames.ATT_SURVEYCOMPILED_SURVEYID + "," +
                    DBNames.ATT_SURVEYCOMPILED_ACCOUNTID + "," +
                    DBNames.ATT_SURVEYCOMPILED_COMPILED + ")" + "VALUES(?,?,?)";
        
            pStmt = connection.prepareStatement(query);
            
            pStmt.setInt(1, pAvailableSurvey.getId());
            pStmt.setInt(2, pParent.getId()); 
            pStmt.setBoolean(3, pAvailableSurvey.getCompiled());
            
            
            pStmt.executeUpdate();
            connection.commit(); 
        }
        finally {
            if(pStmt != null) {
                connection.close();        
            } 
              if (connection != null) {
                  DBConnectionPool.releaseConnection(connection); 
              }
        }              
    }

     
    public void update(Survey pAvailableSurvey, Account pParent) throws SQLException {
    
    }

    
    public void delete(Survey pAvailableSurvey, Account pParent) throws SQLException {
    
    }

    
    public List<Survey> search(Account pParent) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
    
}
