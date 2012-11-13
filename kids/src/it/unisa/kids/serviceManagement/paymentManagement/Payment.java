package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Payment implements Serializable {

	private int id;
	private GregorianCalendar expDate;
	private boolean charge;
	private String paymentDescription;
	private double amount;
	private double amountDue;
	private boolean paid;
	private String discount;		// dovrebbe essere double
	private String discountDescription;
	private String originAccount;
	private String payee;
	private int parentId;

	public Payment() {

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

	public double getAmountDue() {
		return this.amountDue;
	}

	public void setAmountDue(double pAmountDue) {
		this.amountDue = pAmountDue;
	}

	public boolean isPaid() {
		return this.paid;
	}

	public void setPaid(boolean pPaid) {
		this.paid = pPaid;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String pDiscount) {
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
