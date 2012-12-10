package it.unisa.kids.communicationManagement.tests.meeting;

import it.unisa.kids.communicationManagement.meeting.ErroreNeiDati;
import it.unisa.kids.communicationManagement.meeting.JDBCReunionManager;
import it.unisa.kids.communicationManagement.meeting.Reunion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCReunionManagerTest extends TestCase {

    private Reunion bean;
    private JDBCReunionManager managerTest;

    public void setUp() {
        this.bean = new Reunion();
        this.bean.setId(1);
        this.bean.setDate("Date");
        this.bean.setDescription("Desscription");
        this.bean.setFirstTime("FirstTime");
        this.bean.setSecondTime("SecondTime");
        this.bean.setTitle("Title");
        this.bean.setType("Type");
        this.managerTest = JDBCReunionManager.getInstance();

        try {
            try {
                this.managerTest.insert(bean);
            } catch (ErroreNeiDati ex) {
                Logger.getLogger(JDBCReunionManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tearDown() {
        try {
            List<Reunion> list = this.managerTest.getMeetingList();
            int index = list.indexOf(this.bean);
            try {
                this.managerTest.delete(list.get(index));
            } catch (IndexOutOfBoundsException iob) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCReunionManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getInstanceTest() {
        assertEquals("getInstance() does not work!", JDBCReunionManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        Reunion test = new Reunion();
        test.setId(1);
        test.setDate("Date");
        test.setDescription("Desscription");
        test.setFirstTime("FirstTime");
        test.setSecondTime("SecondTime");
        test.setTitle("Title");
        test.setType("Type");

        try {
            try {
                this.managerTest.insert(test);
            } catch (ErroreNeiDati ex) {
                Logger.getLogger(JDBCReunionManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                assertEquals("insert() does not work!", "Title", this.managerTest.getMeetingList().get(this.managerTest.getMeetingList().indexOf(this.bean)).getTitle());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCReunionManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCReunionManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        Reunion toSearch;

        try {
            List<Reunion> list = this.managerTest.getMeetingList();
            int index = list.indexOf(this.bean);
            list.get(index).setTitle("NewTile");
            this.managerTest.update(list.get(index));

            try {
                assertEquals("update() does not work!", "NewTitle", this.managerTest.getMeetingList().get(this.managerTest.getMeetingList().indexOf(this.bean)).getTitle());
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(this.bean);

            try {
                assertEquals("delete() does not work!", null, this.managerTest.getMeetingList().get(this.managerTest.getMeetingList().indexOf(this.bean)));
            } catch (Exception e) {
            }
        } catch (Exception ex) {
        }
    }
}
