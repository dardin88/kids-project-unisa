package it.unisa.kids.accessManagement.tests.registrationChildManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import junit.framework.TestCase;

public class RegistrationChildTest extends TestCase{
    private RegistrationChild bean;
    
    public RegistrationChildTest(){}
    
    @Override
    public void setUp(){
        this.bean=new RegistrationChild();
        this.bean.setName("Name");
        this.bean.setAdditionalNotes("Add");
        this.bean.setBirthPlace("Place");
        this.bean.setCitizenship("Cit");
        this.bean.setFiscalCode("OLHTGB91F04R647I");
        this.bean.setIsVaccinationsSet("Vacc");
        this.bean.setIsSicknessSet("Sick");
        this.bean.setParentId(1);
        this.bean.setSickness("Sickness");
        this.bean.setSurname("Surname");
        this.bean.setIsPrivacyStatementSet("Privacy");
        this.bean.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.bean.setName("Name");
        this.bean.setAdditionalNotes("Add");
        this.bean.setBirthPlace("Place");
        this.bean.setCitizenship("Cit");
        this.bean.setFiscalCode("OLHTGB91F04R647I");
        this.bean.setIsVaccinationsSet("Vacc");
        this.bean.setIsSicknessSet("Sick");
        this.bean.setParentId(1);
        this.bean.setSickness("Sickness");
        this.bean.setSurname("Surname");
        this.bean.setIsPrivacyStatementSet("Privacy");
        this.bean.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.bean.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.bean.getId());
    }
    
    private void testGetName(String pOracle){
        assertEquals(pOracle, this.bean.getName());
    }
    
    public void testGetName(){
        assertEquals("Name", this.bean.getName());
    }
    
    private void testGetAdditionalNotes(String pOracle){
        assertEquals(pOracle, this.bean.getAdditionalNotes());
    }
    
    public void testGetAdditionalNotes(){
        assertEquals("Add", this.bean.getAdditionalNotes());
    }
    
    private void testGetBirthPlace(String pOracle){
        assertEquals(pOracle, this.bean.getBirthDate());
    }
    
    public void testGetBirthPlace(){
        assertEquals("Place", this.bean.getBirthDate());
    }
    
    private void testGetCitizenship(String pOracle){
        assertEquals(pOracle, this.bean.getCitizenship());
    }
    
    public void testGetCitizenship(){
        assertEquals("Cit", this.bean.getCitizenship());
    }
    
    private void testGetFiscalCode(String pOracle){
        assertEquals(pOracle, this.bean.getCitizenship());
    }
    
    public void testGetFiscalCode(){
        assertEquals("OLHTGB91F04R647I", this.bean.getCitizenship());
    }
            
    private void testGetIsVaccinationsSet(String pOracle){
        assertEquals(pOracle, this.bean.getIsVaccinationsSet());
    }
    
    public void testGetIsVaccinationsSet(){
        assertEquals("Vacc", this.bean.getIsVaccinationsSet());
    }
    
    private void testGetIsSicknessSet(String pOracle){
        assertEquals(pOracle, this.bean.getIsVaccinationsSet());
    }
    
    public void testGetIsSicknessSet(){
        assertEquals("Sick", this.bean.getIsSicknessSet());
    }
    
    private void testGetParentId(int pOracle){
        assertEquals(pOracle, this.bean.getParentId());
    }
    
    public void testGetParentId(){
        assertEquals(1, this.bean.getParentId());
    }
    
    private void testGetSickness(String pOracle){
        assertEquals(pOracle, this.bean.getSickness());
    }
    
    public void testGetSickness(){
        assertEquals("Sickness", this.bean.getSickness());
    }
    
    private void testGetSurname(String pOracle){
        assertEquals(pOracle, this.bean.getSurname());
    }
    
    public void testGetSurname(){
        assertEquals("Surname", this.bean.getSurname());
    }
    
    private void testGetIsPrivacyStatementSet(String pOracle){
        assertEquals(pOracle, this.bean.getIsPrivacyStatementSet());
    }
    
    public void testGetIsPrivacyStatementSet(){
        assertEquals("Privacy", this.bean.getIsPrivacyStatementSet());
    }
    
    public void testSetId(){
        this.bean.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetName(){
        this.bean.setName("NewName");
        this.testGetName("NewName");
    } 
    
    public void testSetAdditionalNotes(){
        this.bean.setAdditionalNotes("NewName");
        this.testGetAdditionalNotes("NewName");
    } 
    
    public void testSetBirthPlace(){
        this.bean.setBirthPlace("NewName");
        this.testGetBirthPlace("NewName");
    } 
    
    public void testSetCitizenship(){
        this.bean.setCitizenship("NewName");
        this.testGetCitizenship("NewName");
    }
    
    public void testSetFiscalCode(){
        this.bean.setFiscalCode("OLHTGB94F04R647I");
        this.testGetFiscalCode("OLHTGB94F04R647I");
    }
    
    public void testSetIsPrivacyStatementSet(){
        this.bean.setIsPrivacyStatementSet("New");
        this.testGetIsPrivacyStatementSet("New");
    }
    
    public void testSetIsVaccinationsSet(){
        this.bean.setIsVaccinationsSet("New");
        this.testGetIsVaccinationsSet("New");
    }
    
    public void testSetPrivacyStatement(){
        this.bean.setPrivacyStatement("New");
        this.testGetIsPrivacyStatementSet("New");
    }
}