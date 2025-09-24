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
    private Venditore venditore;

    public InitSistema(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        // Creo il gestore e assegno ruoli
        gestore = new Utente("mario.bianchi@gestore.it", "123123", "Mario", "Bianchi");
        gestore.addRuolo(Ruolo.GESTORE);
        gestore.removeRuolo(Ruolo.ACQUIRENTE);
        accountService.aggiungiUtente(gestore);

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
