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
    protected int getId() {
        return id;
    }

    /**
     * this method set the id of news
     *
     * @param int pId
     */
    protected void setId(int pId) {
        this.id = pId;
    }

    /**
     * this method returns the title of news
     *
     * @return String title
     */
    protected String getTitle() {
        return title;
    }

    /**
     * this method sets the title of news
     *
     * @param String title
     */
    protected void setTitle(String pTitle) {
        this.title = pTitle;
    }

    /**
     * this method returns the description of news
     *
     * @return String description
     */
    protected String getDescription() {
        return description;
    }

    /**
     * this method sets the description of news
     *
     * @param String description
     */
    protected void setDescription(String pDescription) {
        this.description = pDescription;
    }

    /**
     * this method returns the attached of news
     *
     * @return String attached
     */
    protected String getAttached() {
        return attached;
    }

    /**
     * this method sets the attached of news
     *
     * @param String pAttached
     */
    protected void setAttached(String pAttached) {
        this.attached = pAttached;
    }

    /**
     * this method returns the date of news
     *
     * @return GregorianCalendar date
     */
    protected GregorianCalendar getDate() {
        return date;
    }

    /**
     * this method sets the date of news
     *
     * @param GregorianCalendar date
     */
    protected void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }

    /**
     * this method returns the time of news
     *
     * @return GregorianCalendar time
     */
    protected Time getTime() {
        return time;
    }

    /**
     * this method sets the time of news
     *
     * @param GregorianCalendar time
     */
    protected void setTime(Time pTime) {
        this.time = pTime;
    }

    /**
     * this method sets the author of news
     *
     * @param Account delegate
     */
    protected void setDelegate(int pDelegate) {
        this.delegate = pDelegate;
    }

    /**
     * this method returns the author of news
     *
     * @return Account author
     */
    protected int getDelegate() {
        return delegate;
    }

    /**
     * this method returns the type of news
     *
     * @return String type
     */
    protected String getType() {
        return type;
    }

    /**
     * this method sets the type of news
     *
     * @param String type
     */
    protected void setType(String pType) {
        this.type = pType;
    }
}
