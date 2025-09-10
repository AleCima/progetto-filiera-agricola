package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

    // Attributi
    private int id;
    private double prezzoTotale;
    private List<RigaCarrello> contenuti;

    // Costruttore
    public Carrello() {
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

    public List<RigaCarrello> getContenuti() {
        return contenuti;
    }

    public void aggiungiContenuto(RigaCarrello contenuto) {

        contenuti.add(contenuto);
        prezzoTotale += contenuto.getPrezzo();
    }

    public void rimuoviContenuto(RigaCarrello contenuto) {
        contenuti.remove(contenuto);
        prezzoTotale -= contenuto.getPrezzo();
    }

    public void svuota(){
        prezzoTotale = 0.0;
        contenuti = new ArrayList<>();
    }
}

