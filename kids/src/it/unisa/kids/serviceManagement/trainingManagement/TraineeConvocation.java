/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 *
 * @author Moretti Marco
 */
public class TraineeConvocation {
    private int id;
    private GregorianCalendar date;
    private Time startTime;
    private Time endTime;
    private String activityName;
    private int confirmed;
    private int delegateId;
    private int traineeId;

    
    public TraineeConvocation(){
        
    }
    public String getActivityName() {
        return activityName;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public int getDelegateId() {
        return delegateId;
    }

    public Time getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setActivityName(String pActivityName) {
        this.activityName = pActivityName;
    }

    public void setConfirmed(int pConfirmed) {
        this.confirmed = pConfirmed;
    }

    public void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }

    public void setDelegateId(int pDelegateId) {
        this.delegateId = pDelegateId;
    }

    public void setEndTime(Time pEndTime) {
        this.endTime = pEndTime;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public void setStartTime(Time pStartTime) {
        this.startTime = pStartTime;
    }

    public void setTraineeId(int pTraineeId) {
        this.traineeId = pTraineeId;
    }
    
}
