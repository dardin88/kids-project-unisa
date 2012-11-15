package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class ManagerAccount {

	private static ManagerAccount manager;

	private ManagerAccount(){
	}

	public static ManagerAccount getInstance(){
		if (manager!=null)
			return manager;
		else
			return (manager=new ManagerAccount());
	}

	public Account Create(Account account) throws SQLException{
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
				nickname= account.getNameUser()+account.getSurnameUser();
				query1="Select"+DBNames.ATT_ACCOUNT_NAMEUSER+"From"+DBNames.TABLE_ACCOUNT+"Where nameUser='"+account.getNameUser()+"'";
				if(query1!=null)
					nickname=nickname+i;
				else 
					break;
				i++;
			}
			/*generation of password*/
			String password=getPWD();

			query2="Insert into"+DBNames.TABLE_ACCOUNT+"("+DBNames.ATT_ACCOUNT_NAMEUSER+","+DBNames.ATT_ACCOUNT_REGISTRATIONDATE+","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER+","+DBNames.ATT_ACCOUNT_CITIZENSHIP+","+DBNames.ATT_ACCOUNT_TAXCODE+","+DBNames.ATT_ACCOUNT_SURNAMEUSER+","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE+","+DBNames.ATT_ACCOUNT_EMAIL+","+DBNames.ATT_ACCOUNT_FACULTY+","+DBNames.ATT_ACCOUNT_FAX+","+DBNames.ATT_ACCOUNT_PLACEOFBIRTH+","+DBNames.ATT_ACCOUNT_NICKNAME+","+DBNames.ATT_ACCOUNT_NAMEUSER+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE+","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+","+DBNames.ATT_ACCOUNT_PASSWORD+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE+","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+","+DBNames.ATT_ACCOUNT_INCOME+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE+","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+","+DBNames.ATT_ACCOUNT_TYPEPARENT+","+DBNames.ATT_ACCOUNT_QUALIFICATION+","+DBNames.ATT_ACCOUNT_VIADOMICILE+","+DBNames.ATT_ACCOUNT_VIARESIDENCE+") values("+nickname+","+account.getRegistrationDate()+","+account.getCapDomicilie()+","+account.getCapResidence()+","+account.getCellularNumber()+","+account.getCitizenship()+","+account.getTaxCode()+","+account.getSurnameUser()+","+account.getMunicipalityResidence()+","+account.getDataOfBirth()+","+account.getContractExpirationDate()+","+account.getEmail()+","+account.getFaculty()+","+account.getFax()+","+account.getPlaceOfBirth()+","+nickname+","+account.getNameUser()+","+account.getStreetNumberDomicilie()+","+account.getStreetNumberResidence()+","+account.getPassword()+","+account.getProvinceDomicilie()+","+account.getProvinceResidence()+","+account.getIncome()+","+account.getMunicipalityDomicilie()+","+account.getFamilySituation()+","+account.getTelephoneNumber()+","+account.getTypeAccount()+","+account.getTypeParent()+","+account.getQualification()+","+account.getViaDomicile()+","+account.getViaResidence()+")";

			stmt = con.createStatement();
			stmt.executeUpdate(query2);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}



		return account;
	}

	public Account Modify(Account changedAccount) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		try{
			con=DBConnectionPool.getConnection();
			String query="Update table_name " +
					"SET "+DBNames.ATT_ACCOUNT_REGISTRATIONDATE+"="+changedAccount.getRegistrationDate()+","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+"="+changedAccount.getCapDomicilie()+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+"="+changedAccount.getCapResidence()+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER+"="+changedAccount.getCellularNumber()+","+DBNames.ATT_ACCOUNT_CITIZENSHIP+"="+changedAccount.getCitizenship()+","+DBNames.ATT_ACCOUNT_TAXCODE+"="+changedAccount.getTaxCode()+","+DBNames.ATT_ACCOUNT_SURNAMEUSER+"="+changedAccount.getSurnameUser()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+"="+changedAccount.getMunicipalityResidence()+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+"="+changedAccount.getDataOfBirth()+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE+"="+changedAccount.getContractExpirationDate()+","+DBNames.ATT_ACCOUNT_EMAIL+"="+changedAccount.getEmail()+","+DBNames.ATT_ACCOUNT_FACULTY+"="+changedAccount.getFaculty()+","+DBNames.ATT_ACCOUNT_FAX+"="+changedAccount.getFax()+""+DBNames.ATT_ACCOUNT_PLACEOFBIRTH+"="+changedAccount.getPlaceOfBirth()+","+DBNames.ATT_ACCOUNT_NICKNAME+"="+changedAccount.getNickName()+","+DBNames.ATT_ACCOUNT_NAMEUSER+"="+changedAccount.getNameUser()+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE+"="+changedAccount.getStreetNumberDomicilie()+","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+"="+changedAccount.getStreetNumberResidence()+","+DBNames.ATT_ACCOUNT_PASSWORD+"="+changedAccount.getPassword()+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE+"="+changedAccount.getProvinceDomicilie()+","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+"="+changedAccount.getProvinceResidence()+","+DBNames.ATT_ACCOUNT_INCOME+"="+changedAccount.getIncome()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE+"="+changedAccount.getMunicipalityDomicilie()+","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+"="+changedAccount.getFamilySituation()+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+"="+changedAccount.getTelephoneNumber()+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"="+changedAccount.getTypeAccount()+","+DBNames.ATT_ACCOUNT_TYPEPARENT+"="+changedAccount.getTypeParent()+","+DBNames.ATT_ACCOUNT_QUALIFICATION+"="+changedAccount.getQualification()+","+DBNames.ATT_ACCOUNT_VIADOMICILE+"="+changedAccount.getViaDomicile()+","+DBNames.ATT_ACCOUNT_VIARESIDENCE+"="+changedAccount.getViaResidence()+
					"WHERE "+DBNames.ATT_ACCOUNT_ID+"="+changedAccount.getId(); 

			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}


		return changedAccount;
	}

	public Account Delete(Account deletedAccount) throws SQLException{
		Connection con = null;
		Statement stmt=null;
		try{
			con=DBConnectionPool.getConnection();
			String query="Delete From "+DBNames.TABLE_ACCOUNT+" Where "+DBNames.ATT_ACCOUNT_ID+"='"+deletedAccount.getId()+"'";
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}
		return deletedAccount;
	}



	public ArrayList<Account> Search(Account account) throws SQLException{
		Account account2= new Account();
		Connection con = null;
		Statement stmt=null;
		ResultSet rsAccount=null;
		ArrayList<Account> listAccount=new ArrayList<Account>();
		String query=null;
		try{
			con=DBConnectionPool.getConnection();		
			query="SELECT * FROM "+DBNames.TABLE_ACCOUNT+" WHERE ";					  
			if (account.getNickName()!=null)
				query=query+""+DBNames.ATT_ACCOUNT_NICKNAME+"='"+account.getNickName()+"'";
			if (account.getNameUser()!=null)
				query=query+""+DBNames.ATT_ACCOUNT_NAMEUSER+"='"+account.getNameUser()+"'";
			if (account.getSurnameUser()!=null)
				query=query+""+DBNames.ATT_ACCOUNT_SURNAMEUSER+"='"+account.getSurnameUser()+"'";
			if (account.getTypeAccount()!=null)
				query=query+""+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"='"+account.getTypeAccount()+"'";

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
				String nameUser=rsAccount.getString(DBNames.ATT_ACCOUNT_NAMEUSER);
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
				account.setCapDomicilie(capDomicilie);
				account.setCapResidence(capResidence);
				account.setCellularNumber(cellularNumber);
				account.setCitizenship(citizenship);
				account.setContractExpirationDate(contractExpirationDate);
				account.setDataOfBirth(dateOfBirth);
				account.setEmail(email);
				account.setFaculty(faculty);
				account.setFamilySituation(familySituation);
				account.setFax(fax);
				account.setId(id);
				account.setIncome(income);
				account.setMunicipalityDomicilie(municipalityDomicilie);
				account.setMunicipalityResidence(municipalityResidence);
				account.setNameUser(nameUser);
				account.setNickName(nickName);
				account.setPassword(password);
				account.setPlaceofBirth(placeOfBirth);
				account.setProvinceDomicilie(provinceDomicilie);
				account.setProvinceResidence(provinceResidence);
				account.setQualification(qualification);
				account.setRegistrationDate(registrationDate);
				account.setStreetNumberDomicilie(streetNumberDomicilie);
				account.setSurnameUser(surname);
				account.setStreetNumberResidece(streetNumberResidence);
				account.setTaxCode(taxCode);
				account.setTelephoneNumber(telephoneNumber);
				account.setTypeAccount(typeAccount);
				account.setTypeParent(typeParent);
				account.setViaDomicile(viaDomicilie);
				account.setViaResidece(viaResidence);

				listAccount.add(account);

			}
		}finally{
			stmt.close();
			DBConnectionPool.releaseConnection(con);
		}

		return listAccount;

	}




	public String getPWD() {

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


}


