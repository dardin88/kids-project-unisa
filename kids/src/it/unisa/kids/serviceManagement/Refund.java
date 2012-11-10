package it.unisa.kids.serviceManagement;

import java.io.Serializable;

public class Refund implements Serializable {

	private String id;
	private String description;
	private double amount;
	private String parentId;

	public Refund() {

	}

	public Refund(String id, String description, double amount, String parentId) {
		setId(id);
		setDescription(description);
		setAmount(amount);
		setParentId(parentId);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
