package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public abstract class Esperienza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;

    private Date dataEsperienza;

    @ManyToMany
    @JoinTable(
            name = "esperienza_partecipanti",
            joinColumns = @JoinColumn(name = "esperienza_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_email")
    )
    private List<Utente> partecipanti = new ArrayList<>();

    private int numMaxPartecipanti;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posizione_id", referencedColumnName = "id")
    private PuntoMappa posizione;

    public Esperienza(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.partecipanti = new ArrayList<>();
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.posizione = posizione;
    }

    public Esperienza(){

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


    public void setOrganizzatore(Utente utente) {
        organizzatore = utente;
    }
}
