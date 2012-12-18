package it.unisa.kids.communicationManagement.programEducationalManagement;

/**
 * The class model the new entity ProjectAnnual
 *
 * @author Francesco Di Lorenzo, Giuseppe Alfieri
 *
 */
public class AnnualProject {

    private String stato;
    private String path;
    private int id;
    // private MailManager email;

    public AnnualProject() {
    }

    /**
     * this method return the state of the project
     *
     * @return String state
     */
    public synchronized String getState() {
        return stato;
    }

    /**
     * this method set the stato of the project
     *
     * @param pState
     */
    public synchronized void setState(String pState) {
        this.stato = pState;
    }

    /**
     * this method return the path of the project
     *
     * @return String path
     */
    public synchronized String getPath() {
        return path;
    }

    /**
     * this method set the path of the project
     *
     * @param pPath
     */
    public synchronized void setPath(String pPath) {
        this.path = pPath;
    }

    /**
     * this method return the id of the project annual
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }
}
