package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;
import java.util.Set;

public interface Account {

    String getEmail();

    Set<Ruolo> getRuoli();

    void addRuolo(Ruolo ruolo);

    void setRuoli(Set<Ruolo> ruoli);

    String getPassword();

    void removeRuolo(Ruolo ruolo);

}
