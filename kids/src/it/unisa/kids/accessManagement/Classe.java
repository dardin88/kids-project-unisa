package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class Classe {
	private String idClasse;
	private String nomeClasse;
	private List<Educatore> educatori;		//bisogna creare la classe educatore
	private List<Bambini> bambini;			//bisogna creare la classe bambini
	
	public Classe(String nome,ArrayList<Educatore> educa,ArrayList<Bambini> bamb)
	{
		this.educatori=educa;
		this.bambini=bamb;
	}
	
	public String getIdClasse() 
	{
		return idClasse;
	}
	
	public void setIdClasse(String idClasse) 	//il set dell'id credo sia inutile visto che dovrebbe essere autoincrementale
	{
		this.idClasse = idClasse;
	}
	
	public String getNomeClasse() 
	{
		return nomeClasse;
	}
	
	public void setNomeClasse(String nomeClasse) 
	{
		this.nomeClasse = nomeClasse;
	}
	
	public List<Educatore> getEducatori() 
	{
		return educatori;
	}
	
	public void setEducatori(List<Educatore> educatori) 
	{
		this.educatori = educatori;
	}
	
	

}
