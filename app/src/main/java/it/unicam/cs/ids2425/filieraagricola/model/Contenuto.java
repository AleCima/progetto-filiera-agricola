package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contenuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private Conferma statoConferma;

    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;

    @ManyToOne
    @JoinColumn(name = "venditore_email", referencedColumnName = "email")
    private Venditore venditore;

    public Contenuto(int id, Conferma statoConferma, Date dataCaricamento, String descrizione, double prezzo, Venditore venditore) {
        this.id = id;
        this.statoConferma = statoConferma;
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Venditore getVenditore(){return venditore; }
}

