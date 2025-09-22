package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EsperienzaPosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EventoDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {


    @Query("SELECT new it.unicam.cs.ids2425.filieraagricola.controller.DTO.EventoDTO(" +
                "e.titolo, e.descrizione, e.organizzatore.email, e.dataEsperienza, e.numMaxPartecipanti, e.posizione) " +
                "FROM Evento e")
    List<EventoDTO> findAllPosizioni();


}


