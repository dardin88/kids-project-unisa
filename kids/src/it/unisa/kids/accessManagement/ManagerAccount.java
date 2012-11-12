package it.unisa.kids.accessManagement;

import it.unisa.kids.common.DBNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.org.glassfish.external.arc.Taxonomy;

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
	
	public Account Create(Account account){
		String nickname=null;
		String query=null;
		int i=2;
		/*generation of nickname */
		while(true){
			nickname= account.getNameUser()+account.getSurnameUser();
			query="Select"+DBNames.ATT_ACCOUNT_NAMEUSER+"From"+DBNames.TABLE_ACCOUNT+"Where nameUser='"+account.getNameUser()+"'";
			if(query!=null)
				nickname=nickname+i;
			else 
				break;
			i++;
		}
		/*generation of password*/
		String password=getPWD();
		
		 query="Insert into"+DBNames.TABLE_ACCOUNT+"("+DBNames.ATT_ACCOUNT_NAMEUSER+","+DBNames.ATT_ACCOUNT_REGISTRATIONDATE+","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER+","+DBNames.ATT_ACCOUNT_CITIZENSHIP+","+DBNames.ATT_ACCOUNT_TAXCODE+","+DBNames.ATT_ACCOUNT_SURNAMEUSER+","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE+","+DBNames.ATT_ACCOUNT_EMAIL+","+DBNames.ATT_ACCOUNT_FACULTY+","+DBNames.ATT_ACCOUNT_FAX+","+DBNames.ATT_ACCOUNT_PLACEOFBIRTH+","+DBNames.ATT_ACCOUNT_NICKNAME+","+DBNames.ATT_ACCOUNT_NAMEUSER+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE+","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+","+DBNames.ATT_ACCOUNT_PASSWORD+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE+","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+","+DBNames.ATT_ACCOUNT_INCOME+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE+","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+","+DBNames.ATT_ACCOUNT_TYPEPARENT+","+DBNames.ATT_ACCOUNT_QUALIFICATION+","+DBNames.ATT_ACCOUNT_VIADOMICILE+","+DBNames.ATT_ACCOUNT_VIARESIDENCE+") values("+nickname+","+account.getRegistrationDate()+","+account.getCapDomicilie()+","+account.getCapResidence()+","+account.getCellularNumber()+","+account.getCitizenship()+","+account.getTaxCode()+","+account.getSurnameUser()+","+account.getMunicipalityResidence()+","+account.getDataOfBirth()+","+account.getContractExpirationDate()+","+account.getEmail()+","+account.getFaculty()+","+account.getFax()+","+account.getPlaceOFBirth()+","+nickname+","+account.getNameUser()+","+account.getStreetNumberDomicilie()+","+account.getStreetNumberResidence()+","+account.getPassword()+","+account.getProvinceDomicilie()+","+account.getProvinceResidence()+","+account.getIncome()+","+account.getMunicipalityDomicilie()+","+account.getFamilySituation()+","+account.getTelephoneNumber()+","+account.getTypeAccount()+","+account.getTypeParent()+","+account.getQualification()+","+account.getViaDomicile()+","+account.getViaResidence()+")";
		
		  //connection of DB
		  
		  //execute of query		
		  
		return account;
	}
	
	public Account Modify(Account changedAccount){
		
		String query="Update table_name " +
				"SET "+DBNames.ATT_ACCOUNT_REGISTRATIONDATE+"="+changedAccount.getRegistrationDate()+","+DBNames.ATT_ACCOUNT_CAPDOMICILIE+"="+changedAccount.getCapDomicilie()+","+DBNames.ATT_ACCOUNT_CAPRESIDENCE+"="+changedAccount.getCapResidence()+","+DBNames.ATT_ACCOUNT_CELLULARNUMBER+"="+changedAccount.getCellularNumber()+","+DBNames.ATT_ACCOUNT_CITIZENSHIP+"="+changedAccount.getCitizenship()+","+DBNames.ATT_ACCOUNT_TAXCODE+"="+changedAccount.getTaxCode()+","+DBNames.ATT_ACCOUNT_SURNAMEUSER+"="+changedAccount.getSurnameUser()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE+"="+changedAccount.getMunicipalityResidence()+","+DBNames.ATT_ACCOUNT_DATEOFBIRTH+"="+changedAccount.getDataOfBirth()+","+DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE+"="+changedAccount.getContractExpirationDate()+","+DBNames.ATT_ACCOUNT_EMAIL+"="+changedAccount.getEmail()+","+DBNames.ATT_ACCOUNT_FACULTY+"="+changedAccount.getFaculty()+","+DBNames.ATT_ACCOUNT_FAX+"="+changedAccount.getFax()+""+DBNames.ATT_ACCOUNT_PLACEOFBIRTH+"="+changedAccount.getPlaceOFBirth()+","+DBNames.ATT_ACCOUNT_NICKNAME+"="+changedAccount.getNickName()+","+DBNames.ATT_ACCOUNT_NAMEUSER+"="+changedAccount.getNameUser()+","+DBNames.ATT_ACCOUNT_STREETNUMBERDOMICILIE+"="+changedAccount.getStreetNumberDomicilie()+","+DBNames.ATT_ACCOUNT_STREETNUMBERRESIDENCE+"="+changedAccount.getStreetNumberResidence()+","+DBNames.ATT_ACCOUNT_PASSWORD+"="+changedAccount.getPassword()+","+DBNames.ATT_ACCOUNT_PROVINCEDOMICILIE+"="+changedAccount.getProvinceDomicilie()+","+DBNames.ATT_ACCOUNT_PROVINCERESIDENCE+"="+changedAccount.getProvinceResidence()+","+DBNames.ATT_ACCOUNT_INCOME+"="+changedAccount.getIncome()+","+DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE+"="+changedAccount.getMunicipalityDomicilie()+","+DBNames.ATT_ACCOUNT_FAMILYSITUATION+"="+changedAccount.getFamilySituation()+","+DBNames.ATT_ACCOUNT_TELEPHONENUMBER+"="+changedAccount.getTelephoneNumber()+","+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"="+changedAccount.getTypeAccount()+","+DBNames.ATT_ACCOUNT_TYPEPARENT+"="+changedAccount.getTypeParent()+","+DBNames.ATT_ACCOUNT_QUALIFICATION+"="+changedAccount.getQualification()+","+DBNames.ATT_ACCOUNT_VIADOMICILE+"="+changedAccount.getViaDomicile()+","+DBNames.ATT_ACCOUNT_VIARESIDENCE+"="+changedAccount.getViaResidence()+
				"WHERE id="+changedAccount.getId(); //dubbi sulla query
		
		  //connection of DB
		  
		  //execute of query		
		  
		
		return changedAccount;
	}
	
	public Account Delete(Account deletedAccount){
		
		String query2="Delete From "+DBNames.TABLE_ACCOUNT+" Where "+DBNames.ATT_ACCOUNT_ID+"='"+deletedAccount.getId()+"'";
		return deletedAccount;
	}
	
	public ArrayList<Account> Search(Account account){
		ArrayList<Account> listAccount=new ArrayList<Account>();		
		  String query="SELECT * FROM "+DBNames.TABLE_ACCOUNT+" WHERE ";				//change table_name 		  
		  if (account.getNickName()!=null)
			  query=query+""+DBNames.ATT_ACCOUNT_NICKNAME+"='"+account.getNickName()+"'";
		  if (account.getNameUser()!=null)
			  query=query+""+DBNames.ATT_ACCOUNT_NAMEUSER+"='"+account.getNameUser()+"'";
		  if (account.getSurnameUser()!=null)
			  query=query+""+DBNames.ATT_ACCOUNT_SURNAMEUSER+"='"+account.getSurnameUser()+"'";
		  if (account.getTypeAccount()!=null)
			  query=query+""+DBNames.ATT_ACCOUNT_TYPEACCOUNT+"='"+account.getTypeAccount()+"'";
		  
		  //connection of DB
		  
		  //execute of query		
		  
		  //processing of result
		  
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
	

