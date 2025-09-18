package it.unicam.cs.ids2425.filieraagricola.model.builder;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.Date;

public interface ContenutoBuilder {

    ContenutoBuilder setId(int id);

    ContenutoBuilder setDataCaricamento(Date data);

    ContenutoBuilder setDescrizione(String descrizione);

    ContenutoBuilder setPrezzo(double prezzo);

    ContenutoBuilder setVenditore(Venditore venditore);

    Contenuto build();       // restituisce il tipo base, il builder concreto può ritornare anche sottotipo
}
