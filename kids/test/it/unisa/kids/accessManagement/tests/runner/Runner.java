package it.unisa.kids.accessManagement.tests.runner;

import it.unisa.kids.accessManagement.tests.recoursesManagement.JDBCRecoursesManagerTest;
import junit.framework.TestSuite;

public class Runner {
    public static TestSuite suite() { 
         Class[] classes = new Class[1];
         classes[0]=JDBCRecoursesManagerTest.class;
         
         TestSuite suite= new TestSuite(classes);
         return suite;
     }
                       
     
     public static void main(String [] args) throws Exception{
        junit.textui.TestRunner.run(suite());
   }
}
