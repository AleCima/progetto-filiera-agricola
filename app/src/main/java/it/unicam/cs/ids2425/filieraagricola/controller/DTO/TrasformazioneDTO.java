package it.unicam.cs.ids2425.filieraagricola.controller.DTO;


import java.util.Date;

public class TrasformazioneDTO {
    private Date dataCaricamento;
    private String descrizione;
    private String emailTrasformatore;
    private double prezzo;
    private int quant;

    public TrasformazioneDTO(Date dataCaricamento,
                          String descrizione,
                          String emailTrasformatore,
                          double prezzo,
                          int quantita) {
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
        this.emailTrasformatore = emailTrasformatore;
        this.prezzo = prezzo;
        this.quant = quantita;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getEmailTrasformatore() {
        return emailTrasformatore;
    }

    public void setEmailTrasformatore(String emailTrasformatore) {
        this.emailTrasformatore = emailTrasformatore;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
}
