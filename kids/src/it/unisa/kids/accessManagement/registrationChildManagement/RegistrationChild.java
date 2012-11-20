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
	
	private enum userSection {FULL_TIME("FullTime"), PART_TIME_POMERIDIANA("PartTimePomeridiana"), PART_TIME_MATTUTINA("PartTimeMattutina");
		
			private final String description; // attributo

			userSection(String desc) { // costruttore
				this.description = desc;
			}
			public String getDescription() { // metodo
				return description;
			}
	};		
	
	private enum registrationPhase {REGISTRATA("Registrata"), CONFERMATA("Confermata"), ACCETTATA("Accettata"), ELIMINATA("Eliminata"), RINUNCIATA("Rinunciata"), RICORSO("Ricorso");
		
		private final String description; // attributo

		registrationPhase(String desc) { // costruttore
			this.description = desc;
		}
		public String getDescription() { // metodo
			return description;
		}
	};
	
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
	@SuppressWarnings("static-access")
	public String getBornDate() {
		String tmp="";
		return tmp=tmp+bornDate.YEAR+"-"+bornDate.MONTH+"-"+bornDate.DATE;
	}
	public void setBornDate(String bornDates) {
		this.bornDate.set(Integer.parseInt(bornDates.substring(0, 3)), Integer.parseInt(bornDates.substring(5, 7)), Integer.parseInt(bornDates.substring(8, 10)));
	}
	@SuppressWarnings("static-access")
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
			case FULL_TIME:
				result="FullTime";
				break;
			case PART_TIME_POMERIDIANA:
				result="PartTimePomeridiana";
				break;
		}
		return result;
	}
	public void setUserSection(String userSections) {
		if(userSections.equals(userSection.FULL_TIME.getDescription()))
			usSec=userSection.FULL_TIME;
		if(userSections.equals(userSection.PART_TIME_POMERIDIANA.getDescription()))
			usSec=userSection.PART_TIME_POMERIDIANA;
		if(userSections.equals(userSection.PART_TIME_MATTUTINA.getDescription()))
			usSec=userSection.PART_TIME_MATTUTINA;
	}
	public String getRegistrationPhase() {
		String result="";
		
		switch (regPh)
		{
			case ACCETTATA:
				result=registrationPhase.ACCETTATA.getDescription();
				break;
			case ELIMINATA:
				result=registrationPhase.ELIMINATA.getDescription();
				break;
			case REGISTRATA:
				result=registrationPhase.REGISTRATA.getDescription();
				break;
			case CONFERMATA:
				result=registrationPhase.CONFERMATA.getDescription();
				break;
			case RINUNCIATA:
				result=registrationPhase.RINUNCIATA.getDescription();
				break;
			case RICORSO:
				result=registrationPhase.RICORSO.getDescription();
				break;
		}
		return result;
	}
	public void setRegistrationPhase(String registrationPhases) {
		if(registrationPhases.equals(registrationPhase.ACCETTATA.getDescription()))
			regPh=registrationPhase.ACCETTATA;
		if(registrationPhases.equals(registrationPhase.CONFERMATA.getDescription()))
			regPh=registrationPhase.CONFERMATA;
		if(registrationPhases.equals(registrationPhase.ELIMINATA.getDescription()))
			regPh=registrationPhase.ELIMINATA;
		if(registrationPhases.equals(registrationPhase.REGISTRATA.getDescription()))
			regPh=registrationPhase.REGISTRATA;
		if(registrationPhases.equals(registrationPhase.RICORSO.getDescription()))
			regPh=registrationPhase.RICORSO;
		if(registrationPhases.equals(registrationPhase.RINUNCIATA.getDescription()))
			regPh=registrationPhase.RINUNCIATA;
	}
}
