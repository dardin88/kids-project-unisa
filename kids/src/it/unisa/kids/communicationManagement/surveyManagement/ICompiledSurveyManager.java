package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author felice
 */
public interface ICompiledSurveyManager extends IManager {
    public abstract void insert(Survey pAvailableSurvey) throws SQLException;
    public abstract List<Survey> search(Account pParent) throws SQLException;
}
