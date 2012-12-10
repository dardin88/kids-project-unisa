package it.unisa.kids.communicationManagement.tests.childrenManagement;

import it.unisa.kids.communicationManagement.childrenManagement.Communication;
import it.unisa.kids.communicationManagement.childrenManagement.ICommunicationManager;
import it.unisa.kids.communicationManagement.childrenManagement.JDBCCommunicationManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCCommunicationManagerTest extends TestCase {
    private Communication bean;
    private ICommunicationManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new Communication();
        this.bean.setId(1);
        this.bean.setDescription("DescriptionTest");
        this.bean.setIdChild(1);
        this.bean.setIdEducator(1);
        this.bean.setSolved(true);
        this.bean.setType(1);
        this.managerTest=JDBCCommunicationManager.getInstance();
        
        try {
            this.managerTest.insertCommunication(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<Communication> list = this.managerTest.searchCommunication("DescriptionTest");
            try {
            this.managerTest.deleteCommunication(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCCommunicationManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        Communication test=new Communication();
        test.setId(1);
        test.setDescription("NewDescriptionTest");
        test.setIdChild(1);
        test.setIdEducator(1);
        test.setSolved(true);
        test.setType(1);
        
        try {
            this.managerTest.insertCommunication(test);            
            try {
                assertEquals("insert() does not work!", "NewDescriptionTest", this.managerTest.searchCommunication("NewDescriptionTest").get(0).getDescription());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommunicationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.deleteCommunication(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommunicationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        Communication toSearch;
        
        try {
            List<Communication> list=this.managerTest.searchCommunication("DescriptionTest");
            toSearch=list.get(0);
            list.get(0).setDescription("NewDescriptionTest");
            this.managerTest.modifyCommunication(list.get(0));

            try {
                assertEquals("update() does not work!", "NewDescriptionTest", this.managerTest.searchCommunication("NewDescriptionTest").get(0).getDescription());
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.deleteCommunication(this.bean);
            
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.searchCommunication("DescriptionTest").size());
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }   
}