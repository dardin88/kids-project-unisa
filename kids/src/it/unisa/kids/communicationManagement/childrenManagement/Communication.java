package it.unisa.kids.communicationManagement.childrenManagement;

/**This class models the communication entity
 * @author Elena Sollai
 *
 */
public class Communication {
	
	private int id;
	private String type;
	private int idEducator;
	private int idChild;
	private String description;
        private String date;
	private String solved;

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
	 * @return String type
	 */
	public String getType() {
		return type;
	}

	/**this method sets the type
	 * @param String type
	 */
	public void setType(String type) {
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
	 * @return String date
	 */
	public String getDate() {
		return date;
	}

	/**this method sets the date
	 * @param String date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**this method returns the solved
	 * @return String solved
	 */
	public String getSolved() {
		return solved;
	}

	/**this method sets the solved
	 * @param String solved
	 */
	public void setSolved(String solved) {
		this.solved = solved;
	}
}
