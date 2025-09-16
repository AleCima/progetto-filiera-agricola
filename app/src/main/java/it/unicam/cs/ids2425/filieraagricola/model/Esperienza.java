package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public abstract class Esperienza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private Utente organizzatore;
    private Date dataEsperienza;
    private List<Utente> partecipanti;
    private int numMaxPartecipanti;
    private PuntoMappa posizione;

    public Esperienza(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.partecipanti = new ArrayList<>();
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.posizione = posizione;
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

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }
}
