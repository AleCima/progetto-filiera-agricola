package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;

public abstract class Account {
    private String email;
    private String password;
    private List<Ruolo> ruoli;

    public Account(String email, String password, List<Ruolo> ruoli) {
        this.email = email;
        this.password = password;
        this.ruoli = ruoli;
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

    public List<Ruolo> getRuoli() {
        return ruoli;
    }
}
