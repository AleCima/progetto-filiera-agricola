package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Evento extends Esperienza{

    public Evento(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti) {
        super(organizzatore, dataEsperienza, numMaxPartecipanti);
    }
}
