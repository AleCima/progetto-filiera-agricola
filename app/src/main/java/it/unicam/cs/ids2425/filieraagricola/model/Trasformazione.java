package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Trasformazione extends Contenuto {

    Venditore trasformatore;

    public Trasformazione(int id,
                          Date dataCaricamento,
                          String descrizione,
                          Venditore trasformatore) {
        super(id, Conferma.ATTESA, dataCaricamento, descrizione);
        this.trasformatore = trasformatore;
    }

    public Venditore getTrasformatore() {
        return trasformatore;
    }

    public void setTrasformatore(Venditore trasformatore) {
        this.trasformatore = trasformatore;
    }
}
