package it.unicam.cs.ids2425.filieraagricola.controller;


import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EventoDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.PropostaDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VisitaDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.EsperienzaService;
import it.unicam.cs.ids2425.filieraagricola.service.PropostaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/esperienza")
public class EsperienzaController {
    EsperienzaService espService;
    AccountService accService;
    PropostaService propService;

    public EsperienzaController(EsperienzaService espService, AccountService accService, PropostaService propService) {
        this.espService = espService;
        this.accService = accService;
        this.propService = propService;
    }

    //inviaProposta
    @PostMapping("/invia-proposta")
    public ResponseEntity<Object> inviaProposta(@RequestBody PropostaDTO propostaDTO) {
        Proposta proposta = new Proposta(propostaDTO.getTitolo(),propostaDTO.getDescrizione(),accService.getUtenteByEmail(propostaDTO.getOrganizzatore()),accService.getVenditoreByEmail(propostaDTO.getDestinatario()));
        propService.aggiungiProposta(proposta);
        return new ResponseEntity<>("Proposta inviata ed aggiunta con successo", HttpStatus.OK);
    }

    //modificaProposta
    @PutMapping("/modifica-proposta")
    public ResponseEntity<Object> modificaProposta(@RequestBody PropostaDTO propostaDTO, @RequestParam int id) {
        Proposta proposta = propService.getProposta(id);
        proposta.setTitolo(propostaDTO.getTitolo());
        proposta.setDestinatario(accService.getVenditoreByEmail(propostaDTO.getDestinatario()));
        proposta.setOrganizzatore(accService.getUtenteByEmail(propostaDTO.getOrganizzatore()));
        proposta.setDescrizione(propostaDTO.getDescrizione());
        propService.modificaProposta(proposta);
        return new ResponseEntity<>("Proposta modificata con successo", HttpStatus.OK);
    }

    //deleteProposta
    @DeleteMapping("/annulla-proposta")
    public ResponseEntity<Object> annullaProposta(@RequestParam int id) {
        Proposta proposta = propService.getProposta(id);
        propService.rimuoviProposta(proposta);
        return new ResponseEntity<>("Proposta annullata ed eliminata con successo", HttpStatus.OK);
    }

    //creaVisita
    @PostMapping("/crea-visita")
    public ResponseEntity<Object> creaVisita(@RequestBody VisitaDTO visitaDTO) {
        Visita visita = new Visita(accService.getUtenteByEmail(visitaDTO.getOrganizzatore()),visitaDTO.getDataEsperienza(),visitaDTO.getNumMaxPartecipanti(),visitaDTO.getPosizione(),accService.getVenditoreByEmail(visitaDTO.getAzienda()));
        espService.addVisita(visita);
        return new ResponseEntity<>("Visita creata con successo", HttpStatus.OK);
    }

    @PutMapping("/modifica-visita")
    public ResponseEntity<Object> modificaVisita(@RequestBody VisitaDTO visitaDTO, @RequestParam int id) {
        Visita visita = espService.getVisita(id);
        visita.setOrganizzatore(accService.getUtenteByEmail(visitaDTO.getOrganizzatore()));
        visita.setDataEsperienza(visitaDTO.getDataEsperienza());
        visita.setNumMaxPartecipanti(visitaDTO.getNumMaxPartecipanti());
        visita.setPosizione(visitaDTO.getPosizione());
        visita.setAzienda(accService.getVenditoreByEmail(visitaDTO.getAzienda()));
        espService.modificaVisita(visita);
        return new ResponseEntity<>("Visita modificata con successo", HttpStatus.OK);
    }

    @DeleteMapping("/elimina-visita")
    public ResponseEntity<Object> eliminaVisita(@RequestParam int id) {
        Visita visita = espService.getVisita(id);
        espService.rimuoviVisita(visita);
        return new ResponseEntity<>("Visita eliminata con successo", HttpStatus.OK);
    }

    //creaEvento
    @PostMapping("/crea-evento")
    public ResponseEntity<Object> creaEvento(@RequestBody EventoDTO eventoDTO) {
        Evento evento = new Evento(accService.getUtenteByEmail(eventoDTO.getOrganizzatore()),eventoDTO.getDataEsperienza(),eventoDTO.getNumMaxPartecipanti(),eventoDTO.getPosizione());
        espService.addEvento(evento);
        return new ResponseEntity<>("Evento creato con successo", HttpStatus.OK);
    }

    @PutMapping("/modifica-evento")
    public ResponseEntity<Object> modificaEvento(@RequestBody EventoDTO eventoDTO, @RequestParam int id) {
        Evento evento = espService.getEvento(id);
        evento.setOrganizzatore(accService.getUtenteByEmail(eventoDTO.getOrganizzatore()));
        evento.setDataEsperienza(eventoDTO.getDataEsperienza());
        evento.setNumMaxPartecipanti(eventoDTO.getNumMaxPartecipanti());
        evento.setPosizione(eventoDTO.getPosizione());
        espService.addEsperienza(evento);
        return new ResponseEntity<>("Evento modificato con successo", HttpStatus.OK);
    }

    @DeleteMapping("/elimina-evento")
    public ResponseEntity<Object> eliminaEvento(@RequestParam int id) {
        Evento evento = (Evento) espService.getEvento(id);
        espService.rimuoviEvento(evento);
        return new ResponseEntity<>("Evento eliminato con successo", HttpStatus.OK);
    }

    public void aggiungiPartecipante(String email) {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void rimuoviPartecipante(String email) {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }
}
