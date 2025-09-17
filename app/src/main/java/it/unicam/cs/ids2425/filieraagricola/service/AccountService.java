package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private UtenteRepository utenteRepository;
    private VenditoreRepository venditoreRepository;

    @Autowired
    public AccountService(UtenteRepository utenteRepository, VenditoreRepository venditoreRepository) {
        this.utenteRepository = utenteRepository;
        this.venditoreRepository = venditoreRepository;
    }

    public void aggiungiUtente(Utente u) {
        //TODO
    }

    public void rimuoviUtente(Utente u) {
        //TODO
    }

    public void modificaUtente() {
        //TODO
    }

    public void aggiungiVenditore(Venditore v) {
        //TODO
    }

    public void rimuoviVenditore(Venditore v) {
        //TODO
    }

    public void modificaVenditore() {
        //TODO
    }

    public void assegnaRuolo(String email, Ruolo r) {
        //TODO
    }

    public void assegnaRuoli(String email, List<Ruolo> r) {
        //TODO
    }

    public Utente getUtenteByEmail(String email) {
        //TODO
        return null;
    }

    public Venditore getVenditoreByPIVA(String PIVA) {
        //TODO
        return null;
    }

    public List<Utente> getUtenti() {
        //TODO
        return null;
    }

    public List<Venditore> getVenditori() {
        return null;
        //TODO
    }
}
