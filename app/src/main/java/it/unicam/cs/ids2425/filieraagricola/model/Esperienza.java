package it.unicam.cs.ids2425.filieraagricola.model;

public abstract class Esperienza {
    private String organizzatore;
    private String dataEsperienza;
    private List<Utente> partecipanti;
    private int numMaxPartecipanti;

    public Esperienza(String organizzatore, String dataEsperienza, int numMaxPartecipanti) {
        this.organizzatore = organizzatore;
        this.dataEsperienza = dataEsperienza;
        this.partecipanti = new ArrayList<Utente>();
        this.numMaxPartecipanti = numMaxPartecipanti;
    }

    public String getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(String organizzatore) {
        this.organizzatore = organizzatore;
    }

    public String getDataEsperienza() {
        return dataEsperienza;
    }

    public void setDataEsperienza(String dataEsperienza) {
        this.dataEsperienza = dataEsperienza;
    }

    public List<Utente> getPartecipanti() {
        return partecipanti;
    }

    public void aggiungiPartecipante(Utente u) {
        partecipanti.add(u);
    }

    public int getNumMaxPartecipanti() {
        return numMaxPartecipanti;
    }

    public void setNumMaxPartecipanti(int numMaxPartecipanti) {
        this.numMaxPartecipanti = numMaxPartecipanti;
    }
}
