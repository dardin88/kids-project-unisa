package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementor of IRegistrationChildManager with JDBC to a Relational DataBase
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class JDBCRegistrationChildManager implements IRegistrationChildManager {
    private static JDBCRegistrationChildManager manager;

    private JDBCRegistrationChildManager(){
    }

    public static synchronized JDBCRegistrationChildManager getInstance() {
        if(manager == null) {
          manager = new JDBCRegistrationChildManager();
        }
        return manager;
    }

    public synchronized RegistrationChild create(RegistrationChild pChildReg) throws SQLException {
        Connection con = null;
        Statement stmt = null;

        String query = "INSERT INTO '"+DBNames.TABLE_REGISTRATIONCHILD + "' (" +
                      DBNames.ATT_REGISTRATIONCHILD_SURNAME + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_NAME + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_FISCALCODE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_USERSECTION + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_SICK + ", " +
                      DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE + ") " +
                      "VALUES" +
                      "(" + makeString(pChildReg.getSurname()) + ", " +
                      makeString(pChildReg.getName()) + ", "+
                      makeSQLDateString(pChildReg.getBornDate()) + ", "+
                      makeString(pChildReg.getCommuneBorn()) + ", "+
                      pChildReg.getFiscalCode()+"', '" +
                      pChildReg.getCitizenship()+"', '"+
                      pChildReg.getUserSection()+"', '"+
                      pChildReg.getRegistrationDate()+"', '"+
                      pChildReg.isSick()+"', '"+
                      pChildReg.getRegistrationPhase()+"' );"; 			

            try
            {
                    con=DBConnectionPool.getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(query);
            }
            finally{
                    stmt.close();
                    DBConnectionPool.releaseConnection(con);
            }	

            return pChildReg;
    }
	  
	  public synchronized RegistrationChild delete(RegistrationChild pChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  String query="DELETE FROM '"+DBNames.TABLE_CHILD+
				  			"' WHERE '"+DBNames.ATT_CHILD_ID+"'='"+pChildReg.getRegistrationId()+"';";
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  stmt.executeUpdate(query);
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }	
		  
		  return pChildReg;
	  }
	  
	  
	  public synchronized List<RegistrationChild> search(RegistrationChild pChildReg) throws SQLException
	  {
		  Connection con = null;
		  Statement stmt=null;
		  ResultSet result=null;
		  RegistrationChild tmpRegChild=new RegistrationChild();		  
		  boolean andState = false;
		  List<RegistrationChild> listOfChildReg=new ArrayList<RegistrationChild>();		
		  String query="SELECT * FROM '"+DBNames.TABLE_CHILD+"' WHERE ";				
		  
		  if (pChildReg.getBornDate()!=null)
		  {
			  query=query+"'"+DBNames.ATT_CHILD_BORNDATE+"'='"+pChildReg.getBornDate()+"'";
			  andState=true;
		  }
		  if (pChildReg.getCitizenship()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_CITIZENSHIP+"'='"+pChildReg.getCitizenship()+"'";
			  andState=true;
		  } 
		  if (pChildReg.getCommuneBorn()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_COMMONBORN+"'='"+pChildReg.getCommuneBorn()+"'";
			  andState=true;
		  }
		  if (pChildReg.getFiscalCode()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_FISCALCODE+"'='"+pChildReg.getFiscalCode()+"'";
			  andState=true;
		  }
		  if (pChildReg.getName()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_NAME+"'='"+pChildReg.getName()+"'";
			  andState=true;
		  }
		  if (pChildReg.getRegistrationDate()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_REGISTRATIONDATE+"'='"+pChildReg.getRegistrationDate()+"'";
			  andState=true;
		  }
		  if (pChildReg.getRegistrationId()>0)
		  {
			  query=query+"'"+DBNames.ATT_CHILD_ID+"'='"+pChildReg.getRegistrationId()+"'";
			  andState=true;
		  }
		  if (pChildReg.isSick()!=true)							//chiedo conferma per la gestione del boolean
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_SICK+"'='"+pChildReg.isSick()+"'";
			  andState=true;
		  }
		  if (pChildReg.getSurname()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_SURNAME+"'='"+pChildReg.getSurname()+"'";
			  andState=true;
		  }
		  if (pChildReg.getUserSection()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_USERSECTION+"'='"+pChildReg.getUserSection()+"'";
			  andState=true;
		  }
		  if (pChildReg.getRegistrationPhase()!=null)
		  {
			  query+=useAnd(andState)+"'"+DBNames.ATT_CHILD_REGISTRATIONPHASE+"'='"+pChildReg.getRegistrationPhase()+"'";
			  andState=true;
		  }
		  
		  //forse ci manca if: accountgenitore, classe
		  
		  query=query+";";
		  		  
		  
		  try
		  {
			  con=DBConnectionPool.getConnection();
			  stmt = con.createStatement();
			  result=stmt.executeQuery(query);
			  
			  while(result.next())
			  {
				  tmpRegChild.setBornDate(result.getString(DBNames.ATT_CHILD_BORNDATE));
				  tmpRegChild.setCitizenship(result.getString(DBNames.ATT_CHILD_CITIZENSHIP));
				  tmpRegChild.setCommuneBorn(result.getString(DBNames.ATT_CHILD_COMMONBORN));
				  tmpRegChild.setFiscalCode(result.getString(DBNames.ATT_CHILD_FISCALCODE));
				  tmpRegChild.setName(result.getString(DBNames.ATT_CHILD_NAME));
				  tmpRegChild.setRegistrationDate(result.getString(DBNames.ATT_CHILD_REGISTRATIONDATE));
				  tmpRegChild.setRegistrationId(result.getInt(DBNames.ATT_CHILD_ID));
				  tmpRegChild.setSick(result.getBoolean(DBNames.ATT_CHILD_SICK));
				  tmpRegChild.setSurname(result.getString(DBNames.ATT_CHILD_SURNAME));
				  tmpRegChild.setUserSection(result.getString(DBNames.ATT_CHILD_USERSECTION));
				  tmpRegChild.setRegistrationPhase(result.getString(DBNames.ATT_CHILD_REGISTRATIONPHASE));
				  
				  //accountgenitore e classe
				  
				  listOfChildReg.add(tmpRegChild);
			  }
		  }
		  finally{
			  stmt.close();
			  DBConnectionPool.releaseConnection(con);
		  }
		  
		  return listOfChildReg;
	  }
	  
		private String useAnd(boolean pEnableAnd) {
			return pEnableAnd ? " AND " : " ";
		}
	  
	  public synchronized RegistrationChild update(RegistrationChild pChildReg) throws SQLException
	  {
		  	Connection con = null;
		  	Statement stmt=null;
			String query="UPDATE '"+DBNames.TABLE_CHILD+"' " + 
							"SET ("+DBNames.ATT_CHILD_SURNAME+", "+
									DBNames.ATT_CHILD_NAME+", "+
									DBNames.ATT_CHILD_BORNDATE+", "+
							  		DBNames.ATT_CHILD_COMMONBORN+", "+
									DBNames.ATT_CHILD_FISCALCODE+", "+
							  		DBNames.ATT_CHILD_CITIZENSHIP+", "+
									DBNames.ATT_CHILD_USERSECTION+", "+
							  		DBNames.ATT_CHILD_REGISTRATIONDATE+", "+
									DBNames.ATT_CHILD_SICK+", "+
							  		DBNames.ATT_CHILD_REGISTRATIONPHASE+") VALUES ('"+			//anche accountgenitore e classe? 
							  				 pChildReg.getSurname()+"', '"+
											 pChildReg.getName()+"', '"+
											 pChildReg.getBornDate()+"', '"+
											 pChildReg.getCommuneBorn()+"', '"+
											 pChildReg.getFiscalCode()+"', '" +
											 pChildReg.getCitizenship()+"', '"+
											 pChildReg.getUserSection()+"', '"+
											 pChildReg.getRegistrationDate()+"', '"+
											 pChildReg.isSick()+"', '"+
											 pChildReg.getRegistrationPhase()+"') "+ 
					 	"WHERE '"+DBNames.ATT_CHILD_ID+"'="+pChildReg.getRegistrationId()+";"; 
			
			try
			{
				  con=DBConnectionPool.getConnection();
				  stmt = con.createStatement();
				  stmt.executeUpdate(query);
			}
			finally{
				  stmt.close();
				  DBConnectionPool.releaseConnection(con);
			}	
			
			 return pChildReg;
		}
          
    public int getNumberChildren(String parentId) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String query = null;
        int num=0;
        
        try {
            con = DBConnectionPool.getConnection();
            // constructing query string
            query = "SELECT count(*) as NumberOfChild" +
                            "FROM" + DBNames.TABLE_REGISTRATIONCHILD + ","+
                            "WHERE" + DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID + "=" + parentId;

            stmt = con.createStatement();
            stmt.executeUpdate(query);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                num=rs.getInt("NumberOfChild");
            }
            con.commit();
        } finally {
            if(stmt != null)
                stmt.close();
            if(con != null)
                DBConnectionPool.releaseConnection(con);
        }
        return num;
   }
             
             
    private String makeString(String d) {
        if (d == null) {
            return null;
        } else {
            return "'" + d + "'";
        }
    }

    private String makeSqlDateString(GregorianCalendar d) {
        if (d == null) {
            return null;
        } else {
            return "'" + d.get(Calendar.YEAR) + "-" + d.get(Calendar.MONTH) + "-" + d.get(Calendar.DAY_OF_MONTH) + "'";
        }
    }

}