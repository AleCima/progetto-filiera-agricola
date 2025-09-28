package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EventoDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.PropostaDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VisitaDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.EsperienzaService;
import it.unicam.cs.ids2425.filieraagricola.service.PropostaService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.Handler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.NonNullOrEmptyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Classe contenente tutte le operazioni relative alle esperienz/eventi/visite
 */
@RestController
@RequestMapping("/esperienza")
public class EsperienzaController {

    private final EsperienzaService espService;
    private final AccountService accService;
    private final PropostaService propostaService;
    private final Handler esperienzaDataHandler;

    @Autowired
    public EsperienzaController(EsperienzaService espService, AccountService accService, PropostaService propostaService) {
        this.espService = espService;
        this.accService = accService;
        this.propostaService = propostaService;
        this.esperienzaDataHandler = new NonNullOrEmptyHandler();
    }

    // ------------------------ PROPOSTE ------------------------

    /**
     * Metodo per inviare una proposta ad un venditore, che sia un invito ad un evento o una richiesta di una visita
     * Solo gli animatori possono farlo
     * @param propostaDTO Dati della proposta da inviare
     * @return messaggio di successo/errore
     */
    @PostMapping("/invia-proposta")
    public ResponseEntity<Object> inviaProposta(@RequestBody PropostaDTO propostaDTO) {
        Utente organizzatore = accService.getUtenteByEmail(propostaDTO.getOrganizzatore());
        Venditore destinatario = accService.getVenditoreByEmail(propostaDTO.getDestinatario());

        if (organizzatore == null)
            return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);
        if (destinatario == null)
            return new ResponseEntity<>("Destinatario non trovato", HttpStatus.NOT_FOUND);

        // Controllo che l'organizzatore sia un animatore
        if (!organizzatore.getRuoli().contains(Ruolo.ANIMATORE))
            return new ResponseEntity<>("L'organizzatore deve essere un animatore", HttpStatus.BAD_REQUEST);

        Proposta proposta = new Proposta(
                propostaDTO.getTitolo(),
                propostaDTO.getDescrizione(),
                organizzatore,
                destinatario
        );

        try {
            esperienzaDataHandler.check(proposta);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        propostaService.aggiungiProposta(proposta);
        return new ResponseEntity<>("Proposta inviata con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la modifica di una proposta inviata. Solo gli animatori possono farlo
     * @param propostaDTO Dati della proposta modificata
     * @param id Id della proposta da modificare
     * @return messaggio di errore/successo
     */
    @PutMapping("/modifica-proposta")
    public ResponseEntity<Object> modificaProposta(@RequestBody PropostaDTO propostaDTO, @RequestParam int id) {
        Proposta proposta = propostaService.getProposta(id);
        if (proposta == null) return new ResponseEntity<>("Proposta non trovata", HttpStatus.NOT_FOUND);

        Utente organizzatore = accService.getUtenteByEmail(propostaDTO.getOrganizzatore());
        Venditore destinatario = accService.getVenditoreByEmail(propostaDTO.getDestinatario());

        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);
        if (destinatario == null) return new ResponseEntity<>("Destinatario non trovato", HttpStatus.NOT_FOUND);

        proposta.setTitolo(propostaDTO.getTitolo());
        proposta.setDescrizione(propostaDTO.getDescrizione());
        proposta.setOrganizzatore(organizzatore);
        proposta.setDestinatario(destinatario);

        try { esperienzaDataHandler.check(proposta); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }

        propostaService.modificaProposta(proposta);
        return new ResponseEntity<>("Proposta modificata con successo", HttpStatus.OK);
    }

    /**
     * Metodo per annullare una proposta inviata. Solo gli animatori possono
     * @param id Id della proposta da eliminare
     * @return messaggio di Errore/successo
     */
    @DeleteMapping("/annulla-proposta")
    public ResponseEntity<Object> annullaProposta(@RequestParam int id) {
        Proposta proposta = propostaService.getProposta(id);
        if (proposta == null) return new ResponseEntity<>("Proposta non trovata", HttpStatus.NOT_FOUND);
        propostaService.rimuoviProposta(proposta);
        return new ResponseEntity<>("Proposta annullata con successo", HttpStatus.OK);
    }

    /**
     * Metodo per ottenere le proposte inviate ad un venditore, solo l'interessato o gli animatori possono richiamarlo
     * @param email Email del venditore di cui vedere le proposte
     * @return messaggio di errore/lista di proposte
     */
    @PreAuthorize("#email.equals(authentication.email) or hasRole('GESTORE') or hasRole('ANIMATORE')")
    @GetMapping("/proposte-venditore")
    public ResponseEntity<Object> getProposteVenditore(@RequestParam String email) {
        Venditore venditore = accService.getVenditoreByEmail(email);
        if (venditore == null) return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(propostaService.getProposteVenditore(venditore), HttpStatus.OK);
    }

    /**
     * Metodo per ottenere le proposte inviate da un organizzatore
     * @param email Email dell'organizzatore di cui ottenere le proposte
     * @return messaggio di errore/lista di proposte
     */
    @GetMapping("/proposte-organizzatore")
    public ResponseEntity<Object> getProposteOrganizzatore(@RequestParam String email) {
        Utente organizzatore = accService.getUtenteByEmail(email);
        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(propostaService.getProposteOrganizzatore(organizzatore), HttpStatus.OK);
    }

    /**
     * Metodo richiamato per accettare una proposta, solo destinatari possono
     * @param id Id della proposta da accettare
     * @return
     */
    @PreAuthorize("@propostaService.isDestinatario(#id, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/accetta-proposta")
    public ResponseEntity<Object> accettaProposta(@RequestParam int id) {
        Proposta proposta = propostaService.getProposta(id);
        if (proposta == null) {
            return new ResponseEntity<>("Proposta non trovata", HttpStatus.NOT_FOUND);
        }

        // Controllo che la proposta non sia già accettata
        if (proposta.isAccettata()) {
            return new ResponseEntity<>("Proposta già accettata", HttpStatus.BAD_REQUEST);
        }

        // Cambia il flag a true
        proposta.setAccettata(true);
        propostaService.modificaProposta(proposta);

        return new ResponseEntity<>("Proposta accettata con successo", HttpStatus.OK);
    }

    // ------------------------ VISITE ------------------------

    /**
     * Metodo per la creazione di un esperienza(Visita), solo gli animatori possono
     * @param visitaDTO Dati della visita da creare
     * @return messaggio di errore/successo
     */
    @PostMapping("/crea-visita")
    public ResponseEntity<Object> creaVisita(@RequestBody VisitaDTO visitaDTO) {
        Utente organizzatore = accService.getUtenteByEmail(visitaDTO.getOrganizzatore());
        Venditore azienda = accService.getVenditoreByEmail(visitaDTO.getAzienda());

        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);
        if (azienda == null) return new ResponseEntity<>("Azienda non trovata", HttpStatus.NOT_FOUND);

        Visita visita = new Visita(
                visitaDTO.getTitolo(),
                visitaDTO.getDescrizione(),
                organizzatore,
                visitaDTO.getDataEsperienza(),
                visitaDTO.getNumMaxPartecipanti(),
                visitaDTO.getPosizione(),
                azienda
        );

        try { esperienzaDataHandler.check(visita); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }

        espService.addEsperienza(visita);
        return new ResponseEntity<>("Visita creata con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la modifica di una visita gia creata
     * @param visitaDTO Dati aggiornati della visita
     * @param id Id della visita da aggiornare
     * @return messaggio di errore/successo
     */
    @PutMapping("/modifica-visita")
    public ResponseEntity<Object> modificaVisita(@RequestBody VisitaDTO visitaDTO, @RequestParam int id) {
        Visita visita = (Visita) espService.getEsperienzaById(id);
        if (visita == null) return new ResponseEntity<>("Visita non trovata", HttpStatus.NOT_FOUND);

        Utente organizzatore = accService.getUtenteByEmail(visitaDTO.getOrganizzatore());
        Venditore azienda = accService.getVenditoreByEmail(visitaDTO.getAzienda());
        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);
        if (azienda == null) return new ResponseEntity<>("Azienda non trovata", HttpStatus.NOT_FOUND);

        visita.setTitolo(visitaDTO.getTitolo());
        visita.setDescrizione(visitaDTO.getDescrizione());
        visita.setOrganizzatore(organizzatore);
        visita.setDataEsperienza(visitaDTO.getDataEsperienza());
        visita.setNumMaxPartecipanti(visitaDTO.getNumMaxPartecipanti());
        visita.setPosizione(visitaDTO.getPosizione());
        visita.setAzienda(azienda);

        try { esperienzaDataHandler.check(visita); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }

        espService.modificaEsperienza(visita);
        return new ResponseEntity<>("Visita modificata con successo", HttpStatus.OK);
    }

    /**
     * Metodo per l'eliminazione di una visita gia creata
     * @param id Id della visita da eliminare
     * @return messaggio di errore/successo
     */
    @DeleteMapping("/elimina-visita")
    public ResponseEntity<Object> eliminaVisita(@RequestParam int id) {
        Esperienza esperienza = espService.getEsperienzaById(id);
        if (esperienza == null) return new ResponseEntity<>("Visita non trovata", HttpStatus.NOT_FOUND);
        espService.rimuoviEsperienza(esperienza);
        return new ResponseEntity<>("Visita eliminata con successo", HttpStatus.OK);
    }

    // ------------------------ EVENTI ------------------------

    /**
     * Metodo per la creazine di un esperienza(Evento)
     * @param eventoDTO Dati dell'evento da inserire
     * @return messaggio di successo/errore
     */
    @PostMapping("/crea-evento")
    public ResponseEntity<Object> creaEvento(@RequestBody EventoDTO eventoDTO) {
        Utente organizzatore = accService.getUtenteByEmail(eventoDTO.getOrganizzatore());
        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);

        Evento evento = new Evento(
                eventoDTO.getTitolo(),
                eventoDTO.getDescrizione(),
                organizzatore,
                eventoDTO.getDataEsperienza(),
                eventoDTO.getNumMaxPartecipanti(),
                eventoDTO.getPosizione()
        );

        try { esperienzaDataHandler.check(evento); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }

        espService.addEsperienza(evento);
        return new ResponseEntity<>("Evento creato con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la modifica delle informazioni di un evento gia caricato
     * @param eventoDTO Dati aggiornati dell'evento
     * @param id Id dell'evento da modificare
     * @return messaggio di successo/errore
     */
    @PutMapping("/modifica-evento")
    public ResponseEntity<Object> modificaEvento(@RequestBody EventoDTO eventoDTO, @RequestParam int id) {
        Evento evento = (Evento) espService.getEsperienzaById(id);
        if (evento == null) return new ResponseEntity<>("Evento non trovato", HttpStatus.NOT_FOUND);

        Utente organizzatore = accService.getUtenteByEmail(eventoDTO.getOrganizzatore());
        if (organizzatore == null) return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.NOT_FOUND);

        evento.setTitolo(eventoDTO.getTitolo());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setOrganizzatore(organizzatore);
        evento.setDataEsperienza(eventoDTO.getDataEsperienza());
        evento.setNumMaxPartecipanti(eventoDTO.getNumMaxPartecipanti());
        evento.setPosizione(eventoDTO.getPosizione());

        try { esperienzaDataHandler.check(evento); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }

        espService.modificaEsperienza(evento);
        return new ResponseEntity<>("Evento modificato con successo", HttpStatus.OK);
    }

    /**
     * Metodo per la rimozione di un evento da caricare
     * @param id Id dell'evento da rimuovere
     * @return messaggio di successo/errore
     */
    @DeleteMapping("/elimina-evento")
    public ResponseEntity<Object> eliminaEvento(@RequestParam int id) {
        Esperienza esperienza = espService.getEsperienzaById(id);
        if (esperienza == null) return new ResponseEntity<>("Evento non trovato", HttpStatus.NOT_FOUND);
        espService.rimuoviEsperienza(esperienza);
        return new ResponseEntity<>("Evento eliminato con successo", HttpStatus.OK);
    }

    // ------------------------ PARTECIPANTI ------------------------

    /**
     * Metodo per aggiungere un partecipante ad un esperienza, richiamabile da un utente registrato (Acquirente)
     * @param esperienzaId Id dell'esperienza a cui ci si vuole registrare
     * @param emailUtente Email dell'utente che si vuole prenotare
     * @return messaggio di successo/errore
     */
    @PreAuthorize("#emailUtente == authentication.name or hasRole('GESTORE')")
    @PutMapping("/aggiungi-partecipante")
    public ResponseEntity<Object> aggiungiPartecipante(@RequestParam int esperienzaId, @RequestParam String emailUtente) {
        Utente partecipante = accService.getUtenteByEmail(emailUtente);
        Esperienza esperienza = espService.getEsperienzaById(esperienzaId);

        if (esperienza == null) return new ResponseEntity<>("Esperienza non trovata", HttpStatus.NOT_FOUND);
        if (partecipante == null) return new ResponseEntity<>("Partecipante non trovato", HttpStatus.NOT_FOUND);
        if (esperienza.getPartecipanti().size() >= esperienza.getNumMaxPartecipanti())
            return new ResponseEntity<>("Esperienza al completo", HttpStatus.BAD_REQUEST);

        espService.addPartecipante(esperienzaId, emailUtente);
        return new ResponseEntity<>("Partecipante aggiunto con successo", HttpStatus.OK);
    }

    /**
     * Metodo per togliere la partecipazione ad un esperienza, richiamabile da un utente registrato (Acquirente)
     * @param esperienzaId Id dell'esperienza a cui si vuole rimuovere la prenotazione
     * @param emailUtente Email dell'utente interessato
     * @return
     */
    @PreAuthorize("#emailUtente == authentication.name or hasRole('GESTORE')")
    @PutMapping("/rimuovi-partecipante")
    public ResponseEntity<Object> rimuoviPartecipante(@RequestParam int esperienzaId, @RequestParam String emailUtente) {
        Utente partecipante = accService.getUtenteByEmail(emailUtente);
        Esperienza esperienza = espService.getEsperienzaById(esperienzaId);

        if (esperienza == null) return new ResponseEntity<>("Esperienza non trovata", HttpStatus.NOT_FOUND);
        if (partecipante == null) return new ResponseEntity<>("Partecipante non trovato", HttpStatus.NOT_FOUND);

        espService.removePartecipante(esperienzaId, emailUtente);
        return new ResponseEntity<>("Partecipante rimosso con successo", HttpStatus.OK);
    }

    @GetMapping("/ottieni-esperienze")
    public ResponseEntity<Object> getEsperienze(){
        espService.getEsperienze();
        return new ResponseEntity<>(espService.getEsperienze(), HttpStatus.OK);
    }


}


