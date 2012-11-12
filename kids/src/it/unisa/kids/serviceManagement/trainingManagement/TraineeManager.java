package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.exception.MandatoryFieldException;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
					+new Date(pTrainee.getBirthDate().getTimeInMillis())+","
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
	public void deleteTrainee(Trainee pTrainee) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		String query;
		try{
			con=DBConnectionPool.getConnection();
			query="DEELETE FROM"+DBNames.TABLE_TRAINEE+"WHERE "+DBNames.ATT_TRAINEE_REGISTER+"='"+pTrainee.getRegister()+"'";
			stmt=con.createStatement();
			stmt.executeQuery(query);
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}

	}
	public ArrayList<Trainee> getTrainees(Trainee pTrainee){
		if(pTrainee.getName()==null && pTrainee.getSurname()==null)
			throw new MandatoryFieldException("insert name ,surname or both ");
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Trainee> traineeList=new ArrayList<Trainee>();
		String query1,query2;
		try{
			con=DBConnectionPool.getConnection();
			query1="SELECT * FROM"+DBNames.TABLE_TRAINEE+"WHERE ";
			if(pTrainee.getName()!=null && pTrainee.getSurname()!=null)
				query1+=DBNames.ATT_TRAINEE_NAME+"='"+pTrainee.getName()+"' AND"
						+DBNames.ATT_TRAINEE_SURNAME+"='"+pTrainee.getSurname()+"'";
			else if(pTrainee.getName()!=null && pTrainee.getSurname()==null)
				query1+=DBNames.ATT_TRAINEE_NAME+"='"+pTrainee.getName()+"' ";
			else
				query1+=DBNames.ATT_TRAINEE_SURNAME+"='"+pTrainee.getSurname()+"'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(query1);
			while(rs.next()){
				String name=rs.getString(DBNames.ATT_TRAINEE_NAME);
				String surname=rs.getString(DBNames.ATT_TRAINEE_SURNAME);
				String register=rs.getString(DBNames.ATT_TRAINEE_REGISTER);
				String email=rs.getString(DBNames.ATT_TRAINEE_EMAIL);
				String address=rs.getString(DBNames.ATT_TRAINEE_ADDRESS);
				String birthCity=rs.getString(DBNames.ATT_TRAINEE_BIRTHCITY);
				String cityOfResidence=rs.getString(DBNames.ATT_TRAINEE_CITYOFRESIDENCE);
				String cap=rs.getString(DBNames.ATT_TRAINEE_CAP);
				Date birthDateSQL=rs.getDate(DBNames.ATT_TRAINEE_BIRTHDATE);
				java.util.Date date=new java.util.Date(birthDateSQL.getTime());
				GregorianCalendar birthDate=new GregorianCalendar();
				birthDate.setTime(date);
				int delegate=rs.getInt(DBNames.ATT_TRAINEE_DELEGATEACCOUNT);
				query2="SELECT *FROM "+DBNames.TABLE_ACCOUNT;//da completare con i dati del delegato 
				Trainee trainee=new Trainee(register, name, surname, email, address, birthCity, cityOfResidence, cap, birthDate, delegate);
				traineeList.add(trainee);
			}
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);

		}
		return traineeList;
	}
}

