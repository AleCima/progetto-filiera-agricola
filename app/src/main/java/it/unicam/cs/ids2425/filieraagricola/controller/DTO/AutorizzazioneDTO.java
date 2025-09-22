package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;

public class AutorizzazioneDTO {
    private int id;
    private Utente curatore;
    private Contenuto contenutoDaApprovare;

    private String motivazione;
    private boolean autorizzato;

    public AutorizzazioneDTO(Utente curatore, Contenuto contenutoDaApprovare, String motivazione, boolean autorizzato) {
        this.curatore = curatore;
        this.contenutoDaApprovare = contenutoDaApprovare;
        this.motivazione = motivazione;
        this.autorizzato = autorizzato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getCuratore() {
        return curatore;
    }

    public void setCuratore(Utente curatore) {
        this.curatore = curatore;
    }

    public Contenuto getContenutoDaApprovare() {
        return contenutoDaApprovare;
    }

    public void setContenutoDaApprovare(Contenuto contenutoDaApprovare) {
        this.contenutoDaApprovare = contenutoDaApprovare;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public boolean isAutorizzato() {
        return autorizzato;
    }

    public void setAutorizzato(boolean autorizzato) {
        this.autorizzato = autorizzato;
    }
}
