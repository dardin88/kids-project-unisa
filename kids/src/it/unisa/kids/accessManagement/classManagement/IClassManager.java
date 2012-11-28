/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gianmarco
 */
public interface IClassManager extends IManager {
    public Class insert(Class pClass) throws SQLException;
    public List<Class> search(Class pClass) throws SQLException;
    public Class delete(Class pClass) throws SQLException;
    public Class update(Class pClass) throws SQLException;
}
