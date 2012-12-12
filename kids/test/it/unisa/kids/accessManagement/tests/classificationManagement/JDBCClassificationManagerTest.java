package it.unisa.kids.accessManagement.tests.classificationManagement;

import it.unisa.kids.accessManagement.classificationManagement.Classification;
import it.unisa.kids.accessManagement.classificationManagement.Criterion;
import it.unisa.kids.accessManagement.classificationManagement.JDBCClassificationManager;
import it.unisa.kids.accessManagement.classificationManagement.Result;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class JDBCClassificationManagerTest extends TestCase {
    private Classification bean;
    private Criterion criterion;
    private Result result;
    private JDBCClassificationManager managerTest;
    
    @Override
    public void setUp() {
        this.bean = new Classification();
        this.bean.setId(1);
        this.bean.setName("Name");
        this.bean.setStatus("Status");
        
        this.criterion=new Criterion();
        this.criterion.setDescription("Description");
        this.criterion.setWeight(0.0);
        
        this.result=new Result();
        this.result.setClassificationId(1);
        this.result.setRegistrationChildId(1);
        this.result.setResult(true);
        
        this.managerTest=JDBCClassificationManager.getInstance();
        
        try {
            this.managerTest.insert(bean);
            this.managerTest.insertCriterion(criterion);
        } catch (SQLException ex) {
           // Logger.getLogger(JDBCTrainingManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void tearDown() {
        try {
            List<Classification> list = this.managerTest.search(this.bean);
            try {
            this.managerTest.delete(list.get(0));
            } catch(IndexOutOfBoundsException iob){}
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassificationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInstanceTest(){
        assertEquals("getInstance() does not work!", JDBCClassificationManager.getInstance(), this.managerTest);
    }

    public void testInsert() {
        Classification test = new Classification();
        test.setId(2);
        test.setName("NewName");
        test.setStatus("NewStatus");
        
        Criterion criterionTest=new Criterion();
        criterionTest.setDescription("NewDescription");
        criterionTest.setWeight(0.0);
        
        try {
            this.managerTest.insert(test); 
            this.managerTest.insertCriterion(criterionTest);
            try {
                assertEquals("insert() does not work!", "NewName", this.managerTest.search(test).get(0).getName());
                assertEquals("insertCriterion() does not work!", "NewDescription", this.managerTest.getAllCriteria().get(this.managerTest.getAllCriteria().indexOf(criterionTest)).getDescription());
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassificationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.managerTest.delete(test);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClassificationManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdate() {
        Classification toSearch;
        Criterion criterionToSearch;
        try {
            List<Classification> list=this.managerTest.search(this.bean);
            criterionToSearch=this.managerTest.getAllCriteria().get(this.managerTest.getAllCriteria().indexOf(this.criterion));
            toSearch=list.get(0);
            list.get(0).setName("NewNameOf");
            criterionToSearch.setDescription("NewDescriptionOf");
            this.managerTest.update(list.get(0));

            try {
                assertEquals("update() does not work!", "NewNameOf", this.managerTest.search(toSearch).get(0).getName());
                assertEquals("updateCriterion() does not work!", "NewDescriptionOf", this.managerTest.getAllCriteria().get(this.managerTest.getAllCriteria().indexOf(criterionToSearch)).getDescription());
                
            } catch (Exception e) {
            }

        } catch (Exception ex) {
        }
    }

    public void testDelete() {
        try {
            this.managerTest.delete(this.bean);
            this.managerTest.deleteCriterion(this.criterion);
            
            try {
                assertEquals("delete() does not work!", 0, this.managerTest.search(this.bean).size());
                assertEquals("deleteCriterion() does not work!", 0, this.managerTest.getAllCriteria().get(this.managerTest.getAllCriteria().indexOf(this.criterion)));
            } catch (Exception e) {
            }        
        } catch (Exception ex) {}    
    }
}