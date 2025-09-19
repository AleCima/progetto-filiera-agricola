package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.model.builder.PacchettoBuilder;
import it.unicam.cs.ids2425.filieraagricola.model.builder.ProdottoBuilder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pacchetto extends Contenuto {

    String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contenuto_id")
    private List<Contenuto> listaProdotti;

    public Pacchetto(int id, Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore) {
        super(id, Conferma.ATTESA, dataCaricamento, descrizione, prezzo, distributore);
        this.nome = nome;
        this.listaProdotti = new ArrayList<>();
    }

    public Pacchetto(PacchettoBuilder builder) {
        super(builder.getId(), Conferma.ATTESA, builder.getDataCaricamento(), builder.getDescrizione(), builder.getPrezzo(), builder.getVenditore());
        this.nome = builder.getNome();
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
        //Aggiorna prezzo pacchetto
        setPrezzo(getPrezzo() + prodotto.getPrezzo());
    }

    public void removeProdotto(Contenuto prodotto) {
        listaProdotti.remove(prodotto);
        setPrezzo(getPrezzo() - prodotto.getPrezzo());
    }



}

