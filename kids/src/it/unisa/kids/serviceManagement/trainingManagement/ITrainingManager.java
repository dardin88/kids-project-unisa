package it.unisa.kids.serviceManagement.trainingManagement;


import java.sql.SQLException;
import java.util.ArrayList;

public interface ITrainingManager {

	public abstract void insertTrainee(Trainee pTrainee) throws SQLException;

	public abstract void update(Trainee pTrainee);

	public abstract void deleteTrainee(Trainee pTrainee) throws SQLException;

	public abstract ArrayList<Trainee> getTrainees(Trainee pTrainee)
			throws SQLException;

	public abstract void insertActivity(TraineeActivity pTraineeActivity)
			throws SQLException;

	public abstract void deleteActivity(TraineeActivity pTraineeActivity)
			throws SQLException;

}