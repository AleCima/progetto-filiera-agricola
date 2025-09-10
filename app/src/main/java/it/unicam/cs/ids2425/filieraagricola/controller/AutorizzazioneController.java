package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.AutorizzazioneService;

import java.util.List;

public class AutorizzazioneController {
    private AutorizzazioneService autorizzazione;
    private List<Contenuto> contenuti;

    private List<Contenuto> getContenutiInAttesa( ){
        return autorizzazione.getContenutiInAttesa();
    }

    private void autorizzaContenuto(Contenuto contenuti){
        autorizzazione.Autorizza(contenuti);
    }

    private void rifiutaContenuto(Contenuto contenuti){
        autorizzazione.Rifiuta(contenuti);
    }
}
