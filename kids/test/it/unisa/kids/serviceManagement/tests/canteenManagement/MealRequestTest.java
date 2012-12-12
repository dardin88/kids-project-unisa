package it.unisa.kids.serviceManagement.tests.canteenManagement;

import it.unisa.kids.serviceManagement.canteenManagement.MealRequestBean;
import junit.framework.TestCase;

public class MealRequestTest extends TestCase {
    private MealRequestBean bean;
    
    public MealRequestTest(){}
    
    @Override
    public void setUp(){
        this.bean=new MealRequestBean();
        this.bean.setParentId(1);
        this.bean.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.bean.setParentId(1);
        this.bean.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.bean.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.bean.getId());
    }
    
    private void testGetParentId(int pOracle){
        assertEquals(pOracle, this.bean.getParentId());
    }
    
    public void testGetParentId(){
        assertEquals(1, this.bean.getParentId());
    }
    
    public void testSetId(){
        this.bean.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetParentId(){
        this.bean.setId(2);
        this.testGetId(2);
    } 
}