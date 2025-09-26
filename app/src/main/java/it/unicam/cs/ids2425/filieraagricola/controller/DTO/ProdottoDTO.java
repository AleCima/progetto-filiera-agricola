package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.Conferma;
import it.unicam.cs.ids2425.filieraagricola.model.Trasformazione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdottoDTO {
    private Conferma stato;
    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;
    private String emailProduttore;
    private int quantita;
    private String nome;
    private String metodoDiColtivazione;
    private List<String> certificazioni;
    private List<Trasformazione> listaTrasformazioni;
    private Date dataProduzione;

    public ProdottoDTO(Date dataCaricamento,
                       String descrizione,
                       String nome,
                       String metodoDiColtivazione,
                       double prezzo,
                       String produttoreEmail,
                       List<String> certificazioni,
                       Date dataProduzione,
                       int quantita) {
        this.stato = Conferma.ATTESA;
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.emailProduttore = produttoreEmail;
        this.quantita = quantita;
        this.nome = nome;
        this.metodoDiColtivazione = metodoDiColtivazione;
        this.certificazioni = certificazioni;
        this.listaTrasformazioni = new ArrayList<>();
        this.dataProduzione = dataProduzione;
    }

    public Conferma getStato() {
        return stato;
    }

    public void setStato(Conferma stato) {
        this.stato = stato;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getEmailProduttore() {
        return emailProduttore;
    }

    public void setEmailProduttore(String email) {
        this.emailProduttore = email;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMetodoDiColtivazione() {
        return metodoDiColtivazione;
    }

    public void setMetodoDiColtivazione(String metodoDiColtivazione) {
        this.metodoDiColtivazione = metodoDiColtivazione;
    }

    public List<String> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(List<String> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public List<Trasformazione> getListaTrasformazioni() {
        return listaTrasformazioni;
    }

    public void setListaTrasformazioni(List<Trasformazione> listaTrasformazioni) {
        this.listaTrasformazioni = listaTrasformazioni;
    }

    public Date getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(Date dataProduzione) {
        this.dataProduzione = dataProduzione;
    }
}
