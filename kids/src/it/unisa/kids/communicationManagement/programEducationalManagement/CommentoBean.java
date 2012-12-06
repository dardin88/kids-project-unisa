/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.GregorianCalendar;

/**
 *
 * @author Giuseppe Alfieri
 */
public class CommentoBean implements java.io.Serializable {
    private int id;
    private GregorianCalendar date;
    private String tipoModifica;
    private String contenuto;
    private int idAnnuale;
    private int idSezione;
    private int idAutore;
    
    public CommentoBean(){
        this.date=new GregorianCalendar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getTipoModifica() {
        return tipoModifica;
    }

    public void setTipoModifica(String tipoModifica) {
        this.tipoModifica = tipoModifica;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public int getIdAnnuale() {
        return idAnnuale;
    }

    public void setIdAnnuale(int idAnnuale) {
        this.idAnnuale = idAnnuale;
    }

    public int getIdSezione() {
        return idSezione;
    }

    public void setIdSezione(int idSezione) {
        this.idSezione = idSezione;
    }

    public int getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }
    
    
}
