package it.unicam.cs.ids2425.filieraagricola.model;
import java.util.List;

public class PacchettoBuilder extends ContenutoBuilder<ProdottoBuilder>{
    String nome;
    private List<Contenuto> listaProdotti;

    public PacchettoBuilder setNome(String nome) {this.nome = nome; return this;}
    public PacchettoBuilder setListaProdotti(List<Contenuto> listaProdotti) {this.listaProdotti = listaProdotti; return this;}


    protected PacchettoBuilder self() {return this;}

    public Pacchetto build() {
        return new Pacchetto(this);
    }
}
