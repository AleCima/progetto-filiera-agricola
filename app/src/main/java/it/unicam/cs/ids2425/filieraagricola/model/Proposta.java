package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

@Entity
public class Proposta {

    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String titolo;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "organizzatore_email", referencedColumnName = "email")
    private Utente organizzatore;

    @ManyToOne
    @JoinColumn(name = "destinatario_email", referencedColumnName = "email")
    private Venditore destinatario;

    private boolean accettata;

    public Proposta() {
    }
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

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }

    public int getId() {
        return id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setOrganizzatore(Utente organizzatore) {
        this.organizzatore = organizzatore;
    }

    public void setDestinatario(Venditore destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isAccettata() {
        return accettata;
    }
}

