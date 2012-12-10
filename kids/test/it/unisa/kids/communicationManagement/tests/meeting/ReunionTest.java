package it.unisa.kids.communicationManagement.tests.meeting;

import it.unisa.kids.communicationManagement.meeting.Reunion;
import junit.framework.TestCase;

public class ReunionTest extends TestCase {
    private Reunion reunion;
    
    public ReunionTest(){}
    
        public void setUp(){
        this.reunion=new Reunion();
        this.reunion.setDescription("Description");
        this.reunion.setId(1);
        this.reunion.setTitle("Title");
        this.reunion.setType("Type");
    }
    
       public void tearDown(){
        this.reunion.setDescription("Description");
        this.reunion.setId(1);
        this.reunion.setTitle("Title");
        this.reunion.setType("Type");
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.reunion.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.reunion.getId());
    }
    
    public void testGetDescription(){
        assertEquals("Description", this.reunion.getDescription());
    }
    
    private void testGetDescription(String pOracle){
        assertEquals(pOracle, this.reunion.getDescription());
    }
    
    public void testGetTitle(){
        assertEquals("Title", this.reunion.getTitle());
    }
    
    private void testGetTitle(String pOracle){
        assertEquals(pOracle, this.reunion.getTitle());
    }
    
    public void testGetType(){
        assertEquals("Type", this.reunion.getType());
    }
    
    private void testGetType(String pOracle){
        assertEquals(pOracle, this.reunion.getType());
    }
    
    public void testSetId(){
        this.reunion.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetDescription(){
        this.reunion.setDescription("New");
        this.testGetDescription("New");
    }
    
    public void testSetTitle(){
        this.reunion.setTitle("New");
        this.testGetTitle("New");
    }
    
    public void testSetType(){
        this.reunion.setType("New");
        this.testGetType("New");
    }
}