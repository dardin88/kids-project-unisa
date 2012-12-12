package it.unisa.kids.accessManagement.renunciationManagement;

import java.util.GregorianCalendar;

public class Renunciation {

    private int id;
    private GregorianCalendar date;
    private String reason;
    private int registrationChildId;
    private boolean isConfirmed;
    private boolean isSetConfirmed;

    public Renunciation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar data) {
        this.date = data;
    }

    public int getRegistrationChildId() {
        return registrationChildId;
    }

    public void setRegistrationChildId(int registrationChildId) {
        this.registrationChildId = registrationChildId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        this.isSetConfirmed = true;
    }
    
    public boolean isSetConfirmed() {
        return this.isSetConfirmed;
    }
}
