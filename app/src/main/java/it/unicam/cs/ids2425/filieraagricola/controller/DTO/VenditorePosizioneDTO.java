package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;

public class VenditorePosizioneDTO {
    private String email;
    private String descrizione;
    private PuntoMappa posizione;

    public VenditorePosizioneDTO(String email, String descrizione, PuntoMappa posizione) {
        this.email = email;
        this.descrizione = descrizione;
        this.posizione = posizione;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }
}
