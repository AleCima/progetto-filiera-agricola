package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.model.RigaCarrello;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.ContenutoRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.OrdineRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdineService {

    //Necessaria repository "Ordine"
    private final OrdineRepository ordineRepository;
    private final ContenutoRepository contenutoRepository;
    private final CarrelloService carrelloService;

    @Autowired
    public OrdineService(OrdineRepository ordineRepository, ContenutoRepository contenutoRepository, CarrelloService carrelloService) {
        this.ordineRepository = ordineRepository;
        this.contenutoRepository = contenutoRepository;
        this.carrelloService = carrelloService;
    }

    public void addOrdine(Ordine ordine) {

        ordineRepository.save(ordine); // Salva l'ordine nel database
        carrelloService.svuota(ordine.getUtente().getEmail());
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

    public Set<Ordine> getOrdineByVenditore(Venditore venditore) {
        List<Ordine> ordini = ordineRepository.findAll();
        Set<Ordine> ordiniVenditore = new HashSet<>();
        for (Ordine o : ordini){
            for (RigaCarrello rigaCarrello : o.getCarrello().getContenuti()){
                if (Objects.equals(rigaCarrello.getContenuto().getVenditore().getEmail(), venditore.getEmail())){
                    ordiniVenditore.add(o);
                }
            }
        }
        return ordiniVenditore;
    }

    public void evadi(Ordine ordine) {
        List<RigaCarrello> carrello = ordine.getCarrello().getContenuti();
        for (RigaCarrello rigaCarrello : carrello){
            rigaCarrello.getContenuto().setQuantita(rigaCarrello.getContenuto().getQuantita() - rigaCarrello.getQuantita());
            contenutoRepository.save(rigaCarrello.getContenuto());
        }
        ordine.setEvaso(true);
    }
}
