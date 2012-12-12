package it.unisa.kids.communicationManagement.tests.newsManagement;

import it.unisa.kids.communicationManagement.newsManagement.INewsManager;
import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.kids.communicationManagement.newsManagement.News;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCNewsManagerTest extends TestCase {
    private News bean;
    private INewsManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new News();
        this.bean.setId(1);
        this.bean.setAttached("Att");
        this.bean.setDelegate(1);
        this.bean.setDescription("Description");
        this.bean.setTitle("TitleTest");
        this.managerTest=JDBCNewsManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<News> list = this.managerTest.search("TitleTest");
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNewsManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCNewsManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        News test=new News();
        test.setId(1);
        test.setAttached("Att");
        test.setDelegate(1);
        test.setDescription("Description");
        test.setTitle("NewTitleTest");
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", "NewTitleTest", this.managerTest.search("NewTitleNews").get(0).getTitle());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNewsManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNewsManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        News toSearch;
        
        try {
            List<News> list=this.managerTest.search("TitleTest");
            toSearch=list.get(0);
            list.get(0).setTitle("NewTitleTest");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewTitleTest", this.managerTest.search("NewTitleTest").get(0).getTitle());
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(this.bean);
            
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.search("NewTitleTest").size());
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }   
}