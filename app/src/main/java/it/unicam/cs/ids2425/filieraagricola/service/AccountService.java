package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Account;
import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;

import java.util.List;

public class AccountService {
    private List<Utente> utenti;
    private List<Venditore> venditori;

    public void aggiungiUtente(Utente u){
        utenti.add(u);
    }

    public void rimuoviUtente(Utente u){
        utenti.remove(u);
    }

    public void modificaUtente(){
        //TODO
    }

    public void aggiungiVenditore(Venditore v){
        venditori.add(v);
    }

    public void rimuoviVenditore(Venditore v){
        venditori.remove(v);
    }

    public void modificaVenditore(){
        //TODO
    }

    public void assegnaRuolo(String email, Ruolo r){
        //TODO
    }

    public void assegnaRuoli(String email, List<Ruolo> r){
        //TODO
    }

    public Utente getUtenteByEmail(String email){
        //TODO
        return null;
    }

    public Venditore getVenditoreByPIVA(String PIVA){
        //TODO
        return null;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public List<Venditore> getVenditori() {
        return venditori;
    }
}
