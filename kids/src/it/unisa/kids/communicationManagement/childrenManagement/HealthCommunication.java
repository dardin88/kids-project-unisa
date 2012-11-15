package it.unisa.kids.communicationManagement.childrenManagement;

/**This class models the health communication entity
 * @author Elena Sollai
 *
 */
public class HealthCommunication {
	
	private int id;
	private Object child;
	private String food;
	private String quantityFood;
	private String hoursSleep;
	private String mood;
	private String sickness;

	/**Empty constructor
	 */
	public HealthCommunication (){
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

	/**this method returns the food
	 * @return String food
	 */
	public String getFood() {
		return food;
	}

	/**this method sets the food
	 * @param String food
	 */
	public void setFood(String food) {
		this.food=food;
	}

	/**this method returns the quantityFood
	 * @return String quantityFood
	 */
	public String getQuantityFood() {
		return quantityFood;
	}

	/**this method sets the quantityFood
	 * @param String quantityFood
	 */
	public void setQuantityFood(String quantityFood) {
		this.quantityFood=quantityFood;
	}

	/**this method returns the hoursSleep
	 * @return String hoursSleep
	 */
	public String getHoursSleep() {
		return hoursSleep;
	}

	/**this method sets the hoursSleep
	 * @param String hoursSleep
	 */
	public void setHoursSleep(String hoursSleep) {
		this.hoursSleep=hoursSleep;
	}

	/**this method returns the mood
	 * @return String mood
	 */
	public String getMood() {
		return mood;
	}

	/**this method sets the mood
	 * @param String mood
	 */
	public void setMood(String mood) {
		this.mood=mood;
	}	
	
	/**this method returns the sickness
	 * @return String sickness
	 */
	public String getSickness() {
		return sickness;
	}

	/**this method sets the sickness
	 * @param String sickness
	 */
	public void setSickness(String sickness) {
		this.sickness=sickness;
	}
}
