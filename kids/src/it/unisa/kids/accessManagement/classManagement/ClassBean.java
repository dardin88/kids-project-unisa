package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import java.util.ArrayList;
import java.util.List;

public class ClassBean {
	private int idClasse;
	private String className;
	private List<Account> educatori;				
	private List<RegistrationChild> bambini;
        private String state;

	public ClassBean()  {   }        
        
        public ClassBean(String name,ArrayList<Account> educa,ArrayList<RegistrationChild> bamb)
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

	public List<Account> getEducatori() {
		return educatori;
	}

	public void setEducatori(List<Account> educatori) {
		this.educatori = educatori;
	}

	public List<RegistrationChild> getBambini() {
		return bambini;
	}

	public void setBambini(List<RegistrationChild> bambini) {
		this.bambini = bambini;
	}
        
        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
}
