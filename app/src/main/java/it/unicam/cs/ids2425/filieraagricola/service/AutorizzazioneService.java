package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Conferma;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorizzazioneService {
    private List<Contenuto> contenuti;


    public List<Contenuto> getContenutiInAttesa() {
        return contenuti;
    }

    public void Autorizza(Contenuto id) {
        id.setStatoConferma(Conferma.APPROVATO);
    }

    public void Rifiuta(Contenuto id) {
        id.setStatoConferma(Conferma.RIFIUTATO);
    }
}
