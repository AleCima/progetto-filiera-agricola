package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {
    private UtenteRepository utenteRepository;
    private VenditoreRepository venditoreRepository;
    private ConcurrentHashMap<String, String> loginTokenStore = new ConcurrentHashMap<>();

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

    public void assegnaRuolo(String email, Ruolo r) {
        Utente utente = getUtenteByEmail(email);
        if (utente != null) {
            utente.addRuolo(r);
            utenteRepository.save(utente);
        }
    }

    public void assegnaRuoli(String email, List<Ruolo> ruoli) {
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

    public List<Utente> getUtenti() {
        //TODO
        return null;
    }

    public List<Venditore> getVenditori() {
        return null;
        //TODO
    }

    public void storeToken(String token, String email) {
        loginTokenStore.put(token, email);
    }

    public String getEmailByToken(String token) {
        return loginTokenStore.get(token);
    }
}
