package it.unisa.kids.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.kids.accessManagement.accountManagement.Parent;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.storage.connectionPool.DBConnectionPool;

public class AccessFacade implements IAccessFacade
{

	// Stato di Standby
	public int getParentId(int childId)
	{
		JDBCRegistrationChildManager c;
		RegistrationChild child = new RegistrationChild();
		child.setIdParent(childId);
		
		return child.getIdParent();
	}

	public int getNumberOfChildren(int parentId) throws SQLException 
	{
		int idP=p.getId();

		ResultSet rs=null;
		Connection con = null;
		Statement stmt = null;
		String query = null;

		try {
			con = DBConnectionPool.getConnection();

			// constructing query string
			query = "select count * as PIPPO" +
					"from" + DBNames.TABLE_REGISTRATION + ","+
					"where" + DBNames.ATT_REGISTRATION_ACCOUNT_PARENT + "=" + idP;

			stmt = con.createStatement();
			stmt.executeUpdate(query);
			rs=stmt.executeQuery(query);
			con.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}


		int num=0;
		if (rs.next())
			return rs.getInt("PIPPO");

		return num;
	}

	public boolean isSick(RegistrationChild c) {
		return c.isSick();
	}


}
