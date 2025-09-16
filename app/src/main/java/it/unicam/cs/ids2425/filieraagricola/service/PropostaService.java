package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Proposta;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {
    private List<Proposta> proposte;

    public List<Proposta> getProposte() {
        return proposte;
    }

    public Proposta getProposta() {
        //TODO proposta singola
        return null;
    }

    public void aggiungiProposta(Proposta p) {
        proposte.add(p);
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
