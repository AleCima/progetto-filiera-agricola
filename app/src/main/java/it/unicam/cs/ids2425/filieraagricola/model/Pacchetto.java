package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pacchetto extends Contenuto {
    String nome;
    List<Contenuto> listaProdotti;
    double prezzo;
    Venditore distributore;

    public Pacchetto(int id, Conferma statoConferma, Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore) {
        super(id, statoConferma, dataCaricamento, descrizione);
        this.nome = nome;
        this.listaProdotti = new ArrayList<>();
        this.prezzo = prezzo;
        this.distributore = distributore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Contenuto> getListaProdotti() {
        return listaProdotti;
    }

    public void addProdotto(Contenuto prodotto) {
        listaProdotti.add(prodotto);
    }

    public void removeProdotto(Contenuto contenuto) {
        listaProdotti.remove(contenuto);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Venditore getDistributore() {
        return distributore;
    }

    public void setDistributore(Venditore distributore) {
        this.distributore = distributore;
    }
}

