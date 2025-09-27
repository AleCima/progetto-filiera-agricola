package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return utenteRepository.findById(email).orElse(null).getCarrello();
    }

    public void aggiungiContenuto(String email, RigaCarrello rc){
        Utente u = utenteRepository.findById(email).orElse(null);
        u.getCarrello().aggiungiContenuto(rc);
        utenteRepository.save(u);
    }

    public void rimuoviContenuto(String email, Contenuto c, int quant){
        Utente u = utenteRepository.findById(email).orElse(null);
        u.getCarrello().rimuoviQuantita(c, quant);
        utenteRepository.save(u);
    }

    public void aggiungiQuantita(String email, Contenuto c, int quant){
        Utente u = utenteRepository.findById(email).orElse(null);
        u.getCarrello().aggiungiQuantita(c, quant);
        utenteRepository.save(u);
    }

    public void svuota(String email){
        Utente u = utenteRepository.findById(email).orElse(null);
        u.getCarrello().svuota();
        utenteRepository.save(u);
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
