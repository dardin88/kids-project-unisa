package it.unisa.kids.communicationManagement.newsManagement;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 * This class models the news entity
 *
 * @author Francesco Di Lorenzo
 *
 */
public class News {

    public static final String EVENT_NEWS = "event";
    public static final String NEWS_NEWS = "news";
    
    public static final String OPENING_TIMESERV = "opening";
    public static final String CLOSING_TIMESERV = "closing";
    
    private String title;
    private String description;
    private String attached;
    private GregorianCalendar date;
    private Time time;
    private int delegate;
    private String type;
    private int id;

    /**
     * The constructor of the class News empty
     */
    public News() {
    }

    /**
     * this method returns the id of news
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * this method set the id of news
     *
     * @param int pId
     */
    public void setId(int pId) {
        this.id = pId;
    }

    /**
     * this method returns the title of news
     *
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * this method sets the title of news
     *
     * @param String title
     */
    public void setTitle(String pTitle) {
        this.title = pTitle;
    }

    /**
     * this method returns the description of news
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * this method sets the description of news
     *
     * @param String description
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    /**
     * this method returns the attached of news
     *
     * @return String attached
     */
    public String getAttached() {
        return attached;
    }

    /**
     * this method sets the attached of news
     *
     * @param String pAttached
     */
    public void setAttached(String pAttached) {
        this.attached = pAttached;
    }

    /**
     * this method returns the date of news
     *
     * @return GregorianCalendar date
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * this method sets the date of news
     *
     * @param GregorianCalendar date
     */
    public void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }

    /**
     * this method returns the time of news
     *
     * @return GregorianCalendar time
     */
    public Time getTime() {
        return time;
    }

    /**
     * this method sets the time of news
     *
     * @param GregorianCalendar time
     */
    public void setTime(Time pTime) {
        this.time = pTime;
    }

    /**
     * this method sets the author of news
     *
     * @param Account delegate
     */
    public void setDelegate(int pDelegate) {
        this.delegate = pDelegate;
    }

    /**
     * this method returns the author of news
     *
     * @return Account author
     */
    public int getDelegate() {
        return delegate;
    }

    /**
     * this method returns the type of news
     *
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * this method sets the type of news
     *
     * @param String type
     */
    public void setType(String pType) {
        this.type = pType;
    }
}
