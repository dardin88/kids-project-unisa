package it.unisa.kids.accessManagement.accountManagement;

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
	private String dataOfBirth;
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
	private String typeAccount;
	private String typeParent;
	private String familySituation;
	private int income;
	private String contractExpirationDate;
	private String faculty;
	private String registrationDate;
	
	
	/**
	 * Empty constructor 
	 */
	
	public Account(){
		
	}
	
	/**this method sets the id of all accounts
	 * @param String pStreetNumberDomicile
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**this method sets the nickname of all accounts
	 * @param String pNickName
	 */

	public void setNickName(String pNickName) {
		this.nickName = pNickName;
	}

	/**this method sets the password of all accounts
	 * @param String pPassword
	 */

	public void setPassword(String pPassword) {
		this.password = pPassword;
	}
	
	/**this method sets name of all accounts
	 * @param String pNameUser
	 */

	public void setNameUser(String pNameUser) {
		this.nameUser = pNameUser;
	}
	
	/**this method sets the surname of all accounts
	 * @param String pSurnameUser
	 */

	public void setSurnameUser(String pSurnameUser) {
		this.surnameUser = pSurnameUser;
	}
	
	/**this method sets the street number of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */

	public void setDataOfBirth(String pDataOfBirth) {
		this.dataOfBirth = pDataOfBirth;
	}


	/**this method sets the place of birth of all accounts
	 * @param String pPlaceOfBirth
	 */

	public void setPlaceofBirth(String pPlaceofBirth) {
		this.placeOfBirth = pPlaceofBirth;
	}

	/**this method sets the tax code of all accounts
	 * @param String pTaxCode
	 */
	
	public void setTaxCode(String pTaxCode) {
		this.taxCode = pTaxCode;
	}
	
	/**this method sets the citizenship of all accounts
	 * @param String pCitizenship
	 */


	public void setCitizenship(String pCitizenship) {
		this.citizenship = pCitizenship;
	}
	
	/**this method sets the municipality of Residence of all accounts
	 * @param String pMunicipalityResidence
	 */

	public void setMunicipalityResidence(String pMunicipalityResidence) {
		this.municipalityResidence = pMunicipalityResidence;
	}
	
	/**this method sets the province of Residence of all accounts
	 * @param String pProvinceResidence
	 */

	public void setProvinceResidence(String pProvinceResidence) {
		this.provinceResidence = pProvinceResidence;
	}
	
	/**this method sets the via of Residence of all accounts
	 * @param String pViaResidence
	 */



	public void setViaResidece(String pViaResidence) {
		this.viaResidence = pViaResidence;
	}
	
	/**this method set the street number of Residence of all accounts
	 * @param String pStreetNumberResidence
	 */

	public void setStreetNumberResidece(String pStreetNumberResidence) {
		this.streetNumberResidence = pStreetNumberResidence;
	}
	
	/**this method set the telephone number of all accounts
	 * @param String pTelephoneNumber
	 */

	public void setTelephoneNumber(String pTelephoneNumber) {
		this.telephoneNumber = pTelephoneNumber;
	}
	
	/**this method set the cellular number of all accounts
	 * @param String pCellularNumber
	 */

	public void setCellularNumber(String pCellularNumber) {
		this.cellularNumber = pCellularNumber;
	}
	
	/**this method sets the fax of all accounts
	 * @param String pFax
	 */

	public void setFax(String pFax) {
		this.fax = pFax;
	}
	
	/**this method sets the email of all accounts
	 * @param String pEmail
	 */

	public void setEmail(String pEmail) {
		this.email = pEmail;
	}
	
	/**this method sets the via of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */

	public void setViaDomicile(String pViaDomicile) {
		this.viaDomicile = pViaDomicile;
	}
	
	/**this method sets the street number of Domicile of all accounts
	 * @param String pStreetNumberDomicile
	 */

	public void setStreetNumberDomicilie(String pStreetNumberDomicile) {
		this.streetNumberDomicilie = pStreetNumberDomicile;
	}
	
	/**this method sets the municipality of Domicile of all accounts
	 * @param String pMunicipalityDomicile
	 */

	public void setMunicipalityDomicilie(String pMunicipalityDomicile) {
		this.municipalityDomicilie = pMunicipalityDomicile;
	}
	
	/**this method sets the provice of Domicile of all accounts
	 * @param String pProviceDomicile
	 */

	public void setProvinceDomicilie(String pProvinceDomicile) {
		this.provinceDomicilie = pProvinceDomicile;
	}
	
	/**this method sets  the income of all accounts
	 * @param String pIncome
	 */

	public void setCapResidence(String capResidence) {
		this.capResidence = capResidence;
	}
	
	/**this method sets the cap of domicilie of all accounts
	 * @param String pCapDomicile
	 */

	public void setCapDomicilie(String pCapDomicile) {
		this.capDomicilie = pCapDomicile;
	}
	
	/**this method sets  the qualification of all accounts
	 * @param String pQualification
	 */


	public void setQualification(String pQualification) {
		this.qualification = pQualification;
	}
	
	/**this method sets  the type of account of all accounts
	 * @param String pIncome
	 */


	public void setTypeAccount(String pTypeAccount) {
		this.typeAccount = pTypeAccount;
	}
	
	/**this method sets  the type of parent of an accounts
	 * @param String pTypeParent
	 */

	public void setTypeParent(String pTypeParent) {
		this.typeParent = pTypeParent;
	}
	
	/**this method sets  the family situation of all accounts
	 * @param String pFamilySituation
	 */


	public void setFamilySituation(String pFamilySituation) {
		this.familySituation = pFamilySituation;
	}
	
	/**this method sets  the income of all accounts
	 * @param String pIncome
	 */

	public void setIncome(int pIncome) {
		this.income = pIncome;
	}
	
	/**this method sets  the date expiry of contract 
	 * @param String pContractExpirationDate
	 */


	public void setContractExpirationDate(String pContractExpirationDate) {
		this.contractExpirationDate = pContractExpirationDate;
	}
	
	/**this method sets the faculty attended of student
	 * @param String pFaculty
	 */


	public void setFaculty(String pFaculty) {
		this.faculty = pFaculty;
	}
	
	/**this method sets  the date of registration of student
	 * @param String pRegistrationDate
	 */
	
	public void setRegistrationDate(String pRegistrationDate) {
		this.registrationDate = pRegistrationDate;
	}
	
	/**this method returns the id of all accounts
	 * @return String id
	 */
	
	public int getId() {
		return id;
	}
	
	/**this method returns the nickname of all accounts
	 * @return String nickName
	 */
	
	public String getNickName() {
		return nickName;
	}
	
	/**this method returns the street number of Residence of all accounts
	 * @return String password
	 */
	
	public String getPassword() {
		return password;
	}
	
	/**this method returns the name of all accounts
	 * @return String nameUser
	 */
	
	public String getNameUser() {
		return nameUser;
	}
	
	/**this method returns the surname of all accounts
	 * @return String surnameUser
	 */
	
	public String getSurnameUser() {
		return surnameUser;
	}
	
	/**this method returns the data of birth of all accounts
	 * @return String dateOfBirth
	 */
	
	public String getDataOfBirth() {
		return dataOfBirth;
	}
	
	/**this method returns the street number of Residence of all accounts
	 * @return String placeOfBirth
	 */
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	/**this method returns the tax code of Residence of all accounts
	 * @return String taxCode
	 */
	
	public String getTaxCode() {
		return taxCode;
	}
	
	/**this method returns the citizenship of all accounts
	 * @return String citizenship
	 */
	
	public String getCitizenship() {
		return citizenship;
	}
	
	/**this method returns the municipality of Residence of all accounts
	 * @return String municipalityResidence
	 */
	
	public String getMunicipalityResidence() {
		return municipalityResidence;
	}
	
	/**this method returns the province of Residence of all accounts
	 * @return String provinceResidence
	 */
	
	public String getProvinceResidence() {
		return provinceResidence;
	}
	
	/**this method returns the via  of Residence of all accounts
	 * @return String viaResidence
	 */
		
	public String getViaResidence() {
		return viaResidence;
	}
	
	/**this method returns the street number of Residence of all accounts
	 * @return String streetNumberResidence
	 */
	
	public String getStreetNumberResidence() {
		return streetNumberResidence;
	}
	
	/**this method returns the telephone number  of all accounts
	 * @return String telephoneNumber
	 */
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	/**this method returns the cellular number of all accounts
	 * @return String cellularNumber
	 */
	
	public String getCellularNumber() {
		return cellularNumber;
	}
	
	/**this method returns the fax of all accounts
	 * @return String fax
	 */
	
	public String getFax() {
		return fax;
	}
	
	/**this method returns the email of all accounts
	 * @return String email
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**this method returns the via of domicile of all accounts
	 * @return String viaDomicile
	 */
	
	public String getViaDomicile() {
		return viaDomicile;
	}
	
	/**this method returns the street number of domicile of all accounts
	 * @return String streetNumberDomicile
	 */
	
	public String getStreetNumberDomicilie() {
		return streetNumberDomicilie;
	}
	
	/**this method returns the municipality of domicile of all accounts
	 * @return String municipalityDomicile
	 */
	
	public String getMunicipalityDomicilie() {
		return municipalityDomicilie;
	}
	
	/**this method returns the province of domicile of all accounts
	 * @return String provinceDoicile
	 */
	
	public String getProvinceDomicilie() {
		return provinceDomicilie;
	}
	
	/**this method returns the cap of residence of all accounts
	 * @return String capResidence
	 */
	
	public String getCapResidence() {
		return capResidence;
	}
	
	/**this method returns the cap of domicile of all accounts
	 * @return String capDomicile
	 */
	
	
	public String getCapDomicilie() {
		return capDomicilie;
	}
	
	/**this method returns the qualification of all types of accounts
	 * @return String qualification
	 */
	
	
	public String getQualification() {
		return qualification;
	}
	
	/**this method returns the type of account
	 * @return String typeAccount
	 */
	
	
	public String getTypeAccount() {
		return typeAccount;
	}
	
	/**this method returns the income of all types of accounts
	 * @return String typeParent
	 */
	
	
	public String getTypeParent() {
		return typeParent;
	}
	
	/**this method returns the family situation of all types of accounts
	 * @return String familySituation
	 */
	
	
	public String getFamilySituation() {
		return familySituation;
	}
	
	/**this method returns the income of all types of accounts
	 * @return String income
	 */
	
	
	public int getIncome() {
		return income;
	}
	
	/**this method returns the date of expiry of the contract
	 * @return String contractExpirationDate
	 */
	
	public String getContractExpirationDate() {
		return contractExpirationDate;
	}
	
	
	/**this method returns the faculty attended by the student
	 * @return String faculty
	 */
	
	public String getFaculty() {
		return faculty;
	}
	
	/**this method returns the date of registration of a student
	 * @return String registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	
	
}
