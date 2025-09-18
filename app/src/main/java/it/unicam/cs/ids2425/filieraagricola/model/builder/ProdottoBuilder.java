package it.unicam.cs.ids2425.filieraagricola.model.builder;

import it.unicam.cs.ids2425.filieraagricola.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricola.model.Trasformazione;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdottoBuilder implements ContenutoBuilder {

    private int id;
    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;
    private Venditore produttore;

    private String nome;
    private String metodoDiColtivazione;
    private List<String> certificazioni = new ArrayList<>();
    private List<Trasformazione> listaTrasformazioni = new ArrayList<>();
    private Date dataProduzione;

    //Metodi comuni
    @Override
    public ProdottoBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public ProdottoBuilder setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
        return this;
    }

    @Override
    public ProdottoBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    @Override
    public ProdottoBuilder setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    @Override
    public ProdottoBuilder setVenditore(Venditore venditore) {
        produttore = venditore;
        return this;
    }

    //Metodi specifici prodotto

    public ProdottoBuilder setDataProduzione(Date dataProduzione) {
        this.dataProduzione = dataProduzione;
        return this;
    }

    public ProdottoBuilder setListaTrasformazioni(List<Trasformazione> listaTrasformazioni) {
        this.listaTrasformazioni = listaTrasformazioni;
        return this;
    }

    public ProdottoBuilder setCertificazioni(List<String> certificazioni) {
        this.certificazioni = certificazioni;
        return this;
    }

    public ProdottoBuilder setMetodoDiColtivazione(String metodoDiColtivazione) {
        this.metodoDiColtivazione = metodoDiColtivazione;
        return this;
    }

    public ProdottoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public Prodotto build() {
        return new Prodotto(this);
    }

    //Getter

    public int getId() {
        return id;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Venditore getVenditore() {
        return produttore;
    }

    public String getNome() {
        return nome;
    }

    public String getMetodoDiColtivazione() {
        return metodoDiColtivazione;
    }

    public List<String> getCertificazioni() {
        return certificazioni;
    }

    public List<Trasformazione> getListaTrasformazioni() {
        return listaTrasformazioni;
    }

    public Date getDataProduzione() {
        return dataProduzione;
    }
}
