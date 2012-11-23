/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 *
 * @author utente
 */
public class TraineeRequest {
    private int traineeNumber;
    private GregorianCalendar date;
    private int delegate;
    private int id;
    private Time startTime;
    private Time endTime;
    private String activity;
    public TraineeRequest(){
        
    }
    public int getTraineeNumber(){
        return traineeNumber;
        
    }
    public void setTraineeNumber(int pTraineeNumber){
        this.traineeNumber=pTraineeNumber;
    }
    public GregorianCalendar getDate(){
        return date;
    }
    public void setDate(GregorianCalendar pDate){
        this.date=pDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setStartTime(Time pStartTime) {
        this.startTime = pStartTime;
    }

    public void setEndTime(Time pEndTime) {
        this.endTime = pEndTime;
    }

    public void setDelegate(int pDelegate) {
        this.delegate = pDelegate;
    }

    public int getId() {
        return id;
    }

    public int getDelegate() {
        return delegate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setActivity(String pActivity) {
        this.activity = pActivity;
    }
    public void setId(int pId){
        this.id=pId;
    }
    
}
