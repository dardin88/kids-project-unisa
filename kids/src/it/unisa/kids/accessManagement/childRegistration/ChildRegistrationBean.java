package it.unisa.kids.accessManagement.childRegistration;

import java.util.GregorianCalendar;

public class ChildRegistrationBean {
	
	String registrationId,surname, name, communeBorn, fiscalCode, citizenship, sickness;
	GregorianCalendar bornDate,registrationDate; 
	// userSection,FaseDellIscrizione;					di che tipo devono essere?
	
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommuneBorn() {
		return communeBorn;
	}
	public void setCommuneBorn(String communeBorn) {
		this.communeBorn = communeBorn;
	}
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getSickness() {
		return sickness;
	}
	public void setSickness(String sickness) {
		this.sickness = sickness;
	}
	public GregorianCalendar getBornDate() {
		return bornDate;
	}
	public void setBornDate(GregorianCalendar bornDate) {
		this.bornDate = bornDate;
	}
	public GregorianCalendar getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(GregorianCalendar registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}
