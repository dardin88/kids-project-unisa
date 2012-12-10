package it.unisa.kids.accessManagement.renunciationManagement;

import java.util.GregorianCalendar;

public class Renunciation {

    private int id;
    private String motivazione;
    private int idBambino;
    private int conferma;

    public Renunciation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBambino() {
        return idBambino;
    }

    public void setIdBambino(int idBambino) {
        this.idBambino = idBambino;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public int isConferma() {
        return conferma;
    }

    public void setConferma(int conferma) {
        this.conferma = conferma;
    }
}
