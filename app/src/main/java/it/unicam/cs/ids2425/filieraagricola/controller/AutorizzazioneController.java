package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.AutorizzazioneService;

import java.util.List;

public class AutorizzazioneController {
    private AutorizzazioneService autorizzazioneService;


    public List<Contenuto> getContenutiInAttesa() {
        return autorizzazioneService.getContenutiInAttesa();
    }

    public void autorizzaContenuto(Contenuto contenuti) {
        autorizzazioneService.Autorizza(contenuti);
    }

    public void rifiutaContenuto(Contenuto contenuti) {
        autorizzazioneService.Rifiuta(contenuti);
    }
}
