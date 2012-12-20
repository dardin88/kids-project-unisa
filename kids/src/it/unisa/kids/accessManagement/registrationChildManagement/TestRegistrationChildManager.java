package it.unisa.kids.accessManagement.registrationChildManagement;

/**
 *  TestClass utilized by author
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
        } catch (SQLException ex) {
        }
        //*/
        /* Test Cancella
        RegistrationChild deleteId = new RegistrationChild();
        deleteId.setId(3);
        try {
            test.delete(deleteId);
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
        }
        //*/
        /* Test Ricerca
        RegistrationChild selectAll = new RegistrationChild();
        RegistrationChild selectById = new RegistrationChild();
        selectById.setId(1);
        try {
            List<RegistrationChild> list1 = test.search(selectAll);
            List<RegistrationChild> listId = test.search(selectById);
            // Stampo tutti gli elementi
            for(RegistrationChild tmp : list1) {
            }
        } catch (SQLException ex) {
        }
        //*/
    }
}
