package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IClassificationManager extends IManager {
    public boolean insert(Classification classification) throws SQLException;
    /**
     * Aggiorna il database con i dati inizializzati nell'oggetto Classification.
     * Il metodo aggiorna esclusivamente gli attributi diversi da null
     * @param classification Graduatoria da modificare nel database
     * @return true se la modifica è avvenuta, false altrimenti
     * @throws SQLException in caso di un SQLException
     */
    public boolean update(Classification classification) throws SQLException;
    public boolean delete(Classification classification) throws SQLException;
    public List<Classification> search(Classification classification) throws SQLException;

    public boolean insertResult(Result result) throws SQLException;
    public boolean updateResult(Result result) throws SQLException;
    public boolean deleteResult(Result result) throws SQLException;
    public List<Result> searchResult(Result result) throws SQLException;

    public List<Classification> getAllClassification() throws SQLException;
    
    public boolean deleteAllResult(Classification classification) throws SQLException;
    public boolean deleteAllResult(RegistrationChild child) throws SQLException;

    public boolean unapproveResult(RegistrationChild registrationChildId) throws SQLException;

    public boolean insertCriterion(Criterion criterion) throws SQLException;
    public boolean modifyCriterion(Criterion criterion) throws SQLException;
    public boolean deleteCriterion(Criterion criterion) throws SQLException;
    public List<Criterion> searchCriterion(Criterion criterion) throws SQLException;
    
    public List<Criterion> getAllCriteria() throws SQLException;
    /**
     * Calculate the score attributed with the criterialist
     * 
     * @param result result
     * @param listCriteria list of criteria to use as valutation
     * @return the score of the result
     */
    public int calculateScore(Result result, List<Criterion> listCriteria);
}
