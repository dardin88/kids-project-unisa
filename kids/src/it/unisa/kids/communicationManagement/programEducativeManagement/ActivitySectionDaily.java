package it.unisa.kids.communicationManagement.programEducativeManagement;

import java.util.GregorianCalendar;

/**
 * this class extends ProjectAnnualSection
 * model an activity daily for section
 * @author Francesco Di Lorenzo
 *
 */
public class ActivitySectionDaily extends ProjectAnnualSection 
{
	
	private int id;
	private int idActivity;
	private int idEducator;
	private GregorianCalendar data;
	private String notes;
	
	/**
	 * this method return the idActivity of the activity section daily
	 * @return int idActivity
	 */
	public synchronized int getIdActivity() {
		return idActivity;
	}
	
	/**
	 * this method set the idActivity of the activity section daily
	 * @param idActivity
	 */
	public synchronized void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	

	/**
	 * this method return the idEducatore of the activity section daily
	 * @return int idEducator
	 */
	public synchronized int getIdEducator() {
		return idEducator;
	}
	/**
	 * this method set the idEducator of the activity section daily
	 * @param idEducator
	 */
	public synchronized void setIdEducator(int idEducator) {
		this.idEducator = idEducator;
	}
	

	/**
	 * this method return the data of the activity section daily
	 * @return GregorianCalendar data
	 */
	public synchronized GregorianCalendar getData() {
		return data;
	}
	/**
	 * this method set the data of the activity section daily
	 * @param data
	 */
	public synchronized void setData(GregorianCalendar data) {
		this.data = data;
	}
	

	/**
	 * this method return the notes of the activity section daily
	 * @return String notes
	 */
	public synchronized String getNotes() {
		return notes;
	}
	/**
	 * this method set the notes of the activity section daily
	 * @param notes
	 */
	public synchronized void setNotes(String notes) {
		this.notes = notes;
	}
	

	/**
	 * this method return the id of the activity section daily
	 * @return int id
	 */
	public synchronized int getId() {
		return id;
	}

}
