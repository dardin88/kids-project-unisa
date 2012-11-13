package it.unisa.kids.accessManagement;

/**
 * This class models the Account entity
 * @author Gianmarco Del Pozzo
 *
 */


public class Recourse {
	private int id;
	private String date;
	private String reason;
	private String valutation;
	private int id_registration;
	

	/**
	 * Empty constructor 
	 */
	
	public Recourse() {
	}


	/**this method sets the id registration linked with a recourse
	 * @param String pId_registration
	 */
	public void setId_registration(int pId_registration) {
		this.id_registration = pId_registration;
	}
	
	/**this method returns the id registration linked with a recourse
	 * @return String id_registrazion
	 */
	
	public int getId_registration() {
		return id_registration;
	}
	
	/**this method returns the id of a recourse
	 * @return String id
	 */

	public int getId() {
		return id;
	}
	
	/**this method returns the date when the recourse was made
	 * @return String data
	 */

	public String getData() {
		return date;
	}
	
	/**this method returns the reason of a recourse
	 * @return String reason
	 */

	public String getReason() {
		return reason;
	}
	
	/**this method returns the valutation of a recourse
	 * @return String valutation
	 */

	public String getValutation() {
		return valutation;
	}
	
	/**this method set the id of a recourse
	 * @param String pId
	 */
	
	public void setId(int pId) {
		this.id = pId;
	}
	
	/**this method set the date when the recourse was made
	 * @param String pDate
	 */
	
	public void setData(String pDate) {
		this.date = pDate;
	}
	
	/**this method set the reason of recourse
	 * @param String pReason
	 */
	
	public void setReason(String pReason) {
		this.reason = pReason;
	}
	
	/**this method set the valutation of a recourse
	 * @param String pValutation
	 * 	 */

	public void setValutation(String pValutation) {
		this.valutation = pValutation;
	}

}
