package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;

public class RefundBean implements Serializable {

	private String id;
	private String description;
	private double amount;
	private String parentId;

	public RefundBean() {

	}
	
	public RefundBean(String pId, double pAmount, String pParentId) {
		setId(pId);
		setAmount(pAmount);
		setParentId(pParentId);
	}
	

	public String getId() {
		return this.id;
	}

	public void setId(String pId) {
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

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String pParentId) {
		this.parentId = pParentId;
	}
}
