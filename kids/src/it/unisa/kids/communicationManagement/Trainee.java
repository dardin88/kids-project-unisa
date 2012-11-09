package it.unisa.kids.communicationManagement;

import java.util.Date;

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
	
	public Trainee(String register,String name,String surname,String email,String address,String birth_city,String city_of_residence,String CAP,String telephone_number,Date birth_date){
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
				
	}
	public Trainee(String register,String name,String surname,String email,String address,String birth_city,String city_of_residence,String CAP,Date birth_date){
		this(register,name,surname,email,address,birth_city,city_of_residence,CAP,null,birth_date);
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirth_city() {
		return birth_city;
	}
	public void setBirth_city(String birth_city) {
		this.birth_city = birth_city;
	}
	public String getCity_of_residence() {
		return city_of_residence;
	}
	public void setCity_of_residence(String city_of_residence) {
		this.city_of_residence = city_of_residence;
	}
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	public String getTelephone_number() {
		return telephone_number;
	}
	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}
	public Date getBirthday() {
		return birth_date;
	}
	public void setBirthday(Date birthday) {
		this.birth_date = birthday;
	} 
	
	
	
}
