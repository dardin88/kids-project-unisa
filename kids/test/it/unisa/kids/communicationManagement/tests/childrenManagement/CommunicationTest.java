package it.unisa.kids.communicationManagement.tests.childrenManagement;

import it.unisa.kids.communicationManagement.childrenManagement.Communication;
import junit.framework.TestCase;

public class CommunicationTest extends TestCase {
    private Communication communication;
    
    public CommunicationTest(){}
    
     @Override
    public void setUp(){
        this.communication=new Communication();
        this.communication.setIdChild(1);
        this.communication.setSolved(true);
        this.communication.setDescription("Description");
        this.communication.setType(1);
        this.communication.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.communication.setIdChild(1);
        this.communication.setSolved(true);
        this.communication.setDescription("Description");
        this.communication.setType(1);
        this.communication.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.communication.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.communication.getId());
    }

    private void testGetChildId(int pOracle){
        assertEquals(pOracle, this.communication.getIdChild());
    }
    
    public void testGetChildId(){
        assertEquals(1, this.communication.getIdChild());
    }
    
    private void testIsSolved(boolean pOracle){
        assertEquals(pOracle, this.communication.getSolved());
    }
    
    public void testIsSolved(){
        assertEquals(true, this.communication.getSolved());
    }
    
    public void testGetDescription(){
        assertEquals(true, this.communication.getDescription());
    }
    
    private void testGetDescription(String pOracle){
        assertEquals(pOracle, this.communication.getDescription());
    }
    
    public void testSetId(){
        this.communication.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetChildId(){
        this.communication.setIdChild(2);
        this.testGetChildId(2);
    } 
    
    public void testSetSolved(){
        this.communication.setSolved(false);
        this.testIsSolved(false);
    } 
    
    public void testSetDescription(){
        this.communication.setDescription("New");
        this.testGetDescription("New");
    }
}