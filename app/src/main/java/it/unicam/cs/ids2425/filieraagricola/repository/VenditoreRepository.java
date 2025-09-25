package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, String> {

    @Query("SELECT new it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO(v.email, v.descrizione, v.posizione) FROM Venditore v")
    List<VenditorePosizioneDTO> findAllPosizioni();
}
