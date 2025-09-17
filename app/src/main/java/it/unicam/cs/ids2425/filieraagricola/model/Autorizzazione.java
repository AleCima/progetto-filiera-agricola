package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

@Entity
public class Autorizzazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "curatore_id")
    private Utente curatore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contenutoDaApprovare_id", referencedColumnName = "id")
    private Contenuto contenutoDaApprovare;

    private String motivazione;
    private boolean autorizzato;

    public Autorizzazione(Utente curatore, Contenuto contenutoDaApprovare, String motivazione, boolean autorizzato) {
        this.curatore = curatore;
        this.contenutoDaApprovare = contenutoDaApprovare;
        this.motivazione = motivazione;
        this.autorizzato = autorizzato;
    }

    public Utente getCuratore() {
        return curatore;
    }

    public Contenuto getContenutoDaApprovare() {
        return contenutoDaApprovare;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public boolean isAutorizzato() {
        return autorizzato;
    }
}
