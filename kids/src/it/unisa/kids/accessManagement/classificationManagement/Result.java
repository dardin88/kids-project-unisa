/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.classificationManagement;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class Result {
    private int registrationChildId;
    private int classificationId;
    private boolean result;

    public Result() {
    }
    
    public void setRegistrationChildId(int registrationChildId) {
        this.registrationChildId = registrationChildId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getRegistrationChildId() {
        return registrationChildId;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public boolean getResult() {
        return result;
    }
}
