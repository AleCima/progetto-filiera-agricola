package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Conferma;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorizzazioneService {
    private ContenutoRepository contenutoRepository;

    @Autowired
    public AutorizzazioneService(ContenutoRepository contenutoRepository) {
        this.contenutoRepository = contenutoRepository;
    }

    public List<Contenuto> getContenutiInAttesa() {
        return contenutoRepository.findContenutiInAttesa();
    }

    public void Autorizza(Contenuto c) {

        c.setStatoConferma(Conferma.APPROVATO);
        contenutoRepository.save(c);

    }

    public void Rifiuta(Contenuto c) {
        c.setStatoConferma(Conferma.RIFIUTATO);
        contenutoRepository.save(c);
    }
}
