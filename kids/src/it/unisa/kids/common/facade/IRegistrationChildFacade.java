/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common.facade;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marco
 */
public interface IRegistrationChildFacade {
      public  List<RegistrationChild> search(RegistrationChild childToSearch) throws SQLException;

}
