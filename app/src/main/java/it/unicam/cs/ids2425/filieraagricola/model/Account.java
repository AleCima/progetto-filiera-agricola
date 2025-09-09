package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;

public abstract class Account {
    String email;
    String password;
    List<Ruolo> ruoli;

    public Account(String email, String password, List<Ruolo> ruoli) {
        this.email = email;
        this.password = password;
        this.ruoli = ruoli;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String s) {
        this.email = s;
    }

    String getPassword() {
        return password;
    }

    List<Ruolo> getRuoli() {
        return ruoli;
    }
}
