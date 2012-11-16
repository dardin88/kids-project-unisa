package it.unisa.kids.common;

import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;

public class RefinedAbstractManager extends AbstractManager<IManager> {

	public IManager getManagerImplementor(String pManagerType) {
		if (pManagerType.equals(DBNames.TABLE_PAYMENT))
			this.imp = JDBCPaymentManager.getInstance();
		
		else if (pManagerType.equals(DBNames.TABLE_TRAINEE))
			this.imp = JDBCTraineeManager.getInstance();
		
		
		
		
		return this.imp;
	}
}
