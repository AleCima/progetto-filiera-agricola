package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Visita extends Esperienza {

    @OneToOne(cascade = CascadeType.ALL)
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
