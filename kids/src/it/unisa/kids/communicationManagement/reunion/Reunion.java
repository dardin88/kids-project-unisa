package it.unisa.kids.communicationManagement.reunion;

import java.util.GregorianCalendar;

/**This class models the reunion entity
 * @author Pasquale Caldarese
 *
 */
public class Reunion {
	
	private int id;
	private String title;
	private String description;
	private GregorianCalendar date;
	private GregorianCalendar hour;
	private String type;
	
	
	/**The constructor of the class Reunion 
	 *@param id
	 *@param title
	 *@param description
	 *@param date
	 *@param hour
	 *@param type
	 */
	public Reunion (int id, String title, String description, GregorianCalendar date, GregorianCalendar hour, String type){
		this.id=id;
		this.title=title;
		this.description=description;
		this.date=date;
		this.hour=hour;
		this.type=type;
	}
	
	/**this method returns the id of reunion
	 * @return int id
	 */
	public int getId() {
		return id;
	}
	
	/**this method sets the id of reunion
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**this method returns the title of reunion
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}
	
	/**this method sets the title of reunion
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**this method returns the description of reunion
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
	
	/**this method sets the description of reunion
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**this method returns the date of reunion
	 * @return GregorianCalendar date
	 */
	public GregorianCalendar getDate() {
		return date;
	}
	
	/**this method sets the date of reunion
	 * @param GregorianCalendar date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	/**this method returns the hour of reunion
	 * @return GregorianCalendar hour
	 */
	public GregorianCalendar getHour() {
		return hour;
	}
	
	/**this method sets the hour of reunion
	 * @param GregorianCalendar hour
	 */
	public void setHour(GregorianCalendar hour) {
		this.hour = hour;
	}
	
	/**this method returns the type of reunion
	 * @return String type
	 */
	public String getType() {
		return type;
	}
	
	/**this method sets the type of reunion
	 * @param String type
	 */
	public void setType(String type) {
		this.type = type;
	}	
}
