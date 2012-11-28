package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Observable;

public class PaymentBean extends Observable implements Serializable {
	
	private int id;
	private GregorianCalendar expDate;
	private boolean charge;
	private String paymentDescription;
	private double amount;
	private boolean paid;
	private double discount;		// dovrebbe essere double
	private String discountDescription;
	private String originAccount;
	private String payee;
	private int parentId;

	public PaymentBean() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int pId) {
		this.id = pId;
	}

	public GregorianCalendar getExpDate() {
		return this.expDate;
	}

	public void setExpDate(GregorianCalendar pExpDate) {
		this.expDate = pExpDate;
	}

	public boolean isCharge() {
		return this.charge;
	}

	public void setCharge(boolean pCharge) {
		this.charge = pCharge;
		setChanged();		// marks this Observable object as having been changed
	}

	public String getPaymentDescription() {
		return this.paymentDescription;
	}

	public void setPaymentDescription(String pPaymentDescription) {
		this.paymentDescription = pPaymentDescription;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double pAmount) {
		this.amount = pAmount;
	}

	public boolean isPaid() {
		return this.paid;
	}

	public void setPaid(boolean pPaid) {
		this.paid = pPaid;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double pDiscount) {
		this.discount = pDiscount;
	}

	public String getDiscountDescription() {
		return this.discountDescription;
	}

	public void setDiscountDescription(String pDiscountDescription) {
		this.discountDescription = pDiscountDescription;
	}

	public String getOriginAccount() {
		return this.originAccount;
	}

	public void setOriginAccount(String pOriginAccount) {
		this.originAccount = pOriginAccount;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String pPayee) {
		this.payee = pPayee;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int pParentId) {
		this.parentId = pParentId;
	}
}
