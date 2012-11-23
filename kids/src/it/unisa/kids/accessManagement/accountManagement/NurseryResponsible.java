/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

/**
 *
 * @author Gianmarco
 */
public class NurseryResponsible extends Account {
    
    private String parentType;
    private String type;
    
    
    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
