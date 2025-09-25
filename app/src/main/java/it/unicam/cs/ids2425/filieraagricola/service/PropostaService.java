package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Proposta;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;

    @Autowired
    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public Proposta getProposta(int id) {
        return propostaRepository.findById(id).orElse(null);
    }

    public void aggiungiProposta(Proposta p) {
        propostaRepository.save(p);
    }

    public void modificaProposta(Proposta p){
        propostaRepository.save(p);
    }

    public void rimuoviProposta(Proposta p){
        propostaRepository.delete(p);
    }

    public List<Proposta> getProposteVenditore(Venditore v) {
        return propostaRepository.findByDestinatario(v);
    }

    public List<Proposta> getProposteOrganizzatore(Utente u) {
        return propostaRepository.findByOrganizzatore(u);
    }

    public boolean isDestinatario(int idProposta, String emailUtente) {
        Proposta proposta = propostaRepository.findById(idProposta).orElse(null);
        if (proposta == null) {
            return false; // proposta non trovata
        }

        // Verifica se l’utente loggato è il destinatario della proposta
        return proposta.getDestinatario() != null &&
                proposta.getDestinatario().getEmail().equals(emailUtente);
    }
}
