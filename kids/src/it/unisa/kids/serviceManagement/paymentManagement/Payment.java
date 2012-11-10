package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Payment implements Serializable {

	private String id;
	private GregorianCalendar expDate;
	private boolean charge;
	private String paymentDescription;
	private double amount;
	private double amountDue;
	private boolean paid;
	private double discount;
	private String discountDescription;
	private String originAccount;
	private String payee;
	private String parentId;

	public Payment() {

	}
	
	public Payment(String pId, GregorianCalendar pExpDate, double pAmount, double pAmountDue,
			boolean pPaid, String pOriginAccount, String pPayee, String pParentId) {
		setId(pId);
		setExpDate(pExpDate);
		setAmount(pAmount);
		setAmountDue(pAmountDue);
		setPaid(pPaid);
		setOriginAccount(pOriginAccount);
		setPayee(pPayee);
		setParentId(pParentId);
	}
	

	public String getId() {
		return this.id;
	}

	public void setId(String pId) {
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

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String pParentId) {
		this.parentId = pParentId;
	}
}
