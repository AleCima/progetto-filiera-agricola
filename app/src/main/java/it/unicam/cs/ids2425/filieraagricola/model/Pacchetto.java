package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;
import java.util.List;

public class Pacchetto extends Contenuto {
    String nome;
    List<Prodotto> listaProdotti;
    float prezzo;
    Venditore distributore;

    public Pacchetto(int id, Conferma statoConferma, Date dataCaricamento, String descrizione, int id1, String nome, List<Prodotto> listaProdotti, float prezzo, Venditore distributore) {
        super(id, statoConferma, dataCaricamento, descrizione);
        this.id = id1;
        this.nome = nome;
        this.listaProdotti = listaProdotti;
        this.prezzo = prezzo;
        this.distributore = distributore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Venditore getDistributore() {
        return distributore;
    }

    public void setDistributore(Venditore distributore) {
        this.distributore = distributore;
    }
}

