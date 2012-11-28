package it.unisa.kids.serviceManagement.timeServiceManagement;

import java.sql.Time;
import java.util.GregorianCalendar;

public class TimeServiceRequestBean {

    public static final String PRE_ACCEPTANCE_TIMESERV = "pre-acceptance";
    public static final String POST_ACCEPTANCE_TIMESERV = "post-acceptance";
    public static final String EXTENDED_TIME_TIMESERV = "extented-time";
    public static final String AUGUST_OPENING_TIMESERV = "august-opening";
    public static final String MON = "monday";
    public static final String TUE = "tuesday";
    public static final String WED = "wednesday";
    public static final String THU = "thursday";
    public static final String FRI = "friday";
    private int id;
    private String dayRequested;            // one of: TimeServiceRequestBean.MON.....   null if serviceType == AUGUST_OPENING or EXTENDED_TIME
    private String serviceType;             // one of: TimeServiceRequestBean.*_TIMESERV
    private GregorianCalendar date;
    private Time requestTime;
    private int parentId;

    public TimeServiceRequestBean() {
    }

    public int getId() {
        return this.id;
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

    public GregorianCalendar getDate() {
        return this.date;
    }

    public void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }
    
    public Time getRequestTime() {
        return this.requestTime;
    }
    
    public void setRequestTime(Time pRequestTime) {
        this.requestTime = pRequestTime;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int pParentId) {
        this.parentId = pParentId;
    }
}
