package it.unisa.kids.serviceManagement.paymentManagement;

public abstract class AbstractPaymentManager {

	protected IPaymentManager imp;
	
	public abstract IPaymentManager getPaymentManagerImplementor();
}
