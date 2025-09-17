package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacchettoService {
    private ContenutoRepository contenutoRepository;

    @Autowired
    public PacchettoService(ContenutoRepository contenutoRepository) {
        this.contenutoRepository = contenutoRepository;
    }

    public List<Pacchetto> getPacchetti() {
        //TODO
        return null;
    }

    public Pacchetto getPacchettoById(int id) {
        //TODO
        return null;
    }

    public List<Pacchetto> getPacchettoByDistributore(Venditore venditore) {
        //TODO
        return null;
    }

    public void newPacchetto(List<Contenuto> contenuti) {
        //TODO
    }

    public void addToPacchetto(int id, Contenuto contenuto) {
        //TODO
    }

    public void removeFromPacchetto(int id, Contenuto contenuto) {
        //TODO
    }

    public void removePacchetto(int id) {
        //TODO
    }

}
