package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Proposta;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;

    @Autowired
    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public List<Proposta> getProposte() {
        return null;
    }

    public Proposta getProposta(int id) {
        return propostaRepository.findById(id).orElse(null);
    }

    public void aggiungiProposta(Proposta p) {
        //TODO da vedere i controlli
        propostaRepository.save(p);
    }

    public void modificaProposta(Proposta p){
        //TODO da vedere i controlli
        propostaRepository.save(p);
    }

    public void rimuoviProposta(Proposta p){
        //TODO da vedere i controlli
        propostaRepository.delete(p);
    }

    public List<Proposta> getProposteVenditore(Venditore v) {
        //TODO ricerca per venditore e crea lista
        return null;
    }

    public List<Proposta> getProposteOrganizzatore(Utente u) {
        //TODO ricerca per venditore e crea lista
        return null;
    }
}
