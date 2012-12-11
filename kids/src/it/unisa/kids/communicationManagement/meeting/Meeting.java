package it.unisa.kids.communicationManagement.meeting;

/**
 * This class models the reunion entity
 *
 * @author Pasquale Caldarese
 *
 */
public class Meeting {

    private int id;
    private String title;
    private String description;
    private String date;
    private String firstTime;
    private String secondTime;
    private String type;

    /**
     * The constructor of the class Reunion
     *
     * @param id
     * @param title
     * @param description
     * @param date
     * @param time
     * @param type
     */
    public Meeting(int id, String title, String description, String date, String firstTime, String secondTime, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.firstTime = firstTime;
        this.secondTime = secondTime;
        this.type = type;
    }

    public Meeting() {
    }

    /**
     * this method returns the id of meeting
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * this method sets the id of meeting
     *
     * @param int id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * this method returns the title of meeting
     *
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * this method sets the title of meeting
     *
     * @param String title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * this method returns the description of meeting
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * this method sets the description of meeting
     *
     * @param String description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * this method returns the date of meeting
     *
     * @return GregorianCalendar date
     */
    public String getDate() {
        return date;
    }

    /**
     * this method sets the date of meeting
     *
     * @param GregorianCalendar date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * this method returns the start time of the meeting
     *
     * @return String firstTime
     */
    public String getFirstTime() {
        return firstTime;
    }

    /**
     * this method sets the start time of the meeting
     *
     * @param String firstTime
     */
    public void setFirstTime(String fTime) {
        this.firstTime = fTime;
    }

    /**
     * this method returns the end time of the meeting
     *
     * @return String secondTime
     */
    public String getSecondTime() {
        return secondTime;
    }

    /**
     * this method sets the end time of the meeting
     *
     * @param String secondTime
     */
    public void setSecondTime(String sTime) {
        this.secondTime = sTime;
    }
    
     

    /**
     * this method returns the type of meeting
     *
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * this method sets the type of reunion
     *
     * @param String type
     */
    public void setType(String type) {
        this.type = type;
    }
}
