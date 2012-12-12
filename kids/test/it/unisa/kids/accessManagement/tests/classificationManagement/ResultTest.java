package it.unisa.kids.accessManagement.tests.classificationManagement;

import it.unisa.kids.accessManagement.classificationManagement.Result;
import it.unisa.kids.accessManagement.classificationManagement.Result;
import junit.framework.TestCase;

public class ResultTest extends TestCase {
    private Result result;
    
    @Override
    public void setUp(){
        this.result=new Result();
        this.result.setRegistrationChildId(1);
        this.result.setClassificationId(1);
        this.result.setResult(true);
    }
    
    @Override
    public void tearDown(){
        this.result.setRegistrationChildId(1);
        this.result.setClassificationId(1);
        this.result.setResult(true);
    }
    
    private void testGetClassificationId(int pOracle){
        assertEquals(pOracle, this.result.getClassificationId());
    }
    
    public void testGetClassificationId(){
        assertEquals(1, this.result.getClassificationId());
    }
    
    private void testGetRegistrationId(int pOracle){
        assertEquals(pOracle, this.result.getClassificationId());
    }
    
    public void testGetRegistrationId(){
        assertEquals(1, this.result.getRegistrationChildId());
    }
    
    private void testGetResult(boolean pOracle){
        assertEquals(pOracle, this.result.getResult());
    }
    
    public void testGetResult(){
        assertEquals(true, this.result.getResult());
    }
    
    public void testSetClassificationId(){
        this.result.setClassificationId(2);
        this.testGetClassificationId(2);
    } 
    
    public void testSetRegistrationId(){
        this.result.setRegistrationChildId(2);
        this.testGetRegistrationId(2);
    } 
    
    public void testSetResult(){
        this.result.setResult(false);
        this.testGetResult(false);
    } 
}