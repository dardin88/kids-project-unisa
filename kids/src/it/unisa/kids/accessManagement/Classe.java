package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;

public class Classe {
	private String idClasse;
	private String className;
	private List<Educatore> educatori;				//bisogna creare la classe educatore
	private List<Bambini> bambini;					//bisogna creare la classe bambini
	
	public Classe(String name,ArrayList<Educatore> educa,ArrayList<Bambini> bamb)
	{
		className=name;
		this.educatori=educa;
		this.bambini=bamb;
	}

	public String getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(String idClasse) {				//credo che il set non debba esserci visto che è autoincrementale
		this.idClasse = idClasse;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Educatore> getEducatori() {
		return educatori;
	}

	public void setEducatori(List<Educatore> educatori) {
		this.educatori = educatori;
	}

	public List<Bambini> getBambini() {
		return bambini;
	}

	public void setBambini(List<Bambini> bambini) {
		this.bambini = bambini;
	}
	
		

}
