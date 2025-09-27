package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.OrdineDTO;

import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.OrdineService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Classe tutte le operazioni relative agli ordini
 */
@RestController
@RequestMapping("/ordine")
public class OrdineController {
    private final OrdineService ordineService;
    private final AccountService accountService;
    private final Handler ordineDataHandler;

    public OrdineController(OrdineService ordineService, AccountService accountService) {
        this.ordineService = ordineService;
        this.accountService = accountService;
        this.ordineDataHandler = new NonNullOrEmptyHandler()
                .setNext(new IndirizzoFormatHandler())
                .setNext(new PagamentoValidHandler())
                .setNext(new DisponibilitaRigaCarrelloHandler());
    }

    /**
     * Metodo per l'aggiunta di un ordine nel sistema
     * @param email Email dell'utente che crea l'ordine
     * @param ordineDTO Dati dell'ordine
     * @return messaggio di successo/errore
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PostMapping("/aggiungi")
    public ResponseEntity<Object> addOrdine(@RequestParam String email ,@RequestBody OrdineDTO ordineDTO) {
        Utente utente = accountService.getUtenteByEmail(email);
        if (utente == null) return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);

        Ordine ordine = new Ordine(
                ordineDTO.getDataOrdine(),
                utente.getCarrello(),
                ordineDTO.getCartaDiCredito(),
                ordineDTO.getIndirizzoDiFatturazione(),
                utente
        );
        try {
            ordineDataHandler.check(ordine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ordineService.addOrdine(ordine);
        return new ResponseEntity<>("Ordine aggiunto con successo", HttpStatus.CREATED);
    }

    /**
     * Metodo per la modifica di un ordine gia caricato, Solo chi ha creato l'ordine puo modificarlo
     * @param id Id dell'ordine da modificare
     * @param email Email dell'utente che vuole modificare l'ordine
     * @param ordineDTO Dati aggiornati dell'ordine
     * @return messaggio di successo/errore
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PutMapping("/aggiorna")
    public ResponseEntity<Object> updateOrdine(@RequestParam int id, @RequestParam String email, @RequestBody OrdineDTO ordineDTO) {
        Ordine ordine = ordineService.getOrdineById(id);
        if (ordine == null) return new ResponseEntity<>("Ordine non trovato", HttpStatus.NOT_FOUND);
        if (ordine.isEvaso()) return new ResponseEntity<>("Ordine evaso, non modificabile", HttpStatus.BAD_REQUEST);

        var utente = accountService.getUtenteByEmail(email);
        if (utente == null) return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);

        ordine.setDataOrdine(ordineDTO.getDataOrdine());
        ordine.setCarrello(utente.getCarrello());
        ordine.setCartaDiCredito(ordineDTO.getCartaDiCredito());
        ordine.setIndirizzoDiFatturazione(ordineDTO.getIndirizzoDiFatturazione());

        try {
            ordineDataHandler.check(ordine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ordineService.updateOrdine(ordine);
        return new ResponseEntity<>("Ordine aggiornato con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la rimozione di un ordine gia caricato, richiamabile solo dall'utente che lo ha caricato
     * @param id Id dell'ordine da rimuovere
     * @return messaggio di successo/errore
     */
    @PreAuthorize("@ordineService.checkOrdineEmail(#id, authentication.name) or hasRole('GESTORE')")
    @DeleteMapping("/rimuovi")
    public ResponseEntity<Object> removeOrdine(@RequestParam int id) {
        ordineService.removeOrdine(id);
        return new ResponseEntity<>("Ordine rimosso con successo", HttpStatus.OK);
    }

    /**
     * Metodo per ottenere un ordine a partire dal suo id
     * @param id Id dell'ordine che si vuole ottenere
     * @return Ordine richiesto o messaggio di errore
     */
    @PreAuthorize("@ordineService.checkOrdineEmail(#id, authentication.name) or hasRole('GESTORE')")
    @GetMapping("/ottieni")
    public ResponseEntity<Object> getOrdineById(@RequestParam int id){
        Ordine ordine = ordineService.getOrdineById(id);
        if (ordine == null) return new ResponseEntity<>("Ordine non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ordine, HttpStatus.OK);
    }

    /**
     * Metodo per evadere un ordine
     * @param id Id dell'ordine da evadere
     * @return messaggio di errore/successo
     */
    @PreAuthorize("hasRole('GESTORE')")
    @PutMapping("/evadi")
    public ResponseEntity<Object> evadiOrdine(@RequestParam int id){
        Ordine ordine = ordineService.getOrdineById(id);
        if (ordine == null) return new ResponseEntity<>("Ordine non trovato", HttpStatus.NOT_FOUND);

        try {
            ordineDataHandler.check(ordine);
            ordineService.evadi(ordine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ordine evaso con successo", HttpStatus.OK);
    }

    /**
     * Metodo per ottenere tutti gli ordini di un utente
     * @param email Email dell'utente di cui si vogliono ottenre tutti gli ordini
     * @return Lista di ordini o errore
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @GetMapping("ottieni-ordini-utente")
    public ResponseEntity<Object> getOrdiniUtente(@RequestParam String email){
        Utente utente = accountService.getUtenteByEmail(email);
        if (utente == null) return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ordineService.getOrdineByUtente(utente), HttpStatus.OK);
    }

    /**
     * Metodo per ottenere tutti gli ordini arrivati ad un venditore
     * @param email Email del vendiore
     * @return messaggio di errore/successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @GetMapping("ottieni-ordini-venditore")
    public ResponseEntity<Object> getOrdiniVenditore(@RequestParam String email){
        Venditore venditore = accountService.getVenditoreByEmail(email);
        if (venditore == null) return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ordineService.getOrdineByVenditore(venditore), HttpStatus.OK);
    }
}


