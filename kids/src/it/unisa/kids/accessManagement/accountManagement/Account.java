package it.unisa.kids.accessManagement.accountManagement;

import java.util.GregorianCalendar;

/**
 * This class models the Account entity
 * @author Gianmarco Del Pozzo
 *
 */
public class Account {

	private int id;
	private String nickName;
	private String password;
	private String nameUser;
	private String surnameUser;
	private GregorianCalendar dataOfBirth;
	private String placeOfBirth;
	private String taxCode;
	private String citizenship;
	private String municipalityResidence;
	private String provinceResidence;
	private String viaResidence;
	private String streetNumberResidence;
	private String telephoneNumber;
	private String cellularNumber;
	private String fax;
	private String email;
	private String viaDomicile;
	private String streetNumberDomicilie;
	private String municipalityDomicilie;
	private String provinceDomicilie;
	private String capResidence;
	private String capDomicilie;
	private String qualification;
	private String accountType;
	private String familySituation;
	private double income;
	private GregorianCalendar contractExpirationDate;
	private String faculty;
	private GregorianCalendar registrationDate;


	/**
	 * Empty constructor 
	 */
	protected Account(){

	}

	/**this method sets the id of all accounts
	 * @param String pStreetNumberDomicile
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**this method sets the nickname of all accounts
	 * @param String pNickName
	 */
	protected void setNickName(String pNickName) {
		this.nickName = pNickName;
	}

	/**this method sets the password of all accounts
	 * @param String pPassword
	 */
	protected void setPassword(String pPassword) {
		this.password = pPassword;
	}

	/**this method sets name of all accounts
	 * @param String pNameUser
	 */
	protected void setNameUser(String pNameUser) {
		this.nameUser = pNameUser;
	}

	/**this method sets the surname of all accounts
	 * @param String pSurnameUser
	 */
	protected void setSurnameUser(String pSurnameUser) {
		this.surnameUser = pSurnameUser;
	}

	/**this method sets the street number of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */
	protected void setDataOfBirth(GregorianCalendar pDataOfBirth) {
		this.dataOfBirth = pDataOfBirth;
	}


	/**this method sets the place of birth of all accounts
	 * @param String pPlaceOfBirth
	 */
	protected void setPlaceofBirth(String pPlaceofBirth) {
		this.placeOfBirth = pPlaceofBirth;
	}

	/**this method sets the tax code of all accounts
	 * @param String pTaxCode
	 */
	protected void setTaxCode(String pTaxCode) {
		this.taxCode = pTaxCode;
	}

	/**this method sets the citizenship of all accounts
	 * @param String pCitizenship
	 */
	protected void setCitizenship(String pCitizenship) {
		this.citizenship = pCitizenship;
	}

	/**this method sets the municipality of Residence of all accounts
	 * @param String pMunicipalityResidence
	 */
	protected void setMunicipalityResidence(String pMunicipalityResidence) {
		this.municipalityResidence = pMunicipalityResidence;
	}

	/**this method sets the province of Residence of all accounts
	 * @param String pProvinceResidence
	 */
	protected void setProvinceResidence(String pProvinceResidence) {
		this.provinceResidence = pProvinceResidence;
	}

	/**this method sets the via of Residence of all accounts
	 * @param String pViaResidence
	 */
	protected void setViaResidence(String pViaResidence) {
		this.viaResidence = pViaResidence;
	}

	/**this method set the street number of Residence of all accounts
	 * @param String pStreetNumberResidence
	 */
	protected void setStreetNumberResidence(String pStreetNumberResidence) {
		this.streetNumberResidence = pStreetNumberResidence;
	}

	/**this method set the telephone number of all accounts
	 * @param String pTelephoneNumber
	 */
	protected void setTelephoneNumber(String pTelephoneNumber) {
		this.telephoneNumber = pTelephoneNumber;
	}

	/**this method set the cellular number of all accounts
	 * @param String pCellularNumber
	 */
	protected void setCellularNumber(String pCellularNumber) {
		this.cellularNumber = pCellularNumber;
	}

	/**this method sets the fax of all accounts
	 * @param String pFax
	 */
	protected void setFax(String pFax) {
		this.fax = pFax;
	}

	/**this method sets the email of all accounts
	 * @param String pEmail
	 */
	protected void setEmail(String pEmail) {
		this.email = pEmail;
	}

	/**this method sets the via of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */
	protected void setViaDomicile(String pViaDomicile) {
		this.viaDomicile = pViaDomicile;
	}

	/**this method sets the street number of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */
	protected void setStreetNumberDomicile(String pStreetNumberDomicile) {
		this.streetNumberDomicilie = pStreetNumberDomicile;
	}

	/**this method sets the municipality of Domicile of all accounts
	 * @param String pMunicipalityDomicile
	 */
	protected void setMunicipalityDomicile(String pMunicipalityDomicile) {
		this.municipalityDomicilie = pMunicipalityDomicile;
	}

	/**this method sets the provice of Domicile of all accounts
	 * @param String pProviceDomicile
	 */
	protected void setProvinceDomicile(String pProvinceDomicile) {
		this.provinceDomicilie = pProvinceDomicile;
	}

	/**this method sets  the income of all accounts
	 * @param String pIncome
	 */
	protected void setCapResidence(String capResidence) {
		this.capResidence = capResidence;
	}

	/**this method sets the cap of domicilie of all accounts
	 * @param String pCapDomicile
	 */
	protected void setCapDomicile(String pCapDomicile) {
		this.capDomicilie = pCapDomicile;
	}

	/**this method sets  the qualification of all accounts
	 * @param String pQualification
	 */
	protected void setQualification(String pQualification) {
		this.qualification = pQualification;
	}

	/**this method sets  the type of account of all accounts
	 * @param String pIncome
	 */
	protected void setAccountType(String pAccountType) {
		this.accountType = pAccountType;
	}

	/**this method sets  the family situation of all accounts
	 * @param String pFamilySituation
	 */
	protected void setFamilySituation(String pFamilySituation) {
		this.familySituation = pFamilySituation;
	}

	/**this method sets  the income of all accounts
	 * @param String pIncome
	 */
	protected void setIncome(double pIncome) {
		this.income = pIncome;
	}

	/**this method sets  the date expiry of contract 
	 * @param String pContractExpirationDate
	 */
	protected void setContractExpirationDate(GregorianCalendar pContractExpirationDate) {
		this.contractExpirationDate = pContractExpirationDate;
	}

	/**this method sets the faculty attended of student
	 * @param String pFaculty
	 */
	protected void setFaculty(String pFaculty) {
		this.faculty = pFaculty;
	}

	/**this method sets  the date of registration of student
	 * @param String pRegistrationDate
	 */
	protected void setRegistrationDate(GregorianCalendar pRegistrationDate) {
		this.registrationDate = pRegistrationDate;
	}

	/**this method returns the id of all accounts
	 * @return String id
	 */
	protected int getId() {
		return id;
	}

	/**this method returns the nickname of all accounts
	 * @return String nickName
	 */
	protected String getNickName() {
		return nickName;
	}

	/**this method returns the street number of Residence of all accounts
	 * @return String password
	 */
	protected String getPassword() {
		return password;
	}

	/**this method returns the name of all accounts
	 * @return String nameUser
	 */
	protected String getNameUser() {
		return nameUser;
	}

	/**this method returns the surname of all accounts
	 * @return String surnameUser
	 */
	protected String getSurnameUser() {
		return surnameUser;
	}

	/**this method returns the data of birth of all accounts
	 * @return String dateOfBirth
	 */
	protected GregorianCalendar getDataOfBirth() {
		return dataOfBirth;
	}

	/**this method returns the street number of Residence of all accounts
	 * @return String placeOfBirth
	 */
	protected String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**this method returns the tax code of Residence of all accounts
	 * @return String taxCode
	 */
	protected String getTaxCode() {
		return taxCode;
	}

	/**this method returns the citizenship of all accounts
	 * @return String citizenship
	 */
	protected String getCitizenship() {
		return citizenship;
	}

	/**this method returns the municipality of Residence of all accounts
	 * @return String municipalityResidence
	 */
	protected String getMunicipalityResidence() {
		return municipalityResidence;
	}

	/**this method returns the province of Residence of all accounts
	 * @return String provinceResidence
	 */
	protected String getProvinceResidence() {
		return provinceResidence;
	}

	/**this method returns the via  of Residence of all accounts
	 * @return String viaResidence
	 */
	protected String getViaResidence() {
		return viaResidence;
	}

	/**this method returns the street number of Residence of all accounts
	 * @return String streetNumberResidence
	 */
	protected String getStreetNumberResidence() {
		return streetNumberResidence;
	}

	/**this method returns the telephone number  of all accounts
	 * @return String telephoneNumber
	 */
	protected String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**this method returns the cellular number of all accounts
	 * @return String cellularNumber
	 */
	protected String getCellularNumber() {
		return cellularNumber;
	}

	/**this method returns the fax of all accounts
	 * @return String fax
	 */
	protected String getFax() {
		return fax;
	}

	/**this method returns the email of all accounts
	 * @return String email
	 */
	protected String getEmail() {
		return email;
	}

	/**this method returns the via of domicile of all accounts
	 * @return String viaDomicile
	 */
	protected String getViaDomicile() {
		return viaDomicile;
	}

	/**this method returns the street number of domicile of all accounts
	 * @return String streetNumberDomicile
	 */
	protected String getStreetNumberDomicile() {
		return streetNumberDomicilie;
	}

	/**this method returns the municipality of domicile of all accounts
	 * @return String municipalityDomicile
	 */
	protected String getMunicipalityDomicile() {
		return municipalityDomicilie;
	}

	/**this method returns the province of domicile of all accounts
	 * @return String provinceDoicile
	 */
	protected String getProvinceDomicile() {
		return provinceDomicilie;
	}

	/**this method returns the cap of residence of all accounts
	 * @return String capResidence
	 */
	protected String getCapResidence() {
		return capResidence;
	}

	/**this method returns the cap of domicile of all accounts
	 * @return String capDomicile
	 */
	protected String getCapDomicile() {
		return capDomicilie;
	}

	/**this method returns the qualification of all types of accounts
	 * @return String qualification
	 */
	protected String getQualification() {
		return qualification;
	}

	/**this method returns the type of account
	 * @return String typeAccount
	 */
	protected String getAccountType() {
		return accountType;
	}



	/**this method returns the family situation of all types of accounts
	 * @return String familySituation
	 */
	protected String getFamilySituation() {
		return familySituation;
	}

	/**this method returns the income of all types of accounts
	 * @return String income
	 */
	protected double getIncome() {
		return income;
	}

	/**this method returns the date of expiry of the contract
	 * @return String contractExpirationDate
	 */
	protected GregorianCalendar getContractExpirationDate() {
		return contractExpirationDate;
	}


	/**this method returns the faculty attended by the student
	 * @return String faculty
	 */
	protected String getFaculty() {
		return faculty;
	}

	/**this method returns the date of registration of a student
	 * @return String registrationDate
	 */
	protected GregorianCalendar getRegistrationDate() {
		return registrationDate;
	}



}
