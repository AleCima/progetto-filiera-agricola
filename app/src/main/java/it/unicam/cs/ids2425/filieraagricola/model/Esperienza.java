package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // importante per le sottoclassi Evento e Visita
public abstract class Esperienza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titolo;
    private String descrizione;

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

    // Costruttore completo
    public Esperienza(String titolo, String descrizione, Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.partecipanti = new ArrayList<>();
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.posizione = posizione;
    }

    // Costruttore minimo (utile per JPA)
    public Esperienza() {
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Utente organizzatore) {
        this.organizzatore = organizzatore;
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

    public void rimuoviPartecipante(Utente u) {
        partecipanti.remove(u);
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
