package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VisitaDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {

    @Query("SELECT new it.unicam.cs.ids2425.filieraagricola.controller.DTO.VisitaDTO(" +
            "v.titolo, v.descrizione, v.organizzatore.email, v.dataEsperienza, v.numMaxPartecipanti, v.posizione, v.azienda.email) " +
            "FROM Visita v")
    List<VisitaDTO> findAllPosizioni();
}

