package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

@Entity
public class RigaCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contenuto_id", referencedColumnName = "id")
    private Contenuto contenuto;

    private int quantita;
    private double prezzo;

    public RigaCarrello(Contenuto contenuto, int quantita) {
        this.contenuto = contenuto;
        this.quantita = quantita;
        this.prezzo = contenuto.getPrezzo() * quantita;
    }
    public RigaCarrello(){}

    public Contenuto getContenuto() {
        return contenuto;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
        this.prezzo = contenuto.getPrezzo() * quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }
}
