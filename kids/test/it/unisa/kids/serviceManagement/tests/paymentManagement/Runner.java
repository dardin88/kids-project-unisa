/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.tests.paymentManagement;


import junit.framework.TestSuite;

/**
 *
 * @author Gilda
 */
public class Runner {
    public static TestSuite suite() { 
         Class[] classes = new Class[1];
         
         
         TestSuite suite= new TestSuite(classes);
         return suite;
     }
                       
     
     public static void main(String [] args) throws Exception{
        junit.textui.TestRunner.run(suite());
   }
}
