/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MealRequestBean;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author navi
 */
class CanteenUtilities {

    // quest'attributo dovrebbe essere presente nella classe it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild
    public static final String FULL_TIME = "full_time";
    static ICanteenManager canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MENU);

    static boolean needsLunch(RegistrationChild rc) throws SQLException {
        if (rc.getUserRange().equals(FULL_TIME)) {
            return true;
        }

        MealRequestBean mr = new MealRequestBean();
        mr.setParentId(rc.getParentId());
        mr.setDate(new GregorianCalendar());    // setto la data corrente per verificare solo le richieste per il giorno corrente
        //mr.setFulfilledUsable(true);
        List<MealRequestBean> mealReqList = canteenManager.search(mr);

        if (mealReqList.size() > 0) {
            return true;
        }

        return false;
    }

    static boolean needsDiffMenu(RegistrationChild rc) {
        if (rc.getSickness() != null && !rc.getSickness().trim().equals("")) {
            return true;
        }
        if (rc.getAdditionalNotes() != null && !rc.getAdditionalNotes().trim().equals("")) {
            return true;
        }
        return false;
    }
}
