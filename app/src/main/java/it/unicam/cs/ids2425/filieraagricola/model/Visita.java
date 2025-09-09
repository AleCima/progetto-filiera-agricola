package it.unicam.cs.ids2425.filieraagricola.model;

public class Visita extends Esperienza{

    private Venditore azienda;
    public Visita(String organizzatore, String dataEsperienza, int partecipanti, int numMaxPartecipanti) {
        super(organizzatore, dataEsperienza, partecipanti, numMaxPartecipanti);
    }

    public Venditore getAzienda() {
        return azienda;
    }
}
