package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private List<Ruolo> ruoli;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
        this.ruoli = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String s) {
        this.email = s;
    }

    public String getPassword() {
        return password;
    }

    public void addRuolo(Ruolo r) {
        ruoli.add(r);
    }

    public void removeRuolo(Ruolo r) {
        ruoli.remove(r);
    }

    public List<Ruolo> getRuoli() {
        return ruoli;
    }
}
