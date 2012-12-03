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
    public ClassBean insert(ClassBean pClass) throws SQLException;
    public List<ClassBean> search(ClassBean pClass) throws SQLException;
    public ClassBean delete(ClassBean pClass) throws SQLException;
    public ClassBean update(ClassBean pClass) throws SQLException;
}
