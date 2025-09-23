package it.unicam.cs.ids2425.filieraagricola.model.builder;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.Date;
import java.util.List;


public class PacchettoBuilder implements ContenutoBuilder{
    private int id;
    private Date dataCaricamento;
    private String descrizione;
    private String nome;
    private double prezzo;
    private Venditore distributore;
    private int quantita;
    private List<Contenuto> listaContenuti;

    //Metodi comuni
    @Override
    public PacchettoBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public PacchettoBuilder setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
        return this;
    }

    @Override
    public PacchettoBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    @Override
    public PacchettoBuilder setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    @Override
    public PacchettoBuilder setVenditore(Venditore venditore) {
        distributore = venditore;
        return this;
    }

    @Override
    public PacchettoBuilder setQuantita(int quantita) {
        this.quantita = quantita;
        return this;
    }

    public PacchettoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PacchettoBuilder setListaContenuti(List<Contenuto> listaContenuti) {
        this.listaContenuti = listaContenuti;
        return this;
    }

    @Override
    public Pacchetto build() {
        return new Pacchetto(this);
    }

    public int getId() {
        return id;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Venditore getVenditore() {
        return distributore;
    }

    public int getQuantita() {
        return quantita;
    }

    public List<Contenuto> getListaContenuti() {
        return listaContenuti;
    }
}
