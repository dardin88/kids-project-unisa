package it.unisa.kids.serviceManagement.trainingManagement;


import java.sql.SQLException;
import java.util.ArrayList;

public interface ITrainingManager {

	public abstract void insert(Trainee pTrainee) throws SQLException;

	public abstract void update(Trainee pTrainee) throws SQLException;

	public abstract void delete(Trainee pTrainee) throws SQLException;

	public abstract ArrayList<Trainee> search(Trainee pTrainee)
			throws SQLException;

	public abstract void insert(TraineeActivity pTraineeActivity)
			throws SQLException;

	public abstract void delete(TraineeActivity pTraineeActivity)
			throws SQLException;
        public abstract void update(TraineeActivity pTrainee) throws SQLException;
        public abstract ArrayList<Trainee> search()
			throws SQLException;
        public abstract ArrayList<TraineeRequest> search(TraineeRequest pTraineeRequest) throws SQLException;
        public abstract ArrayList<TraineeRequest> getRequestsList() throws SQLException;
        public abstract ArrayList<TraineeActivity> getTraineeActivityList() throws SQLException;
        

}