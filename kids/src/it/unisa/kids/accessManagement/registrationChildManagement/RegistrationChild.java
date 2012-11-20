package it.unisa.kids.accessManagement.registrationChildManagement;

import java.util.GregorianCalendar;

public class RegistrationChild {
	
	private int registrationId;
	private String surname;
	private String name;
	private String communeBorn;
	private String fiscalCode;
	private String citizenship;
	private boolean sick;
	private GregorianCalendar bornDate;
	private GregorianCalendar registrationDate; 
	
	private enum userSection {FULLTIME, PART_TIME_POMERIDIANA, PART_TIME_MATTUTINA};			
	private enum registrationPhase {REGISTRATA, CONFERMATA, ACCETTATA, ELIMINATA, RINUNCIATA, RICORSO};
	
	private userSection usSec;
	private registrationPhase regPh;
	
	public RegistrationChild() { }
	
	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
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
	public boolean isSick() {
		return sick;
	}
	public void setSick(boolean sickness) {
		this.sick = sickness;
	}
	public String getBornDate() {
		String tmp="";
		return tmp=tmp+bornDate.YEAR+"-"+bornDate.MONTH+"-"+bornDate.DATE;
	}
	public void setBornDate(String bornDates) {
		this.bornDate.set(Integer.parseInt(bornDates.substring(0, 3)), Integer.parseInt(bornDates.substring(5, 7)), Integer.parseInt(bornDates.substring(8, 10)));
	}
	public String getRegistrationDate() {
		String tmp="";
		return tmp=tmp+registrationDate.YEAR+"-"+registrationDate.MONTH+"-"+registrationDate.DATE;
	}
	public void setRegistrationDate(String registrationDates) {
		this.registrationDate.set(Integer.parseInt(registrationDates.substring(0, 3)), Integer.parseInt(registrationDates.substring(5, 7)), Integer.parseInt(registrationDates.substring(8, 10)));
	}
	public String getUserSection() 
	{
		String result="";
		
		switch (usSec)
		{
			case PART_TIME_MATTUTINA:
				result="PartTimeMattutina";
				break;
			case FULLTIME:
				result="FullTime";
				break;
			case PART_TIME_POMERIDIANA:
				result="PartTimePomeridiana";
				break;
		}
		return result;
	}
	public void setUserSection(String userSection) {
		this.userSection = userSection;
	}
	public String getRegistrationPhase() {
		String result="";
		
		switch (regPh)
		{
			case ACCETTATA:
				result="Accettata";
				break;
			case ELIMINATA:
				result="Eliminata";
				break;
			case REGISTRATA:
				result="Registrata";
				break;
			case CONFERMATA:
				result="Confermata";
				break;
			case RINUNCIATA:
				result="Rinunciata";
				break;
			case RICORSO:
				result="Ricorso";
				break;
		}
		return result;
	}
	public void setRegistrationPhase(String registrationPhase) {
		this.registrationPhase = registrationPhase;
	}
}
