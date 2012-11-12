package it.unisa.kids.accessManagement;

import it.unisa.kids.common.DBNames;

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

public Recourse Crete(Recourse recourse){
	
	String query="INSERT INTO "+DBNames.TABLE_RECOURSE+" ("+DBNames.ATT_RECOURSE_DATA+","+DBNames.ATT_RECOURSE_REASON+","+DBNames.ATT_RECOURSE_VALUTATION+") VALUES ("+recourse.getData()+","+recourse.getReason()+","+null+")"; 
	
	//connection of DB
	  
	  //execute of query		
	  
	return recourse;
}

public Recourse Delete(Recourse recourse){
	String query="delete from name_table where id='"+recourse.getId()+"'";
	return recourse;
}



public Recourse Accept(Recourse recourse){
	
	String query="Update "+DBNames.TABLE_RECOURSE+"" +
			"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
			"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";
	
	//connection of DB
	  
	  //execute of query		
	  
	
	return recourse;
}

public Recourse Refuse(Recourse recourse){
	String query="Update "+DBNames.TABLE_RECOURSE+"" +
			"set "+DBNames.ATT_RECOURSE_VALUTATION+"='"+recourse.getValutation()+
			"where "+DBNames.ATT_RECOURSE_ID+"='"+recourse.getId()+"";
	
	//connection of DB
	  
	  //execute of query		
	  
	
	return recourse;
}

public ArrayList<Recourse> Search(Recourse recourse){
	 ArrayList<Recourse> listRecourse=new ArrayList<Recourse>();		
	  				//request= iscrizione?
	  String query="Select * From "+DBNames.TABLE_RECOURSE+","+DBNames.TABLE_REQUEST+","+DBNames.TABLE_ACCOUNT+" where "+DBNames.ATT_RECOURSE_IDREGISTRATION+"="+DBNames.TABLE_REQUEST+"."+DBNames.ATT_REQUEST_ID+" AND "+DBNames.ATT_REQUEST_IDACCOUNT+"="+DBNames.TABLE_ACCOUNT+"."+DBNames.ATT_ACCOUNT_ID+"";
	  if (recourse.getId()!=null)
		  query=query+"'"+DBNames.ATT_RECOURSE_ID+"'='"+recourse.getId()+"'";
	  if (recourse.getData()!=null)
		  query=query+"'"+DBNames.ATT_RECOURSE_DATA+"'='"+recourse.getData()+"'";
	  if (parent.getNameUser()!=null)
		  query=query+"'"+DBNames.ATT_ACCOUNT_NAMEUSER+"'='"+parent.getNameUser()+"'";
	  if (parent.getSurnameUser()!=null)
		  query=query+"'"+DBNames.ATT_ACCOUNT_SURNAMEUSER+"'='"+parent.getSurnameUser()+"'";
	  
	  
	  //connection of DB
	  
	  //execute of query		
	  
	  //processing of result
	  
	return listRecourse;
	
}

}
