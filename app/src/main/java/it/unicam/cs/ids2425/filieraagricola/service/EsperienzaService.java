package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.Evento;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Visita;
import it.unicam.cs.ids2425.filieraagricola.repository.EsperienzaRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.EventoRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsperienzaService {

    private final EsperienzaRepository esperienzaRepository;
    private final AccountService accountService;
    private final VisitaRepository visitaRepository;
    private final EventoRepository eventoRepository;


    @Autowired
    public EsperienzaService(EsperienzaRepository esperienzaRepository, AccountService accountService, VisitaRepository visitaRepository, EventoRepository eventoRepository) {
        this.esperienzaRepository = esperienzaRepository;
        this.accountService = accountService;
        this.visitaRepository = visitaRepository;
        this.eventoRepository = eventoRepository;
    }

    public List<Esperienza> getEsperienze() {
        //TODO
        return null;
    }

    public List<Esperienza> getEsperienzeByAnimatore(Utente utente) {
        //TODO
        return null;
    }

    public Esperienza getEsperienzaById(int id) {
        //TODO
        return null;
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

    public void addVisita(Visita visita) {
        esperienzaRepository.save(visita);
    }


    public void addEvento(Evento evento) {
        esperienzaRepository.save(evento);
    }

    public void addEsperienza(Esperienza esperienza) {
        esperienzaRepository.save(esperienza);
    }

    public Evento getEvento(int id) {
        return (Evento) esperienzaRepository.findById(id).orElse(null);
    }

    public Visita getVisita(int id) {
        return (Visita) esperienzaRepository.findById(id).orElse(null);
    }

    public void rimuoviEvento(Evento evento) {
        esperienzaRepository.delete(evento);
    }

    public void rimuoviVisita(Visita visita) {
        esperienzaRepository.delete(visita);
    }

    public void modificaVisita(Visita visita) {
        esperienzaRepository.save(visita);
    }
}
