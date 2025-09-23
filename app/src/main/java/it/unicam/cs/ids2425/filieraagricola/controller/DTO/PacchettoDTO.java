package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.Conferma;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.Date;
import java.util.List;

public class PacchettoDTO {
    private String nome;
    private List<Integer> listaProdotti;
    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;
    private String emailDistributore;
    private int quantita;

    public PacchettoDTO(Date dataCaricamento, String descrizione, String nome, double prezzo, String emailDistributore, int quantita, List<Integer> listaProdotti) {
        this.dataCaricamento = dataCaricamento;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.emailDistributore = emailDistributore;
        this.quantita = quantita;
        this.nome = nome;
        this.listaProdotti = listaProdotti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Integer> listaProdotti) {
        this.listaProdotti = listaProdotti;
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

    public String getEmailDistributore() {
        return emailDistributore;
    }

    public void setEmailDistributore(String emailDistributore) {
        this.emailDistributore = emailDistributore;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
