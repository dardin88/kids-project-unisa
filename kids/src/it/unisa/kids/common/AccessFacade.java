package it.unisa.kids.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import it.unisa.kids.accessManagement.accountManagement.Parent;
import it.unisa.kids.accessManagement.classificationManagement.Classification;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.storage.connectionPool.DBConnectionPool;

public class AccessFacade implements IAccessFacade
{

	public int getParentId(RegistrationChild c) {

		return c.getRegistrationId();
	}

	public int getNumberOfChildren(Parent p) throws SQLException 
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
		while (rs.next()) 
			num=rs.getInt("PIPPO");

		return num;
	}

	public boolean isSick(RegistrationChild c) {
		return c.isSick();
	}


}
