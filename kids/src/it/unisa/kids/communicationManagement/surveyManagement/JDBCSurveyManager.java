package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felice
 */
public class JDBCSurveyManager implements ISurveyManager {

    private static ISurveyManager manager;

    /**
     * Constructor is empty.
     */
    private JDBCSurveyManager() {
    }

    /**
     * Method who implements Singleton pattern.
     *
     * @return manager
     */
    public static ISurveyManager getInstance() {
        if (manager == null) {
            return (manager = new JDBCSurveyManager());
        } else {
            return manager;
        }
    }

    /**
     * Insert surveys in the table.
     *
     * @param pSurvey
     */
    @Override
    public void insert(Survey pSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "INSERT INTO " + DBNames.TABLE_SURVEY + "("
                    + DBNames.ATT_SURVEY_SURVEYLINK + ") VALUES (?)";
            pStmt = connection.prepareStatement(query);
            pStmt.setString(1, pSurvey.getLink());
            pStmt.executeUpdate();
            connection.commit();
        } finally {
            if (pStmt != null) {
                connection.close();
            }
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Update/Modify a survey in database.
     *
     * @param pSurvey
     */
    @Override
    public void update(Survey pChangedSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;

        try {
            connection = DBConnectionPool.getConnection();
            query = "UPDATE" + DBNames.TABLE_SURVEY + "SET"
                    + DBNames.ATT_SURVEY_SURVEYLINK + "= ?";

            query += "WHERE" + DBNames.ATT_SURVEY_SURVEYID + "= ?";

            pStmt = connection.prepareStatement(query);
            pStmt.setString(1, pChangedSurvey.getLink());
            pStmt.setInt(2, pChangedSurvey.getId());

            pStmt.executeUpdate();
            connection.commit();
        } finally {
            if (pStmt != null) {
                pStmt.close();
            }
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection);
            }

        }

    }

    /**
     * Shows a list of surveys.
     *
     * @param
     */
    @Override
    public List<Survey> search() throws SQLException {
        Connection connection = null;
        Statement pStmt = null;
        ResultSet rsSurvey = null;
        String query;
        List<Survey> listSurvey = new ArrayList<>();

        try {
            connection = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_SURVEY;
            pStmt = connection.createStatement();
            rsSurvey = pStmt.executeQuery(query);
            connection.commit();

            while (rsSurvey.next()) {
                int id = rsSurvey.getInt(DBNames.ATT_SURVEY_SURVEYID);
                String link = rsSurvey.getString(DBNames.ATT_SURVEY_SURVEYLINK);

                Survey survey = new Survey();
                survey.setId(id);
                survey.setLink(link);
                listSurvey.add(survey);
            }

            return listSurvey;
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(connection);
        }

    }

    /**
     * Delete a survey from the database.
     *
     * @param pSurvey
     */
    @Override
    public void delete(Survey pSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;

        try {
            connection = DBConnectionPool.getConnection();
            query = "DELETE FROM " + DBNames.TABLE_SURVEY
                    + " WHERE " + DBNames.ATT_SURVEY_SURVEYID + " = '" + pSurvey.getId() + "'";
            pStmt = connection.prepareStatement(query);
            pStmt.executeUpdate();
            connection.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
    }
}