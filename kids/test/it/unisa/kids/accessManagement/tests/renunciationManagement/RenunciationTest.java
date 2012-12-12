package it.unisa.kids.accessManagement.tests.renunciationManagement;

import it.unisa.kids.accessManagement.renunciationManagement.Renunciation;
import junit.framework.TestCase;

public class RenunciationTest extends TestCase {
    private Renunciation renunciation;
    
    public RenunciationTest(){}
    
     @Override
    public void setUp(){
        this.renunciation=new Renunciation();
        this.renunciation.setIsConfirmed(1);
        this.renunciation.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.renunciation.setIsConfirmed(1);
        this.renunciation.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.renunciation.getId());
    }
    
    public void testGetId(){
        assertEquals("1", this.renunciation.getId());
    }
    
    public void testSetId(){
        this.renunciation.setId(2);
        this.testGetId(2);
    } 
    
    public void testGetConfirmation(){
        assertEquals(1, this.renunciation.getIsConfirmed());
    }
    
    private void testGetConfirmation(int pOracle){
        assertEquals(1, this.renunciation.getIsConfirmed());
    }
    
    public void testSetCharge(){
        this.renunciation.setIsConfirmed(1);
        this.testGetConfirmation(1);
    }
}