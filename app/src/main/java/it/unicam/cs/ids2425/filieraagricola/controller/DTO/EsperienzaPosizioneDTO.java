package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;

public class EsperienzaPosizioneDTO {
    private String titolo;
    private String descrizione;
    private PuntoMappa posizione;

    public EsperienzaPosizioneDTO(String titolo, String descrizione, PuntoMappa posizione) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.posizione = posizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }
}
