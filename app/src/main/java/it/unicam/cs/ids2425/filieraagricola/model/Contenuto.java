package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public abstract class Contenuto {
    int id;
    Conferma statoConferma;
    Date dataCaricamento;
    String descrizione;

    public Contenuto(int id, Conferma statoConferma, Date dataCaricamento, String descrizione) {
        this.id = id;
        this.statoConferma = statoConferma;
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
    }

    public void setStatoConferma(Conferma statoConferma) {
        this.statoConferma = statoConferma;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Conferma getStatoConferma() {
        return statoConferma;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }
}

