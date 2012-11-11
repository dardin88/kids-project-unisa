package it.unisa.kids.serviceManagement.paymentManagement;

public class RefinedAbstractPaymentManager extends AbstractPaymentManager {

	public IPaymentManager getPaymentManagerImplementor() {
		this.imp = JDBCPaymentManager.getInstance();
		return this.imp;
	}
}
