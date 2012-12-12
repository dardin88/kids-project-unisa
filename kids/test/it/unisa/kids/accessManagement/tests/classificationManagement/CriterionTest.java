package it.unisa.kids.accessManagement.tests.classificationManagement;

import it.unisa.kids.accessManagement.classificationManagement.Criterion;
import junit.framework.TestCase;

public class CriterionTest extends TestCase {
    private Criterion criterion;
    
    @Override
    public void setUp(){
        this.criterion=new Criterion();
        this.criterion.setDescription("Descr");
        this.criterion.setWeight(0.0);
        this.criterion.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.criterion.setDescription("Descr");
        this.criterion.setWeight(0.0);
        this.criterion.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.criterion.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.criterion.getId());
    }
    
    private void testGetDescription(String pOracle){
        assertEquals(pOracle, this.criterion.getDescription());
    }
    
    public void testGetDescription(){
        assertEquals("Desc", this.criterion.getDescription());
    }
    
     private void testGetWeight(double pOracle){
        assertEquals(pOracle, this.criterion.getWeight());
    }
    
    public void testGetWeight(){
        assertEquals(0.0, this.criterion.getWeight());
    }
    
    public void testSetId(){
        this.criterion.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetName(){
        this.criterion.setDescription("NewDescr");
        this.testGetDescription("NewDescr");
    } 
 
     public void testSetWeight(){
        this.criterion.setWeight(1.0);
        this.testGetWeight(1.0);
    } 
}