package it.unisa.kids.serviceManagement.paymentManagement;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Observable;

public class PaymentBean implements Serializable {
    
    public static final String DEFAULT_PAYEE = "Unisa - Kids";

    private int id;
    private GregorianCalendar expDate;
    private String paymentDescription;
    private double amount;
    private boolean paid;
    private boolean paidUsable;         // used to determine if it's ok to use paid in querys
    private double discount;
    private String discountDescription;
    private String originAccount;
    private String payee;
    private int parentId;
    private String receiptCode;

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
    
    public boolean isPaidUsable() {
        return this.paidUsable;
    }
    
    public void setPaidUsable(boolean pPaidUsable) {
        this.paidUsable = pPaidUsable;
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
    
    public String getReceiptCode() {
        return this.receiptCode;
    }
    
    public void setReceiptCode(String pReceiptCode) {
        this.receiptCode = pReceiptCode;
    }
}
