package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
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

	private JDBCAccountManager(){
	}

	public static JDBCAccountManager getInstance(){
		if (manager!=null)
			return manager;
		else
			return (manager=new JDBCAccountManager());
	}

	public Account insert(Account pAccount) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		String nickname=null;
		String query1=null;
		String query2=null;
		int i=2;
		try{
			con=DBConnectionPool.getConnection();
			/*generation of nickname */
			while(true){
				nickname= pAccount.getNameUser()+pAccount.getSurnameUser();
				query1="Select"+DBNames.ATT_ACCOUNT_NAME +"From"+DBNames.TABLE_ACCOUNT+"Where nameUser='"+pAccount.getNameUser()+"'";
				if(query1!=null)
					nickname=nickname+i;
				else 
					break;
				i++;
			}
			/*generation of password*/
			String password=generatePassword();

			query2="Insert into"+DBNames.TABLE_ACCOUNT+"("+DBNames.ATT_ACCOUNT_NAME+","+DBNames.ATT_ACCOUNT_REGISTRATIONDATE +
					","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER +
					","+DBNames.ATT_ACCOUNT_CITIZENSHIP+","+DBNames.ATT_ACCOUNT_TAXCODE+","+DBNames.ATT_ACCOUNT_SURNAMEUSER + 
					","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE + 
					","+DBNames.ATT_ACCOUNT_EMAIL+","+DBNames.ATT_ACCOUNT_FACULTY+","+DBNames.ATT_ACCOUNT_FAX+","+DBNames.ATT_ACCOUNT_PLACEOFBIRTH + 
					","+DBNames.ATT_ACCOUNT_NICKNAME+","+DBNames.ATT_ACCOUNT_NAME+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE + 
					","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+","+DBNames.ATT_ACCOUNT_PASSWORD+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE +
					","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+","+DBNames.ATT_ACCOUNT_INCOME+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE +
					","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+
					"," + DBNames.ATT_ACCOUNT_TYPEPARENT+","+DBNames.ATT_ACCOUNT_QUALIFICATION+","+DBNames.ATT_ACCOUNT_VIADOMICILE +
					","+DBNames.ATT_ACCOUNT_VIARESIDENCE+") values("+nickname+","+pAccount.getRegistrationDate() + 
					","+pAccount.getCapDomicile()+","+pAccount.getCapResidence()+","+pAccount.getCellularNumber() +
					","+pAccount.getCitizenship()+","+pAccount.getTaxCode()+","+pAccount.getSurnameUser() + 
					","+pAccount.getMunicipalityResidence()+","+pAccount.getDataOfBirth()+","+pAccount.getContractExpirationDate() +
					","+pAccount.getEmail()+","+pAccount.getFaculty()+","+pAccount.getFax()+","+pAccount.getPlaceOfBirth()+
					","+nickname+","+pAccount.getNameUser()+","+pAccount.getStreetNumberDomicile()+","+pAccount.getStreetNumberResidence() +
					","+pAccount.getPassword()+","+pAccount.getProvinceDomicile()+","+pAccount.getProvinceResidence()+","+pAccount.getIncome() +
					","+pAccount.getMunicipalityDomicile()+","+pAccount.getFamilySituation()+","+pAccount.getTelephoneNumber()+","+pAccount.getAccountType()+
					","+pAccount.getQualification()+","+pAccount.getViaDomicile()+","+pAccount.getViaResidence()+")";

			stmt = con.createStatement();
			stmt.executeUpdate(query2);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}



		return pAccount;
	}

	public Account update(Account pChangedAccount) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		try{
			con=DBConnectionPool.getConnection();
			String query="Update table_name " +
					"SET "+DBNames.ATT_ACCOUNT_REGISTRATIONDATE+"="+pChangedAccount.getRegistrationDate()+","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+"="+pChangedAccount.getCapDomicile()+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+"="+pChangedAccount.getCapResidence()+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER+"="+pChangedAccount.getCellularNumber()+","+DBNames.ATT_ACCOUNT_CITIZENSHIP+"="+pChangedAccount.getCitizenship()+","+DBNames.ATT_ACCOUNT_TAXCODE+"="+pChangedAccount.getTaxCode()+","+DBNames.ATT_ACCOUNT_SURNAMEUSER+"="+pChangedAccount.getSurnameUser()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+"="+pChangedAccount.getMunicipalityResidence()+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+"="+pChangedAccount.getDataOfBirth()+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE+"="+pChangedAccount.getContractExpirationDate()+","+DBNames.ATT_ACCOUNT_EMAIL+"="+pChangedAccount.getEmail()+","+DBNames.ATT_ACCOUNT_FACULTY+"="+pChangedAccount.getFaculty()+","+DBNames.ATT_ACCOUNT_FAX+"="+pChangedAccount.getFax()+""+DBNames.ATT_ACCOUNT_PLACEOFBIRTH+"="+pChangedAccount.getPlaceOfBirth()+","+DBNames.ATT_ACCOUNT_NICKNAME+"="+pChangedAccount.getNickName()+","+DBNames.ATT_ACCOUNT_NAME+"="+pChangedAccount.getNameUser()+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE+"="+pChangedAccount.getStreetNumberDomicile()+","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+"="+pChangedAccount.getStreetNumberResidence()+","+DBNames.ATT_ACCOUNT_PASSWORD+"="+pChangedAccount.getPassword()+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE+"="+pChangedAccount.getProvinceDomicile()+","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+"="+pChangedAccount.getProvinceResidence()+","+DBNames.ATT_ACCOUNT_INCOME+"="+pChangedAccount.getIncome()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE+"="+pChangedAccount.getMunicipalityDomicile()+","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+"="+pChangedAccount.getFamilySituation()+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+"="+pChangedAccount.getTelephoneNumber()+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"="+pChangedAccount.getAccountType() +","+ DBNames.ATT_ACCOUNT_QUALIFICATION+"="+pChangedAccount.getQualification()+","+DBNames.ATT_ACCOUNT_VIADOMICILE+"="+pChangedAccount.getViaDomicile()+","+DBNames.ATT_ACCOUNT_VIARESIDENCE+"="+pChangedAccount.getViaResidence()+
					"WHERE "+DBNames.ATT_ACCOUNT_ID+"="+pChangedAccount.getId(); 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return pChangedAccount;
	}

	public Account delete(Account pDeletedAccount) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		try{
			con=DBConnectionPool.getConnection();
			String query="Delete From "+DBNames.TABLE_ACCOUNT+" Where "+DBNames.ATT_ACCOUNT_ID+"='"+pDeletedAccount.getId()+"'";
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return pDeletedAccount;
	}

	/*
	 public synchronized List<Account> search(Account pAccount) throws SQLException 
	{
		/*
		  Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		List<PaymentBean> payments = null;

		boolean andState = false;

		try {
			con = DBConnectionPool.getConnection();

			// constructing search query string
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " WHERE";

			if (pAccount.getId() > 0) {		// or >= 0 ???
				query += " " + DBNames.ATT_ACCOUNT_ID + " = ?";
				andState = true;
			}

			if (pAccount.getNickName()!=null)
			{
				query=query+""+DBNames.ATT_ACCOUNT_NICKNAME+"='"+pAccount.getNickName()+"'";
				andState = true;
			}

			if (pAccount.getNameUser()!=null)
			{
				query=query+""+DBNames.ATT_ACCOUNT_NAME+"='"+pAccount.getNameUser()+"'";
				andState = true;
			}

			if (pAccount.getSurnameUser()!=null)
			{
				query=query+""+DBNames.ATT_ACCOUNT_SURNAMEUSER+"='"+pAccount.getSurnameUser()+"'";
				andState = true;
			}

			if (pAccount.getTypeAccount()!=null)
			{
				query=query+""+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"='"+pAccount.getTypeAccount()+"'";
				andState = true;
			}

			if



			pstmt = con.prepareStatement(query);

			// setting pstmt's parameters
			int i = 1;		// index of pstmt first argument
			if (pAccount.getId() > 0) {		// >= 0 ??
				pstmt.setInt(i, pAccount.getId());
				i++;
			}

			if (pAccount.getExpDate() != null) {
				pstmt.setDate(i, new java.sql.Date(pAccount.getExpDate().getTimeInMillis()));
				i++;
			}

			pstmt.setBoolean(i, pAccount.isCharge());
			i++;

			if (pAccount.getPaymentDescription() != null) {
				pstmt.setString(i, pAccount.getPaymentDescription());
				i++;
			}

			if (pAccount.getAmount() >= 0) {
				pstmt.setDouble(i, pAccount.getAmount());
				i++;
			}

			if (pAccount.getAmountDue() >= 0) {
				pstmt.setDouble(i, pAccount.getAmountDue());
				i++;
			}

			pstmt.setBoolean(i, pAccount.isPaid());
			i++;

			if (pAccount.getDiscount() != null) {
				pstmt.setString(i, pAccount.getDiscount());
				i++;
			}

			if (pAccount.getDiscountDescription() != null) {
				pstmt.setString(i, pAccount.getDiscountDescription());
				i++;
			}

			if (pAccount.getOriginAccount() != null) {
				pstmt.setString(i, pAccount.getOriginAccount());
				i++;
			}

			if (pAccount.getPayee() != null) {
				pstmt.setString(i, pAccount.getPayee());
				i++;
			}

			if (pAccount.getParentId() > 0) {		// or >= 0 ???
				pstmt.setInt(i, pAccount.getParentId());
				i++;
			}

			// executing select query
			rs = pstmt.executeQuery();
			con.commit();

			// constructing payment list
			payments = new ArrayList<PaymentBean>();
			while (rs.next()) {
				PaymentBean p = new PaymentBean();
				p.setId(rs.getInt(DBNames.ATT_PAYMENT_ID));

				//getting Date from ResultSet and converting it to GregorianCalendar
				GregorianCalendar expDate = new GregorianCalendar();
				expDate.setTime(rs.getDate(DBNames.ATT_PAYMENT_EXPDATE));
				p.setExpDate(expDate);

				p.setCharge(rs.getBoolean(DBNames.ATT_PAYMENT_CHARGE));
				p.setPaymentDescription(rs.getString(DBNames.ATT_PAYMENT_DESCRIPTION));
				p.setAmount(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNT));
				p.setAmountDue(rs.getDouble(DBNames.ATT_PAYMENT_AMOUNTDUE));
				p.setPaid(rs.getBoolean(DBNames.ATT_PAYMENT_PAID));
				p.setDiscount(rs.getString(DBNames.ATT_PAYMENT_DISCOUNT));
				p.setDiscountDescription(rs.getString(DBNames.ATT_PAYMENT_DISCDESCRIPTION));
				p.setOriginAccount(rs.getString(DBNames.ATT_PAYMENT_ORIGINACCOUNT));
				p.setPayee(rs.getString(DBNames.ATT_PAYMENT_PAYEE));
				p.setParentId(rs.getInt(DBNames.ATT_PAYMENT_PARENTID));

				payments.add(p);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				DBConnectionPool.releaseConnection(con);
		}
		return payments;
	}


	/*
 	Connection con = null;
	Statement stmt=null;
	ResultSet rsAccount=null;
	ArrayList<Account> listAccount=new ArrayList<Account>();
	String query=null;
	try{

		con=DBConnectionPool.getConnection();		
		query="SELECT * FROM "+DBNames.TABLE_ACCOUNT+" WHERE ";					  

		if (pAccount.getNickName()!=null)
			query=query+""+DBNames.ATT_ACCOUNT_NICKNAME+"='"+pAccount.getNickName()+"'";
		if (pAccount.getNameUser()!=null)
			query=query+""+DBNames.ATT_ACCOUNT_NAME+"='"+pAccount.getNameUser()+"'";
		if (pAccount.getSurnameUser()!=null)
			query=query+""+DBNames.ATT_ACCOUNT_SURNAMEUSER+"='"+pAccount.getSurnameUser()+"'";
		if (pAccount.getTypeAccount()!=null)
			query=query+""+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"='"+pAccount.getTypeAccount()+"'";

		stmt = con.createStatement();
		rsAccount=stmt.executeQuery(query);

		while(rsAccount.next()){
			int    id=rsAccount.getInt(DBNames.ATT_ACCOUNT_ID);
			String registrationDate=rsAccount.getString(DBNames.ATT_ACCOUNT_REGISTRATIONDATE);
			String capDomicilie=rsAccount.getString(DBNames.ATT_ACCOUNT_CAPDOMICILIE);
			String capResidence=rsAccount.getString(DBNames.ATT_ACCOUNT_CAPRESIDENCE);
			String cellularNumber=rsAccount.getString(DBNames.ATT_ACCOUNT_CELLULARNUMBER);
			String citizenship=rsAccount.getString(DBNames.ATT_ACCOUNT_CITIZENSHIP);
			String taxCode=rsAccount.getString(DBNames.ATT_ACCOUNT_TAXCODE);
			String surname=rsAccount.getString(DBNames.ATT_ACCOUNT_SURNAMEUSER);
			String municipalityResidence=rsAccount.getString(DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE);
			String dateOfBirth=rsAccount.getString(DBNames.ATT_ACCOUNT_DATEOFBIRTH);
			String contractExpirationDate=rsAccount.getString(DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE);
			String email=rsAccount.getString(DBNames.ATT_ACCOUNT_EMAIL);
			String faculty=rsAccount.getString(DBNames.ATT_ACCOUNT_FACULTY);
			String fax=rsAccount.getString(DBNames.ATT_ACCOUNT_FAX);
			String placeOfBirth=rsAccount.getString(DBNames.ATT_ACCOUNT_PLACEOFBIRTH);
			String nickName=rsAccount.getString(DBNames.ATT_ACCOUNT_NICKNAME);
			String nameUser=rsAccount.getString(DBNames.ATT_ACCOUNT_NAME);
			String streetNumberDomicilie=rsAccount.getString(DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE);
			String streetNumberResidence=rsAccount.getString(DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE);
			String password=rsAccount.getString(DBNames.ATT_ACCOUNT_PASSWORD);
			String provinceDomicilie=rsAccount.getString(DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE);
			String provinceResidence=rsAccount.getString(DBNames.ATT_ACCOUNT_PROVINCERESIDENCE);
			int income=rsAccount.getInt(DBNames.ATT_ACCOUNT_INCOME);
			String municipalityDomicilie=rsAccount.getString(DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE);
			String familySituation=rsAccount.getString(DBNames.ATT_ACCOUNT_FAMILYSITUATION);
			String telephoneNumber=rsAccount.getString(DBNames.ATT_ACCOUNT_TELEPHONENUMBER);
			String typeAccount=rsAccount.getString(DBNames.ATT_ACCOUNT_TYPEACCOUNT);
			String typeParent=rsAccount.getString(DBNames.ATT_ACCOUNT_TYPEPARENT);
			String qualification=rsAccount.getString(DBNames.ATT_ACCOUNT_QUALIFICATION);
			String viaDomicilie=rsAccount.getString(DBNames.ATT_ACCOUNT_VIADOMICILE);
			String viaResidence=rsAccount.getString(DBNames.ATT_ACCOUNT_VIARESIDENCE);
			pAccount.setCapDomicile(capDomicilie);
			pAccount.setCapResidence(capResidence);
			pAccount.setCellularNumber(cellularNumber);
			pAccount.setCitizenship(citizenship);
			pAccount.setContractExpirationDate(contractExpirationDate);
			pAccount.setDataOfBirth(dateOfBirth);
			pAccount.setEmail(email);
			pAccount.setFaculty(faculty);
			pAccount.setFamilySituation(familySituation);
			pAccount.setFax(fax);
			pAccount.setId(id);
			pAccount.setIncome(income);
			pAccount.setMunicipalityDomicile(municipalityDomicilie);
			pAccount.setMunicipalityResidence(municipalityResidence);
			pAccount.setNameUser(nameUser);
			pAccount.setNickName(nickName);
			pAccount.setPassword(password);
			pAccount.setPlaceofBirth(placeOfBirth);
			pAccount.setProvinceDomicile(provinceDomicilie);
			pAccount.setProvinceResidence(provinceResidence);
			pAccount.setQualification(qualification);
			pAccount.setRegistrationDate(registrationDate);
			pAccount.setStreetNumberDomicile(streetNumberDomicilie);
			pAccount.setSurnameUser(surname);
			pAccount.setStreetNumberResidence(streetNumberResidence);
			pAccount.setTaxCode(taxCode);
			pAccount.setTelephoneNumber(telephoneNumber);
			pAccount.setTypeAccount(typeAccount);
			pAccount.setTypeParent(typeParent);
			pAccount.setViaDomicile(viaDomicilie);
			pAccount.setViaResidence(viaResidence);

			listAccount.add(pAccount);

		}
	}finally{
		stmt.close();
		DBConnectionPool.releaseConnection(con);
	}

	return listAccount;

}


	private String useAnd(boolean pEnableAnd) {
		return pEnableAnd ? " AND " : " ";
	}

	 */
	private String generatePassword() {

		Random rnd = new Random();
		//size password
		int dim = 8;
		//quantity of number
		int num = rnd.nextInt(dim-4)+1;
		//quantity of letter
		int alfa = dim-num;
		int appoggio=0;
		String pass="";
		int alterna=0; //decide if choose a letter or a number 
		int contnum=1;
		int contalfa=1;
		for (int i=1;i<=dim;i++) {
			alterna = rnd.nextInt(2);
			if (contalfa>alfa){alterna=0;}
			else{if (contnum>num) {alterna=1;}
			}
			if (alterna==1) {contalfa++;
			appoggio = rnd.nextInt(25);
			switch (appoggio) {
			case 0 : pass = pass+"A";break;  //potevo farlo in altra maniera
			case 1 : pass = pass+"B";break;
			case 2 : pass = pass+"C";break;
			case 3 : pass = pass+"D";break;
			case 4 : pass = pass+"E";break;
			case 5 : pass = pass+"F";break;
			case 6 : pass = pass+"G";break;
			case 7 : pass = pass+"H";break;
			case 8 : pass = pass+"I";break;
			case 9 : pass = pass+"J";break;
			case 10 : pass = pass+"K";break;
			case 11 : pass = pass+"L";break;
			case 12 : pass = pass+"M";break;
			case 13 : pass = pass+"N";break;
			case 14 : pass = pass+"O";break;
			case 15 : pass = pass+"P";break;
			case 16 : pass = pass+"Q";break;
			case 17 : pass = pass+"R";break;
			case 18 : pass = pass+"S";break;
			case 19 : pass = pass+"T";break;
			case 20 : pass = pass+"U";break;
			case 21 : pass = pass+"W";break;
			case 22 : pass = pass+"X";break;
			case 23 : pass = pass+"Y";break;
			case 24 : pass = pass+"Z";break;
			}
			}
			if (alterna==0) {contnum++;
			appoggio = rnd.nextInt(10);
			switch (appoggio) {
			case 0 : pass = pass+"0";break;
			case 1 : pass = pass+"1";break;
			case 2 : pass = pass+"2";break;
			case 3 : pass = pass+"3";break;
			case 4 : pass = pass+"4";break;
			case 5 : pass = pass+"5";break;
			case 6 : pass = pass+"6";break;
			case 7 : pass = pass+"7";break;
			case 8 : pass = pass+"8";break;
			case 9 : pass = pass+"9";break;
			}
			}
		}

		return pass;
	}

	@Override
	public List<Account> search(Account pAccount) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}


