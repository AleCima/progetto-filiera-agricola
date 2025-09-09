package it.unicam.cs.ids2425.filieraagricola.model;

public class RigaCarrello {

    private Contenuto contenuto;
    private int quantita;
    private double prezzo;

    public RigaCarrello(Contenuto contenuto, int quantita, double prezzo) {
        this.contenuto = contenuto;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

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
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
