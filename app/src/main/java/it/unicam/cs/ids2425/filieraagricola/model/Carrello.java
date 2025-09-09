package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

    // Attributi
    private int id;
    private double prezzoTotale;
    private List<Contenuto> contenuti;

    // Costruttore
    public Carrello(int id) {
        this.id = id;
        this.prezzoTotale = 0.0;
        this.contenuti = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    public void aggiungiContenuto(Contenuto contenuto, int quantita) {
        for (int i = 0; i < quantita; i++) {
            contenuti.add(contenuto);
        }
        prezzoTotale += contenuto.getPrezzo() * quantita;
    }

    public void rimuoviContenuto(Contenuto contenuto) {
        contenuti.remove(contenuto);
        prezzoTotale -= contenuto.getPrezzo();
    }
}

