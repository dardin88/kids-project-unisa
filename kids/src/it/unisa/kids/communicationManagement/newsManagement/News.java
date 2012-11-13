package it.unisa.kids.communicationManagement.newsManagement;

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
	private GregorianCalendar date,time,dataCreation;
	private Account delegate;
	private String type;
	
	/**The constructor of the class News 
	 * empty
	 */
	public News()
	{
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
	public void setTitle(String pTitle) {
		this.title = pTitle;
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
	public void setDescription(String pDescription) {
		this.description = pDescription;
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
	public void setAttached(Object pAttached) {
		this.attached = pAttached;
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
	public void setDate(GregorianCalendar pDate) {
		this.date = pDate;
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

	public void setTime(GregorianCalendar pTime) {
		this.time = pTime;
	}

	/**this method sets the author of news
	 * @param Account delegate
	 */

	public void setDelegate(Account pDelegate){
		this.delegate=pDelegate;
	}
	/**this method returns the author of news
	 * @return Account author
	 */
	public Account getDelegate(){
		return delegate;
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
	public void setType(String pType) {
		this.type = pType;
	}
	
	/**
	 * this method set the data of creation of the news
	 * @param pDataCreation
	 */
	public void setDataCreation(GregorianCalendar pDataCreation)
	{
		this.dataCreation=pDataCreation;
	}
	/**
	 * this method return the data of creation of the news
	 * @return GregorianCalendar dataCreation
	 */
	public GregorianCalendar getDataCreation()
	{
		return dataCreation;
	}

}