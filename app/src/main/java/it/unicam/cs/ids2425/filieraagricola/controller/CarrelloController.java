package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.RigaCarrelloDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Carrello;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.RigaCarrello;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.CarrelloService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.DisponibilitaRigaCarrelloHandler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.Handler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.NonNullOrEmptyHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO aggiungi controlli autorizzazione
@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    CarrelloService carrelloService;
    AccountService accountService;
    ContenutoService contenutoService;
    Handler cartDataHandler;

    public CarrelloController(CarrelloService carrelloService, AccountService accountService, ContenutoService contenutoService) {
        this.carrelloService = carrelloService;
        this.accountService = accountService;
        this.contenutoService = contenutoService;
        cartDataHandler = new NonNullOrEmptyHandler().setNext(new DisponibilitaRigaCarrelloHandler());
    }

    @GetMapping("/carrello-utente")
    public ResponseEntity<Object> getCarrelloUtente(@RequestParam String email){
        if (accountService.getUtenteByEmail(email) == null){
            return new ResponseEntity<>("Utente non presente nel Sistema", HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(carrelloService.getCarrelloFromUtente(email), HttpStatus.OK);
    }

    @PostMapping("/aggiungi-contenuto")
    public ResponseEntity<Object> aggiungiContenutoAlCarrello(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        //TODO controlli su contenuto
        RigaCarrello rc = new RigaCarrello(contenutoService.getContenutoById(rcDTO.getId()), rcDTO.getQuantita());
        try{
            cartDataHandler.check(rc);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        carrelloService.aggiungiContenuto(email, rc);
        return new ResponseEntity<>("Contenuto aggiunto correttamente", HttpStatus.OK);
    }

    @PostMapping("/rimuovi-contenuto")
    public ResponseEntity<Object> rimuoviContenuto(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaRimuovere = contenutoService.getContenutoById(rcDTO.getId());
        if (!carrelloService.contains(carrelloUt, contenutoDaRimuovere)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }
        //TODO controlli aut
        carrelloService.rimuoviContenuto(email, contenutoDaRimuovere, rcDTO.getQuantita());

        return new ResponseEntity<>("Quantità del contenuto rimossa con successo", HttpStatus.OK);
    }

    @PostMapping("/aggiungi-quantita")
    public ResponseEntity<Object> aggiungiQuantita(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaModficare = contenutoService.getContenutoById(rcDTO.getId());
        //TODO controlli aut
        if (!carrelloService.contains(carrelloUt, contenutoDaModficare)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }
        carrelloService.aggiungiQuantita(email, contenutoDaModficare, rcDTO.getQuantita());

        return new ResponseEntity<>("Quantita aggiunta al carrello correttamente", HttpStatus.OK);
    }

    @DeleteMapping("/svuota-carrello")
    public ResponseEntity<Object> svuotaCarrello(@RequestParam String email){
        //TODO autenticazione
        carrelloService.svuota(email);
        return new ResponseEntity<>("Carrello svuotato con successo", HttpStatus.OK);
    }
}
