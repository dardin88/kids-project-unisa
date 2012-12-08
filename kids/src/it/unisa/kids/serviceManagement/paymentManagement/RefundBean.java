package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;

public class RefundBean implements Serializable {

    private int id;
    private String description;
    private double amount;
    private int parentId;
    private boolean performed;
    private boolean performedUsable;        // used to determine if it's ok to use performed in querys

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

    public boolean isPerformed() {
        return this.performed;
    }

    public void setPerformed(boolean pPerformed) {
        this.performed = pPerformed;
    }

    public boolean isPerformedUsable() {
        return this.performedUsable;
    }

    public void setPerformedUsable(boolean pPerformedUsable) {
        this.performedUsable = pPerformedUsable;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
                + ", description=" + description
                + ", amount=" + amount
                + ", parentId=" + parentId
                + ", performed=" + performed
                + ", performedUsable=" + performedUsable;
    }
}
