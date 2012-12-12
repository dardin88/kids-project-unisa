package it.unisa.kids.serviceManagement.canteenManagement;

import java.util.GregorianCalendar;

public class MealRequestBean {
	
	public static final double PRICE_MEALREQ = 5.00;
	
	private int id;
	private GregorianCalendar date;
        private boolean fulfilled;
        private boolean fulfilledUsable;
	private int parentId;
	
	public MealRequestBean() {
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int pId) {
		this.id = pId;
	}
	
	public GregorianCalendar getDate() {
		return this.date;
	}
	
	public void setDate(GregorianCalendar pDate) {
		this.date = pDate;
	}
        
        public boolean isFulfilled() {
            return this.fulfilled;
        }
        
        public void setFulfilled(boolean pFulfilled) {
            this.fulfilled = pFulfilled;
        }
        
        public boolean isFulfilledUsable() {
            return this.fulfilledUsable;
        }
        
        public void setFulfilledUsable(boolean pFulfilledUsable) {
            this.fulfilledUsable = pFulfilledUsable;
        }
	
	public int getParentId() {
		return this.parentId;
	}
	
	public void setParentId(int pParentId) {
		this.parentId = pParentId;
	}
}
