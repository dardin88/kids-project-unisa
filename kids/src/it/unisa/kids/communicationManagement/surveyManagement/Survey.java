package it.unisa.kids.communicationManagement.surveyManagement;

/**
 *
 * @author felice
 */
public class Survey {
    
    private int id;
    private String link;
    
    /**
     *Constructor is empty.
     */
    public Survey() {}
    
    /**
     *Sets an ID for the survey.
     * @param pId
     */
    public void setId(int pId){
        this.id = pId;
    }
    /**
     *Returns the ID for a survey.
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     *Sets the link of the survey.
     * @param pLink
     */
     public void setLink(String pLink) {
        this.link = pLink;
    }
    
     /**
      *Returns the link of the survey.
      * @return link
      */
    public String getLink() {
        return this.link;
    }
    
}
