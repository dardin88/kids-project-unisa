/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.GregorianCalendar;

/**
 *
 * @author navi
 */
public class Activity {

    private int id;
    private String name;
    private String description;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private int idClass;

    public int getId() {
        return this.id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public GregorianCalendar getStartDate() {
        return this.startDate;
    }

    public void setStartDate(GregorianCalendar pStartDate) {
        this.startDate = pStartDate;
    }

    public GregorianCalendar getEndDate() {
        return this.endDate;
    }

    public void setEndDate(GregorianCalendar pEndDate) {
        this.endDate = pEndDate;
    }

    public int getIdClass() {
        return this.idClass;
    }

    public void setIdClass(int pIdClass) {
        this.idClass = pIdClass;
    }
}
