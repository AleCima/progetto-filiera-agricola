package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.PacchettoService;

import java.util.List;

public class PacchettoController {
    PacchettoService pacchettoService;

    public PacchettoController(PacchettoService pacchettoService) {
        this.pacchettoService = pacchettoService;
    }

    public void newPacchetto(List<Contenuto> contenuti) {
        pacchettoService.newPacchetto(contenuti);
    }

    public void addToPacchetto(int id, Contenuto contenuto) {
        pacchettoService.addToPacchetto(id, contenuto);
    }

    public void removeFromPacchetto(int id, Contenuto contenuto) {
        pacchettoService.removeFromPacchetto(id, contenuto);
    }

    public void removePacchetto(int id) {
        pacchettoService.removePacchetto(id);
    }

}
