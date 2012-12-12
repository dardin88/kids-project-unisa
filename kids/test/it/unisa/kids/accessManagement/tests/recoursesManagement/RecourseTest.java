package it.unisa.kids.accessManagement.tests.recoursesManagement;

import it.unisa.kids.accessManagement.recoursesManagement.Recourse;
import junit.framework.TestCase;

public class RecourseTest extends TestCase {
    private Recourse recourse;
    
    public RecourseTest(){}
    
    @Override
    public void setUp(){
        this.recourse=new Recourse();
        this.recourse.setReason("Reason");
        this.recourse.setId_registration(1);
        this.recourse.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.recourse.setReason("Reason");
        this.recourse.setId_registration(1);
        this.recourse.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.recourse.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.recourse.getId());
    }
   
     private void testGetRegistrationId(int pOracle){
        assertEquals(pOracle, this.recourse.getId_registration());
    }
    
    public void testGetRegistrationId(){
        assertEquals(1, this.recourse.getId_registration());
    }
   
    private void testGetReason(String pOracle){
        assertEquals(pOracle, this.recourse.getReason());
    }
    
    public void testGetReason(){
        assertEquals("Reason", this.recourse.getReason());
    }
    
    public void testSetId(){
        this.recourse.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetRegistrationId(){
        this.recourse.setId_registration(2);
        this.testGetRegistrationId(2);
    }
    
    public void testSetName(){
        this.recourse.setReason("NewReason");
        this.testGetReason("NewReason");
    } 
}