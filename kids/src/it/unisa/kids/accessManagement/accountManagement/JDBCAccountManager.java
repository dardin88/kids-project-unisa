package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class JDBCAccountManager implements IAccountManager {

	private static JDBCAccountManager manager;

	private JDBCAccountManager() {
	}

	public static JDBCAccountManager getInstance() {
		if (manager != null) {
			return manager;
		} else {
			return (manager = new JDBCAccountManager());
		}
	}

	public Account authenticate(String pUserName, String pPass) throws SQLException {
		Account searchAccount = new Account();
		searchAccount.setNickName(pUserName);
		searchAccount.setPassword(pPass);

		List<Account> accountList = search(searchAccount);

		if (accountList.size() > 0) {
			return accountList.get(0);
		} else {
			return null;
		}

	}

	public Account insert(Account pAccount) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String nickname = null;
		String query1 = null;
		String query2 = null;
		String data1=null;
		String data2=null;
		String data3=null;
		ResultSet rs=null;

		GregorianCalendar birthDate=pAccount.getDataOfBirth();
		GregorianCalendar expDate=pAccount.getContractExpirationDate();
		GregorianCalendar regDate=pAccount.getRegistrationDate();

		if(birthDate!=null){
           data1= makeSqlDateString(birthDate);
        }
        if(expDate!=null){
           data2= makeSqlDateString(expDate);
        }
        if(regDate!=null){
           data3= makeSqlDateString(regDate);
        }
		
		try {
			con = DBConnectionPool.getConnection();
			/*generation of nickname */
            int i=2;
			while (true) {
				nickname = pAccount.getNameUser() + pAccount.getSurnameUser();
				query1 = "Select " + DBNames.ATT_ACCOUNT_NICKNAME + " From " + DBNames.TABLE_ACCOUNT + " Where "+DBNames.ATT_ACCOUNT_NICKNAME+" = '" + pAccount.getNickName() + "'";
				stmt=con.createStatement();
				rs=stmt.executeQuery(query1);
                                con.commit();

				if (rs.next()){
					nickname = nickname + i;
				} else {
					break;
				}
				i++;
			}
			/*generation of password*/
			String password = generatePassword();

                     query2 = "Insert into " + DBNames.TABLE_ACCOUNT + "( " + DBNames.ATT_ACCOUNT_NAME + "," +
                     DBNames.ATT_ACCOUNT_REGISTRATIONDATE+
                     "," + DBNames.ATT_ACCOUNT_CAPDOMICILIE + 
                    "," + DBNames.ATT_ACCOUNT_CAPRESIDENCE + 
                    "," + DBNames.ATT_ACCOUNT_CELLULARNUMBER
                    + "," + DBNames.ATT_ACCOUNT_CITIZENSHIP +
                    "," + DBNames.ATT_ACCOUNT_TAXCODE + 
                    "," + DBNames.ATT_ACCOUNT_SURNAMEUSER+
                     "," + DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE +
                    "," + DBNames.ATT_ACCOUNT_DATEOFBIRTH + 
                    ","+ DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE +
                    "," + DBNames.ATT_ACCOUNT_EMAIL + 
                    "," + DBNames.ATT_ACCOUNT_FACULTY + 
                    "," + DBNames.ATT_ACCOUNT_FAX + 
                    "," + DBNames.ATT_ACCOUNT_PLACEOFBIRTH +
                    "," + DBNames.ATT_ACCOUNT_NICKNAME 
                    + "," + DBNames.ATT_ACCOUNT_PASSWORD + 
                    "," + DBNames.ATT_ACCOUNT_PROVINCEDOMICILE
                    + "," + DBNames.ATT_ACCOUNT_PROVINCERESIDENCE + 
                    "," + DBNames.ATT_ACCOUNT_INCOME + 
                    "," + DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE
                    + "," + DBNames.ATT_ACCOUNT_FAMILYSITUATION + 
                    "," + DBNames.ATT_ACCOUNT_TELEPHONENUMBER + 
                    "," + DBNames.ATT_ACCOUNT_TYPEACCOUNT
                    + "," + DBNames.ATT_ACCOUNT_TYPEPARENT + 
                    "," + DBNames.ATT_ACCOUNT_QUALIFICATION + 
                    "," + DBNames.ATT_ACCOUNT_ADDRESSDOMICILE
                    + "," + DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE +
                    ","+ DBNames.ATT_ACCOUNT_STATE +
                    ","+ DBNames.ATT_ACCOUNT_REGISTER +") values (" + makeString(pAccount.getNameUser()) + ","+data3
                    + "," + makeString(pAccount.getCapDomicile()) + "," + makeString(pAccount.getCapResidence()) + "," + makeString(pAccount.getCellularNumber())
                    + "," + makeString(pAccount.getCitizenship()) + "," + makeString(pAccount.getTaxCode()) + "," + makeString(pAccount.getSurnameUser())
                    + "," + makeString(pAccount.getMunicipalityResidence()) + "," + data1 + "," + data2
                    + "," + makeString(pAccount.getEmail()) + "," + makeString(pAccount.getFaculty()) + "," + makeString(pAccount.getFax()) + "," + makeString(pAccount.getPlaceOfBirth())
                    + "," + makeString(nickname) + "," + makeString(password) 
                    + "," + makeString(pAccount.getProvinceDomicile()) + "," + makeString(pAccount.getProvinceResidence()) + "," + pAccount.getIncome()
                    + "," + makeString(pAccount.getMunicipalityDomicile()) + "," + makeString(pAccount.getFamilySituation()) + "," + makeString(pAccount.getTelephoneNumber()) + "," + makeString(pAccount.getAccountType())+","+ makeString(pAccount.getTypeParent())
                    + "," + makeString(pAccount.getQualification()) + "," + makeString(pAccount.getViaDomicile()) + "," + makeString(pAccount.getViaResidence()) + ","+ makeString(pAccount.getState()) +","+makeString(pAccount.getRegister())+")";

			stmt = con.createStatement();
			stmt.executeUpdate(query2);
			con.commit();
			return pAccount;
		} finally {
			if(stmt!=null){
				stmt.close();
			}
			DBConnectionPool.releaseConnection(con);
		}
	}

	public Account update(Account pChangedAccount) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String data1=null;
		String data2=null;
		String data3=null;
	GregorianCalendar birthDate=pChangedAccount.getDataOfBirth();
        GregorianCalendar expDate=pChangedAccount.getContractExpirationDate();
        GregorianCalendar regDate=pChangedAccount.getRegistrationDate();
        
        if(birthDate!=null){
           data1= makeSqlDateString(birthDate);
        }
        if(expDate!=null){
           data2= makeSqlDateString(expDate);
        }
        if(regDate!=null){
           data3= makeSqlDateString(regDate);
        }

		try {
			con = DBConnectionPool.getConnection();
            String query = "Update "+DBNames.TABLE_ACCOUNT
                    + " SET " + DBNames.ATT_ACCOUNT_REGISTRATIONDATE + "=" + data3 + "," 
                    + DBNames.ATT_ACCOUNT_CAPDOMICILIE + "=" + makeString(pChangedAccount.getCapDomicile()) + ","
                    + DBNames.ATT_ACCOUNT_CAPRESIDENCE + "=" + makeString(pChangedAccount.getCapResidence()) + "," 
                    + DBNames.ATT_ACCOUNT_CELLULARNUMBER + "=" + makeString(pChangedAccount.getCellularNumber()) + "," 
                    + DBNames.ATT_ACCOUNT_CITIZENSHIP + "=" + makeString(pChangedAccount.getCitizenship()) + ","
                    + DBNames.ATT_ACCOUNT_TAXCODE + "=" + makeString(pChangedAccount.getTaxCode()) + ","
                    + DBNames.ATT_ACCOUNT_SURNAMEUSER + "=" + makeString(pChangedAccount.getSurnameUser()) + "," 
                    + DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE + "=" + makeString(pChangedAccount.getMunicipalityResidence()) + ","
                    + DBNames.ATT_ACCOUNT_DATEOFBIRTH + "=" + data1 + ","
                    + DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE + "=" + data3 + "," 
                    + DBNames.ATT_ACCOUNT_EMAIL + "="+ makeString(pChangedAccount.getEmail()) + ","
                    + DBNames.ATT_ACCOUNT_FACULTY + "=" + makeString(pChangedAccount.getFaculty()) + "," 
                    + DBNames.ATT_ACCOUNT_FAX + "=" + makeString(pChangedAccount.getFax()) + ","
                    + DBNames.ATT_ACCOUNT_PLACEOFBIRTH + "=" + makeString(pChangedAccount.getPlaceOfBirth()) + "," 
                    + DBNames.ATT_ACCOUNT_NICKNAME + "=" + makeString(pChangedAccount.getNickName()) + ","
                    + DBNames.ATT_ACCOUNT_NAME + "=" + makeString(pChangedAccount.getNameUser()) + ","
                    + DBNames.ATT_ACCOUNT_PASSWORD + "=" + makeString(pChangedAccount.getPassword()) + "," 
                    + DBNames.ATT_ACCOUNT_PROVINCEDOMICILE + "=" + makeString(pChangedAccount.getProvinceDomicile()) + ","
                    + DBNames.ATT_ACCOUNT_PROVINCERESIDENCE + "=" + makeString(pChangedAccount.getProvinceResidence()) + "," 
                    + DBNames.ATT_ACCOUNT_INCOME + "=" + pChangedAccount.getIncome() + "," 
                    + DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE + "=" + makeString(pChangedAccount.getMunicipalityDomicile()) + ","
                    + DBNames.ATT_ACCOUNT_FAMILYSITUATION + "=" + makeString(pChangedAccount.getFamilySituation()) + ","
                    + DBNames.ATT_ACCOUNT_TELEPHONENUMBER + "=" + makeString(pChangedAccount.getTelephoneNumber()) + ","
                    + DBNames.ATT_ACCOUNT_TYPEACCOUNT + "=" + makeString(pChangedAccount.getAccountType()) + "," 
                    + DBNames.ATT_ACCOUNT_QUALIFICATION + "=" + makeString(pChangedAccount.getQualification()) + "," 
                    + DBNames.ATT_ACCOUNT_ADDRESSDOMICILE + "=" + makeString(pChangedAccount.getViaDomicile()) + "," 
                    + DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE + "=" + makeString(pChangedAccount.getViaResidence())+","
                    + DBNames.ATT_ACCOUNT_STATE + "=" + makeString(pChangedAccount.getViaResidence())+","
                    + DBNames.ATT_ACCOUNT_REGISTER + "=" + makeString(pChangedAccount.getRegister())+","
                    + DBNames.ATT_ACCOUNT_TYPEPARENT+"="+makeString(pChangedAccount.getTypeParent())
                    + " WHERE " + DBNames.ATT_ACCOUNT_ID + "=" + pChangedAccount.getId();

                        
			stmt = con.createStatement();
			stmt.executeUpdate(query);
                        con.commit();
                       
		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return pChangedAccount;
	}


          
	public Account delete(Account pDeletedAccount) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBConnectionPool.getConnection();
			String query = "Delete From " + DBNames.TABLE_ACCOUNT + " Where " + DBNames.ATT_ACCOUNT_ID + "='" + pDeletedAccount.getId() + "'";
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		} finally {
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return pDeletedAccount;
	}

	public synchronized List<Account> search(Account pAccount) throws SQLException 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<Account> account = null;
		boolean andState = false;

		try {
			con = DBConnectionPool.getConnection();

			// constructing search query string
			query = "SELECT * " +
					"FROM " + DBNames.TABLE_ACCOUNT +
					" WHERE ";
			if (pAccount.getId() > 0) {		
				query += " " + DBNames.ATT_ACCOUNT_ID + " = ?";
				andState = true;
			}

			if (pAccount.getNickName()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_NICKNAME + " = ?";
				andState = true;
			}
          

			if (pAccount.getSurnameUser()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_SURNAMEUSER + " = ?";
				andState = true;
			}

			if (pAccount.getNameUser()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_NAME + " = ?";
				andState = true;
			}

			
			

			if (pAccount.getTaxCode()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_TAXCODE + " = ?";
				andState = true;
			}

			
                        if (pAccount.getAccountType()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_TYPEACCOUNT+ " = ?";
				andState = true;
			}
		

             
                        
			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pAccount.getId() > 0) {		// >= 0 ??
				pstmt.setInt(1, pAccount.getId());
				i++;
			}

			if (pAccount.getNickName()!=null) {
				pstmt.setString(2, pAccount.getNickName());
				i++;
			}


			if (pAccount.getSurnameUser()!=null) {
				pstmt.setString(3, pAccount.getSurnameUser());
				i++;
			}

			if (pAccount.getNameUser()!=null) {
				pstmt.setString(4, pAccount.getNameUser());
				i++;
			}
                        
                        if (pAccount.getNameUser()!=null) {
				pstmt.setString(5, pAccount.getTaxCode());
				i++;
			}
                        if (pAccount.getAccountType()!=null) {
				pstmt.setString(6,pAccount.getAccountType());
				i++;
			}
		

			

			rs = pstmt.executeQuery();
			con.commit();

			// constructing payment list
			account = new ArrayList<Account>();
			while (rs.next()) {
				Account p = new Account();
                                p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));
                                p.setNickName(rs.getString(DBNames.ATT_ACCOUNT_NICKNAME));
                                p.setPassword(rs.getString(DBNames.ATT_ACCOUNT_PASSWORD));
                                p.setSurnameUser(rs.getString(DBNames.ATT_ACCOUNT_SURNAMEUSER));
                                p.setNameUser(rs.getString(DBNames.ATT_ACCOUNT_NAME));
                                p.setEmail(rs.getString(DBNames.ATT_ACCOUNT_EMAIL));
                                p.setTelephoneNumber(rs.getString(DBNames.ATT_ACCOUNT_TELEPHONENUMBER));
                                p.setCellularNumber(rs.getString(DBNames.ATT_ACCOUNT_CELLULARNUMBER));
                                p.setFax(rs.getString(DBNames.ATT_ACCOUNT_FAX));

                                //getting Date from ResultSet and converting it to GregorianCalendar
                               /* GregorianCalendar dateBirth = new GregorianCalendar();
                                dateBirth.setTime(rs.getDate(DBNames.ATT_ACCOUNT_DATEOFBIRTH));
                                p.setDataOfBirth(dateBirth);
                                
                                GregorianCalendar expDate = new GregorianCalendar();
                                expDate.setTime(rs.getDate(DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE));
                                p.setContractExpirationDate(dateBirth);
                                
                                GregorianCalendar regDate = new GregorianCalendar();
                                regDate.setTime(rs.getDate(DBNames.ATT_ACCOUNT_REGISTRATIONDATE));
                                p.setRegistrationDate(dateBirth);
                                */
                                p.setPlaceofBirth(rs.getString(DBNames.ATT_ACCOUNT_PLACEOFBIRTH));
                                p.setTaxCode(rs.getString(DBNames.ATT_ACCOUNT_TAXCODE));
                                p.setCitizenship(rs.getString(DBNames.ATT_ACCOUNT_CITIZENSHIP));
                                p.setViaResidence(rs.getString(DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE));
                                p.setMunicipalityResidence(rs.getString(DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE));
                                p.setProvinceResidence(rs.getString(DBNames.ATT_ACCOUNT_PROVINCERESIDENCE));
                                p.setCapResidence(rs.getString(DBNames.ATT_ACCOUNT_CAPRESIDENCE));
                                p.setAddressDomicile(rs.getString(DBNames.ATT_ACCOUNT_ADDRESSDOMICILE));
                                p.setMunicipalityDomicile(rs.getString(DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE));
                                p.setProvinceDomicile(rs.getString(DBNames.ATT_ACCOUNT_PROVINCEDOMICILE));
                                p.setProvinceResidence(rs.getString(DBNames.ATT_ACCOUNT_PROVINCERESIDENCE));
                                p.setQualification(rs.getString(DBNames.ATT_ACCOUNT_QUALIFICATION));
                                p.setFamilySituation(rs.getString(DBNames.ATT_ACCOUNT_FAMILYSITUATION));
                                p.setIncome(rs.getDouble(DBNames.ATT_ACCOUNT_INCOME));
                                p.setState(rs.getString(DBNames.ATT_ACCOUNT_STATE));
                                p.setRegister(rs.getString(DBNames.ATT_ACCOUNT_REGISTER));
                                account.add(p);
			}
                        con.commit();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				DBConnectionPool.releaseConnection(con);
			}
		}
		return account;
	}
        
	private String useAnd(boolean pEnableAnd) {
		return pEnableAnd ? " AND " : " ";
	}

	private String generatePassword() {

		Random rnd = new Random();
		//size password
		int dim = 8;
		//quantity of number
		int num = rnd.nextInt(dim - 4) + 1;
		//quantity of letter
		int alfa = dim - num;
		int appoggio = 0;
		String pass = "";
		int alterna = 0; //decide if choose a letter or a number 
		int contnum = 1;
		int contalfa = 1;
		for (int i = 1; i <= dim; i++) {
			alterna = rnd.nextInt(2);
			if (contalfa > alfa) {
				alterna = 0;
			} else {
				if (contnum > num) {
					alterna = 1;
				}
			}
			if (alterna == 1) {
				contalfa++;
				appoggio = rnd.nextInt(25);
				switch (appoggio) {
				case 0:
					pass = pass + "A";
					break;  //potevo farlo in altra maniera
				case 1:
					pass = pass + "B";
					break;
				case 2:
					pass = pass + "C";
					break;
				case 3:
					pass = pass + "D";
					break;
				case 4:
					pass = pass + "E";
					break;
				case 5:
					pass = pass + "F";
					break;
				case 6:
					pass = pass + "G";
					break;
				case 7:
					pass = pass + "H";
					break;
				case 8:
					pass = pass + "I";
					break;
				case 9:
					pass = pass + "J";
					break;
				case 10:
					pass = pass + "K";
					break;
				case 11:
					pass = pass + "L";
					break;
				case 12:
					pass = pass + "M";
					break;
				case 13:
					pass = pass + "N";
					break;
				case 14:
					pass = pass + "O";
					break;
				case 15:
					pass = pass + "P";
					break;
				case 16:
					pass = pass + "Q";
					break;
				case 17:
					pass = pass + "R";
					break;
				case 18:
					pass = pass + "S";
					break;
				case 19:
					pass = pass + "T";
					break;
				case 20:
					pass = pass + "U";
					break;
				case 21:
					pass = pass + "W";
					break;
				case 22:
					pass = pass + "X";
					break;
				case 23:
					pass = pass + "Y";
					break;
				case 24:
					pass = pass + "Z";
					break;
				}
			}
			if (alterna == 0) {
				contnum++;
				appoggio = rnd.nextInt(10);
				switch (appoggio) {
				case 0:
					pass = pass + "0";
					break;
				case 1:
					pass = pass + "1";
					break;
				case 2:
					pass = pass + "2";
					break;
				case 3:
					pass = pass + "3";
					break;
				case 4:
					pass = pass + "4";
					break;
				case 5:
					pass = pass + "5";
					break;
				case 6:
					pass = pass + "6";
					break;
				case 7:
					pass = pass + "7";
					break;
				case 8:
					pass = pass + "8";
					break;
				case 9:
					pass = pass + "9";
					break;
				}
			}
		}

		return pass;
	}








     private String makeString(String d) {
    if (d == null)
        return null;
    else
        return "'"+d+"'";

    }
     
     private String makeSqlDateString(GregorianCalendar d) {
    if (d == null)
        return null;
    else
        return "'"+d.get(Calendar.YEAR)+"-"+d.get(Calendar.MONTH)+""+d.get(Calendar.DAY_OF_MONTH)+"'";
          

    }
}
