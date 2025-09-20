package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    CarrelloService carrelloService;
    AccountService accountService;


    public CarrelloController(CarrelloService carrelloService, AccountService accountService) {
        this.carrelloService = carrelloService;
        this.accountService = accountService;
    }

    @GetMapping("/carrello-utente")
    public ResponseEntity<Object> getCarrelloUtente(@RequestParam String email){
        if (accountService.getUtenteByEmail(email) == null){
            return new ResponseEntity<>("Utente non presente nel Sistema", HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(carrelloService.getCarrelloFromUtente(email), HttpStatus.OK);
    }
}
