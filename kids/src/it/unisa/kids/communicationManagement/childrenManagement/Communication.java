package it.unisa.kids.communicationManagement.childrenManagement;

import java.util.GregorianCalendar;

/**This class models the communication entity
 * @author Elena Sollai
 *
 */
public class Communication {
	
	private int id;
	private int type;
	private int idEducator;
	private int idParent;
	private String description;
	private GregorianCalendar date;

	/**Empty constructor
	 */
	public Communication (){
	}

	/**this method returns the id
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**this method sets the id
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**this method returns the type
	 * @return int type
	 */
	public int getType() {
		return type;
	}

	/**this method sets the type
	 * @param int type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**this method returns the idEducator
	 * @return int idEducator
	 */
	public int getIdEducator() {
		return idEducator;
	}

	/**this method sets the idEducator
	 * @param int idEducator
	 */
	public void setIdEducator(int idEducator) {
		this.idEducator = idEducator;
	}
	
	/**this method returns the idParent
	 * @return int idParent
	 */
	public int getIdParent() {
		return idParent;
	}

	/**this method sets the idParent
	 * @param int idParent
	 */
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}
	
	/**this method returns the description
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**this method sets the description
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**this method returns the date
	 * @return GregorianCalendar date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**this method sets the date
	 * @param GregorianCalendar date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
}
