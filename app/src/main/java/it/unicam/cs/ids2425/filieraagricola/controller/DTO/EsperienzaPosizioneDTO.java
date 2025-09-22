package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

public class EsperienzaPosizioneDTO {
    private String titolo;
    private String descrizione;
    private double latitudine;
    private double longitudine;

    public EsperienzaPosizioneDTO(String titolo, String descrizione, double latitudine, double longitudine) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }
}
