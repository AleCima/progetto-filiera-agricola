package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Trasformazione extends Contenuto {

    public Trasformazione(int id,
                          Date dataCaricamento,
                          String descrizione,
                          Venditore trasformatore,
                          double prezzo) {
        super(id, Conferma.ATTESA, dataCaricamento, descrizione, prezzo, trasformatore);
    }

}
