package it.unisa.kids.communicationManagement.programEducationalManagement;

/**
 * the class model a project annual for section
 * is a underclass of ProjectAnnual
 * @author Francesco Di Lorenzo
 *
 */
public class AnnualProjectSection extends AnnualProject 
{
	private int id;
	private String name;
	private String description;
	private String section;
	private int idYear; //serve come chiave esterna per progetto annuale
	
	/**
	 * the costructor recall the costuctor of the father
	 */
	public AnnualProjectSection(){
		super();
	}

	/**
	 * this method return the id of the projectAnnualSection
	 * @return int id
	 */
	public synchronized int getId() {
		return id;
	}

	/**
	 * this method return the name of the projectAnnualSection
	 * @return String name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * this method set the name of the projectAnnualSection
	 * @param name
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}
	/**
	 * this method return the description of the projectAnnualSection
	 * @return String description
	 */
	public synchronized String getDescription() {
		return description;
	}


	/**
	 * this method set the description of the projectAnnualSection
	 * @param description
	 */
	public synchronized void setDescription(String description) {
		this.description = description;
	}
	/**
	 * this method return the section of the projectAnnualSection
	 * @return String section
	 */
	public synchronized String getSection() {
		return section;
	}


	/**
	 * this method set the section of the projectAnnualSection
	 * @param section
	 */
	public synchronized void setSection(String section) {
		this.section = section;
	}
	/**
	 * this method return the idYear of the projectAnnualSection
	 * @return int idYear
	 */
	public synchronized int getIdYear() {
		return idYear;
	}


	/**
	 * this method set the idYear of the projectAnnualSection
	 * @param idYear
	 */
	public synchronized void setIdYear(int idYear) {
		this.idYear = idYear;
	}
	
	

}
