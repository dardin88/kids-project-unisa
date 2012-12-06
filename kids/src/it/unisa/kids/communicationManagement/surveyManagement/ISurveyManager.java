package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author felice
 */
public interface ISurveyManager extends IManager {
    
     public abstract void insert(Survey pSurvey) throws SQLException;
     public abstract void update(Survey pChangedSurvey) throws SQLException;
     public abstract void delete(Survey pSurvey) throws SQLException;
     public abstract List<Survey> search(Survey pSurvey) throws SQLException;
     
     
     
}
