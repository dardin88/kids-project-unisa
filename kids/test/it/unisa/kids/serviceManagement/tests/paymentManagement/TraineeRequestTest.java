package it.unisa.kids.serviceManagement.tests.paymentManagement;

import it.unisa.kids.serviceManagement.trainingManagement.TraineeRequest;
import junit.framework.TestCase;

public class TraineeRequestTest extends TestCase {
    private TraineeRequest request;
    
    public TraineeRequestTest(){}
    
    @Override
    public void setUp(){
        this.request=new TraineeRequest();
        this.request.setActivity("TestActivity");
        this.request.setDelegate(1);
        this.request.setTraineeNumber(1);
        this.request.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.request.setActivity("TestActivity");
        this.request.setDelegate(1);
        this.request.setTraineeNumber(1);
        this.request.setId(1);
    }
    
    public void testGetDelegate(int pOracle){
        assertEquals(pOracle, this.request.getDelegate());
    }
    
    public void testGetTraineeNumber(int pOracle){
        assertEquals(pOracle, this.request.getTraineeNumber());
    }
    
    public void testGetId(int pOracle){
        assertEquals(pOracle, this.request.getId());
    }
    
    public void testGetActivity(String pOracle){
        assertEquals(pOracle, this.request.getActivity());
    }
    
    public void testSetDelegate(){
        this.request.setDelegate(2);
        this.testGetDelegate(2);
    }
    
    public void testSetId(){
        this.request.setId(2);
        this.testGetId(2);
    }
    
    public void testSetTraineeNumber(){
        this.request.setTraineeNumber(2);
        this.testGetTraineeNumber(2);
    }
    
    public void testSetActivity(){
        this.request.setActivity("NewActivity");
        this.testGetActivity("NewActivity");
    }
}