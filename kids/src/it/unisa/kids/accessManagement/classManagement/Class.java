package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Educator;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;

import java.util.ArrayList;
import java.util.List;

public class Class {
	private String idClasse;
	private String className;
	private List<Educator> educatori;				//bisogna creare la classe educatore <- già esistenti
	private List<RegistrationChild> bambini;					//bisogna creare la classe bambini <- già esistenti: ChildRegistrationBean
	
	public Class(String name,ArrayList<Educator> educa,ArrayList<RegistrationChild> bamb)
	{
		className=name;
		this.educatori=educa;
		this.bambini=bamb;
	}

	public String getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(String idClasse) {				//credo che il set non debba esserci visto che Ã¨ autoincrementale
		this.idClasse = idClasse;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Educator> getEducatori() {
		return educatori;
	}

	public void setEducatori(List<Educator> educatori) {
		this.educatori = educatori;
	}

	public List<RegistrationChild> getBambini() {
		return bambini;
	}

	public void setBambini(List<RegistrationChild> bambini) {
		this.bambini = bambini;
	}
}
