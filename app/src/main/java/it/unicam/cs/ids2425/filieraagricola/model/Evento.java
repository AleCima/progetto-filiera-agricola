package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Evento extends Esperienza {

    public Evento(String titolo, String descrizione, Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        super(titolo, "Evento", organizzatore, dataEsperienza, numMaxPartecipanti, posizione);
    }

    public Evento() {
        super();
    }
}
