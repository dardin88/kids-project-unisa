package it.unisa.kids.accessManagement.recoursesManagement;

import java.util.GregorianCalendar;

/**
 * This class models the Recourse entity
 * @author Gianmarco Del Pozzo, Lauri Giuseppe Giovanni
 *
 */
public class Recourse {
    private int id;
    private GregorianCalendar date;
    private String reason;
    private String valutation;
    
    private int registrationChildId;
    private String registrationChildFiscalCode;
    private String registrationChildSurname;
    private String registrationChildName;

    /**
     * Empty constructor 
     */
    public Recourse() {
    }


    /**
     * This method sets the id registration linked with a recourse
     * @param String pRegistrationChildId
     */
    public void setRegistrationChildId(int pRegistrationChildId) {
            this.registrationChildId = pRegistrationChildId;
    }

    /**
     * This method returns the id registration linked with a recourse
     * @return String id_registrazion
     */
    public int getRegistrationChildId() {
            return registrationChildId;
    }

    /**
     * This method returns the id of a recourse
     * @return String id
     */
    public int getId() {
            return id;
    }

    /**
     * This method returns the date when the recourse was made
     * @return String data
     */
    public GregorianCalendar getDate() {
            return date;
    }

    /**
     * This method returns the reason of a recourse
     * @return String reason
     */
    public String getReason() {
            return reason;
    }

    /**
     * This method returns the valutation of a recourse
     * @return String valutation
     */
    public String getValutation() {
            return valutation;
    }

    /**
     * This method set the id of a recourse
     * @param String pId
     */
    public void setId(int pId) {
            this.id = pId;
    }

    /**
     * This method set the date when the recourse was made
     * @param String pDate
     */
    public void setDate(GregorianCalendar pDate) {
            this.date = pDate;
    }

    /**
     * This method set the reason of recourse
     * @param String pReason
     */
    public void setReason(String pReason) {
            this.reason = pReason;
    }

    /**
     * This method set the valutation of a recourse
     * @param String pValutation
     * 	 */
    public void setValutation(String pValutation) {
            this.valutation = pValutation;
    }

    public String getRegistrationChildFiscalCode() {
        return registrationChildFiscalCode;
    }

    public void setRegistrationChildFiscalCode(String registrationChildFiscalCode) {
        this.registrationChildFiscalCode = registrationChildFiscalCode;
    }

    public String getRegistrationChildSurname() {
        return registrationChildSurname;
    }

    public void setRegistrationChildSurname(String registrationChildSurname) {
        this.registrationChildSurname = registrationChildSurname;
    }

    public String getRegistrationChildName() {
        return registrationChildName;
    }

    public void setRegistrationChildName(String registrationChildName) {
        this.registrationChildName = registrationChildName;
    }
}
