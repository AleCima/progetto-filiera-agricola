package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Pacchetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContenutoRepository extends JpaRepository<Contenuto, Integer> {
    @Query("SELECT c FROM Contenuto c WHERE c.statoConferma = 'ATTESA'")
    List<Contenuto> findContenutiInAttesa();

    @Query("SELECT c FROM Contenuto c WHERE c.venditore.id = :venditoreId")
    List<Contenuto> findAllByVenditore(@Param("venditoreId") String emailVenditore);

    @Query("SELECT p FROM Pacchetto p JOIN p.listaProdotti c WHERE c.id = :contenutoId")
    List<Pacchetto> findPacchettiByContenutoId(@Param("contenutoId") int contenutoId);
}