package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, String> {
}
