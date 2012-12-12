package it.unisa.kids.serviceManagement.tests.canteenManagement;

import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import junit.framework.TestCase;

public class MenuBeanTest extends TestCase {
    private MenuBean bean;
    
    public MenuBeanTest(){}
    
    @Override
    public void setUp(){
        this.bean=new MenuBean();
        this.bean.setFirst("First");
        this.bean.setFruit("Fruit");
        this.bean.setSecond("Second");
        this.bean.setSideDish("Side");
        this.bean.setType("Type");
        this.bean.setId(1);
    }
    
    @Override
    public void tearDown(){
        this.bean.setFirst("First");
        this.bean.setFruit("Fruit");
        this.bean.setSecond("Second");
        this.bean.setSideDish("Side");
        this.bean.setType("Type");
        this.bean.setId(1);
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.bean.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.bean.getId());
    }
    
    public void testGetFirst(){
        assertEquals("First", this.bean.getFirst());
    }
    
    public void testGetFirst(String pOracle){
        assertEquals(pOracle, this.bean.getFirst());
    }
    
    public void testGetFruit(){
        assertEquals("Fruit", this.bean.getFruit());
    }
    
    public void testGetFruit(String pOracle){
        assertEquals(pOracle, this.bean.getFruit());
    }
    
    public void testGetSecond(){
        assertEquals("Second", this.bean.getSecond());
    }
    
    public void testGetSecond(String pOracle){
        assertEquals(pOracle, this.bean.getSecond());
    }
    
    public void testGetSideDish(){
        assertEquals("Side", this.bean.getSideDish());
    }
    
    public void testGetSideDish(String pOracle){
        assertEquals(pOracle, this.bean.getSideDish());
    }
    
    public void testGetType(){
        assertEquals("Type", this.bean.getType());
    }
    
    public void testGetType(String pOracle){
        assertEquals(pOracle, this.bean.getType());
    }
    
    public void testSetId(){
        this.bean.setId(2);
        this.testGetId(2);
    }
    
    public void testSetFirst(){
        this.bean.setFirst("First");
        this.testGetFirst("First");
    }
    
    public void testSetSecond(){
        this.bean.setSecond("Second");
        this.testGetSecond("Second");
    }
    
    public void testSetFruit(){
        this.bean.setFruit("Fruit");
        this.testGetFruit("Fruit");
    }
            
    public void testSetSideDish(){
        this.bean.setSideDish("Side");
        this.testGetSideDish("Side");
    }
    
    public void testSetType(){
        this.bean.setSideDish("Type");
        this.testGetType("Type");
    }
}