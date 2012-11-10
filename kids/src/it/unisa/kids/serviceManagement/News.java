package it.unisa.kids.serviceManagement;

import it.unisa.kids.accessManagement.Account;

import java.util.Date;

/**This class models the news entity
 * @author Marco Moretti
 *
 */

public class News {
	private String type;
	private String description;
	private Date date;
	private Account author;
	private int id;
	
	/**The constructor of the class News
	 * @param type
	 * @param description
	 * @param date
	 * @param author
	 * @param id
	 */
	public News(String type,String description,Date date,Account author,int id){
		this.type=type;
		this.description=description;
		this.date=date;
		this.author=author;
		this.id=id;
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

	
	/**this method returns the description of the news 
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**this method sets the description of the news
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**this method returns the insertion date of the news
	 * @return Date date
	 */
	public Date getDate() {
		return date;
	}

	/**this method sets the insertion date of the news 
	 * @param Date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**this method returns the author of the news 
	 * @return Account author
	 */
	public Account getAuthor() {
		return author;
	}

	/**this method sets  the author of the news
	 * @param Account author 
	 */
	public void setAuthor(Account author) {
		this.author = author;
	}

	/**this method returns the id of the news
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**this method sets the id of the news
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
