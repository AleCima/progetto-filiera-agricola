package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Esperienza {
    private Utente organizzatore;
    private Date dataEsperienza;
    private List<Utente> partecipanti;
    private int numMaxPartecipanti;

    public Esperienza(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti) {
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.partecipanti = new ArrayList<>();
        this.numMaxPartecipanti = numMaxPartecipanti;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }

    public Date getDataEsperienza() {
        return dataEsperienza;
    }

    public void setDataEsperienza(Date dataEsperienza) {
        this.dataEsperienza = dataEsperienza;
    }

    public List<Utente> getPartecipanti() {
        return partecipanti;
    }

    public void aggiungiPartecipante(Utente u) {
        partecipanti.add(u);
    }

    public int getNumMaxPartecipanti() {
        return numMaxPartecipanti;
    }

    public void setNumMaxPartecipanti(int numMaxPartecipanti) {
        this.numMaxPartecipanti = numMaxPartecipanti;
    }
}
