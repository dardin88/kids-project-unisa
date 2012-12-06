package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * the class model a project annual for section
 * is a underclass of ProjectAnnual
 * @author Francesco Di Lorenzo
 *
 */
public class AnnualProjectSection extends AnnualProject implements Serializable  
{
        private ArrayList<CommentoBean> commenti;
	private int id;
	private String name;
	private String description;
	private int section;
	private int idYear; //serve come chiave esterna per progetto annuale
	private String attached;

    public void setCommenti(ArrayList<CommentoBean> commenti) {
        this.commenti = commenti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttached(String attached) {
        this.attached = attached;
    }

    public String getAttached() {
        return attached;
    }
        
	/**
	 * the costructor recall the costuctor of the father
	 */
	public AnnualProjectSection(){
            super();
            commenti=new ArrayList<CommentoBean>();
            
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
	public synchronized int getSection() {
		return section;
	}


	/**
	 * this method set the section of the projectAnnualSection
	 * @param section
	 */
	public synchronized void setSection(int section) {
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
	
	public synchronized void addComment(Account author, String comment){
            CommentoBean toAdd=new CommentoBean();
            toAdd.setContenuto(comment);
            toAdd.setDate(new GregorianCalendar());
            toAdd.setIdAnnuale(this.getId());
            toAdd.setIdAutore(author.getId());
            toAdd.setIdSezione(this.getSection());
            toAdd.setId(this.getId());
            toAdd.setContenuto(this.getTopic());
            
            this.commenti.add(toAdd);
           /* Mail toSend=new Mail();
            toSend.setBody(comment);
            toSend.setSubject(author);
            ArrayList<String> destinatari=new ArrayList<String>();
            destinatari.add(name)
            toSend.setTo(null);*/
            
            }

}
