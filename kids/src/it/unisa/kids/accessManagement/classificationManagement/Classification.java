package it.unisa.kids.accessManagement.classificationManagement;

import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class Classification {
	
    private int id;
    private GregorianCalendar date;
    private String status;
    private String name;
    private List<Result> results;

    public Classification() {
		
    }
    
    /*
     * aggiungere la lista di bambini appartenenti a questa graduatoria
     */
    public int getId() {
            return this.id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public GregorianCalendar getDate() {
            return this.date;
    }

    public void setDate(GregorianCalendar date) {
            this.date = date;
    }

    public String getStatus() {
            return this.status;
    }

    public void setStatus(String status) {
            this.status = status;

    }
    
    public List<Result> getResults() {
        return results;
    }
    
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
