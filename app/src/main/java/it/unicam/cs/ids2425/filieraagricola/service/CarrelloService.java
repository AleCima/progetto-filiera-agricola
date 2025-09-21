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
        //Deve fa save se no non funzia post
        Utente u = utenteRepository.findById(email).orElse(null);
        u.getCarrello().aggiungiContenuto(rc);
        utenteRepository.save(u);
    }

    public void rimuoviContenuto(RigaCarrello rc, int quant){
        rc.setQuantita(rc.getQuantita() - quant);
    }

    public void aggiungiQuantita(RigaCarrello rc, int quant){

        rc.setQuantita(rc.getQuantita() + quant);
    }

    public void svuota(String email){
        accountService.getUtenteByEmail(email).setCarrello(new Carrello());
    }

    public boolean contains(Carrello carrello, Contenuto c){
        for(RigaCarrello rc : carrello.getContenuti()){
            if (rc.getContenuto().equals(c)){
                return true;
            }
        }
        return false;
    }
}
