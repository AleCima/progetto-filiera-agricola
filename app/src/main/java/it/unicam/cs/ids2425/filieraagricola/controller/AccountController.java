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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PostMapping("/registrazione")
    public ResponseEntity<Object> creaUtente(@RequestBody UtenteDTO uDTO) {
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        try {
            accountDataHandler.check(u);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.aggiungiUtente(u);
        return new ResponseEntity<>("Utente creato con successo", HttpStatus.CREATED);
    }

    @PutMapping("/modifica-utente")
    public ResponseEntity<Object> modificaUtente(@RequestParam String precEmail, @RequestBody UtenteDTO uDTO) {
        //TODO controllo autenticazione
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        try {
            accountDataHandler.check(u);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.modificaUtente(precEmail, u);
        return new ResponseEntity<>("Utente modificato con successo", HttpStatus.CREATED);
    }
    @DeleteMapping("/elimina-account")
    public ResponseEntity<String> rimuoviUtente(@RequestParam String email) {
        //TODO controllo autenticazione
        accService.rimuoviUtente(email);
        return new ResponseEntity<>("Utente rimosso con successo", HttpStatus.OK);
    }
    @PostMapping("/registrazione-venditore")
    public ResponseEntity<String> creaVenditore(@RequestBody VenditoreDTO vDTO) {
        Venditore v = new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli());
        try {
            accountDataHandler.check(v);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.aggiungiVenditore(v);
        return new ResponseEntity<>("Venditore creato con successo", HttpStatus.CREATED);
    }
    @PutMapping("/modifica-venditore")
    public ResponseEntity<String> modificaVenditore(@RequestParam String precEmail, @RequestBody VenditoreDTO vDTO) {
        //TODO Controllo autenticazione
        Venditore v = new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli());
        try {
            accountDataHandler.check(v);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        accService.modificaVenditore(precEmail, v);
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }
    @DeleteMapping("/elimina-venditore")
    public ResponseEntity<String> rimuoviVenditore(@RequestParam String email) {
        //TODO Controllo autenticazione
        accService.rimuoviVenditore(email);
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }

    @GetMapping("/ricerca-utente")
    public ResponseEntity<Object> getUtente(@RequestParam String email){
        return  new ResponseEntity<>(accService.getUtenteByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/ricerca-venditore")
    public ResponseEntity<Object> getVenditore(@RequestParam String email) {
        return new ResponseEntity<>(accService.getVenditoreByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/test-auth")
    public String testAuth(Authentication authentication) {
        if (authentication == null) {
            return "Non autenticato!";
        }
        return "Utente: " + authentication.getName() +
                " - Ruoli: " + authentication.getAuthorities();
    }

    // Assegna un singolo ruolo a un utente
    @PostMapping("/assegna-ruolo")
    public ResponseEntity<String> assegnaRuolo(@RequestParam String email, @RequestParam Ruolo ruolo) {
        Utente utente = accService.getUtenteByEmail(email);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        accService.assegnaRuolo(email, ruolo);
        return new ResponseEntity<>("Ruolo assegnato con successo", HttpStatus.OK);
    }

    // Assegna più ruoli a un utente (sovrascrivendo eventuali ruoli esistenti)
    @PostMapping("/assegna-ruoli")
    public ResponseEntity<String> assegnaRuoli(@RequestParam String email, @RequestBody List<Ruolo> ruoli) {
        Utente utente = accService.getUtenteByEmail(email);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        accService.assegnaRuoli(email, ruoli);
        return new ResponseEntity<>("Ruoli assegnati con successo", HttpStatus.OK);
    }

}
