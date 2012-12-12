package it.unisa.kids.accessManagement.tests.classManagement;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import junit.framework.TestCase;

public class ClassBeanTest extends TestCase {
    private ClassBean clazz;
    
    public ClassBeanTest(){}
    
    @Override
    public void setUp(){
        this.clazz=new ClassBean();
        this.clazz.setClassName("Name");
        this.clazz.setIdClasse(1);
        this.clazz.setState("State");
    }
    
    @Override
    public void tearDown(){
        this.clazz.setClassName("Name");
        this.clazz.setIdClasse(1);
        this.clazz.setState("State");
    }
    
    private void testGetClassId(int pOracle){
        assertEquals(pOracle, this.clazz.getIdClasse());
    }
    
    public void testGetClassId(){
        assertEquals(1, this.clazz.getIdClasse());
    }
    
    private void testGetClassName(String pOracle){
        assertEquals(pOracle, this.clazz.getClassName());
    }
    
    public void testGetClassName(){
        assertEquals("Name", this.clazz.getClassName());
    }
    
    private void testGetState(String pOracle){
        assertEquals(pOracle, this.clazz.getState());
    }
    
    public void testGetState(){
        assertEquals("State", this.clazz.getState());
    }
    
    public void testSetClassId(){
        this.clazz.setIdClasse(2);
        this.testGetClassId(2);
    } 
    
    public void testSetName(){
        this.clazz.setClassName("NewName");
        this.testGetClassName("NewName");
    } 
    
    public void testSetStatus(){
        this.clazz.setState("NewStatus");
        this.testGetState("NewStatus");
    } 
}