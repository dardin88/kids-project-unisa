/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.timeServiceManagement;

/**
 *
 * @author marco
 */
public class ModifyTimeServiceRequest {
    private int id;
    private String motivation;
    private String userRange;
    private int idChild;
    private int idParent;
    private String state;
    private String opinion;
    
    public String getOpinion(){
        return opinion;
    }
    
    public void setOpinion(String pOpinion){
        opinion=pOpinion;
    }

    public String getState(){
        return state;
    }
    public void setState(String pState){
        state=pState;
    }
    public int getId() {
        return id;
    }

    public int getIdChild() {
        return idChild;
    }

    public int getIdParent() {
        return idParent;
    }

    public String getMotivation() {
        return motivation;
    }

    public String getUserRange() {
        return userRange;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public void setIdChild(int pIdChild) {
        this.idChild = pIdChild;
    }

    public void setIdParent(int pIdParent) {
        this.idParent = pIdParent;
    }

    public void setMotivation(String pMotivation) {
        this.motivation = pMotivation;
    }

    public void setUserRange(String pUserRange) {
        this.userRange = pUserRange;
    }
    
    
}
