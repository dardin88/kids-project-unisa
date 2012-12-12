
package it.unisa.kids.serviceManagement.tests.traineeManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager;
import it.unisa.kids.serviceManagement.trainingManagement.JDBCTrainingManager;
import it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author 
 */
public class JDBCTrainingManagerTest extends TestCase {

    private ITrainingManager managerTest = JDBCTrainingManager.getInstance();
    private Account traineeTest;
    private TraineeActivity activityTest;

    @Override
    public void setUp() {
        Account trainee = new Account();
        trainee.setNameUser("Test");
        trainee.setSurnameUser("Test");
        traineeTest=trainee;
        
       /* TraineeActivity activity=new TraineeActivity();
        activity.setName("activity test");
        activity.setTrainee(this.traineeTest.getId());
        this.activityTest=activity;*/
        
        try {
            this.managerTest.insert(trainee);
        } catch (Exception ex) {
            //Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<Account> list = this.managerTest.search(traineeTest);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCTrainingManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        Account test=new Account();
        test.setNameUser("NewTest");
        test.setSurnameUser("NewTest");
        try {
            this.managerTest.insert(test);
            
            try {
                assertEquals("insert() does not work!", "NewTest", this.managerTest.search(test).get(0).getNameUser());
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
        Account toSearch;
        
        try {
            List<Account> list=this.managerTest.search(traineeTest);
            toSearch=list.get(0);
            list.get(0).setNameUser("Test");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "Test", this.managerTest.search(toSearch).get(0).getNameUser());
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(traineeTest);
            
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.search(traineeTest).size());
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }

    /*public void testInsertActivity() {
        TraineeActivity activity=new TraineeActivity();
        activity.setName("activity test");
        activity.setDate(new GregorianCalendar(1, 1, 1));
        activity.setTrainee(this.traineeTest.getId());
        try {
            this.managerTest.insert(activityTest);
            
            try {
                assertEquals("insert() does not work!", "activity test", this.managerTest.search(activityTest).get(0).getName());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(activityTest);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
