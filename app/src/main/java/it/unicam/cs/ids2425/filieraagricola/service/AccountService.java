package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Account;
import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AccountService {
    private UtenteRepository utenteRepository;
    private VenditoreRepository venditoreRepository;
    private Set<Ruolo> ruoliVenditoreValidi = new HashSet<>(Set.of(Ruolo.DISTRIBUTORE, Ruolo.PRODUTTORE, Ruolo.TRASFORMATORE));
    private Set<Ruolo> ruoliUtenteValidi = new HashSet<>(Set.of(Ruolo.ACQUIRENTE, Ruolo.ANIMATORE, Ruolo.CURATORE));

    @Autowired
    public AccountService(UtenteRepository utenteRepository, VenditoreRepository venditoreRepository) {
        this.utenteRepository = utenteRepository;
        this.venditoreRepository = venditoreRepository;
    }

    public void aggiungiUtente(Utente u) {
        utenteRepository.save(u);
    }

    public void rimuoviUtente(String email) {
        utenteRepository.delete(getUtenteByEmail(email));
    }

    public void modificaUtente(String email, Utente utenteModificato) {
        if (utenteRepository.findById(email).isPresent()){
            utenteRepository.save(utenteModificato);
        }
    }

    public void aggiungiVenditore(Venditore v) {
        venditoreRepository.save(v);
    }

    public void rimuoviVenditore(String email) {
        venditoreRepository.delete(getVenditoreByEmail(email));
    }

    public void modificaVenditore(String email, Venditore v) {
        if (venditoreRepository.findById(email).isPresent()){
            venditoreRepository.save(v);
        }
    }

    public void assegnaRuolo(Utente utente, Ruolo r) {
        utente.addRuolo(r);
        if (ruoliUtenteValidi.contains(r)){
            utenteRepository.save(utente);
        }
    }
    public void assegnaRuolo(Venditore venditore, Ruolo r) {
        venditore.addRuolo(r);
        if (ruoliVenditoreValidi.contains(r)){
            venditoreRepository.save(venditore);
        }
    }


    public void assegnaRuoli(String email, Set<Ruolo> ruoli) {
        Utente utente = getUtenteByEmail(email);
        if (utente != null) {
            utente.setRuoli(ruoli);
            utenteRepository.save(utente);
        }
    }

    public Utente getUtenteByEmail(String email) {
        return utenteRepository.findById(email).orElse(null);
    }

    public Venditore getVenditoreByEmail(String email) {
        return venditoreRepository.findById(email).orElse(null);
    }

    public List<Venditore> getVenditori() {
        return venditoreRepository.findAll();
    }

    public Account getAccount(String email){
        if (utenteRepository.findById(email).isPresent()) {
            return utenteRepository.findById(email).orElse(null);
        }

        if (venditoreRepository.findById(email).isPresent()) {
            return venditoreRepository.findById(email).orElse(null);
        }
        return null;
    }

}
