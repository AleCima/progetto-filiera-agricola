package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.model.builder.PacchettoBuilder;
import it.unicam.cs.ids2425.filieraagricola.model.builder.ProdottoBuilder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pacchetto extends Contenuto {

    String nome;

    @ManyToMany
    @JoinTable(
            name = "pacchetto_contenuto",
            joinColumns = @JoinColumn(name = "pacchetto_id"),
            inverseJoinColumns = @JoinColumn(name = "contenuto_id")
    )
    private List<Contenuto> listaProdotti;

    public Pacchetto() {
        super();
    }

    public Pacchetto(Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore, int quantita, List<Contenuto> listaProdotti) {
        super(Conferma.ATTESA, dataCaricamento, descrizione, prezzo, distributore, quantita);
        this.nome = nome;
        this.listaProdotti = listaProdotti;
    }

    public Pacchetto(PacchettoBuilder builder) {
        super(Conferma.ATTESA, builder.getDataCaricamento(), builder.getDescrizione(), builder.getPrezzo(), builder.getVenditore(), builder.getQuantita());
        this.nome = builder.getNome();
        this.listaProdotti = builder.getListaContenuti();
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

    public void removeProdotto(Contenuto prodotto) {
        listaProdotti.remove(prodotto);
    }



}

