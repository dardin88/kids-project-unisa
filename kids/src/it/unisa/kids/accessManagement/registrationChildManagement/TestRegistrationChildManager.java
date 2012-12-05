/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class TestRegistrationChildManager {
    
    public static void main(String[] args) {
        JDBCRegistrationChildManager test = JDBCRegistrationChildManager.getInstance();
        
        /* Test inserimento
        RegistrationChild newChild = new RegistrationChild();
        newChild.setId(10);
        newChild.setSurname("ProvoS");
        newChild.setName("ProvoN");
        newChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DRAFT);
        try {
            test.create(tmp);
            System.out.println("Inserimento non ha dato problemi");
        } catch (SQLException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
        //*/
        /* Test Cancella
        RegistrationChild deleteId = new RegistrationChild();
        deleteId.setId(3);
        try {
            test.delete(deleteId);
            System.out.println("Cancellazione non ha dato problemi");
        } catch (SQLException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
        //*/
        /* Test Aggiorna
        RegistrationChild tomodify = new RegistrationChild();
        tomodify.setId(2);
        tomodify.setName("Loppolo");
        tomodify.setRegistrationDate(new GregorianCalendar(2012,12,1));
        tomodify.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED);
        try {
            test.update(tomodify);
            System.out.println("Modifica non ha dato problemi: Nome: " + tomodify.getName() + " Id: " + tomodify.getId());
        } catch (SQLException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
        //*/
        /* Test Ricerca
        RegistrationChild selectAll = new RegistrationChild();
        RegistrationChild selectById = new RegistrationChild();
        selectById.setId(1);
        try {
            List<RegistrationChild> list1 = test.search(selectAll);
            System.out.println("La ricerca di tutti ha restituito " + list1.size() + " elementi");
            List<RegistrationChild> listId = test.search(selectById);
            System.out.println("La ricerca dell'id ha restituito " + listId.size() + " elementi");
            // Stampo tutti gli elementi
            for(RegistrationChild tmp : list1) {
                System.out.println(tmp.getName() + " " + tmp.getSurname());
            }
        } catch (SQLException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
        //*/
    }
}
