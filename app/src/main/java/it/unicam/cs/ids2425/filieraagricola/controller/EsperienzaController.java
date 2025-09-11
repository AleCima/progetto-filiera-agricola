package it.unicam.cs.ids2425.filieraagricola.controller;


import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.EsperienzaService;
import it.unicam.cs.ids2425.filieraagricola.service.PropostaService;

import java.util.Date;

public class EsperienzaController {
    EsperienzaService espService = new EsperienzaService();
    AccountService accService = new AccountService();
    PropostaService propService = new PropostaService();

    public void inviaProposta(String titolo, String descrizione, Utente organizzatore, Venditore destinatario) {
        propService.aggiungiProposta(new Proposta(titolo, descrizione, organizzatore, destinatario));
    }

    public void modificaProposta() {
        //TODO aggiungi qualcosa di unico per proposte
    }

    public void annullaProposta() {
        //TODO aggiungi qualcosa di unico per proposte
    }

    public void creaVisita(String email, Date dataEsperienza, int numMaxPartecipanti, String PIVA) {
        espService.addEsperienza(new Visita(accService.getUtenteByEmail(email), dataEsperienza, numMaxPartecipanti, accService.getVenditoreByPIVA(PIVA)));
    }

    public void modificaVisita() {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void annullaVisita() {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void creaEvento(String email, Date dataEsperienza, int numMaxPartecipanti, PuntoMappa posizione) {
        espService.addEsperienza(new Evento(accService.getUtenteByEmail(email), dataEsperienza, numMaxPartecipanti, posizione));
    }

    public void modificaEvento() {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void annullaEvento() {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void aggiungiPartecipante(String email) {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }

    public void rimuoviPartecipante(String email) {
        //TODO aggiungi qualcosa di unico nelle esperienze
    }
}
