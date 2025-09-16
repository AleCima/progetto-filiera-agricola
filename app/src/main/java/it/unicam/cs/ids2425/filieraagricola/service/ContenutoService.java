package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenutoService {

    //Necessaria repository "Contenuto"
    private final ContenutoRepository contenutoRepository;

    @Autowired
    public ContenutoService(ContenutoRepository contenutoRepository) {
        this.contenutoRepository = contenutoRepository;
    }

    public List<Contenuto> getContenuti() {
        //TODO
        return null;
    }

    public Contenuto getContenutoById(int id) {
        //TODO
        return null;
    }

    public List<Contenuto> getContenutoByVenditore(Venditore venditore) {
        //TODO
        return null;
    }

    public void addContenuto(Contenuto contenuto) {
        //TODO
    }

    public void updateContenuto(int id) {
        //TODO
    }

    public void removeContenuto(int id) {
        //TODO
    }

    public void addTrasformazioneTo(int idProdotto, int idTrasformazione) {
        //TODO
    }

    public void removeTrasformazioneFrom(int idProdotto, int idTrasformazione) {
        //TODO
    }
}
