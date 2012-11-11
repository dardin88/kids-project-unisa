package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TraineeManager {
	private static TraineeManager manager;
	public TraineeManager(){
		
	}
	public TraineeManager getInstance(){
		if(manager==null){
			manager=new TraineeManager();
		}
		return manager;
	}
	public void insertTrainee(Trainee pTrainee) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		String query1;
		String query2;
		try {
			con=DBConnectionPool.getConnection();
			query1="INSERT INTO "+DBNames.TABLE_TRAINEE+"("
					+DBNames.ATT_TRAINEE_REGISTER+","
					+DBNames.ATT_TRAINEE_NAME+","
					+DBNames.ATT_TRAINEE_SURNAME +","
					+DBNames.ATT_TRAINEE_EMAIL+","
					+DBNames.ATT_TRAINEE_BIRTHDATE+","
					+DBNames.ATT_TRAINEE_BIRTHCITY+","
					+DBNames.ATT_TRAINEE_CITYOFRESIDENCE+","
					+DBNames.ATT_TRAINEE_ADDRESS+","
					+DBNames.ATT_TRAINEE_CAP+","
					+DBNames.ATT_TRAINEE_DELEGATEACCOUNT;
			query2="VALUES ("
					+pTrainee.getRegister()+","
					+pTrainee.getName()+","
					+pTrainee.getSurname()+","
					+pTrainee.getEmail()+","
					+pTrainee.getBirthDate()+","
					+pTrainee.getBirthCity()+","
					+pTrainee.getCityOfResidence()+","
					+pTrainee.getAddress()+","
					+pTrainee.getCap()+","
					+pTrainee.getDelegate().getId();
			if (pTrainee.getTelephoneNumber() != null) {
				query1 += ", " + DBNames.ATT_TRAINEE_TELEPHONENUMBER;
				query2 += ", " + pTrainee.getTelephoneNumber();
			}
			query1+=")";
			query2+=")";
			stmt = con.createStatement();
			stmt.executeUpdate(query1 + query2);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	public void update(Trainee pTrainee){
		
	}
	public void delete(Trainee pTrainee) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		String query1;
		try{
			con=DBConnectionPool.getConnection();
			query1="DEELETE FROM"+DBNames.TABLE_TRAINEE+"WHERE register='"+pTrainee.getRegister()+"'";
			stmt=con.createStatement();
			stmt.executeQuery(query1);
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		
	}
}
