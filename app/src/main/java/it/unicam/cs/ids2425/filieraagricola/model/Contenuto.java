package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Contenuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private Conferma statoConferma;

    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "venditore_email")
    private Venditore venditore;

    public Contenuto(){}
    public Contenuto(Conferma statoConferma, Date dataCaricamento, String descrizione, double prezzo, Venditore venditore, int quantita) {
        this.statoConferma = statoConferma;
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
        this.venditore = venditore;
        this.prezzo = prezzo;
        this.quantita = quantita;
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

    public int getQuantita() {
        return quantita;
    }

    public void setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setVenditore(Venditore venditore) {
        this.venditore = venditore;
    }

    public Venditore getVenditore(){return venditore; }
}

