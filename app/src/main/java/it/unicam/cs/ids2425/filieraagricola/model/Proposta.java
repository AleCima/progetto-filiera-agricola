package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proposta {

    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String titolo;
    private String descrizione;
    private Utente organizzatore;
    private Venditore destinatario;
    private boolean accettata;

    // Costruttore
    public Proposta(String titolo, String descrizione, Utente organizzatore,
                    Venditore destinatario) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.destinatario = destinatario;
        this.accettata = false;
    }

    // Getter e Setter
    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }

    public Venditore getDestinatario() {
        return destinatario;
    }

    public boolean getAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }
}

