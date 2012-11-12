package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.Account;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class models the Trainee entity
 * @author Marco Moretti
 *
 */

public class Trainee {
	private String register;
	private String name;
	private String surname;
	private String email;
	private String address;
	private String birthCity;
	private String cityOfResidence;
	private String cap;
	private String telephoneNumber;
	private GregorianCalendar birthDate;
	private Account delegate;
	
	/**This is first the constructor of the class .This constructor takes also the telephone number as parameter
	 * @param String pRegister
	 * @param String pName
	 * @param String pSurname
	 * @param String pEmail
	 * @param String pAddress
	 * @param String pBirthCity
	 * @param String pCityOfResidence
	 * @param String pCap
	 * @param String pTelephoneNumber
	 * @param GregorianCalendar pBirthDate
	 * @param Account pDelegate
	 */
	public Trainee(String pRegister,String pName,String pSurname,String pEmail,String pAddress,String pBirthCity,String pCityOfResidence,String pCap,GregorianCalendar pBirthDate,Account delegate){
		this.register=pRegister;
		this.name=pName;
		this.surname=pSurname;
		this.email=pEmail;
		this.address=pAddress;
		this.birthCity=pBirthCity;
		this.cityOfResidence=pCityOfResidence;
		this.cap=pCap;
		this.birthDate=pBirthDate;
		this.delegate=delegate;
				
	}
	
	
	/**this method returns the register of trainee
	 * @return String register
	 */
	public String getRegister() {
		return register;
	}
	
	/**this method sets  the register of trainee
	 * @param String pRegister
	 */
	public void setRegister(String pRegister) {
		this.register = pRegister;
	}
	
	/**this method returns the name of trainee
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	/**this method sets  the name of trainee
	 * @param String pName
	 */
	public void setName(String pName) {
		this.name = pName;
	}
	
	/**this method returns the surname of trainee
	 * @return String surname
	 */
	public String getSurname() {
		return surname;
	}

	/**this method sets  the surname of trainee
	 * @param String pSurname
	 */
	public void setSurname(String pSurname) {
		this.surname = pSurname;
	}
	
	/**this method returns the email of trainee
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**this method sets  the email of trainee
	 * @param String pEmail
	 */
	public void setEmail(String pEmail) {
		this.email = pEmail;
	}
	
	/**this method returns the address of trainee
	 * @return String address
	 */
	public String getAddress() {
		return address;
	}

	/**this method sets  the address of trainee
	 * @param String pRegister
	 */
	public void setAddress(String pAddress) {
		this.address = pAddress;
	}
	
	/**this method returns the birth city of trainee
	 * @return String birthCity
	 */
	public String getBirthCity() {
		return birthCity;
	}

	/**this method sets  the birth city  of trainee
	 * @param String birthCity
	 */
	public void setBirthCity(String pBirthCity) {
		this.birthCity = pBirthCity;
	}
	
	/**this method returns the city of residence of trainee
	 * @return String cityOfResidence
	 */
	public String getCityOfResidence() {
		return cityOfResidence;
	}

	/**this method sets  the city of residence of trainee
	 * @param String pCityOfResidence
	 */
	public void setCityOfResidence(String pCityOfResidence) {
		this.cityOfResidence = pCityOfResidence;
	}
	
	/**this method returns the CAP of the city where trainee lives 
	 * @return String cap
	 */
	public String getCap() {
		return cap;
	}

	/**this method sets  the CAP of the city where the trainee lives
	 * @param String pCap
	 */
	public void setCap(String pCap) {
		cap = pCap;
	}
	
	/**this method returns the telephone number of trainee
	 * @return String telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**this method sets  the telephone number of trainee
	 * @param String pTelephoneNumber
	 */
	public void setTelephoneNumber(String pTelephoneNumber) {
		this.telephoneNumber = pTelephoneNumber;
	}
	
	/**this method returns the birth date  of trainee
	 * @return GregorianCalendar birthDate
	 */
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	/**this method sets  the birth date of trainee
	 * @param String pBirthDate
	 */
	public void setBirthDate(GregorianCalendar pBirthDate) {
		this.birthDate = pBirthDate;
		
	}

	
	/**this method returns the delegate that has added trainee
	 * @return  Account delegate
	 */
	public Account getDelegate() {
		return delegate;
	}

	/**this method sets the delegate that has added trainee
	 * @param  Account pDelegate
	 */
	public void setDelegate(Account pDelegate) {
		this.delegate = pDelegate;
	} 
	
	
	
	
}
