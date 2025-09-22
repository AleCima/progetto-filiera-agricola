package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Evento extends Esperienza {

    public Evento(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        super(organizzatore, dataEsperienza, numMaxPartecipanti, posizione);
    }

    public Evento() {
        super();
    }
}
