package it.unisa.kids.accessManagement.tests.recoursesManagement;

import it.unisa.kids.accessManagement.recoursesManagement.JDBCRecoursesManager;
import it.unisa.kids.accessManagement.recoursesManagement.Recourse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCRecoursesManagerTest extends TestCase {
    private Recourse bean;
    private JDBCRecoursesManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new Recourse();
        this.bean.setId(1);
        this.bean.setRegistrationChildId(1);
        this.bean.setReason("ReasonTest");
        this.bean.setValutation(true);
        this.managerTest=JDBCRecoursesManager.getInstace();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<Recourse> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRecoursesManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCRecoursesManager.getInstace(), this.managerTest);
    }

    public void testInsert() {
        Recourse test=new Recourse();
        test.setId(2);
        test.setRegistrationChildId(3);
        test.setReason("NewReasonTest");
        test.setValutation(false);
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", false, this.managerTest.search(test).get(0).getValutation());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRecoursesManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRecoursesManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        Recourse toSearch;
        
        try {
            List<Recourse> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setReason("NewReason");
            this.managerTest.modify(list.get(0));

            try {
                assertEquals("update() does not work!", "NewReason", this.managerTest.search(toSearch).get(0).getReason());
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