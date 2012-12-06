package it.unisa.kids.communicationManagement.surveyManagement;

/**
 *
 * @author felice
 */
public class Survey {
    /**
     *Constructor is empty.
     */
    public Survey() {}
    
    /**
     *Sets the Survey's id.
     * @param pId
     */
    public void setId(int pId){
        this.id = pId;
    }
    /**
     *Returns the Survey's id.
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     *Sets the Survey's link.
     * @param pLink
     */
     public void setLink(String pLink) {
        this.link = pLink;
    }
    
     /**
      *Returns the Survey's link.
      * @return link
      */
    public String getLink() {
        return this.link;
    }
    
    
    public void setCompiled() {
       this.compiled = true; 
    }
    
    public boolean getCompiled() {
        return (this.compiled);
    }
    
    private int id;
    private String link;
    private boolean compiled;
}
