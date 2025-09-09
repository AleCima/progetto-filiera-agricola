package it.unicam.cs.ids2425.filieraagricola.model;

public class Visita extends Esperienza{

    private Venditore azienda;
    public Visita(String organizzatore, String dataEsperienza, int partecipanti, int numMaxPartecipanti, Venditore azienda) {
        super(organizzatore, dataEsperienza, partecipanti, numMaxPartecipanti);
        this.azienda = azienda;
    }

    public Venditore getAzienda() {
        return azienda;
    }
}
