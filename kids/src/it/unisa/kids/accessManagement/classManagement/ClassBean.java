package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Educator;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;

import java.util.ArrayList;
import java.util.List;

public class ClassBean {
	private int idClasse;
	private String className;
	private List<Educator> educatori;				
	private List<RegistrationChild> bambini;					
	
	public ClassBean()  {   }        
        
        public ClassBean(String name,ArrayList<Educator> educa,ArrayList<RegistrationChild> bamb)
	{
		className=name;
		this.educatori=educa;
		this.bambini=bamb;
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {			
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