package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.*;

/**
 * Classe utilizzata per l'inizializzazione di alcuni utenti e venditori per i test
 */
@Component
public class InitSistema {

    private final AccountService accountService;
    private final ContenutoService contenutoService;
    private Utente gestore;
    private Venditore produttore;
    private Venditore trasformatore;
    private Venditore distributore;
    private Utente animatore;
    private Utente curatore;
    private Utente acquirente;
    private Venditore venditore;
    private Contenuto contenutoDefault;
    private Trasformazione trasformazioneDefault;
    private Pacchetto pacchettoDefault;

    public InitSistema(AccountService accountService, ContenutoService contenutoService) {
        this.accountService = accountService;
        this.contenutoService = contenutoService;
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
        produttore = new Venditore(
                "produttore@gmail.com",
                "produttore",
                "PIVA_PRODUTTORE",
                "Ragione Sociale Produttore",
                "Venditore di prodotti agricoli",
                new PuntoMappa(1.0, 1.0),
                new HashSet<>(List.of(Ruolo.PRODUTTORE))
        );
        accountService.aggiungiVenditore(produttore);

// TRASFORMATORE
        trasformatore = new Venditore(
                "trasformatore@gmail.com",
                "trasformatore",
                "PIVA_TRASFORMATORE",
                "Ragione Sociale Trasformatore",
                "Venditore trasformatore prodotti",
                new PuntoMappa(2.0, 2.0),
                new HashSet<>(List.of(Ruolo.TRASFORMATORE))
        );
        accountService.aggiungiVenditore(trasformatore);

// DISTRIBUTORE
        distributore = new Venditore(
                "distributore@gmail.com",
                "distributore",
                "PIVA_DISTRIBUTORE",
                "Ragione Sociale Distributore",
                "Venditore distributore prodotti",
                new PuntoMappa(3.0, 3.0),
                new HashSet<>(List.of(Ruolo.DISTRIBUTORE))
        );
        accountService.aggiungiVenditore(distributore);
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
        Set<Ruolo> ruoliVenditore = new HashSet<>();
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
// CONTENUTO
        contenutoDefault = new Prodotto(
                new Date(),
                "Prugne",
                "Prugne",
                "Biologico",
                2.5,
                produttore,
                new ArrayList<>(List.of("BIO", "DOC")),
                new Date(),
                100
                );
        contenutoService.addContenuto(contenutoDefault);
// TRASFORMAZIONE
        trasformazioneDefault = new Trasformazione(
                new Date(),
                "Marmellate",
                trasformatore,
                100.0,
                5
        );
        contenutoService.addContenuto(trasformazioneDefault);
//PACCHETTO
        pacchettoDefault = new Pacchetto(
                new Date(),
                "Prugne Marmellate",
                "Marmellata di prugne",
                250.0,
                distributore,
                5,
                Collections.emptyList()
        );
        contenutoService.addContenuto(pacchettoDefault);
    }

    public Utente getGestore() {
        return gestore;
    }

    public Venditore getVenditore() {
        return venditore;
    }

    public Venditore getProduttore() {
        return produttore;
    }

    public Venditore getTrasformatore() {
        return trasformatore;
    }

    public Venditore getDistributore() {
        return distributore;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public Utente getCuratore() {
        return curatore;
    }

    public Utente getAcquirente() {
        return acquirente;
    }
}
