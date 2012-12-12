
package it.unisa.kids.serviceManagement.tests.traineeManagement;

import it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity;
import junit.framework.TestCase;

public class TraineeActivityTest extends TestCase {
    private TraineeActivity activity;
    
    public TraineeActivityTest(){}
    
    @Override
    public void setUp(){
        this.activity=new TraineeActivity();
        this.activity.setName("Test");
        this.activity.setDelegate(1);
        this.activity.setDescription("TestDescription");
        this.activity.setOpinion("TestOpinion");
        this.activity.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.activity.setName("Test");
        this.activity.setDelegate(1);
        this.activity.setDescription("TestDescription");
        this.activity.setOpinion("TestOpinion");
        this.activity.setId(1);
    }
    
    public void testGetDelegate(int pOracle){
        assertEquals(pOracle, this.activity.getDelegate());
    }
    
    public void testGetId(int pOracle){
        assertEquals(pOracle, this.activity.getId());
    }
    
    public void testGetName(String pOracle){
        assertEquals(pOracle, this.activity.getName());
    }
    
    public void testGetOpinion(String pOracle){
        assertEquals(pOracle, this.activity.getOpinion());
    }
    
    public void testGetDescription(String pOracle){
        assertEquals(pOracle, this.activity.getDescription());
    }
    
    public void testSetDelegate(){
        this.activity.setDelegate(2);
        this.testGetDelegate(2);
    }
    
    public void testSetId(){
        this.activity.setId(2);
        this.testGetId(2);
    }
    
    public void testSetName(){
        this.activity.setName("NewTest");
        this.testGetName("NewTest");
    }
    
     public void testSetOpinion(){
        this.activity.setOpinion("NewOpinion");
        this.testGetOpinion("NewOpinion");
    }
    
    public void testSetDescription(){
        this.activity.setDescription("NewDescription");
        this.testGetDescription("NewDescription");
    }
}