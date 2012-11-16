package it.unisa.kids.common;

import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;
import it.unisa.kids.serviceManagement.trainingManagement.JDBCTrainingManager;

public class RefinedAbstractManager extends AbstractManager<IManager> {

	public IManager getManagerImplementor(String pManagerType) {
		if (pManagerType.equals(DBNames.TABLE_PAYMENT))
			this.imp = JDBCPaymentManager.getInstance();
		
		else if (pManagerType.equals(DBNames.TABLE_TRAINEE))
			this.imp = JDBCTrainingManager.getInstance();
		
		else if(pManagerType.equals(DBNames.TABLE_NEWS))
			this.imp=JDBCNewsManager.getInstance();
		
		
		return this.imp;
	}
}
