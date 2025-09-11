package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;

public class AccountController {
    AccountService accService = new AccountService();
    ContenutoService conService = new ContenutoService();

    public void creaUtente(String email, String password, String nome, String cognome) {
        Utente u = new Utente(email, password, nome, cognome);
        accService.aggiungiUtente(u);
    }

    public void modificaUtente(String precEmail, String email, String password, String nome, String cognome) {
        Utente u = new Utente(email, password, nome, cognome);
        //TODO finire
        accService.modificaUtente();
    }

    public void rimuoviUtente(String email) {
        //TODO modifica
        accService.rimuoviUtente(null);
    }

    public void creaVenditore(String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione) {
        Venditore v = new Venditore(email, password, PIVA, ragioneFiscale, descrizione, posizione);
        accService.aggiungiVenditore(v);
    }

    public void modificaVenditore(String precEmail, String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione) {
        Venditore v = new Venditore(email, password, PIVA, ragioneFiscale, descrizione, posizione);
        //TODO finire
        accService.modificaVenditore();
    }

    public void rimuoviVenditore(String PIVA) {
        //TODO modifica
        accService.rimuoviVenditore(null);
    }

    public Utente getUtente(String email) {
        return accService.getUtenteByEmail(email);
    }

    public Venditore getVenditore(String PIVA) {
        return accService.getVenditoreByPIVA(PIVA);
    }

    public Carrello getCarrelloUtente(String email) {
        return accService.getUtenteByEmail(email).getCarrello();
    }

    public void svuotaCarrello(String email) {
        accService.getUtenteByEmail(email).getCarrello().svuota();
    }

    public void aggiungiContenutoCarrello(String email, Contenuto c, int quant) {
        accService.getUtenteByEmail(email).getCarrello().aggiungiContenuto(new RigaCarrello(c, quant));
    }
}
