package it.unisa.kids.communicationManagement;

import it.unisa.kids.accessManagement.Account;

import java.util.GregorianCalendar;

/**This class models the news entity
 * @author Francesco Di Lorenzo
 *
 */
public class News 
{
	private String title;
	private String description;
	private Object attached;
	private GregorianCalendar date,time;
	private int id;
	private Account author;
	private String type;
	
	/**The constructor of the class News
	 * @param title
	 * @param description
	 * @param attached
	 * @param date
	 * @param time
	 * @param id
	 * @param author
	 * @param type
	 * 
	 */
	public News(String title,String description,Object attached,GregorianCalendar date,GregorianCalendar time,int id,Account author,String type)
	{
		this.title=title;
		this.description=description;
		this.attached=attached;
		this.date=date;
		this.time=time;
		this.id=id;
		this.author=author;
		this.type=type;
	}
	
	/**this method returns the title of news
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}
	/**this method sets the title of news
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**this method returns the description of news
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
	/**this method sets the description of news
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**this method returns the attached of news
	 * @return Object attached
	 */
	public Object getAttached() {
		return attached;
	}
	/**this method sets the attached of news
	 * @param Object attached
	 */
	public void setAttached(Object allegato) {
		this.attached = allegato;
	}
	/**this method returns the date of news
	 * @return GregorianCalendar date
	 */
	public GregorianCalendar getDate() {
		return date;
	}
	/**this method sets the date of news
	 * @param GregorianCalendar date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	/**this method returns the time of news
	 * @return GregorianCalendar time
	 */
	public GregorianCalendar getTime() {
		return time;
	}
	/**this method sets the time of news
	 * @param GregorianCalendar time
	 */

	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	/**this method sets the id of news
	 * @param int id
	 */

	public void setId(int id){
		this.id=id;
	}
	/**this method returns the id of news
	 * @return int id
	 */
	public int getId(){
		return id;
	}
	/**this method sets the author of news
	 * @param Account author
	 */

	public void setAuthor(Account author){
		this.author=author;
	}
	/**this method returns the author of news
	 * @return Account author
	 */
	public Account getAuthor(){
		return author;
	}
	/**this method returns the type of news
	 * @return String type
	 */
	public String getType() {
		return type;
	}
	/**this method sets the type of news
	 * @param String type
	 */
	public void setType(String type) {
		this.type = type;
	}
	

}
