package it.unisa.kids.accessManagement.tests.renunciationManagement;

import it.unisa.kids.accessManagement.renunciationManagement.JDBCRenunciationManager;
import it.unisa.kids.accessManagement.renunciationManagement.Renunciation;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCRenunciationManagerTest extends TestCase {
    private Renunciation bean;
    private JDBCRenunciationManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new Renunciation();
        this.bean.setId(1);
        this.bean.setConferma(1);
        this.managerTest=JDBCRenunciationManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<Renunciation> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRenunciationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCRenunciationManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        Renunciation test=new Renunciation();
        test.setId(2);
        test.setConferma(1);
        
        try {
            this.managerTest.insert(test);            
            try {
                assertEquals("insert() does not work!", 2, this.managerTest.search(test).get(0).getId());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRenunciationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCRenunciationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        Renunciation toSearch;
        
        try {
            List<Renunciation> list=this.managerTest.search(this.bean);
            toSearch=list.get(0);
            list.get(0).setConferma(1);
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", 1, this.managerTest.search(toSearch).get(0).isConferma());
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