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
	private int idChild;
	private String description;
	private GregorianCalendar date;
	private boolean solved;

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
	
	/**this method returns the idChild
	 * @return int idChild
	 */
	public int getIdChild() {
		return idChild;
	}

	/**this method sets the idChild
	 * @param int idChild
	 */
	public void setIdChild(int idChild) {
		this.idChild = idChild;
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
	/**this method returns the solved
	 * @return boolean solved
	 */
	public boolean getSolved() {
		return solved;
	}

	/**this method sets the solved
	 * @param boolean solved
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
}