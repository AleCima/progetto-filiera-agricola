package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pacchetto extends Contenuto {
    String nome;
    List<Contenuto> listaProdotti;
    Venditore distributore;

    public Pacchetto(int id, Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore) {
        super(id, Conferma.ATTESA, dataCaricamento, descrizione, prezzo);
        this.nome = nome;
        this.listaProdotti = new ArrayList<>();
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
        //Aggiora prezzo pacchetto
        setPrezzo(getPrezzo() + prodotto.getPrezzo());
    }

    public void removeProdotto(Contenuto prodotto) {
        listaProdotti.remove(prodotto);
        setPrezzo(getPrezzo() - prodotto.getPrezzo());
    }

    public Venditore getDistributore() {
        return distributore;
    }

    public void setDistributore(Venditore distributore) {
        this.distributore = distributore;
    }
}

