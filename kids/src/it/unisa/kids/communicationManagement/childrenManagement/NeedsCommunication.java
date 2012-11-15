package it.unisa.kids.communicationManagement.childrenManagement;

/**This class models the needs communication entity
 * @author Elena Sollai
 *
 */
public class NeedsCommunication {
	
	private int id;
	private Object child;
	private String type;
	private String needs;

	/**Empty constructor
	 */
	public NeedsCommunication (){
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

	/**this method returns the child
	 * @return Object child
	 */
	public Object getChild() {
		return child;
	}

	/**this method sets the child
	 * @param Object child
	 */
	public void setChild(Object child) {
		this.child=child;
	}

	/**this method returns the type
	 * @return String type
	 */
	public String getType() {
		return type;
	}

	/**this method sets the food
	 * @param String food
	 */
	public void setType(String type) {
		this.type=type;
	}

	/**this method returns the needs
	 * @return String needs
	 */
	public String getNeeds() {
		return needs;
	}

	/**this method sets the needs
	 * @param String needs
	 */
	public void setNeeds(String needs) {
		this.needs=needs;
	}

}
