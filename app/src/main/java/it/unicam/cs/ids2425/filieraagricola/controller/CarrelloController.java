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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Classe contenente tutte le operazione che posono essere effettuate sul carrello di un utente
 */
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

    /**
     * Metodo per la visualizzazione del carrello di un utente, ad invocare questo metodo puo essere l'utente che possiede tale carrello o il gestore
     * @param email Email dell'utente di cui si vuole visualizzare il carrello
     * @return Carrello dell'utente
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @GetMapping("/carrello-utente")
    public ResponseEntity<Object> getCarrelloUtente(@RequestParam String email){
        if (accountService.getUtenteByEmail(email) == null){
            return new ResponseEntity<>("Utente non presente nel Sistema", HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(carrelloService.getCarrelloFromUtente(email), HttpStatus.OK);
    }

    /**
     * Metodo per l'aggiunta di un contenuto al carrello dell'utente che richiama il metodo
     * @param email Email dell'utente che vuole inserire un contenuto nel proprio carrello
     * @param rcDTO Dati del contenuto da aggiungere, presi dal body della richiesta HTTP
     * @return messaggio di successo o insuccesso
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PostMapping("/aggiungi-contenuto")
    public ResponseEntity<Object> aggiungiContenutoAlCarrello(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Contenuto contenuto = contenutoService.getContenutoById(rcDTO.getId());
        if (contenuto == null){
            return new ResponseEntity<>("Contenuto non presente nel sistema", HttpStatus.NOT_FOUND);
        }
        RigaCarrello rc = new RigaCarrello(contenuto, rcDTO.getQuantita());
        try{
            cartDataHandler.check(rc);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        carrelloService.aggiungiContenuto(email, rc);
        return new ResponseEntity<>("Contenuto aggiunto correttamente", HttpStatus.OK);
    }

    /**
     * Metodo per la rimozione di un contenuto o di una quantita custom, richiamabile da un utente sul suo carrello
     * @param email Email dell'utente possessore del carrello
     * @param rcDTO Dati ottenuti dalla richiesta HTTP
     * @return messaggio di successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PostMapping("/rimuovi-contenuto")
    public ResponseEntity<Object> rimuoviQuantita(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaRimuovere = contenutoService.getContenutoById(rcDTO.getId());
        if (contenutoDaRimuovere == null){
            return new ResponseEntity<>("Contenuto non presente nel sistema", HttpStatus.NOT_FOUND);
        }

        if (!carrelloService.contains(carrelloUt, contenutoDaRimuovere)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }

        carrelloService.rimuoviContenuto(email, contenutoDaRimuovere, rcDTO.getQuantita());

        return new ResponseEntity<>("Quantità del contenuto rimossa con successo", HttpStatus.OK);
    }

    /**
     * Metodo per l'aggiunta di quantita ad un contenuto gia presente nel carrello, richiamabile da un utente sul suo carrello
     * @param email Email del possessore del carrello
     * @param rcDTO dati da modificare nel carrello
     * @return messaggio di successo o errore
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @PostMapping("/aggiungi-quantita")
    public ResponseEntity<Object> aggiungiQuantita(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaModficare = contenutoService.getContenutoById(rcDTO.getId());
        if (contenutoDaModficare == null){
            return new ResponseEntity<>("Contenuto non presente nel sistema", HttpStatus.NOT_FOUND);
        }
        if (!carrelloService.contains(carrelloUt, contenutoDaModficare)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }
        carrelloService.aggiungiQuantita(email, contenutoDaModficare, rcDTO.getQuantita());

        return new ResponseEntity<>("Quantita aggiunta al carrello correttamente", HttpStatus.OK);
    }

    /**
     * Metodo per svuotare il proprio carrello
     * @param email Email del possessore del carrello
     * @return messaggio di successo
     */
    @PreAuthorize("#email == authentication.name or hasRole('GESTORE')")
    @DeleteMapping("/svuota-carrello")
    public ResponseEntity<Object> svuotaCarrello(@RequestParam String email){
        carrelloService.svuota(email);
        return new ResponseEntity<>("Carrello svuotato con successo", HttpStatus.OK);
    }
}
