package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Visita extends Esperienza{

    private final Venditore azienda;
    public Visita(Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, Venditore azienda) {
        super(organizzatore, dataEsperienza, numMaxPartecipanti);
        this.azienda = azienda;
    }

    public Venditore getAzienda() {
        return azienda;
    }
}
