package it.unisa.kids.common;

import it.unisa.kids.communicationManagement.childrenManagement.JDBCCommunicationManager;
import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;
import it.unisa.kids.serviceManagement.trainingManagement.JDBCTrainingManager;
import it.unisa.kids.accessManagement.accountManagement.*;

public class RefinedAbstractManager extends AbstractManager<IManager> {

    // Singleton Design Pattern's implementation
    private static RefinedAbstractManager refinedManager;

    private RefinedAbstractManager() {
    }

    public static RefinedAbstractManager getInstance() {
        if (refinedManager == null) {
            refinedManager = new RefinedAbstractManager();
        }
        return refinedManager;
    }
    // end of Singleton Design Pattern's implementation
    
    public IManager getManagerImplementor(String pManagerType) {
        if (pManagerType.equals(DBNames.TABLE_PAYMENT)) {
            this.imp = JDBCPaymentManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_TRAINEEACTIVITY)) {
            this.imp = JDBCTrainingManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_NEWS)) {
            this.imp = JDBCNewsManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_COMUNICATION)) {
            this.imp = JDBCCommunicationManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_ACCOUNT)) {
            this.imp = JDBCAccountManager.getInstance();
        }

        return this.imp;
    }
}
