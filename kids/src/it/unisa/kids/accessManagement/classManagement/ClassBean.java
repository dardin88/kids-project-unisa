package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Educator;
import it.unisa.kids.accessManagement.childRegisteredManagement.ChildRegistrationBean;

import java.util.ArrayList;
import java.util.List;

public class ClassBean {
	private String idClasse;
	private String className;
	private List<Educator> educatori;				//bisogna creare la classe educatore <- già esistenti
	private List<ChildRegistrationBean> bambini;					//bisogna creare la classe bambini <- già esistenti: ChildRegistrationBean
	
	public ClassBean(String name,ArrayList<Educator> educa,ArrayList<ChildRegistrationBean> bamb)
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

	public List<ChildRegistrationBean> getBambini() {
		return bambini;
	}

	public void setBambini(List<ChildRegistrationBean> bambini) {
		this.bambini = bambini;
	}
}
