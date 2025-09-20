package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.model.builder.ProdottoBuilder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Prodotto extends Contenuto {
    private String nome;
    private String metodoDiColtivazione;
    private List<String> certificazioni;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trasformazione_id")
    private List<Trasformazione> listaTrasformazioni;
    private Date dataProduzione;

    public Prodotto(int id,
                    Date dataCaricamento,
                    String descrizione,
                    String nome,
                    String metodoDiColtivazione,
                    double prezzo,
                    Venditore produttore,
                    List<String> certificazioni,
                    Date dataProduzione,
                    int quantita) {
        super(id, Conferma.ATTESA, dataCaricamento, descrizione, prezzo, produttore, quantita);
        this.nome = nome;
        this.metodoDiColtivazione = metodoDiColtivazione;
        this.certificazioni = certificazioni;
        this.listaTrasformazioni = new ArrayList<>();
        this.dataProduzione = dataProduzione;
    }

    public Prodotto(ProdottoBuilder builder) {
        super(builder.getId(), Conferma.ATTESA, builder.getDataCaricamento(), builder.getDescrizione(), builder.getPrezzo(), builder.getVenditore(), builder.getQuantita());
        this.nome = builder.getNome();
        this.metodoDiColtivazione = builder.getMetodoDiColtivazione();
        this.certificazioni = builder.getCertificazioni();
        this.listaTrasformazioni = builder.getListaTrasformazioni();
        this.dataProduzione = builder.getDataProduzione();
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

    public void addTrasformazione(Trasformazione trasformazione) {
        listaTrasformazioni.add(trasformazione);
    }

    public void removeTrasformazione(Trasformazione trasformazione) {
        listaTrasformazioni.remove(trasformazione);
    }

    public Date getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(Date dataProduzione) {
        this.dataProduzione = dataProduzione;
    }
}
