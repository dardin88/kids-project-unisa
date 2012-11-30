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
		//String data1=null;
		//String data2=null;
		//String data3=null;
		ResultSet rs=null;

		GregorianCalendar birthDate=pAccount.getDataOfBirth();
		GregorianCalendar expDate=pAccount.getContractExpirationDate();
		GregorianCalendar regDate=pAccount.getRegistrationDate();

		/*if(birthDate!=null){
           data1= ""+birthDate.YEAR+"-"+birthDate.MONTH+"-"+birthDate.DAY_OF_MONTH;
        }
        if(expDate!=null){
           data1= ""+expDate.YEAR+"-"+expDate.MONTH+"-"+expDate.DAY_OF_MONTH;
        }
        if(regDate!=null){
           data1= ""+regDate.YEAR+"-"+regDate.MONTH+"-"+regDate.DAY_OF_MONTH;
        }*/
		Date data3;
		Date data2;
		Date data1;
		if(birthDate!=null){
			data1=new Date(pAccount.getDataOfBirth().getTimeInMillis());
			data2=new Date(pAccount.getContractExpirationDate().getTimeInMillis());
			data3=new Date(pAccount.getRegistrationDate().getTimeInMillis());
		}
		else{ data1=null;
		data2=null;
		data3=null;
		}

		int i = 2;
		try {
			con = DBConnectionPool.getConnection();
			/*generation of nickname */

			while (true) {
				nickname = pAccount.getNameUser() + pAccount.getSurnameUser();
				query1 = "Select " + DBNames.ATT_ACCOUNT_NICKNAME + " From " + DBNames.TABLE_ACCOUNT + " Where "+DBNames.ATT_ACCOUNT_NICKNAME+" = '" + pAccount.getNickName() + "'";
				stmt=con.createStatement();
				rs=stmt.executeQuery(query1);

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
					+ "," + DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE +") values ('" + pAccount.getNameUser() + "','"+null
					+ "','" + pAccount.getCapDomicile() + "','" + pAccount.getCapResidence() + "','" + pAccount.getCellularNumber()
					+ "','" + pAccount.getCitizenship() + "','" + pAccount.getTaxCode() + "','" + pAccount.getSurnameUser()
					+ "','" + pAccount.getMunicipalityResidence() + "','" + data1 + "','" + data2
					+ "','" + pAccount.getEmail() + "','" + pAccount.getFaculty() + "','" + pAccount.getFax() + "','" + pAccount.getPlaceOfBirth()
					+ "','" + nickname + "','" + pAccount.getNameUser()
					+ "','" + pAccount.getPassword() + "','" + pAccount.getProvinceDomicile() + "','" + pAccount.getProvinceResidence() + "','" + pAccount.getIncome()
					+ "','" + pAccount.getMunicipalityDomicile() + "','" + pAccount.getFamilySituation() + "','" + pAccount.getTelephoneNumber() + "','" + pAccount.getAccountType()
					+ "','" + pAccount.getQualification() + "','" + pAccount.getViaDomicile() + "','" + pAccount.getViaResidence() + "')";

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
			data1= ""+birthDate.DAY_OF_MONTH+birthDate.MONTH+birthDate.YEAR;
		}
                
		if(expDate!=null){
			data2= ""+expDate.DAY_OF_MONTH+expDate.MONTH+expDate.YEAR;
		}
                
		if(regDate!=null){
			data3= ""+regDate.DAY_OF_MONTH+regDate.MONTH+regDate.YEAR;
		}
                
		try {
			con = DBConnectionPool.getConnection();
			String query = "Update table_name "
					+ "SET " + DBNames.ATT_ACCOUNT_REGISTRATIONDATE + "=" + data3 + "," + DBNames.ATT_ACCOUNT_CAPDOMICILIE + "=" + pChangedAccount.getCapDomicile() + "," + DBNames.ATT_ACCOUNT_CAPRESIDENCE + "=" + pChangedAccount.getCapResidence() + "," + DBNames.ATT_ACCOUNT_CELLULARNUMBER + "=" + pChangedAccount.getCellularNumber() + "," + DBNames.ATT_ACCOUNT_CITIZENSHIP + "=" + pChangedAccount.getCitizenship() + "," + DBNames.ATT_ACCOUNT_TAXCODE + "=" + pChangedAccount.getTaxCode() + "," + DBNames.ATT_ACCOUNT_SURNAMEUSER + "=" + pChangedAccount.getSurnameUser() + "," + DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE + "=" + pChangedAccount.getMunicipalityResidence() + "," + DBNames.ATT_ACCOUNT_DATEOFBIRTH + "=" + data1 + "," + DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE + "=" + data3 + "," + DBNames.ATT_ACCOUNT_EMAIL + "=" + pChangedAccount.getEmail() + "," + DBNames.ATT_ACCOUNT_FACULTY + "=" + pChangedAccount.getFaculty() + "," + DBNames.ATT_ACCOUNT_FAX + "=" + pChangedAccount.getFax() + "" + DBNames.ATT_ACCOUNT_PLACEOFBIRTH + "=" + pChangedAccount.getPlaceOfBirth() + "," + DBNames.ATT_ACCOUNT_NICKNAME + "=" + pChangedAccount.getNickName() + "," + DBNames.ATT_ACCOUNT_NAME + "=" + pChangedAccount.getNameUser() + "," + DBNames.ATT_ACCOUNT_PASSWORD + "=" + pChangedAccount.getPassword() + "," + DBNames.ATT_ACCOUNT_PROVINCEDOMICILE + "=" + pChangedAccount.getProvinceDomicile() + "," + DBNames.ATT_ACCOUNT_PROVINCERESIDENCE + "=" + pChangedAccount.getProvinceResidence() + "," + DBNames.ATT_ACCOUNT_INCOME + "=" + pChangedAccount.getIncome() + "," + DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE + "=" + pChangedAccount.getMunicipalityDomicile() + "," + DBNames.ATT_ACCOUNT_FAMILYSITUATION + "=" + pChangedAccount.getFamilySituation() + "," + DBNames.ATT_ACCOUNT_TELEPHONENUMBER + "=" + pChangedAccount.getTelephoneNumber() + "," + DBNames.ATT_ACCOUNT_TYPEACCOUNT + "=" + pChangedAccount.getAccountType() + "," + DBNames.ATT_ACCOUNT_QUALIFICATION + "=" + pChangedAccount.getQualification() + "," + DBNames.ATT_ACCOUNT_ADDRESSDOMICILE + "=" + pChangedAccount.getViaDomicile() + "," + DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE + "=" + pChangedAccount.getViaResidence()+","+DBNames.ATT_ACCOUNT_TYPEPARENT+"="+pChangedAccount.getTypeParent()
					+ "WHERE " + DBNames.ATT_ACCOUNT_ID + "=" + pChangedAccount.getId();
                        
			stmt = con.createStatement();
			stmt.executeUpdate(query);
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
					" WHERE";
			if (pAccount.getId() > 0) {		
				query += " " + DBNames.ATT_ACCOUNT_ID + " = ?";
				andState = true;
			}

			if (pAccount.getNickName()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_NICKNAME + " = ?";
				andState = true;
			}

			if (pAccount.getPassword()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_PASSWORD + " = ?";
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

			if (pAccount.getEmail()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_EMAIL + " = ?";
				andState = true;
			}
			query += useAnd(andState) + DBNames.ATT_ACCOUNT_TELEPHONENUMBER + " = ?";
			andState = true;

			query += useAnd(andState) + DBNames.ATT_ACCOUNT_CELLULARNUMBER + " = ?";
			andState = true;

			query += useAnd(andState) + DBNames.ATT_ACCOUNT_FAX + " = ?";
			andState = true;

			if (pAccount.getDataOfBirth() != null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_DATEOFBIRTH + " = ?";
				andState = true;
			}

			if (pAccount.getPlaceOfBirth()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_PLACEOFBIRTH + " = ?";
				andState = true;
			}

			if (pAccount.getTaxCode()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_TAXCODE + " = ?";
				andState = true;
			}

			if (pAccount.getCitizenship()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_CITIZENSHIP + " = ?";
				andState = true;
			}

			if (pAccount.getViaResidence()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE + " = ?";
				andState = true;
			}

			if (pAccount.getMunicipalityResidence()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE + " = ?";
				andState = true;
			}

			if (pAccount.getProvinceResidence()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_PROVINCERESIDENCE + " = ?";
				andState = true;
			}

			if (pAccount.getCapResidence()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_CAPRESIDENCE + " = ?";
				andState = true;
			}

			if (pAccount.getViaDomicile()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_ADDRESSDOMICILE + " = ?";
				andState = true;
			}

			if (pAccount.getMunicipalityDomicile()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE + " = ?";
				andState = true;
			}

			if (pAccount.getProvinceDomicile()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_PROVINCEDOMICILE + " = ?";
				andState = true;
			}

			if (pAccount.getProvinceResidence()!=null) {
				query += useAnd(andState) + DBNames.ATT_ACCOUNT_CAPDOMICILIE + " = ?";
				andState = true;
			}

			query += useAnd(andState) + DBNames.ATT_ACCOUNT_QUALIFICATION + " = ?";
			andState = true;

			query += useAnd(andState) + DBNames.ATT_ACCOUNT_FAMILYSITUATION + " = ?";
			andState = true;

			query += useAnd(andState) + DBNames.ATT_ACCOUNT_INCOME + " = ?";
			andState = true;

                        // tipi enum come gestirli?
			query += useAnd(andState) + DBNames.ATT_ACCOUNT_ + " = ?";
			andState = true;

			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pAccount.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pAccount.getId());
				i++;
			}

			if (pAccount.getNickName()!=null) {
				pstmt.setString(i, pAccount.getNickName());
				i++;
			}

			if (pAccount.getPassword()!=null) {
				pstmt.setString(i, pAccount.getPassword());
				i++;
			}

			if (pAccount.getSurnameUser()!=null) {
				pstmt.setString(i, pAccount.getSurnameUser());
				i++;
			}

			if (pAccount.getNameUser()!=null) {
				pstmt.setString(i, pAccount.getNameUser());
				i++;
			}

			if (pAccount.getEmail()!=null) {
				pstmt.setString(i, pAccount.getEmail());
				i++;
			}

			pstmt.setString(i, pAccount.getTelephoneNumber());
			i++;


			pstmt.setString(i, pAccount.getCellularNumber());
			i++;

			pstmt.setString(i, pAccount.getFax());
			i++;

			if (pAccount.getDataOfBirth() != null) {
				pstmt.setDate(i, new java.sql.Date(pAccount.getDataOfBirth().getTimeInMillis()));
				i++;
			}

			if (pAccount.getPlaceOfBirth()!=null) {
				pstmt.setString(i, pAccount.getPlaceOfBirth());
				i++;
			}

			if (pAccount.getTaxCode()!=null) {
				pstmt.setString(i, pAccount.getTaxCode());
				i++;
			}

			if (pAccount.getCitizenship()!=null) {
				pstmt.setString(i, pAccount.getCitizenship());
				i++;
			}

			if (pAccount.getViaResidence()!=null) {
				pstmt.setString(i, pAccount.getViaResidence());
				i++;
			}

			if (pAccount.getMunicipalityResidence()!=null) {
				pstmt.setString(i, pAccount.getMunicipalityResidence());
				i++;
			}

			if (pAccount.getProvinceResidence()!=null) {
				pstmt.setString(i, pAccount.getProvinceResidence());
				i++;
			}

			if (pAccount.getCapResidence()!=null) {
				pstmt.setString(i, pAccount.getCapResidence());
				i++;
			}

			if (pAccount.getViaDomicile()!=null) {
				pstmt.setString(i, pAccount.getViaDomicile());
				i++;
			}

			if (pAccount.getMunicipalityDomicile()!=null) {
				pstmt.setString(i, pAccount.getMunicipalityDomicile());
				i++;
			}

			if (pAccount.getProvinceDomicile()!=null) {
				pstmt.setString(i, pAccount.getProvinceDomicile());
				i++;
			}

			if (pAccount.getProvinceResidence()!=null) {
				pstmt.setString(i, pAccount.getProvinceResidence());
				i++;
			}
			pstmt.setString(i, pAccount.getQualification());
			i++;

			pstmt.setString(i, pAccount.getFamilySituation());
			i++;

			pstmt.setDouble(i, pAccount.getIncome());
			i++;

			// executing select query
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
				GregorianCalendar dateBirth = new GregorianCalendar();
				dateBirth.setTime(rs.getDate(DBNames.ATT_ACCOUNT_DATEOFBIRTH));
				p.setDataOfBirth(dateBirth);

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
				account.add(p);
			}
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








}
