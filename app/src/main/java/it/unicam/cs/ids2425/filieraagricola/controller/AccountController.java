package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.UtenteDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditoreDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.EmailFormatHandler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.Handler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.NonNullOrEmptyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Classe contenente tutte le possibili operazioni che possono essere effettuate su un account
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accService;
    ContenutoService conService;
    private final InitSistema init;
    Handler accountDataHandler;
    @Autowired
    public AccountController(AccountService accService, ContenutoService conService, InitSistema init) {
        this.accService = accService;
        this.conService = conService;
        this.init = init;
        accountDataHandler = new NonNullOrEmptyHandler().setNext(new EmailFormatHandler());
    }

    /**
     * Metodo per la registrazione di un nuovo utente
     * @param uDTO Dati da ottenere da un richiesta HTTP
     * @return messaggio di successo
     */
    @PostMapping("/registrazione")
    public ResponseEntity<Object> creaUtente(@RequestBody UtenteDTO uDTO) {
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        try {
            accountDataHandler.check(u);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if(accService.getUtenteByEmail(uDTO.getEmail())!=null || accService.getVenditoreByEmail(uDTO.getEmail())!=null){
            return new ResponseEntity<>("Un account con questa email e' gia stato creato", HttpStatus.BAD_REQUEST);
        }
        accService.aggiungiUtente(u);
        return new ResponseEntity<>("Utente creato con successo", HttpStatus.CREATED);
    }

    /**
     * Metodo per la modifica dei dati personali di un utente, un utente puo modificare i dati solo di se stesso
     * @param email dell'utente da modificare
     * @param uDTO Dati da sostituire a quelli esistenti, sempre presi nel body della richiesta HTTP
     * @return messaggio di successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PutMapping("/modifica-utente")
    public ResponseEntity<Object> modificaUtente(@RequestParam String email, @RequestBody UtenteDTO uDTO) {
        if (!uDTO.getEmail().equals(email)){
            return new ResponseEntity<>("Non e' possibile cambiare la mail", HttpStatus.BAD_REQUEST);
        }
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        try {
            accountDataHandler.check(u);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.modificaUtente(email, u);
        return new ResponseEntity<>("Utente modificato con successo", HttpStatus.CREATED);
    }

    /**
     * Metodo per la rimozione di un utente, un utente puo rimuovere solo se stesso o il gestore puo rimuovere un utente
     * @param email dell'utente da rimuovere
     * @return messaggio di successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @DeleteMapping("/elimina-utente")
    public ResponseEntity<String> rimuoviUtente(@RequestParam String email) {
        accService.rimuoviUtente(email);
        return new ResponseEntity<>("Utente rimosso con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la registrazione di un nuovo venditore
     * @param vDTO dati ottenuti dalla richiesta HTTP
     * @return messaggio di successo
     */
    @PostMapping("/registrazione-venditore")
    public ResponseEntity<String> creaVenditore(@RequestBody VenditoreDTO vDTO) {
        Venditore v = new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli());
        try {
            accountDataHandler.check(v);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if(accService.getUtenteByEmail(vDTO.getEmail())!=null || accService.getVenditoreByEmail(vDTO.getEmail())!=null){
            return new ResponseEntity<>("Un account con questa email e' gia stato creato", HttpStatus.BAD_REQUEST);
        }
        accService.aggiungiVenditore(v);
        return new ResponseEntity<>("Venditore creato con successo", HttpStatus.CREATED);
    }

    /**
     * Metodo per la modifica dei dati di un venditore, un venditore puo modificre solo se stesso
     * @param email del venditore da modificare
     * @param vDTO i dati da sostituire con quelli esistenti
     * @return messaggio di succeso
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PutMapping("/modifica-venditore")
    public ResponseEntity<String> modificaVenditore(@RequestParam String email, @RequestBody VenditoreDTO vDTO) {
        Venditore v = new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli());
        try {
            accountDataHandler.check(v);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.modificaVenditore(email, v);
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la rimozione di un venditore, un venditore puo rimuovere solo se stesso o un gestore puo rimuovere un venditore
     * @param email del venditore da rimuovere
     * @return messaggio di successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @DeleteMapping("/elimina-venditore")
    public ResponseEntity<String> rimuoviVenditore(@RequestParam String email) {
        accService.rimuoviVenditore(email);
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la visione di un utente
     * @param email dell'utente da visualizzare
     * @return utente richiesto
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @GetMapping("/ricerca-utente")
    public ResponseEntity<Object> getUtente(@RequestParam String email){
        Utente utente = accService.getUtenteByEmail(email);
        if(utente == null){
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    /**
     * Metodo per la visione dei dati di un venditore
     * @param email del venditore da visualizzare
     * @return venditore richiesto
     */
    @GetMapping("/ricerca-venditore")
    public ResponseEntity<Object> getVenditore(@RequestParam String email) {
        Venditore venditore = accService.getVenditoreByEmail(email);
        if(venditore == null){
            return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accService.getVenditoreByEmail(email), HttpStatus.OK);
    }

    /**
     * Metodo per ottenere i dati di tutti i venditori
     * @return la lista di tutti i venditori
     */
    @GetMapping("/ricerca-venditori")
    public ResponseEntity<Object> getVenditori() {
        List<Venditore> venditori = accService.getVenditori();
        if (venditori == null || venditori.isEmpty()) {
            return new ResponseEntity<>("Nessun venditore trovato", HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(venditori, HttpStatus.OK);
    }

    /**
     * Metodo per assegnare un ruolo ad un account, solo un gestore puo richiamare tale metodo
     * @param email dell'account da modificare
     * @param ruolo da aggiungere
     * @return messaggio di successo o insuccesso
     */
    @PostMapping("/assegna-ruolo")
    public ResponseEntity<String> assegnaRuolo(@RequestParam String email, @RequestParam Ruolo ruolo) {
        Utente utente = accService.getUtenteByEmail(email);
        Venditore venditore = accService.getVenditoreByEmail(email);
        if (utente == null) {
            accService.assegnaRuolo(venditore, ruolo);
            return new ResponseEntity<>("Ruolo assegnato con successo", HttpStatus.OK);
        } else if (venditore == null){
            accService.assegnaRuolo(utente, ruolo);
            return new ResponseEntity<>("Ruolo assegnato con successo", HttpStatus.OK);
        }
        return new ResponseEntity<>("Account con quell'email non trovato", HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo per l'assegnazione in bulk di ruoli per un account
     * @param email dell'account da modificare
     * @param ruoli da aggiungere
     * @return messaggio di successo o insuccesso
     */
    // Assegna più ruoli a un utente (sovrascrivendo eventuali ruoli esistenti)
    @PostMapping("/assegna-ruoli")
    public ResponseEntity<String> assegnaRuoli(@RequestParam String email, @RequestBody Set<Ruolo> ruoli) {
        Utente utente = accService.getUtenteByEmail(email);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        accService.assegnaRuoli(email, ruoli);
        return new ResponseEntity<>("Ruoli assegnati con successo", HttpStatus.OK);
    }

}
