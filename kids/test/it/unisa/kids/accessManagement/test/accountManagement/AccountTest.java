/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.test.accountManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import junit.framework.TestCase;

/**
 *
 * @author Gilda
 */
public class AccountTest extends TestCase{
    private Account account;

    public AccountTest(){}
    
    @Override
    public void setUp(){
        this.account = new Account();
        this.account.setId(1);
        this.account.setPassword("0000");
        this.account.setNameUser("pOracle");
        this.account.setSurnameUser("pOracle");
        //this.account.setDataOfBirth(new GregorianCalendar());
        this.account.setPlaceofBirth("pOracle");
        this.account.setTaxCode("pOracle");
        this.account.setCitizenship("pOracle");
        this.account.setMunicipalityResidence("pOracle");
        this.account.setProvinceDomicile("pOracle");
        this.account.setCapResidence("pOracle");
        this.account.setCapDomicile("pOracle");
        this.account.setQualification("pOracle");
        this.account.setAccountType("pOracle");
        this.account.setFamilySituation("pOracle");
        this.account.setIncome(0.0);
        //this.account.setContractExpirationDate(new GregorianCalendar());
        this.account.setFaculty("pOracle");
        //this.account.setRegistrationDate(new GregorianCalendar());
        this.account.setTypeParent("pOracle");
        this.account.setRegister("pOracle");
        this.account.setState("pOracle");
        
    }
    
    @Override
    public void tearDown(){
        this.account.setId(1);
        this.account.setPassword("0000");
        this.account.setNameUser("pOracle");
        this.account.setSurnameUser("pOracle");
        this.account.setPlaceofBirth("pOracle");
        this.account.setTaxCode("pOracle");
        this.account.setCitizenship("pOracle");
        this.account.setMunicipalityResidence("pOracle");
        this.account.setProvinceDomicile("pOracle");
        this.account.setCapResidence("pOracle");
        this.account.setCapDomicile("pOracle");
        this.account.setQualification("pOracle");
        this.account.setAccountType("pOracle");
        this.account.setFamilySituation("pOracle");
        this.account.setIncome(0.0);
        //this.account.setContractExpirationDate(new GregorianCalendar());
        this.account.setFaculty("pOracle");
        //this.account.setRegistrationDate(new GregorianCalendar());
        this.account.setTypeParent("pOracle");
        this.account.setRegister("pOracle");
        this.account.setState("pOracle");
        }
    
        private void testGetId(int pOracle){
            assertEquals(pOracle, this.account.getId());
        }
    
        public void testGetId(){
            assertEquals(1, this.account.getId());
        }
    
        public void testGetPassword(){
            assertEquals("0000", this.account.getPassword());
        }
   
        public void testGetNameUser(){
            assertEquals("pOracle", this.account.getNameUser());
        }
        
        public void testGetSurnameUser(){
            assertEquals("pOracle", this.account.getSurnameUser());
        }
        
        public void testGetPlaceofBirth(){
            assertEquals("pOracle", this.account.getPlaceOfBirth());
        }
        
        public void testGetTaxCode(){
            assertEquals("pOracle", this.account.getTaxCode());
        }
        
        public void testGetCitizenship(){
            assertEquals("pOracle", this.account.getCitizenship());
        }
        
        public void testGetMunicipalityResidence(){
            assertEquals("pOracle", this.account.getMunicipalityResidence());
        }
        
        public void testGetProvinceDomicile(){
            assertEquals("pOracle", this.account.getProvinceDomicile());
        }
        
        public void testGetCapResidence(){
            assertEquals("pOracle", this.account.getCapResidence());
        }
        
        public void testGetCapDomicile(){
            assertEquals("pOracle", this.account.getCapDomicile());
        }
        
        public void testGetQualification(){
            assertEquals("pOracle", this.account.getQualification());
        }
        
        public void testGetAccountType(){
            assertEquals("pOracle", this.account.getAccountType());
        }
        
        public void testGetFamilySituation(){
            assertEquals("pOracle", this.account.getFamilySituation());
        }
        
        public void testGetIncome(){
            assertEquals("0.0", this.account.getIncome());
        }
        
        public void testGetFaculty(){
            assertEquals("pOracle", this.account.getFaculty());
        }
        
        public void testGetTypeParent(){
            assertEquals("pOracle", this.account.getTypeParent());
        }
        
        public void testGetRegister(){
            assertEquals("pOracle", this.account.getRegister());
        }
        
         public void testGetState(){
            assertEquals("pOracle", account.getState());
        }
         
         
         
        
  
        private void testSetId(int pOracle){
            this.account.setId(pOracle);
            assertEquals(pOracle, this.account.getId());
        }
    
        public void testSetPassword(String pPassword){
            this.account.setPassword(pPassword);
            assertEquals(0000, this.account.getPassword());
        }
   
        public void testSetNameUser(String pOracle){
            this.account.setNameUser(pOracle);
            assertEquals(pOracle, this.account.getNameUser());
        }
        
        public void testSetSurnameUser(String pOracle){
            this.account.setSurnameUser(pOracle);
            assertEquals(pOracle, this.account.getSurnameUser());
        }
        
        public void testSetPlaceofBirth(String pOracle){
            this.account.setPlaceofBirth(pOracle);
            assertEquals(pOracle, this.account.getPlaceOfBirth());
        }
        
        public void testSetTaxCode(String pOracle){
            this.account.setTaxCode(pOracle);
            assertEquals(pOracle, this.account.getTaxCode());
        }
        
        public void testSetCitizenship(String pOracle){
            this.account.setCitizenship(pOracle);
            assertEquals(pOracle, this.account.getCitizenship());
        }
        
        public void testSetMunicipalityResidence(String pOracle){
            this.account.setMunicipalityResidence(pOracle);
            assertEquals(pOracle, this.account.getMunicipalityResidence());
        }
        
        /**
     *
     * @param pOracle
     */
    public void testSetProvinceDomicile(String pOracle){
            this.account.setProvinceDomicile(pOracle);
            assertEquals(pOracle, this.account.getProvinceDomicile());
        }
        
        public void testSetCapResidence(String pOracle){
            this.account.setCapResidence(pOracle);
            assertEquals(pOracle, this.account.getCapResidence());
        }
        
        public void testSetCapDomicile(String pOracle){
            this.account.setCapDomicile(pOracle);
            assertEquals(pOracle, this.account.getCapDomicile());
        }
        
        public void testSetQualification(String pOracle){
            this.account.setQualification(pOracle);
            assertEquals(pOracle, this.account.getQualification());
        }
        
        public void testSetAccountType(String pOracle){
            this.account.setAccountType(pOracle);
            assertEquals(pOracle, this.account.getAccountType());
        }
        
        public void testSetFamilySituation(String pOracle){
            this.account.setFamilySituation(pOracle);
            assertEquals(pOracle, this.account.getFamilySituation());
        }
        
        public void testSetIncome(double pOracle){
            this.account.setIncome(pOracle);
            assertEquals(pOracle, this.account.getIncome());
        }
        
        public void testSetFaculty(String pOracle){
            this.account.setFaculty(pOracle);
            assertEquals(pOracle, this.account.getFaculty());
        }
        
        public void testSetTypeParent(String pOracle){
            this.account.setTypeParent(pOracle);
            assertEquals(pOracle, this.account.getTypeParent());
        }
        
        public void testSetRegister(String pOracle){
            this.account.setRegister(pOracle);
            assertEquals(pOracle, this.account.getRegister());
        }
        
         public void testSetState(String pOracle){
             this.account.setState(pOracle);
            assertEquals(pOracle, this.account.getState());
        }
 }

