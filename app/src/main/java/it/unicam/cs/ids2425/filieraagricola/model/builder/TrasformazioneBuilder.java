package it.unicam.cs.ids2425.filieraagricola.model.builder;

import it.unicam.cs.ids2425.filieraagricola.model.Trasformazione;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.Date;

public class TrasformazioneBuilder implements ContenutoBuilder{

    private int id;
    private Date dataCaricamento;
    private String descrizione;
    private double prezzo;
    private Venditore trasformatore;

    //Metodi comuni
    @Override
    public ContenutoBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public ContenutoBuilder setDataCaricamento(Date data) {
        dataCaricamento = data;
        return this;
    }

    @Override
    public ContenutoBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    @Override
    public ContenutoBuilder setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    @Override
    public ContenutoBuilder setVenditore(Venditore venditore) {
        trasformatore = venditore;
        return this;
    }

    @Override
    public Trasformazione build() {
        return new Trasformazione(this);
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

    public double getPrezzo() {
        return prezzo;
    }

    public Venditore getVenditore() {
        return trasformatore;
    }
}
