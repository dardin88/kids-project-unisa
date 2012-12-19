/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.GregorianCalendar;

/**
 *
 * @author navi
 */
public class CommentBean {

    public static final String ANNUAL_COMMENT = "annual_comm";
    public static final String CLASS_COMMENT = "class_comm";
    private int id;
    private GregorianCalendar date;
    private String content;
    private int annualId;
    private int classId;
    private int authorId;

    public CommentBean() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public GregorianCalendar getDate() {
        return this.date;
    }

    public void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String pContent) {
        this.content = pContent;
    }

    public int getAnnualId() {
        return this.annualId;
    }

    public void setAnnualId(int pAnnualId) {
        this.annualId = pAnnualId;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int pClassId) {
        this.classId = pClassId;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int pAuthorId) {
        this.authorId = pAuthorId;
    }
}
