package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.repository.EsperienzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsperienzaService {

    private final EsperienzaRepository esperienzaRepository;
    private final AccountService accountService;


    @Autowired
    public EsperienzaService(EsperienzaRepository esperienzaRepository, AccountService accountService) {
        this.esperienzaRepository = esperienzaRepository;
        this.accountService = accountService;
    }

    // Restituisce tutte le esperienze
    public List<Esperienza> getEsperienze() {
        return esperienzaRepository.findAll();
    }

    // Restituisce le esperienze di un animatore/organizzatore specifico
    public List<Esperienza> getEsperienzeByAnimatore(Utente utente) {
        return esperienzaRepository.findByOrganizzatore(utente);
    }

    // Restituisce un'esperienza per ID
    public Esperienza getEsperienzaById(int id) {
        return esperienzaRepository.findById(id).orElse(null);
    }

    public void addPartecipante(int idEsperienza, String emailUtente) {
        Esperienza esperienza = esperienzaRepository.findById(idEsperienza).orElse(null);
        Utente utente = accountService.getUtenteByEmail(emailUtente);
        esperienza.aggiungiPartecipante(utente);
        esperienzaRepository.save(esperienza);
    }

    //FINIRE
    public void removePartecipante(int idEsperienza, String emailUtente) {
        Esperienza esperienza = esperienzaRepository.findById(idEsperienza).orElse(null);
        Utente utente = accountService.getUtenteByEmail(emailUtente);
        esperienza.rimuoviPartecipante(utente);
        esperienzaRepository.save(esperienza);
    }


    public void addEsperienza(Esperienza esperienza) {
        esperienzaRepository.save(esperienza);
    }
    public Esperienza getEsperienza(int id) {
        return esperienzaRepository.findById(id).orElse(null);
    }
    public void rimuoviEsperienza(Esperienza esperienza){
        esperienzaRepository.delete(esperienza);
    }
    public void modificaEsperienza(Esperienza esperienza){
        esperienzaRepository.save(esperienza);
    }

}
