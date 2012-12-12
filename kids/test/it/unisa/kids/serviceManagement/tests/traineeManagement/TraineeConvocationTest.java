package it.unisa.kids.serviceManagement.tests.traineeManagement;

import it.unisa.kids.serviceManagement.trainingManagement.TraineeConvocation;
import junit.framework.TestCase;

public class TraineeConvocationTest extends TestCase {
    private TraineeConvocation convocation;
    
    public TraineeConvocationTest(){}
    
    @Override
    public void setUp(){
        this.convocation=new TraineeConvocation();
        this.convocation.setActivityName("ActivityName");
        this.convocation.setConfirmed(1);
        this.convocation.setDelegateId(1);
        this.convocation.setTraineeId(1);
        this.convocation.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.convocation.setActivityName("ActivityName");
        this.convocation.setConfirmed(1);
        this.convocation.setDelegateId(1);
        this.convocation.setTraineeId(1);
        this.convocation.setId(1);
    }
    
    public void testGetId(int pOracle){
        assertEquals(pOracle, this.convocation.getId());
    }
    
    public void testGetConfirmed(int pOracle){
        assertEquals(pOracle, this.convocation.getConfirmed());
    }
    
    public void testGetDelegateId(int pOracle){
        assertEquals(pOracle, this.convocation.getDelegateId());
    }
    
    public void testGetTraineeId(int pOracle){
        assertEquals(pOracle, this.convocation.getTraineeId());
    }
    
     public void testGetActivityName(String pOracle){
        assertEquals(pOracle, this.convocation.getActivityName());
    }
      
    public void testSetId(){
        this.convocation.setId(2);
        this.testGetId(2);
    }    
    
    public void testSetConfirmed(){
        this.convocation.setConfirmed(2);
        this.testGetConfirmed(2);
    }   
    
    public void testSetDelegateId(){
        this.convocation.setDelegateId(2);
        this.testGetDelegateId(2);
    }
    
    public void testSetTraineeId(){
        this.convocation.setTraineeId(2);
        this.testGetTraineeId(2);
    }
    
    public void testSetActivityName(){
        this.convocation.setActivityName("NewActivityName");
        this.testGetActivityName("NewActivityName");
    }
}