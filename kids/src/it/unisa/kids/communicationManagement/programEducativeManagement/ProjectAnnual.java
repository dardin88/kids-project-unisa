package it.unisa.kids.communicationManagement.programEducativeManagement;

/**
 * The class model the new entity ProjectAnnual
 * @author Francesco Di Lorenzo
 *
 */
public class ProjectAnnual 
{
	private String name;
	private String topic;
	private String description;
	private String applicationYear;
	private int id;
	
	/**
	 * the empty costructor
	 */
	public ProjectAnnual()
	{	
	}

	/**
	 * this method return the name of the project
	 * @return String name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * this method set the name of the project
	 * @param name
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}

	/**
	 * this method return the tema of the project
	 * @return String tema
	 */
	public synchronized String getTopic() {
		return topic;
	}

	/**
	 * this method set the topic of the project
	 * @param topic
	 */
	public synchronized void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * this method return the description of the project
	 * @return String description
	 */
	public synchronized String getDescription() {
		return description;
	}

	/**
	 * this method set the description of the project
	 * @param description
	 */
	public synchronized void setDescription(String description) {
		this.description = description;
	}

	/**
	 * this method return the applicationYear of the project
	 * @return String applicationYear
	 */
	public synchronized String getApplicationYear() {
		return applicationYear;
	}
	/**
	 * this method set the applicationYear of the project
	 * @param applicationYear
	 */
	public synchronized void setApplicationYear(String applicationYear) {
		this.applicationYear = applicationYear;
	}
	/**
	 * this method return the id of the project annual
	 * @return int id
	 */
	public int getId(){
		return id;
	}
	

}
