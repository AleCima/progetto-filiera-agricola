package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.UtenteDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditoreDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accService;
    ContenutoService conService;

    public AccountController(AccountService accService, ContenutoService conService) {
        this.accService = accService;
        this.conService = conService;
    }

    @PostMapping("/registrazione")
    public ResponseEntity<Object> creaUtente(@RequestBody UtenteDTO uDTO) {
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        accService.aggiungiUtente(u);
        return new ResponseEntity<>("Utente creato con successo", HttpStatus.CREATED);
    }

    @PutMapping("/modifica-utente")
    public ResponseEntity<Object> modificaUtente(@RequestParam String precEmail, @RequestBody UtenteDTO uDTO) {
        //TODO controllo autenticazione
        accService.modificaUtente(precEmail, new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome()));
        return new ResponseEntity<>("Utente modificato con successo", HttpStatus.CREATED);
    }
    @DeleteMapping("elimina-account")
    public ResponseEntity<String> rimuoviUtente(@RequestParam String email) {
        //TODO controllo autenticazione
        accService.rimuoviUtente(email);
        return new ResponseEntity<>("Utente rimosso con successo", HttpStatus.OK);
    }
    @PostMapping("/registrazione-venditore")
    public ResponseEntity<String> creaVenditore(@RequestBody VenditoreDTO vDTO) {
        accService.aggiungiVenditore(new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli()));
        return new ResponseEntity<>("Venditore creato con successo", HttpStatus.CREATED);
    }
    @PutMapping("/modifica-venditore")
    public ResponseEntity<String> modificaVenditore(@RequestParam String precEmail, @RequestBody VenditoreDTO vDTO) {
        //TODO Controllo autenticazione
        accService.modificaVenditore(precEmail, new Venditore(vDTO.getEmail(), vDTO.getPassword(), vDTO.getPIVA(), vDTO.getRagioneFiscale(), vDTO.getDescrizione(), vDTO.getPosizione(), vDTO.getRuoli()));
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }
    @DeleteMapping("elimina-venditore")
    public ResponseEntity<String> rimuoviVenditore(@RequestParam String email) {
        //TODO Controllo autenticazione
        accService.rimuoviVenditore(email);
        return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
    }

    @GetMapping("/ricerca-utente")
    public ResponseEntity<Object> getUtente(@RequestParam String email) {
        return  new ResponseEntity<>(accService.getUtenteByEmail(email), HttpStatus.OK);
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

    public Venditore getVenditore(String email) {
        return accService.getVenditoreByEmail(email);
    }

    public Carrello getCarrelloUtente(String email) {
        return accService.getUtenteByEmail(email).getCarrello();
    }

    public void svuotaCarrello(String email) {
        accService.getUtenteByEmail(email).getCarrello().svuota();
    }

    public void aggiungiContenutoCarrello(String email, Contenuto c, int quant) {
        accService.getUtenteByEmail(email).getCarrello().aggiungiContenuto(new RigaCarrello(c, quant));
    }
}
