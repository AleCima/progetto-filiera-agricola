package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;

public class ContenutoController {
    ContenutoService contenutoService = new ContenutoService();
    AccountService accountService = new AccountService(); //Probabilmente servira per controllare chi pubblica cosa(?)

    public void addContenuto(Contenuto contenuto) {
        contenutoService.addContenuto(contenuto);
    }

    public void updateContenuto(Contenuto contenuto) {
        contenutoService.updateContenuto(contenuto);
    }

    public void removeContenuto(Contenuto contenuto) {
        contenutoService.removeContenuto(contenuto);
    }

}
