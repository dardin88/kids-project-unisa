/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common;

import java.util.ArrayList;

/**
 *
 * @author utente
 */
public class Mail {
    private ArrayList<String> to;
    private String subject;
    private String body;
    public  Mail(){
        
    }

    public String getBody() {
        return body;
    }

    

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }
    
    
    
}
