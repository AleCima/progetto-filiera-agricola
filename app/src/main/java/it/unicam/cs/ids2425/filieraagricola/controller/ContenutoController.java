package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricola.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricola.model.Trasformazione;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;

import java.util.Date;
import java.util.List;

public class ContenutoController {
    ContenutoService contenutoService;
    AccountService accountService; //Probabilmente servira' per controllare chi pubblica cosa(?)

    public ContenutoController(ContenutoService contenutoService, AccountService accountService) {
        this.contenutoService = contenutoService;
        this.accountService = accountService;
    }

    //addProdotto
    public void addContenuto(int id,
                             Date dataCaricamento,
                             String descrizione,
                             String nome,
                             String metodoDiColtivazione,
                             double prezzo,
                             Venditore produttore,
                             List<String> certificazioni,
                             Date dataProduzione) {
    }

    //addTrasformazione
    public void addContenuto(int id,
                             Date dataCaricamento,
                             String descrizione,
                             Venditore trasformatore,
                             double prezzo) {
     }

    //addPacchetto
    public void addContenuto(int id, Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore) {
    }

    public void updateContenuto(int id) {
        contenutoService.updateContenuto(id);
    }

    public void removeContenuto(int id) {
        contenutoService.removeContenuto(id);
    }

    public void addTrasformazioneTo(int idProdotto, int idTrasformazione) {
    }

    public void removeTrasformazioneFrom(int idProdotto, int idTrasformazione) {
        contenutoService.removeTrasformazioneFrom(idProdotto, idTrasformazione);
    }

}
