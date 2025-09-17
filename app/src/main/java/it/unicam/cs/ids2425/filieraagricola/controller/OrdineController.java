package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.service.OrdineService;

public class OrdineController {
    OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    public void addOrdine(Ordine ordine) {
        ordineService.addOrdine(ordine);
    }

    public void updateOrdine(Ordine ordine) {
        ordineService.updateOrdine(ordine);
    }

    public void removeOrdine(Ordine ordine) {
        ordineService.removeOrdine(ordine);
    }
}
