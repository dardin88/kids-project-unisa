package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;

public class RefundBean implements Serializable {

	private int id;
	private String description;
	private double amount;
	private int parentId;

	public RefundBean() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int pId) {
		this.id = pId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double pAmount) {
		this.amount = pAmount;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int pParentId) {
		this.parentId = pParentId;
	}
}
