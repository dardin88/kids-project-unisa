package it.unisa.kids.serviceManagement.tests.serviceTimes;

import it.unisa.kids.serviceManagement.timeServiceManagement.TimeServiceRequest;
import junit.framework.TestCase;

public class ServiceTimeRequestBeanTest extends TestCase {
    private TimeServiceRequest timeService;
    
    public ServiceTimeRequestBeanTest(){}
    
    @Override
    public void setUp() {
        this.timeService=new TimeServiceRequest();
        this.timeService.setId(1);
        this.timeService.setDayRequested("Day");
        this.timeService.setParentId(1);
        this.timeService.setServiceType("Type");
    }
    
    @Override
    public void tearDown(){
        this.timeService.setId(1);
        this.timeService.setDayRequested("Day");
        this.timeService.setParentId(1);
        this.timeService.setServiceType("Type");
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.timeService.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.timeService.getId());
    }
    
    private void testGetParentId(int pOracle){
        assertEquals(pOracle, this.timeService.getParentId());
    }

    public void testGetParentId(){
        assertEquals(1, this.timeService.getParentId());
    }
    
    private void testGetDayRequested(String pOracle){
        assertEquals(pOracle, this.timeService.getDayRequested());
    }

    public void testGetDayRequested(){
        assertEquals("Day", this.timeService.getDayRequested());
    }
    
    private void testGetServiceType(String pOracle){
        assertEquals(pOracle, this.timeService.getServiceType());
    }

    public void testGetServiceType(){
        assertEquals("Type", this.timeService.getServiceType());
    }
    
    public void testSetId(){
        this.timeService.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetParentId(){
        this.timeService.setParentId(2);
        this.testGetParentId(2);
    } 
    
    public void testSetDayRequested(){
        this.timeService.setDayRequested("NewDay");
        this.testGetDayRequested("NewDay");
    }
    
    public void testSetServiceType(){
        this.timeService.setServiceType("New");
        this.testGetServiceType("New");
    }
}