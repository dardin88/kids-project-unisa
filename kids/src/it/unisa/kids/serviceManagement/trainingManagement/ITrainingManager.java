package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ITrainingManager {
//insert

    public abstract void insert(Account pTrainee) throws SQLException;

    public abstract void insert(TraineeRequest pTraineeRequest) throws SQLException;

    public abstract void insert(TraineeActivity pTraineeActivity)
            throws SQLException;

    public abstract void insert(TraineeConvocation pTraineeConvocation) throws SQLException;
//Update

    public abstract void update(TraineeActivity pTraineeActivity) throws SQLException;

    public abstract void update(TraineeRequest pTraineeRequest) throws SQLException;

    public abstract void update(Account pTrainee) throws SQLException;

    public abstract void update(TraineeConvocation pTraineeConvocation) throws SQLException;
//delete

    public abstract void delete(Account pTrainee) throws SQLException;

    public abstract void delete(TraineeActivity pTraineeActivity) throws SQLException;

    public abstract void delete(TraineeRequest pTraineeRequest) throws SQLException;

    public abstract void delete(TraineeConvocation pTraineeConvocation) throws SQLException;

    //search
    public abstract List<Account> search(Account pTrainee)
            throws SQLException;
    
    public abstract List<TraineeActivity> search(TraineeActivity pTraineeActivity) throws SQLException;
    
    public abstract List<TraineeRequest> search(TraineeRequest pTraineeRequest) throws SQLException;
    
    public abstract List<TraineeConvocation> search(TraineeConvocation pTraineeConvocation) throws SQLException;
//getList
    
    public abstract List<TraineeRequest> getRequestsList() throws SQLException;

    public abstract List<TraineeActivity> getTraineeActivityList() throws SQLException;

    public abstract List<Account> getTraineeList() throws SQLException;
    
    public abstract List<TraineeConvocation> getTraineeConvocationList() throws SQLException;
}