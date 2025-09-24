package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.OrdineDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.OrdineService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ordini")
public class OrdineController {
    OrdineService ordineService;
    AccountService accountService;
    Handler ordineDataHandler;

    public OrdineController(OrdineService ordineService, AccountService accountService) {
        this.ordineService = ordineService;
        this.accountService = accountService;
        ordineDataHandler = new NonNullOrEmptyHandler()
                .setNext(new IndirizzoFormatHandler())
                .setNext(new PagamentoValidHandler())
                .setNext(new DisponibilitaRigaCarrelloHandler());
    }


    @PostMapping("/aggiungi")
    public ResponseEntity<String> addOrdine(@RequestParam String email ,@RequestBody OrdineDTO ordineDTO) {


        // Creazione dell'oggetto Ordine
        Ordine ordine = new Ordine(
                ordineDTO.getDataOrdine(),
                accountService.getUtenteByEmail(email).getCarrello(),
                ordineDTO.getCartaDiCredito(),
                ordineDTO.getIndirizzoDiFatturazione(),
                accountService.getUtenteByEmail(email)
        );
        try{
            ordineDataHandler.check(ordine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // Chiamata al servizio per aggiungere l'ordine
        ordineService.addOrdine(ordine);

        return new ResponseEntity<>("Ordine aggiunto con successo", HttpStatus.CREATED); // Restituisce 201 (CREATED)
    }

    @PutMapping("/aggiorna")
    public ResponseEntity<String> updateOrdine(@RequestParam int id, @RequestParam String email, @RequestBody OrdineDTO ordineDTO) {
        // Recuperiamo l'ordine esistente dal database usando l'ID
        Ordine ordine = ordineService.getOrdineById(id);


        // Aggiorniamo i dati dell'ordine con quelli del DTO
        ordine.setDataOrdine(ordineDTO.getDataOrdine());
        ordine.setCarrello(accountService.getUtenteByEmail(email).getCarrello()); // Impostiamo il carrello aggiornato
        ordine.setCartaDiCredito(ordineDTO.getCartaDiCredito()); // Impostiamo il pagamento aggiornato
        ordine.setIndirizzoDiFatturazione(ordineDTO.getIndirizzoDiFatturazione()); // Impostiamo l'indirizzo aggiornato
        try{
            ordineDataHandler.check(ordine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // Chiamata al servizio per aggiornare l'ordine
        ordineService.updateOrdine(ordine);

        return new ResponseEntity<>("Ordine aggiornato con successo", HttpStatus.OK); // Restituisce 200 (OK)
    }
    @DeleteMapping("/rimuovi")
    public ResponseEntity<String> removeOrdine(@RequestParam int id) {
            ordineService.removeOrdine(id);
            return new ResponseEntity<>("Ordine rimosso con successo", HttpStatus.OK); // Restituisce 200 (OK)
    }

    @GetMapping("/ottieni")
    public ResponseEntity<Object> getOrdineById(@RequestParam int id){
        return new ResponseEntity<>(ordineService.getOrdineById(id), HttpStatus.OK);
    }

    @PutMapping("/evadi")
    public ResponseEntity<Object> evadiOrdine(@RequestParam int id){
        Ordine ordine = ordineService.getOrdineById(id);
        ordineDataHandler.check(ordine);
        ordineService.evadi(ordine);
        return new ResponseEntity<>("Ordine evaso con successo", HttpStatus.OK);
    }
    @GetMapping("ottieni-ordini-utente")
    public ResponseEntity<Object> getOrdiniUtente(@RequestParam String email){
        return new ResponseEntity<>(ordineService.getOrdineByUtente(accountService.getUtenteByEmail(email)), HttpStatus.OK);
    }

    @GetMapping("ottieni-ordini-venditore")
    public ResponseEntity<Object> getOrdiniVenditore(@RequestParam String email){
        return new ResponseEntity<>(ordineService.getOrdineByVenditore(accountService.getVenditoreByEmail(email)), HttpStatus.OK);
    }
}
