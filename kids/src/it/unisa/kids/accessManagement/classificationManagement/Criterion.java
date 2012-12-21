package it.unisa.kids.accessManagement.classificationManagement;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class Criterion {
    private int id;
    private String description;
    private String dbFieldsTable;    // Field's Table, tabella in cui cercare il campo
    private String dbField;
    private String comparator;
    private String condition;
    private double weight;
    private boolean isSetWeight;    // altrimenti non sarebbe possibile controllare se è stato impostato il peso
    private boolean active;
    private boolean isSetActive;
    
    public Criterion() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        isSetWeight = true;
    }

    public String getDbFieldsTable() {
        return dbFieldsTable;
    }

    public void setDbFieldsTable(String dbTableField) {
        this.dbFieldsTable = dbTableField;
    }

    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
        this.isSetActive = true;
    }
    
    public boolean isSetWeight() {
        return isSetWeight;
    }
    public boolean isSetActive() {
        return isSetActive;
    }
}
