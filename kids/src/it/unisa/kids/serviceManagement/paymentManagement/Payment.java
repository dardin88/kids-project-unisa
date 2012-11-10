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

	public Payment(String id, GregorianCalendar expDate, boolean charge,
			String paymentDescription, double amount, double amountDue,
			boolean paid, double discount, String discountDescription,
			String originAccount, String payee, String parentId) {
		setId(id);
		setExpDate(expDate);
		setCharge(charge);
		setPaymentDescription(paymentDescription);
		setAmount(amount);
		setAmountDue(amountDue);
		setPaid(paid);
		setDiscount(discount);
		setDiscountDescription(discountDescription);
		setOriginAccount(originAccount);
		setPayee(payee);
		setParentId(parentId);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GregorianCalendar getExpDate() {
		return expDate;
	}

	public void setExpDate(GregorianCalendar expDate) {
		this.expDate = expDate;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
