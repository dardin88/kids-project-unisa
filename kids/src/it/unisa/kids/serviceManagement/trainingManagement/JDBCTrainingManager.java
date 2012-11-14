package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.Account;
import it.unisa.kids.accessManagement.ManagerAccount;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class JDBCTrainingManager implements ITrainingManager {
	private static ITrainingManager manager;
	private JDBCTrainingManager(){

	}
	public static ITrainingManager getInstance(){
		if(manager==null){
			manager=new JDBCTrainingManager();
		}
		return manager;
	}
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#insertTrainee(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
	 */
	@Override
	public void insertTrainee(Trainee pTrainee) throws SQLException{
		Connection con = null;
		PreparedStatement pStmt=null;
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
			query2="VALUES (?,?,?,?,?,?,?,?,?,?";
					
			if (pTrainee.getTelephoneNumber() != null) {
				query1 += ", " + DBNames.ATT_TRAINEE_TELEPHONENUMBER;
				query2 += ",?" ;
			}
			query1+=")";
			query2+=")";
			pStmt = con.prepareStatement(query1+query2);
			pStmt.setString(1, pTrainee.getRegister());
			pStmt.setString(2,pTrainee.getName());
			pStmt.setString(3, pTrainee.getSurname());
			pStmt.setString(4,pTrainee.getEmail());
			pStmt.setDate(5, new Date(pTrainee.getBirthDate().getTimeInMillis()));
			pStmt.setString(6,pTrainee.getBirthCity());
			pStmt.setString(7, pTrainee.getCityOfResidence());
			pStmt.setString(8, pTrainee.getAddress());
			pStmt.setString(9, pTrainee.getCap());
			pStmt.setInt(10, pTrainee.getDelegate().getId());
			if (pTrainee.getTelephoneNumber() != null){
				pStmt.setString(11, pTrainee.getTelephoneNumber());
			}
			pStmt.executeUpdate();
			con.commit();

			
		} 
		finally{
			pStmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#update(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
	 */
	@Override
	public void update(Trainee pTrainee){

	}
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#deleteTrainee(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
	 */
	@Override
	public void deleteTrainee(Trainee pTrainee) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		String query;
		try{
			con=DBConnectionPool.getConnection();
			query="DEELETE FROM"+DBNames.TABLE_TRAINEE+"WHERE "+DBNames.ATT_TRAINEE_REGISTER+"='"+pTrainee.getRegister()+"'";
			stmt=con.createStatement();
			stmt.executeUpdate(query);
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}

	}
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#getTrainees(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
	 */
	@Override
	public ArrayList<Trainee> getTrainees(Trainee pTrainee) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		ResultSet rsTrainee=null;
		ArrayList<Trainee> traineeList=new ArrayList<Trainee>();
		String query;
		try{
			con=DBConnectionPool.getConnection();
			query="SELECT * FROM "+DBNames.TABLE_TRAINEE+" WHERE ";
			if(pTrainee.getName()!=null && pTrainee.getSurname()!=null)
				query+=DBNames.ATT_TRAINEE_NAME+"='"+pTrainee.getName()+"' AND "
						+DBNames.ATT_TRAINEE_SURNAME+"='"+pTrainee.getSurname()+"'";
			else if(pTrainee.getName()!=null && pTrainee.getSurname()==null)
				query+=DBNames.ATT_TRAINEE_NAME+"='"+pTrainee.getName()+"' ";
			else
				query+=DBNames.ATT_TRAINEE_SURNAME+"='"+pTrainee.getSurname()+"'";
			stmt=con.createStatement();
			rsTrainee=stmt.executeQuery(query);
			con.commit();
			while(rsTrainee.next()){
				String name=rsTrainee.getString(DBNames.ATT_TRAINEE_NAME);
				String surname=rsTrainee.getString(DBNames.ATT_TRAINEE_SURNAME);
				String register=rsTrainee.getString(DBNames.ATT_TRAINEE_REGISTER);
				String email=rsTrainee.getString(DBNames.ATT_TRAINEE_EMAIL);
				String address=rsTrainee.getString(DBNames.ATT_TRAINEE_ADDRESS);
				String birthCity=rsTrainee.getString(DBNames.ATT_TRAINEE_BIRTHCITY);
				String cityOfResidence=rsTrainee.getString(DBNames.ATT_TRAINEE_CITYOFRESIDENCE);
				String cap=rsTrainee.getString(DBNames.ATT_TRAINEE_CAP);
				Date birthDateSQL=rsTrainee.getDate(DBNames.ATT_TRAINEE_BIRTHDATE);
				GregorianCalendar birthDate=new GregorianCalendar();
				birthDate.setTime(birthDateSQL);
				int delegateId=rsTrainee.getInt(DBNames.ATT_TRAINEE_DELEGATEACCOUNT);
				String telephoneNumber=rsTrainee.getString(DBNames.ATT_TRAINEE_TELEPHONENUMBER);
				ManagerAccount managerAccount=ManagerAccount.getInstance();
				Account delegate=new Account();
				delegate.setId(delegateId);
				ArrayList<Account> listDelegate=managerAccount.Search(delegate);
				delegate=listDelegate.get(0);
				Trainee trainee=new Trainee();
				trainee.setName(name);
				trainee.setSurname(surname);
				trainee.setRegister(register);
				trainee.setEmail(email);
				trainee.setAddress(address);
				trainee.setBirthCity(birthCity);
				trainee.setCityOfResidence(cityOfResidence);
				trainee.setCap(cap);
				trainee.setBirthDate(birthDate);
				trainee.setDelegate(delegate);
				trainee.setTelephoneNumber(telephoneNumber);
				traineeList.add(trainee);
			}
		}
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);

		}
		return traineeList;
	}
	
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#insertActivity(it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity)
	 */
	@Override
	public void insertActivity(TraineeActivity pTraineeActivity) throws SQLException{
		Connection con = null;
		PreparedStatement pStmt=null;
		String query1;
		String query2;
		try {
			con=DBConnectionPool.getConnection();
			query1="INSERT INTO "+DBNames.TABLE_TRAINEE_ACT+"("
					+DBNames.ATT_TRAINEEACTIVITY_DATE+","
					+DBNames.ATT_TRAINEEACTIVITY_NAME+","
					+DBNames.ATT_TRAINEEACTIVITY_STARTTIME +","
					+DBNames.ATT_TRAINEEACTIVITY_ENDTIME+","
					+DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT;
					
					
			query2="VALUES (?,?,?,?,?";
			if (pTraineeActivity.getDescription() != null) {
				query1 += "," + DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION;
				query2 += ",?" ;
			}
			query1+=")";
			query2+=")";
			pStmt = con.prepareStatement(query1+query2);
			pStmt.setDate(1,new Date(pTraineeActivity.getDate().getTimeInMillis()));
			pStmt.setString(2, pTraineeActivity.getName());
			pStmt.setTime(3,pTraineeActivity.getStart());
			pStmt.setTime(4,pTraineeActivity.getEnd());
			pStmt.setInt(5, pTraineeActivity.getDelegate().getId());
			if(pTraineeActivity.getDescription()!=null){
				pStmt.setString(6, pTraineeActivity.getDescription());
			}
			pStmt.executeUpdate();
			con.commit();
		} 
		finally{
			pStmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	/* (non-Javadoc)
	 * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#deleteActivity(it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity)
	 */
	@Override
	public void deleteActivity(TraineeActivity pTraineeActivity) throws SQLException{
		Connection con = null;
		PreparedStatement pStmt=null;
		String query;
		try{
			con=DBConnectionPool.getConnection();
			query="DELETE FROM "+DBNames.TABLE_TRAINEE_ACT+" WHERE "+DBNames.ATT_TRAINEEACTIVITY_DATE+"=? AND "+DBNames.ATT_TRAINEE_NAME+"=?" ;
			
			pStmt=con.prepareStatement(query);
			pStmt.setDate(1, new Date(pTraineeActivity.getDate().getTimeInMillis()));
			pStmt.setString(2, pTraineeActivity.getName());
			pStmt.executeUpdate();
			con.commit();
		}
		finally{
			pStmt.close();
			DBConnectionPool.releaseConnection(con);
		}
	}
	public ArrayList<TraineeActivity> getActivities() throws SQLException{
            Connection con=null;
            Statement stmt=null;
            String query;
            ArrayList<TraineeActivity> listActivity=null;
            try{
                con=DBConnectionPool.getConnection();
                query="SELECT * FROM "+DBNames.TABLE_ACT;
                stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                return listActivity;
            }
            finally{
                stmt.close();
                DBConnectionPool.releaseConnection(con);
                
            }
            
            
        }
	
	
}

