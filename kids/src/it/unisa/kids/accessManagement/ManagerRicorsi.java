package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class ManagerRicorsi {

private static ManagerRicorsi manager;

private ManagerRicorsi(){}

public ManagerRicorsi getInstace(){
	if (manager!=null)
		return manager;
	else
		return manager=new ManagerRicorsi();
}

public Recourse Crete(Recourse recourse){
	
	String query="INSERT INTO name_table (data,motivo,valutazione) VALUES ("+recourse.getData()+","+recourse.getReason()+","+null+")"; 
	
	//connection of DB
	  
	  //execute of query		
	  
	return recourse;
}

public Recourse Delete(Recourse recourse){
	String query="delete from name_table where id='"+recourse.getId()+"'";
	return recourse;
}



public Recourse Accept(Recourse recourse){
	
	String query="Update name table" +
			"set valutazione='"+recourse.getValutation()+
			"where id='"+recourse.getId()+"";
	
	//connection of DB
	  
	  //execute of query		
	  
	
	return recourse;
}

public Recourse Refuse(Recourse recourse){
	String query="Update name table" +
			"set valutazione='"+recourse.getValutation()+
			"where id='"+recourse.getId()+"";
	
	//connection of DB
	  
	  //execute of query		
	  
	
	return recourse;
}

public ArrayList<Recourse> Search(Recourse recourse,Account account){
	 ArrayList<Recourse> listRecourse=new ArrayList<Recourse>();		
	  				//change name table
	  String query="Select * From ((Ricorso join Iscrizione on id_iscrizione=id)join Account on id_Account=id)where";
	  
	  if (recourse.getId()!=null)
		  query=query+"'id'='"+recourse.getId()+"'";
	  if (recourse.getData()!=null)
		  query=query+"'data'='"+recourse.getData()+"'";
	  if (account.getNameUser()!=null)
		  query=query+"'nomeUtente'='"+account.getNameUser()+"'";
	  if (account.getSurnameUser()!=null)
		  query=query+"'cognome'='"+account.getSurnameUser()+"'";
	  
	  
	  //connection of DB
	  
	  //execute of query		
	  
	  //processing of result
	  
	return listRecourse;
	
}

}
