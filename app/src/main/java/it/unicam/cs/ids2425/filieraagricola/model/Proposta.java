package it.unicam.cs.ids2425.filieraagricola.model;

public class Proposta {

    // Attributi
    private String titolo;
    private String descrizione;
    private String organizzatore;
    private String destinatario;
    private String esperienza;
    private boolean accettata;

    // Costruttore
    public Proposta(String titolo, String descrizione, String organizzatore,
                    String destinatario, String esperienza, boolean accettata) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.destinatario = destinatario;
        this.esperienza = esperienza;
        this.accettata = accettata;
    }

    // Getter e Setter


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

    // Getter e Setter per il Destinatario
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEsperienza() {
        return esperienza;
    }

    public void setEsperienza(String esperienza) {
        this.esperienza = esperienza;
    }


    public boolean getAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }
}

