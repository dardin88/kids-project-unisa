package it.unisa.kids.serviceManagement.tests.serviceTimes;

import it.unisa.kids.serviceManagement.tests.traineeManagement.JDBCTrainingManagerTest;
import it.unisa.kids.serviceManagement.timeServiceManagement.JDBCTimeServiceManager;
import it.unisa.kids.serviceManagement.timeServiceManagement.TimeServiceRequest;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCServiceTimeManagerTest extends TestCase {
    private TimeServiceRequest bean;
    private JDBCTimeServiceManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new TimeServiceRequest();
        this.bean.setId(1);
        this.bean.setParentId(1);
        this.bean.setDayRequested("Day");
        this.bean.setServiceType("Type");
        this.managerTest=JDBCTimeServiceManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<TimeServiceRequest> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCTimeServiceManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        TimeServiceRequest test=new TimeServiceRequest();
        test.setId(1);
        test.setParentId(1);
        test.setDayRequested("Day");
        test.setServiceType("Type");
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", "Type", this.managerTest.search(test).get(0).getServiceType());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        TimeServiceRequest toSearch;
        
        try {
            List<TimeServiceRequest> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setServiceType("NewType");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewType", this.managerTest.search(toSearch).get(0).getServiceType());
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
