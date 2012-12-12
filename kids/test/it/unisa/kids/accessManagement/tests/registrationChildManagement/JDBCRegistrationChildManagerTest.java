package it.unisa.kids.accessManagement.tests.registrationChildManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCRegistrationChildManagerTest extends TestCase {
    private RegistrationChild bean;
    private JDBCRegistrationChildManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new RegistrationChild();
        this.bean.setName("Name");
        this.bean.setAdditionalNotes("Add");
        this.bean.setBirthPlace("Place");
        this.bean.setCitizenship("Cit");
        this.bean.setFiscalCode("OLHTGB91F04R647I");
        this.bean.setIsVaccinationsSet("Vacc");
        this.bean.setIsSicknessSet("Sick");
        this.bean.setParentId(1);
        this.bean.setSickness("Sickness");
        this.bean.setSurname("Surname");
        this.bean.setIsPrivacyStatementSet("Privacy");
        this.bean.setId(1);
        this.managerTest=JDBCRegistrationChildManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<RegistrationChild> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRegistrationChildManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCRegistrationChildManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        RegistrationChild test=new RegistrationChild();
        test.setId(2);
        test.setName("Name");
        test.setAdditionalNotes("Add");
        test.setBirthPlace("Place");
        test.setCitizenship("Cit");
        test.setFiscalCode("OLHTGB91F04R647I");
        test.setIsVaccinationsSet("Vacc");
        test.setIsSicknessSet("Sick");
        test.setParentId(1);
        test.setSickness("Sickness");
        test.setSurname("Surname");
        test.setIsPrivacyStatementSet("Privacy");
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", 2, this.managerTest.search(test).get(0).getId());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRegistrationChildManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRegistrationChildManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        RegistrationChild toSearch;
        
        try {
            List<RegistrationChild> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setAdditionalNotes("NewAdd");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewAdd", this.managerTest.search(toSearch).get(0).getAdditionalNotes());
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