package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double prezzoTotale;

    // Un carrello può avere più righe
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrello_id")
    private List<RigaCarrello> contenuti;

    // Costruttore
    public Carrello() {
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

    public void aggiungiQuantita(Contenuto c, int quant){
        for (RigaCarrello rigaCarrello : contenuti) {
            if(rigaCarrello.getContenuto().equals(c)) {
                prezzoTotale =- rigaCarrello.getPrezzo();
                rigaCarrello.setQuantita(rigaCarrello.getQuantita() + quant);
                prezzoTotale =+ rigaCarrello.getPrezzo();
            }
        }
    }

    public void rimuoviQuantita(Contenuto c, int quant){
        for (RigaCarrello rc : contenuti) {
            if (rc.getContenuto().equals(c)) {
                if (rc.getQuantita() - quant < 1) {
                    prezzoTotale = prezzoTotale - rc.getPrezzo();
                    contenuti.remove(rc);
                    return;
                } else {
                    rc.setQuantita(rc.getQuantita() - quant);
                    prezzoTotale = prezzoTotale - (rc.getContenuto().getPrezzo() * quant);
                }
            }
        }
    }

    public void rimuoviContenuto(RigaCarrello contenuto) {
        contenuti.remove(contenuto);
        prezzoTotale -= contenuto.getPrezzo();
    }

    public void svuota() {
        contenuti.clear();
        prezzoTotale = 0.0;
    }
}

