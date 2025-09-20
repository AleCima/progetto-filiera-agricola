package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContenutoRepository extends JpaRepository<Contenuto, Integer> {

}