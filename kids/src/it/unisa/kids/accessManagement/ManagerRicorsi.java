package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class ManagerRicorsi {

private static ManagerRicorsi manager;
private Account parent;

private ManagerRicorsi(){
	parent=new Account();
}

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

public ArrayList<Recourse> Search(Recourse recourse){
	 ArrayList<Recourse> listRecourse=new ArrayList<Recourse>();		
	  				//change name table
	  String query="Select * From Ricorso,Iscrizione,Account where id_iscrizione=table_name.id AND id_Account=table_name.id";
	  if (recourse.getId()!=null)
		  query=query+"'id'='"+recourse.getId()+"'";
	  if (recourse.getData()!=null)
		  query=query+"'data'='"+recourse.getData()+"'";
	  if (parent.getNameUser()!=null)
		  query=query+"'nomeUtente'='"+parent.getNameUser()+"'";
	  if (parent.getSurnameUser()!=null)
		  query=query+"'cognome'='"+parent.getSurnameUser()+"'";
	  
	  
	  //connection of DB
	  
	  //execute of query		
	  
	  //processing of result
	  
	return listRecourse;
	
}

}
