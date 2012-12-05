/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giuseppe Alfieri
 */
public class Test {
    
    public static void main(String args[]){
        IProgramEducational manager=JDBCProgramEducational.getInstance();
        CommentoBean toAdd=new CommentoBean();
        toAdd.setTipoModifica("Lieve");
        toAdd.setIdSezione(25);
        toAdd.setIdAnnuale(43);
        toAdd.setIdAutore(23);
        toAdd.setContenuto("Succhiami la banana");
        try {
            manager.insertComment(toAdd);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
