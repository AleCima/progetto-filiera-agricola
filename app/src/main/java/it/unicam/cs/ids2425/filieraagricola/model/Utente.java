package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente implements Account {
    @Id
    private String email;

    private String password;

    @ElementCollection(targetClass = Ruolo.class)
    @CollectionTable(name = "ruoliUtente", joinColumns = @JoinColumn(name = "email"))
    @Column(name = "ruolo")
    @Enumerated(EnumType.STRING)
    private List<Ruolo> ruoli = new ArrayList<>();

    private String nome;
    private String cognome;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrello_id", referencedColumnName = "id")
    private Carrello carrello;

    public Utente(){}

    public Utente(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.carrello = new Carrello();
        ruoli.add(Ruolo.ACQUIRENTE);
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
    public void setCarrello(Carrello carrello){
        this.carrello = carrello;
    }


    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public List<Ruolo> getRuoli() {
        return ruoli;
    }

    @Override
    public void addRuolo(Ruolo ruolo) {
        if (!ruoli.contains(ruolo)){
            ruoli.add(ruolo);
        }
    }

    @Override
    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli.clear();
        this.ruoli.addAll(ruoli);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void removeRuolo(Ruolo ruolo) {
        ruoli.remove(ruolo);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
