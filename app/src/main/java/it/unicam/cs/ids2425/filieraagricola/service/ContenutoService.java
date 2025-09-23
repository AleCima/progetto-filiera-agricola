package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.*;
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
        return contenutoRepository.findAll();
    }

    public Contenuto getContenutoById(int id) {

        return contenutoRepository.findById(id).orElse(null);
    }

    public List<Contenuto> getContenutoByVenditore(String emailVenditore) {
        return contenutoRepository.findAllByVenditore(emailVenditore);
    }

    public void addContenuto(Contenuto contenuto) {
        contenutoRepository.save(contenuto);
    }

    public void updateContenuto(Contenuto contenuto) {
        contenutoRepository.save(contenuto);
    }

    public void removeContenuto(Contenuto contenuto) {
        contenutoRepository.delete(contenuto);
    }

    public void addTrasformazioneTo(Prodotto prodotto, Trasformazione trasformazione) {
        prodotto.addTrasformazione(trasformazione);
        contenutoRepository.save(prodotto);
    }

    public void removeTrasformazioneFrom(Prodotto prodotto, Trasformazione trasformazione) {
        prodotto.removeTrasformazione(trasformazione);
        contenutoRepository.save(prodotto);
    }

    public void addContenutoToPacchetto(Contenuto contenuto, Pacchetto pacchetto) {
        pacchetto.addProdotto(contenuto);
        contenutoRepository.save(pacchetto);
    }

    public void removeProdottoFromPacchetto(Prodotto prodotto, Pacchetto pacchetto) {
        pacchetto.removeProdotto(prodotto);
        contenutoRepository.save(pacchetto);
    }
}
