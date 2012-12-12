package it.unisa.kids.accessManagement.tests.classManagement;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.classManagement.JDBCClassManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCClassManagerTest extends TestCase {
    private ClassBean bean;
    private JDBCClassManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new ClassBean();
        this.bean.setClassName("Name");
        this.bean.setIdClasse(1);
        this.bean.setState("State");
        
        this.managerTest=JDBCClassManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<ClassBean> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCClassManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        ClassBean test = new ClassBean();
        test.setClassName("NewName");
        test.setIdClasse(1);
        test.setState("State");
        
        try {
            this.managerTest.insert(test); 
            try {
                assertEquals("insert() does not work!", "NewName", this.managerTest.search(test).get(0).getClassName());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        ClassBean toSearch;
        try {
            List<ClassBean> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setClassName("NewNameOf");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewNameOf", this.managerTest.search(toSearch).get(0).getClassName());                
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(this.bean);
        
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.search(this.bean).size());
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }   
}