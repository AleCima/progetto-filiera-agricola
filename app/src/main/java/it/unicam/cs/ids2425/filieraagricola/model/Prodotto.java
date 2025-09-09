package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;
import java.util.List;

public class Prodotto extends Contenuto {
    String nome;
    String metodoDiColtivazione;
    float prezzoUnitario;
    Venditore produttore;
    List<String> certificazioni;
    List<Trasformazione> listaTrasformazioni;
    Date dataProduzione;

    public Prodotto(int id, Conferma statoConferma, Date dataCaricamento, String descrizione, String nome, String metodoDiColtivazione, float prezzoUnitario, Venditore produttore, List<String> certificazioni, List<Trasformazione> listaTrasformazioni, Date dataProduzione) {
        super(id, statoConferma, dataCaricamento, descrizione);
        this.nome = nome;
        this.metodoDiColtivazione = metodoDiColtivazione;
        this.prezzoUnitario = prezzoUnitario;
        this.produttore = produttore;
        this.certificazioni = certificazioni;
        this.listaTrasformazioni = listaTrasformazioni;
        this.dataProduzione = dataProduzione;
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

    public float getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(float prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public Venditore getProduttore() {
        return produttore;
    }

    public void setProduttore(Venditore produttore) {
        this.produttore = produttore;
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
