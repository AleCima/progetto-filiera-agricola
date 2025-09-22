package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.RigaCarrelloDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Carrello;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.RigaCarrello;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.CarrelloService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
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

    public CarrelloController(CarrelloService carrelloService, AccountService accountService, ContenutoService contenutoService) {
        this.carrelloService = carrelloService;
        this.accountService = accountService;
        this.contenutoService = contenutoService;
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
        carrelloService.aggiungiContenuto(email, rc);
        System.out.println(rcDTO.getQuantita());
        return new ResponseEntity<>("Contenuto aggiunto correttamente", HttpStatus.OK);
    }

    @PostMapping("/rimuovi-contenuto")
    public ResponseEntity<Object> rimuoviContenuto(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaRimuovere = contenutoService.getContenutoById(rcDTO.getId());
        if (!carrelloService.contains(carrelloUt, contenutoDaRimuovere)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }
        //TODO Controlla se va fatto in un altro modo creando un metodo che restituisce la riga carrello
        // che contiene il contenuto da rimuovere. DA TESTARE

        for(RigaCarrello rc : carrelloUt.getContenuti()){
            if (rc.getContenuto().getId() == rcDTO.getId()){
                //Controllo se si intende rimuovere il contenuto dal carrello
                if(rc.getQuantita() - rcDTO.getQuantita() < 1){
                    carrelloService.rimuoviContenuto(rc , rcDTO.getQuantita());
                }else {
                    rc.setQuantita( rc.getQuantita() - rcDTO.getQuantita());
                }
            }
        }
        return new ResponseEntity<>("Quantità del contenuto rimossa con successo", HttpStatus.OK);
    }

    @PostMapping("/aggiungi-quantita")
    public ResponseEntity<Object> aggiungiQuantita(@RequestParam String email, @RequestBody RigaCarrelloDTO rcDTO){
        Carrello carrelloUt = carrelloService.getCarrelloFromUtente(email);
        Contenuto contenutoDaModficare = contenutoService.getContenutoById(rcDTO.getId());
        if (!carrelloService.contains(carrelloUt, contenutoDaModficare)){
            return new ResponseEntity<>("Contenuto non presente nel carrello", HttpStatus.BAD_REQUEST);
        }
        carrelloService.aggiungiQuantita(email, contenutoDaModficare, rcDTO.getQuantita());

        return new ResponseEntity<>("Quantita aggiunta al carrello correttamente", HttpStatus.OK);
    }

    @DeleteMapping("/svuota-carrello")
    public ResponseEntity<Object> svuotaCarrello(@RequestParam String email){
        carrelloService.svuota(email);
        return new ResponseEntity<>("Carrello svuotato con successo", HttpStatus.OK);
    }
}
