package it.unisa.kids.accessManagement.classificationManagement;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class Result {
    private int registrationChildId;
    private String registrationChildFiscalCode;
    private String registrationChildSurname;
    private String registrationChildName;
    private int classificationId;
    
    private double score;
    private boolean result;
    private boolean isSetResult;

    public Result() {
        this.score = -1;    // valore null dello score, in quanto il punteggio può essere solo positivo o al minimo 0
    }
    
    public void setRegistrationChildId(int registrationChildId) {
        this.registrationChildId = registrationChildId;
    }

    protected void setRegistrationChildFiscalCode(String registrationChildFiscalCode) {
        this.registrationChildFiscalCode = registrationChildFiscalCode;
    }

    protected void setRegistrationChildSurname(String registrationChildSurname) {
        this.registrationChildSurname = registrationChildSurname;
    }

    protected void setRegistrationChildName(String registrationChildName) {
        this.registrationChildName = registrationChildName;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setResult(boolean result) {
        this.result = result;
        this.isSetResult = true;
    }

    public int getRegistrationChildId() {
        return registrationChildId;
    }

    public String getRegistrationChildFiscalCode() {
        return registrationChildFiscalCode;
    }

    public String getRegistrationChildSurname() {
        return registrationChildSurname;
    }

    public String getRegistrationChildName() {
        return registrationChildName;
    }

    public boolean isSetResult() {
        return isSetResult;
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
