package it.unicam.cs.ids2425.filieraagricola.model;

public class Proposta {

    // Attributi
    private String titolo;
    private String descrizione;
    private Utente organizzatore;
    private Venditore destinatario;
    private boolean accettata;

    // Costruttore
    public Proposta(String titolo, String descrizione, Utente organizzatore,
                    Venditore destinatario, boolean accettata) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.destinatario = destinatario;
        this.accettata = accettata;
    }

    // Getter e Setter


    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }
    // Getter e Setter per il Destinatario
    public Venditore getDestinatario() {
        return destinatario;
    }

    public boolean getAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }
}

