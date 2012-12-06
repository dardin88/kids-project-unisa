package it.unisa.kids.communicationManagement.surveyManagement;

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

    
    public List<Survey> search(Survey pSurvey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Survey pSurvey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
