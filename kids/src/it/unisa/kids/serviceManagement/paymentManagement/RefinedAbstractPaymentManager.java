package it.unisa.kids.serviceManagement.paymentManagement;

import it.unisa.kids.common.AbstractManager;

public class RefinedAbstractPaymentManager extends AbstractManager<IPaymentManager> {

	public IPaymentManager getManagerImplementor() {
		this.imp = JDBCPaymentManager.getInstance();
		return this.imp;
	}
}
