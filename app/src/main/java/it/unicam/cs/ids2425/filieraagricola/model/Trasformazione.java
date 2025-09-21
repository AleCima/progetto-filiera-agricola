package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.model.builder.TrasformazioneBuilder;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Trasformazione extends Contenuto {

    public Trasformazione(Date dataCaricamento,
                          String descrizione,
                          Venditore trasformatore,
                          double prezzo,
                          int quantita) {
        super(Conferma.ATTESA, dataCaricamento, descrizione, prezzo, trasformatore,quantita);
    }

    public Trasformazione(TrasformazioneBuilder builder){
        super(Conferma.ATTESA, builder.getDataCaricamento(), builder.getDescrizione(), builder.getPrezzo(), builder.getVenditore(), builder.getQuantita());
    }

}
