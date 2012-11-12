package it.unisa.kids.accessManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerRecourses {

	private static ManagerRecourses manager;
	private Account parent;

	private ManagerRecourses(){
		parent=new Account();
	}

	public ManagerRecourses getInstace(){
		if (manager!=null)
			return manager;
		else
			return manager=new ManagerRecourses();
	}

	public Recourse Create(Recourse recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;

		try {
			con=DBConnectionPool.getConnection();

			String query="INSERT INTO "+DBNames.TABLE_RECOURSE+" ("+DBNames.ATT_RECOURSE_DATA+","+DBNames.ATT_RECOURSE_REASON+","+DBNames.ATT_RECOURSE_VALUTATION+") VALUES ("+recourse.getData()+","+recourse.getReason()+","+null+")"; 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}

		return recourse;
	}

	public Recourse Delete(Recourse recourse){
		String query="delete from name_table where id='"+recourse.getId()+"'";
		return recourse;
	}



	public Recourse Accept(Recourse recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;

		try {
			con=DBConnectionPool.getConnection();

			String query="Update "+DBNames.TABLE_RECOURSE+"" +
					"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
					"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return recourse;
	}

	public Recourse Refuse(Recourse recourse) throws SQLException{
		Connection con = null;
		Statement stmt=null;

		try {
			con=DBConnectionPool.getConnection();

			String query="Update "+DBNames.TABLE_RECOURSE+"" +
					"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
					"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";


			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return recourse;
	}

	public ArrayList<Recourse> Search(Recourse recourse){
		Connection con = null;
		Statement stmt=null;
		ResultSet rsRecourse=null;
		Recourse recourse2=new Recourse();
		ArrayList<Recourse> listRecourse=new ArrayList<Recourse>();

		try {
			con=DBConnectionPool.getConnection();


			//request= iscrizione?
			String query="Select * From "+DBNames.TABLE_RECOURSE+","+DBNames.TABLE_REQUEST+","+DBNames.TABLE_ACCOUNT+" where "+DBNames.ATT_RECOURSE_IDREGISTRATION+"="+DBNames.TABLE_REQUEST+"."+DBNames.ATT_REQUEST_ID+" AND "+DBNames.ATT_REQUEST_IDACCOUNT+"="+DBNames.TABLE_ACCOUNT+"."+DBNames.ATT_ACCOUNT_ID+"";
			if (recourse.getId()==0)
				query=query+"'"+DBNames.ATT_RECOURSE_ID+"'='"+recourse.getId()+"'";
			if (recourse.getData()!=null)
				query=query+"'"+DBNames.ATT_RECOURSE_DATA+"'='"+recourse.getData()+"'";
			if (parent.getNameUser()!=null)
				query=query+"'"+DBNames.ATT_ACCOUNT_NAMEUSER+"'='"+parent.getNameUser()+"'";
			if (parent.getSurnameUser()!=null)
				query=query+"'"+DBNames.ATT_ACCOUNT_SURNAMEUSER+"'='"+parent.getSurnameUser()+"'";

			stmt = con.createStatement();
			rsRecourse=stmt.executeQuery(query);
			while(rsRecourse.next()){
				int id=rsRecourse.getInt(DBNames.ATT_RECOURSE_ID);
				String data=rsRecourse.getString(DBNames.ATT_RECOURSE_DATA);
				String reason=rsRecourse.getString(DBNames.ATT_RECOURSE_REASON);
				String valutation=rsRecourse.getString(DBNames.ATT_RECOURSE_VALUTATION);
				int id_registration=rsRecourse.getInt(DBNames.ATT_RECOURSE_IDREGISTRATION);
				recourse2.setData(data);
				recourse2.setId(id);
				recourse2.setId_registration(id_registration);
				recourse2.setReason(reason);
				recourse2.setValutation(valutation);
				listRecourse.add(recourse2);

			}
		} 
		finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}

		return listRecourse;

	}

}
