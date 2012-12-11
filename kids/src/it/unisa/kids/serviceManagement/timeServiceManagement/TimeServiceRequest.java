package it.unisa.kids.serviceManagement.timeServiceManagement;

import java.sql.Time;
import java.util.GregorianCalendar;

public class TimeServiceRequest {

    private int id;
    private String dayRequested;            // one of: TimeServiceRequestBean.MON.....   null if serviceType == AUGUST_OPENING or EXTENDED_TIME
    private String serviceType;             // one of: TimeServiceRequestBean.*_TIMESERV
    private int parentId;
    private int confirmed;
    
    public TimeServiceRequest() {
    }

    public int getId() {
        return this.id;
    }
    
    public int getConfirmed(){
        return confirmed;
    }
    public void setConfirmed(int pConfirmed){
        confirmed=pConfirmed;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getDayRequested() {
        return this.dayRequested;
    }

    public void setDayRequested(String pDayReq) {
        this.dayRequested = pDayReq;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String pServType) {
        this.serviceType = pServType;
    }

   
    
    

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int pParentId) {
        this.parentId = pParentId;
    }
}
