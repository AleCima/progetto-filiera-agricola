package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;

public interface Account {

    String getEmail();

    List<Ruolo> getRuoli();

    void addRuolo(Ruolo ruolo);

    void setRuoli(List<Ruolo> ruoli);

    String getPassword();

    void removeRuolo(Ruolo ruolo);

}
