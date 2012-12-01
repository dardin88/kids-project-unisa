package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import java.util.GregorianCalendar;

/**
 * Class of rappresentation of an Application of Registration for a Child
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class RegistrationChild {
	private int id;
	private String surname;
	private String name;
	private GregorianCalendar birthDate;
	private String birthPlace;
	private String fiscalCode;
	private String citizenship;
	private String sick;
	private GregorianCalendar registrationDate; 
	private int idParent; 

	private enum UserSection {
		FULL_TIME(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERSECTION_FULLTIME), 
                PART_TIME_POMERIDIANA(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERSECTION_PARTTIMEPM), 
                PART_TIME_MATTUTINA(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERSECTION_PARTTIMEAM);

		private final String description; // attributo

		UserSection(String desc) { // costruttore
			this.description = desc;
		}
		public String getDescription() { // metodo
			return description;
		}
	};		

	private enum RegistrationPhase {
		REGISTRATA("Registrata"),
                CONFERMATA("Confermata"),
                ACCETTATA("Accettata"),
                ELIMINATA("Eliminata"),
                RINUNCIATA("Rinunciata"),
                RICORSO("Ricorso");

		private final String description; // attributo

		RegistrationPhase(String desc) { // costruttore
			this.description = desc;
		}
		public String getDescription() { // metodo
			return description;
		}
	};

	private UserSection usSec;
	private RegistrationPhase regPh;

	public RegistrationChild() { }

	public int getRegistrationId() {
		return id;
	}
	public void setRegistrationId(int registrationId) {
		this.id = registrationId;
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
		return birthPlace;
	}
	public void setCommuneBorn(String communeBorn) {
		this.birthPlace = communeBorn;
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
		return tmp=tmp+birthDate.YEAR+"-"+birthDate.MONTH+"-"+birthDate.DATE;
	}
	public void setBornDate(String bornDates) {
		this.birthDate.set(Integer.parseInt(bornDates.substring(0, 3)), Integer.parseInt(bornDates.substring(5, 7)), Integer.parseInt(bornDates.substring(8, 10)));
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
		if(userSections.equals(UserSection.FULL_TIME.getDescription()))
			usSec=UserSection.FULL_TIME;
		if(userSections.equals(UserSection.PART_TIME_POMERIDIANA.getDescription()))
			usSec=UserSection.PART_TIME_POMERIDIANA;
		if(userSections.equals(UserSection.PART_TIME_MATTUTINA.getDescription()))
			usSec=UserSection.PART_TIME_MATTUTINA;
	}
	public String getRegistrationPhase() {
		String result="";

		switch (regPh)
		{
		case ACCETTATA:
			result=RegistrationPhase.ACCETTATA.getDescription();
			break;
		case ELIMINATA:
			result=RegistrationPhase.ELIMINATA.getDescription();
			break;
		case REGISTRATA:
			result=RegistrationPhase.REGISTRATA.getDescription();
			break;
		case CONFERMATA:
			result=RegistrationPhase.CONFERMATA.getDescription();
			break;
		case RINUNCIATA:
			result=RegistrationPhase.RINUNCIATA.getDescription();
			break;
		case RICORSO:
			result=RegistrationPhase.RICORSO.getDescription();
			break;
		}
		return result;
	}
	public void setRegistrationPhase(String registrationPhases) {
		if(registrationPhases.equals(RegistrationPhase.ACCETTATA.getDescription()))
			regPh=RegistrationPhase.ACCETTATA;
		if(registrationPhases.equals(RegistrationPhase.CONFERMATA.getDescription()))
			regPh=RegistrationPhase.CONFERMATA;
		if(registrationPhases.equals(RegistrationPhase.ELIMINATA.getDescription()))
			regPh=RegistrationPhase.ELIMINATA;
		if(registrationPhases.equals(RegistrationPhase.REGISTRATA.getDescription()))
			regPh=RegistrationPhase.REGISTRATA;
		if(registrationPhases.equals(RegistrationPhase.RICORSO.getDescription()))
			regPh=RegistrationPhase.RICORSO;
		if(registrationPhases.equals(RegistrationPhase.RINUNCIATA.getDescription()))
			regPh=RegistrationPhase.RINUNCIATA;
	}

	public int getIdParent() {
		return this.idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}
}
