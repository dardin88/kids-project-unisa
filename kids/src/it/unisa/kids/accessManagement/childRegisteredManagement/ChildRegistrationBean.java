package it.unisa.kids.accessManagement.childRegisteredManagement;

import java.util.GregorianCalendar;

public class ChildRegistrationBean {
	
	private String registrationId;
	private String surname;
	private String name;
	private String communeBorn;
	private String fiscalCode;
	private String citizenship;
	private String sickness;
	private GregorianCalendar bornDate;
	private GregorianCalendar registrationDate; 
	private String userSection;			// deve essere String perchè e' l'id della Classe
	private String faseDellIscrizione;
	
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
	public String getUserSection() {
		return userSection;
	}
	public void setUserSection(String userSection) {
		this.userSection = userSection;
	}
	public String getFaseDellIscrizione() {
		return faseDellIscrizione;
	}
	public void setFaseDellIscrizione(String faseDellIscrizione) {
		this.faseDellIscrizione = faseDellIscrizione;
	}
}
