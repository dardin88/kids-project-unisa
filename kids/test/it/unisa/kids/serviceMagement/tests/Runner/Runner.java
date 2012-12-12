package it.unisa.kids.serviceMagement.tests.Runner;

import it.unisa.kids.serviceManagement.tests.paymentManagement.PaymentBeanTest;
import it.unisa.kids.serviceManagement.tests.paymentManagement.RefundBeanTest;
import it.unisa.kids.serviceManagement.tests.serviceTimes.JDBCServiceTimeManagerTest;
import it.unisa.kids.serviceManagement.tests.serviceTimes.ServiceTimeRequestBeanTest;
import it.unisa.kids.serviceManagement.tests.traineeManagement.JDBCTrainingManagerTest;
import it.unisa.kids.serviceManagement.tests.traineeManagement.TraineeActivityTest;
import it.unisa.kids.serviceManagement.tests.traineeManagement.TraineeConvocationTest;
import it.unisa.kids.serviceManagement.tests.traineeManagement.TraineeRequestTest;
import junit.framework.TestSuite;

public class Runner {
    public static TestSuite suite() { 
         Class[] classes = new Class[6];
         //classes[0]=JDBCTrainingManagerTest.class;
         //classes[1]=JDBCServiceTimeManagerTest.class;
         classes[0]=TraineeActivityTest.class;
         classes[1]=TraineeConvocationTest.class;
         classes[2]=TraineeRequestTest.class;
         classes[3]=ServiceTimeRequestBeanTest.class;
         classes[4]=PaymentBeanTest.class;
         classes[5]=RefundBeanTest.class;
         
         TestSuite suite= new TestSuite(classes);
         return suite;
     }
                       
     
     public static void main(String [] args) throws Exception{
        junit.textui.TestRunner.run(suite());
   }
}
