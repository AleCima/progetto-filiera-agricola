package it.unicam.cs.ids2425.filieraagricola.repository;

import it.unicam.cs.ids2425.filieraagricola.model.Proposta;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
    // Tutte le proposte di un determinato venditore (destinatario)
    List<Proposta> findByDestinatario(Venditore destinatario);

    // Tutte le proposte create da un organizzatore
    List<Proposta> findByOrganizzatore(Utente organizzatore);
}

