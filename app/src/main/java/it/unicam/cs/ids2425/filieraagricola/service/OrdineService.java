package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdineService {

    //Necessaria repository "Ordine"
    private final OrdineRepository ordineRepository;

    @Autowired
    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    public void addOrdine(Ordine ordine) {
        if (ordine != null) {
            ordineRepository.save(ordine); // Salva l'ordine nel database
        } else {
            throw new IllegalArgumentException("Ordine non valido");
        }
    }

    public void updateOrdine(Ordine ordine) {
        Optional<Ordine> ordineEsistente = ordineRepository.findById(ordine.getId());
        if (ordineEsistente.isPresent()) {
            ordineRepository.save(ordine); // Aggiorna l'ordine nel database
        } else {
            throw new IllegalArgumentException("Ordine non trovato");
        }
    }

    public void removeOrdine(int id) {
        // Verifica se l'ordine esiste nel database
        Optional<Ordine> ordine = ordineRepository.findById(id);

        if (ordine.isPresent()) {
            Ordine ordineTrovato = ordine.get();

            // Verifica se l'ordine ha un carrello associato
            if (ordineTrovato.getCarrello() != null) {
                // Rimuovi la relazione tra ordine e carrello senza eliminare il carrello
                ordineTrovato.setCarrello(null);
            }

            // Ora elimina solo l'ordine
            ordineRepository.delete(ordineTrovato);
        } else {
            throw new IllegalArgumentException("Ordine non trovato");
        }
    }

    public Ordine getOrdineById(int id) {
        Optional<Ordine> ordine = ordineRepository.findById(id);
        return ordine.orElseThrow(() -> new IllegalArgumentException("Ordine non trovato"));
    }

    public List<Ordine> getOrdineByUtente(Utente utente) {
        return ordineRepository.findByUtente(utente);
    }

    public List<Ordine> getOrdineByVenditore(Venditore venditore) {
        return null;
    }

}
