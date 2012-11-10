package it.unisa.kids.serviceManagement;

import it.unisa.kids.accessManagement.Account;

import java.util.Date;

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
	private String birth_city;
	private String city_of_residence;
	private String CAP;
	private String telephone_number;
	private Date birth_date;
	private Account delegate;
	
	/**This is first the constructor of the class .This constructor takes also the telephone number as parameter
	 * @param String register
	 * @param String name
	 * @param String surname
	 * @param String email
	 * @param String address
	 * @param String birth_city
	 * @param String city_of_residence
	 * @param String CAP
	 * @param String telephone_number
	 * @param Date birth_date
	 * @param Account delegate
	 */
	public Trainee(String register,String name,String surname,String email,String address,String birth_city,String city_of_residence,String CAP,String telephone_number,Date birth_date,Account delegate){
		this.register=register;
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.address=address;
		this.birth_city=birth_city;
		this.city_of_residence=city_of_residence;
		this.CAP=CAP;
		this.telephone_number=telephone_number;
		this.birth_date=birth_date;
		this.delegate=delegate;
				
	}
	
	/**This is second the constructor of the class .
	 * @param String register
	 * @param String name
	 * @param String surname
	 * @param String email
	 * @param String address
	 * @param String birth_city
	 * @param String city_of_residence
	 * @param String CAP
	 * @param String telephone_number
	 * @param Date birth_date
	 * @param Account delegate
	 */
	public Trainee(String register,String name,String surname,String email,String address,String birth_city,String city_of_residence,String CAP,Date birth_date,Account delegate){
		this(register,name,surname,email,address,birth_city,city_of_residence,CAP,null,birth_date,delegate);
	}
	
	/**this method returns the register of trainee
	 * @return String register
	 */
	public String getRegister() {
		return register;
	}
	
	/**this method sets  the register of trainee
	 * @param String register
	 */
	public void setRegister(String register) {
		this.register = register;
	}
	
	/**this method returns the name of trainee
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	/**this method sets  the name of trainee
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**this method returns the surname of trainee
	 * @return String surname
	 */
	public String getSurname() {
		return surname;
	}

	/**this method sets  the surname of trainee
	 * @param String surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**this method returns the email of trainee
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**this method sets  the email of trainee
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**this method returns the address of trainee
	 * @return String address
	 */
	public String getAddress() {
		return address;
	}

	/**this method sets  the address of trainee
	 * @param String register
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**this method returns the birth city of trainee
	 * @return String birth_city
	 */
	public String getBirth_city() {
		return birth_city;
	}

	/**this method sets  the birth city  of trainee
	 * @param String birth_city
	 */
	public void setBirth_city(String birth_city) {
		this.birth_city = birth_city;
	}
	
	/**this method returns the city of residence of trainee
	 * @return String city_of_residence
	 */
	public String getCity_of_residence() {
		return city_of_residence;
	}

	/**this method sets  the city of residence of trainee
	 * @param String city_of_residence
	 */
	public void setCity_of_residence(String city_of_residence) {
		this.city_of_residence = city_of_residence;
	}
	
	/**this method returns the CAP of the city where trainee lives 
	 * @return String CAP
	 */
	public String getCAP() {
		return CAP;
	}

	/**this method sets  the CAP of the city where the trainee lives
	 * @param String CAP
	 */
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	
	/**this method returns the telephone number of trainee
	 * @return String telephone_number
	 */
	public String getTelephone_number() {
		return telephone_number;
	}

	/**this method sets  the telephone number of trainee
	 * @param String telephone_number
	 */
	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}
	
	/**this method returns the birth date  of trainee
	 * @return String birth_date
	 */
	public Date getBirth_date() {
		return birth_date;
	}

	/**this method sets  the birth date of trainee
	 * @param String birth_date
	 */
	public void setBirth_date(Date birthday) {
		this.birth_date = birthday;
	} 
	
	
	
}
