package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerAccount {

	private static ManagerAccount manager;
	
	private ManagerAccount(){
	}
	
	public ManagerAccount getInstance(){
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
			query="Select nameUser From name_table Where nameUser='"+account.getNameUser()+"'";
			if(query!=null)
				nickname=nickname+i;
			else 
				break;
			i++;
		}
		/*generation of password*/
		String password=getPWD();
		
		 query="Insert into 'table_name'(nickname,RegistrationDate,capDomicilie,capResidence,cellularNumber,citizenship,taxCode,surnameUser,municipalityDomicilie,dateOfBirth,contractExpirationDate,email,faculty,fax,placeOfBirth,nickName,nameUser,streetNumberDomicilie,streetNumberResidence,password,provinceDomicilie,provinceResidence,income,municipalityResidence,familySituation,telephoneNumber,typeAccount,typeParent,qualification,viaDomicilie,viaResidence) values("+nickname+","+account.getRegistrationDate()+","+account.getCapDomicilie()+","+account.getCapResidence()+","+account.getCellularNumber()+","+account.getCitizenship()+","+account.getTaxCode()+","+account.getSurnameUser()+","+account.getMunicalityResidence()+","+account.getDataOfBirth()+","+account.getContractExpirationDate()+","+account.getEmail()+","+account.getFaculty()+","+account.getFax()+","+account.getPlaceOFBirth()+","+nickname+","+account.getNameUser()+","+account.getStreetNumberDomicilie()+","+account.getStreetNumberResidence()+","+account.getPassword()+","+account.getProvinceDomicilie()+","+account.getProvinceResidence()+","+account.getIncome()+","+account.getMunicalityResidence()+","+account.getFamilySituation()+","+account.getTelephoneNumber()+","+account.getTypeAccount()+","+account.getTypeParent()+","+account.getQualification()+","+account.getViaDomicile()+","+account.getViaResidence()+")";
		
		  //connection of DB
		  
		  //execute of query		
		  
		return account;
	}
	
	public Account Modify(Account changedAccount){
		
		String query="Update table_name " +
				"SET RegistrationDate="+changedAccount.getRegistrationDate()+",capDomicilie="+changedAccount.getCapDomicilie()+",capResidence="+changedAccount.getCapResidence()+",cellularNumber="+changedAccount.getCellularNumber()+",citizenship="+changedAccount.getCitizenship()+",taxCode="+changedAccount.getTaxCode()+",surnameUser="+changedAccount.getSurnameUser()+",municipalityResidence="+changedAccount.getMunicalityResidence()+",dateOfBirth="+changedAccount.getDataOfBirth()+",contractExpirationDate="+changedAccount.getContractExpirationDate()+",email="+changedAccount.getEmail()+",faculty="+changedAccount.getFaculty()+",fax="+changedAccount.getFax()+"placeOfBirth="+changedAccount.getPlaceOFBirth()+",nickName="+changedAccount.getNickName()+",nameUser="+changedAccount.getNameUser()+",streetNumberDomicilie="+changedAccount.getStreetNumberDomicilie()+",streetNumberResidence="+changedAccount.getStreetNumberResidence()+",password="+changedAccount.getPassword()+",provinceDomicilie="+changedAccount.getProvinceDomicilie()+",provinceResidence="+changedAccount.getProvinceResidence()+",income="+changedAccount.getIncome()+",municipalityResidence="+changedAccount.getMunicalityResidence()+",familySituation="+changedAccount.getFamilySituation()+",telephoneNumber="+changedAccount.getTelephoneNumber()+",typeAccount="+changedAccount.getTypeAccount()+",typeParent="+changedAccount.getTypeParent()+",qualification="+changedAccount.getQualification()+",viaDomicilie="+changedAccount.getViaDomicile()+",viaResidence="+changedAccount.getViaResidence()+
				"WHERE id="+changedAccount.getId(); //dubbi sulla query
		
		  //connection of DB
		  
		  //execute of query		
		  
		
		return changedAccount;
	}
	
	public Account Delete(Account deletedAccount){
		
		String query2="Delete From name_table Where id='"+deletedAccount.getId()+"'";
		return deletedAccount;
	}
	
	public ArrayList<Account> Search(Account account){
		ArrayList<Account> listAccount=new ArrayList<Account>();		
		  String query="SELECT * FROM name_table WHERE ";				//change table_name 		  
		  if (account.getNickName()!=null)
			  query=query+"nickName='"+account.getNickName()+"'";
		  if (account.getNameUser()!=null)
			  query=query+"nameUser='"+account.getNameUser()+"'";
		  if (account.getSurnameUser()!=null)
			  query=query+"surnameUser='"+account.getSurnameUser()+"'";
		  if (account.getTypeAccount()!=null)
			  query=query+"typeAccount='"+account.getTypeAccount()+"'";
		  
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
	

