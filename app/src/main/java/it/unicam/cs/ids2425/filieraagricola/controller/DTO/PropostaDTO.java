package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.Utente;

public class PropostaDTO {
   private String titolo;
   private String descrizione;
   private String organizzatore;
   private String destinatario;
   private boolean accettata;

    public PropostaDTO(String titolo, String descrizione, String organizzatore, String destinatario, boolean accettata) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.destinatario = destinatario;
        this.accettata = accettata;
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

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }
}
