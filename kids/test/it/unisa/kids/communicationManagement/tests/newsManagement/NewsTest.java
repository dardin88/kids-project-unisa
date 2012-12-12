package it.unisa.kids.communicationManagement.tests.newsManagement;

import it.unisa.kids.communicationManagement.newsManagement.News;
import junit.framework.TestCase;

public class NewsTest extends TestCase {
    private News news;
    
    public NewsTest(){}
    
     @Override
    public void setUp(){
        this.news=new News();
        this.news.setAttached("Att");
        this.news.setDelegate(1);
        this.news.setDescription("Description");
        this.news.setTitle("Title");
        this.news.setType("Type");
    }
    
    @Override
    public void tearDown(){
        this.news.setAttached("Att");
        this.news.setDelegate(1);
        this.news.setDescription("Description");
        this.news.setTitle("Title");
        this.news.setType("Type");
    }
    
    private void testGetId(int pOracle){
        assertEquals(pOracle, this.news.getId());
    }
    
    public void testGetId(){
        assertEquals(1, this.news.getId());
    }
    
    public void testGetDescription(){
        assertEquals(true, this.news.getDescription());
    }
    
    private void testGetDescription(String pOracle){
        assertEquals(pOracle, this.news.getDescription());
    }
    
    public void testGetTitle(){
        assertEquals(true, this.news.getTitle());
    }
    
    private void testGetTitle(String pOracle){
        assertEquals(pOracle, this.news.getTitle());
    }
    
    public void testGetType(){
        assertEquals(true, this.news.getType());
    }
    
    private void testGetType(String pOracle){
        assertEquals(pOracle, this.news.getType());
    }
    
    public void testSetId(){
        this.news.setId(2);
        this.testGetId(2);
    } 
    
    public void testSetDescription(){
        this.news.setDescription("New");
        this.testGetDescription("New");
    }
    
    public void testSetTitle(){
        this.news.setTitle("New");
        this.testGetTitle("New");
    }
    
    public void testSetType(){
        this.news.setType("New");
        this.testGetType("New");
    }
}
