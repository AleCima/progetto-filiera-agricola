package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.UtenteDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accService;
    ContenutoService conService;

    public AccountController(AccountService accService, ContenutoService conService) {
        this.accService = accService;
        this.conService = conService;
    }

    @PostMapping("/Registrazione")
    public ResponseEntity<Object> creaUtente(@RequestBody UtenteDTO uDTO) {
        Utente u = new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome());
        accService.aggiungiUtente(u);
        return new ResponseEntity<>("Utente creato con successo", HttpStatus.CREATED);
    }
    @PutMapping("/ModificaUtente")
    public ResponseEntity<Object> modificaUtente(@RequestParam String precEmail, @RequestBody UtenteDTO uDTO) {
        //TODO controllo autenticazione
        accService.modificaUtente(precEmail, new Utente(uDTO.getEmail(), uDTO.getPassword(), uDTO.getNome(), uDTO.getCognome()));
        return new ResponseEntity<>("Utente modificato con successo", HttpStatus.CREATED);
    }

    public void rimuoviUtente(String email) {
        //TODO
    }

    public void creaVenditore(String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione) {
        //TODO
    }

    public void modificaVenditore(String precEmail, String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione) {

        //TODO finire

    }

    public void rimuoviVenditore(String PIVA) {
        //TODO modifica
        accService.rimuoviVenditore(null);
    }

    @GetMapping("/ricercaUtente")
    public ResponseEntity<Object> getUtente(@RequestParam String email) {
        return  new ResponseEntity<>(accService.getUtenteByEmail(email), HttpStatus.OK);
    }

    public Venditore getVenditore(String PIVA) {
        return accService.getVenditoreByPIVA(PIVA);
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
