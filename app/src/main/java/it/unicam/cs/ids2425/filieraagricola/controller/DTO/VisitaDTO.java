package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;

import java.util.Date;

public class VisitaDTO {
    private String organizzatore;   // email dell'organizzatore
    private Date dataEsperienza;
    private int numMaxPartecipanti;
    private PuntoMappa posizione;
    private String azienda;         // email del venditore

    public VisitaDTO() {
    }

    public VisitaDTO(String organizzatore, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione, String azienda) {
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.posizione = posizione;
        this.azienda = azienda;
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

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }
}
