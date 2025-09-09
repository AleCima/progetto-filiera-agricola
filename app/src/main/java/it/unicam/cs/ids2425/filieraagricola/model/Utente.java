package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;

public class Utente extends Account{
    private String nome;
    private String cognome;
    private Carrello carrello;
    public Utente(String email, String password, String nome, String cognome) {
        super(email, password);
        this.nome = nome;
        this.cognome = cognome;
        this.carrello = new Carrello();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Carrello getCarrello() {
        return carrello;
    }
}
