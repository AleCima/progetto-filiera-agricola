package it.unicam.cs.ids2425.filieraagricola.model;

public class Autorizzazione {
    Utente curatore;
    Contenuto contenutoDaApprovare;
    String motivazione;
    boolean autorizzato;

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
