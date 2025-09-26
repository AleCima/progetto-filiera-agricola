package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Visita extends Esperienza {

    @ManyToOne
    @JoinColumn(name = "azienda_email", referencedColumnName = "email")
    private Venditore azienda;

    public Visita(String titolo, String descrizione, Utente organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione, Venditore azienda) {
        super(titolo, "Visita", organizzatore, dataEsperienza, numMaxPartecipanti, posizione);
        this.azienda = azienda;
    }

    public Visita() {
        super();
    }

    public Venditore getAzienda() {
        return azienda;
    }

    public void setAzienda(Venditore azienda) {
        this.azienda = azienda;
    }
}
