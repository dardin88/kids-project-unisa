
package it.unisa.kids.accessManagement.tests.classificationManagement;

import it.unisa.kids.accessManagement.classificationManagement.Classification;
import junit.framework.TestCase;

public class ClassificationTest extends TestCase {
    private Classification classification;
    
    public ClassificationTest(){}
    
    @Override
    public void setUp(){
        this.classification=new Classification();
        this.classification.setName("Name");
        this.classification.setStatus("Status");
        this.classification.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.classification.setName("Name");
        this.classification.setStatus("Status");
        this.classification.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.classification.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.classification.getId());
    }
    
    private void testGetName(String pOracle){
        assertEquals(pOracle, this.classification.getName());
    }
    
    public void testGetName(){
        assertEquals("Name", this.classification.getName());
    }
    
    private void testGetStatus(String pOracle){
        assertEquals(pOracle, this.classification.getStatus());
    }
    
    public void testGetStatus(){
        assertEquals("Status", this.classification.getStatus());
    }
    
    public void testSetId(){
        this.classification.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetName(){
        this.classification.setName("NewName");
        this.testGetName("NewName");
    } 
    
    public void testSetStatus(){
        this.classification.setStatus("NewStatus");
        this.testGetStatus("NewStatus");
    } 
}
