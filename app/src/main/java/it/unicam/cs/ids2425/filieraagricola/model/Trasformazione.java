package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.model.builder.TrasformazioneBuilder;
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

    public Trasformazione(TrasformazioneBuilder builder){
        super(builder.getId(), Conferma.ATTESA, builder.getDataCaricamento(), builder.getDescrizione(), builder.getPrezzo(), builder.getVenditore());
    }

}
