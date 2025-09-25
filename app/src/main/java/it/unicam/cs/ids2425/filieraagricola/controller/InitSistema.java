package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitSistema {

    private final AccountService accountService;
    private Utente gestore;
    private Utente produttore;
    private Utente trasformatore;
    private Utente distributore;
    private Utente animatore;
    private Utente curatore;
    private Utente acquirente;
    private Venditore venditore;

    public InitSistema(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        // Creo il gestore e assegno ruoli
        // GESTORE
        gestore = new Utente("gestore@gmail.com", "gestore", "Gestore", "Gestore");
        gestore.addRuolo(Ruolo.GESTORE);
        gestore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(gestore);

// PRODUTTORE
        produttore = new Utente("produttore@gmail.com", "produttore", "Produttore", "Produttore");
        produttore.addRuolo(Ruolo.PRODUTTORE);
        produttore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(produttore);

// TRASFORMATORE
        trasformatore = new Utente("trasformatore@gmail.com", "trasformatore", "Trasformatore", "Trasformatore");
        trasformatore.addRuolo(Ruolo.TRASFORMATORE);
        trasformatore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(trasformatore);

// DISTRIBUTORE
        distributore = new Utente("distributore@gmail.com", "distributore", "Distributore", "Distributore");
        distributore.addRuolo(Ruolo.DISTRIBUTORE);
        distributore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(distributore);

// ANIMATORE
        animatore = new Utente("animatore@gmail.com", "animatore", "Animatore", "Animatore");
        animatore.addRuolo(Ruolo.ANIMATORE);
        animatore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(animatore);

// CURATORE
        curatore = new Utente("curatore@gmail.com", "curatore", "Curatore", "Curatore");
        curatore.addRuolo(Ruolo.CURATORE);
        curatore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(curatore);

// ACQUIRENTE
        acquirente = new Utente("acquirente@gmail.com", "acquirente", "Acquirente", "Acquirente");
        accountService.aggiungiUtente(acquirente);

        // Creo il venditore e assegno ruoli
        List<Ruolo> ruoliVenditore = new ArrayList<>();
        ruoliVenditore.add(Ruolo.TRASFORMATORE);
        ruoliVenditore.add(Ruolo.PRODUTTORE);
        ruoliVenditore.add(Ruolo.DISTRIBUTORE);

        venditore = new Venditore(
                "mario.verdi@venditore.it",
                "123123",
                "PIvaProva",
                "RagFiscProva",
                "Descrizione",
                new PuntoMappa(1.0, 2.0),
                ruoliVenditore
        );
        accountService.aggiungiVenditore(venditore);
    }

    public Utente getGestore() {
        return gestore;
    }

    public Venditore getVenditore() {
        return venditore;
    }
}
