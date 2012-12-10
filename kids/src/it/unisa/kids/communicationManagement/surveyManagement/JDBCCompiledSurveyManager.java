package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
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
public class JDBCCompiledSurveyManager implements ICompiledSurveyManager {

    private static ICompiledSurveyManager manager;

    /**
     * Constructor is empty.
     */
    private JDBCCompiledSurveyManager() {
    }

    /**
     * Method who implements Singleton pattern.
     *
     * @return manager
     */
    public static ICompiledSurveyManager getInstance() {
        if (manager == null) {
            return (manager = new JDBCCompiledSurveyManager());
        } else {
            return manager;
        }
    }

    /* Methods used for insert and show Available Survey for parents. */
    @Override
    public void insert(Survey pAvailableSurvey) throws SQLException {
        Connection connection = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "INSERT INTO " + DBNames.TABLE_SURVEYCOMPILED + "("
                    + DBNames.ATT_SURVEYCOMPILED_SURVEYID + ","
                    + DBNames.ATT_SURVEYCOMPILED_ACCOUNTID + ","
                    + DBNames.ATT_SURVEYCOMPILED_COMPILED + ")" + " VALUES(?,?,?)";

            pStmt = connection.prepareStatement(query);
            pStmt.setInt(1, pAvailableSurvey.getId());
            pStmt.setInt(2, pAvailableSurvey.getParent());
            pStmt.setBoolean(3, pAvailableSurvey.getCompiled());
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
     * Shows a list of surveys.
     *
     * @param
     */
    @Override
    public List<Survey> search(Account pParent) throws SQLException {
        Connection connection = null;
        Statement pStmt = null;
        ResultSet rsCompiledSurvey, rsSurvey = null;
        String query;
        List<Survey> listSurvey = new ArrayList<>();
        List<Integer> listCompiledSurvey = new ArrayList<>();

        try {
            connection = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_SURVEYCOMPILED;
            pStmt = connection.createStatement();
            rsCompiledSurvey = pStmt.executeQuery(query);
            while (rsCompiledSurvey.next()) {
                if (rsCompiledSurvey.getBoolean(DBNames.ATT_SURVEYCOMPILED_COMPILED)) {
                    int id = rsCompiledSurvey.getInt(DBNames.ATT_SURVEYCOMPILED_SURVEYID);
                    listCompiledSurvey.add(id);
                }
            }
            connection = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_SURVEY;
            pStmt = connection.createStatement();
            rsSurvey = pStmt.executeQuery(query);

            while (rsSurvey.next()) {
                int id = rsSurvey.getInt(DBNames.ATT_SURVEY_SURVEYID);
                String link = rsSurvey.getString(DBNames.ATT_SURVEY_SURVEYLINK);

                Survey survey = new Survey();
                survey.setId(id);
                survey.setLink(link);
                if (!listCompiledSurvey.contains(id)) {
                    listSurvey.add(survey);
                }
            }

            return listSurvey;
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
    }
}