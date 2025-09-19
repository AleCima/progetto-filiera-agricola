package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContenutoRepository extends JpaRepository<Contenuto, Integer> {

    //Restituisce la disponibilità corrente del contenuto con l'id specificato.
    @Query("SELECT c.quantita FROM Contenuto c WHERE c.id = :id")
    int getDisponibilita(@Param("id") int id);
}