package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;

import java.util.Date;

public class EventoDTO {
    private String titolo;
    private String descrizione;
    private String organizzatore;   // email dell'organizzatore
    private Date dataEsperienza;
    private int numMaxPartecipanti;
    private PuntoMappa posizione;  // oggetto semplificato

    public EventoDTO() {
    }

    public EventoDTO(String titolo, String descrizione, String organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.posizione = posizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(String organizzatore) {
        this.organizzatore = organizzatore;
    }

    public Date getDataEsperienza() {
        return dataEsperienza;
    }

    public void setDataEsperienza(Date dataEsperienza) {
        this.dataEsperienza = dataEsperienza;
    }

    public int getNumMaxPartecipanti() {
        return numMaxPartecipanti;
    }

    public void setNumMaxPartecipanti(int numMaxPartecipanti) {
        this.numMaxPartecipanti = numMaxPartecipanti;
    }

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }
}
