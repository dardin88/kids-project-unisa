package it.unisa.kids.accessManagement.renunciationManagement;

import java.util.GregorianCalendar;

/**
 * 
 * @author Lauri Giuseppe Giovanni
 *
 */
public class Renunciation {

    private int id;
    private GregorianCalendar date;
    private String reason;
    
    private int registrationChildId;
    private String registrationChildFiscalCode;
    private String registrationChildSurname;
    private String registrationChildName;
    
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

    protected String getRegistrationChildFiscalCode() {
        return registrationChildFiscalCode;
    }

    protected void setRegistrationChildFiscalCode(String registrationChildFiscalCode) {
        this.registrationChildFiscalCode = registrationChildFiscalCode;
    }

    protected String getRegistrationChildSurname() {
        return registrationChildSurname;
    }

    protected void setRegistrationChildSurname(String registrationChildSurname) {
        this.registrationChildSurname = registrationChildSurname;
    }

    protected String getRegistrationChildName() {
        return registrationChildName;
    }

    protected void setRegistrationChildName(String registrationChildName) {
        this.registrationChildName = registrationChildName;
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
