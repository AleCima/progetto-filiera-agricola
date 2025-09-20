package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrelloService {
    UtenteRepository utenteRepository;
    AccountService accountService;

    @Autowired
    public CarrelloService(UtenteRepository utenteRepository, AccountService accountService) {
        this.utenteRepository = utenteRepository;
        this.accountService = accountService;
    }

    public Carrello getCarrelloFromUtente(String email){
        return accountService.getUtenteByEmail(email).getCarrello();
    }

    public void aggiungiContenuto(String email, RigaCarrello rc){
        accountService.getUtenteByEmail(email).getCarrello().aggiungiContenuto(rc);
    }

    public void rimuoviContenuto(String email, Contenuto c, int quant){
        for (RigaCarrello rc : accountService.getUtenteByEmail(email).getCarrello().getContenuti()) {
            if (rc.getContenuto().equals(c)){
                rc.setQuantita(rc.getQuantita() - quant);
            }
        }
    }

    public void svuota(String email){
        accountService.getUtenteByEmail(email).setCarrello(new Carrello());
    }
}
