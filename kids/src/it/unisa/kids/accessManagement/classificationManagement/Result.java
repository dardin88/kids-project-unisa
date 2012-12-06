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
    private double score;
    private boolean result;

    public Result() {
        this.score = -1;    // valore null dello score, in quanto il punteggio può essere solo positivo o al minimo 0
    }
    
    public void setRegistrationChildId(int registrationChildId) {
        this.registrationChildId = registrationChildId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public void setScore(double score) {
        this.score = score;
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

    public double getScore() {
        return score;
    }

    public boolean getResult() {
        return result;
    }
}
