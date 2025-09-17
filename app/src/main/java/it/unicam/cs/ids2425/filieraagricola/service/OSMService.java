package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.EsperienzaRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OSMService {
    private VenditoreRepository venditoreRepository;
    private EsperienzaRepository esperienzaRepository;

    @Autowired
    public OSMService(VenditoreRepository venditoreRepository, EsperienzaRepository esperienzaRepository) {
        this.venditoreRepository = venditoreRepository;
        this.esperienzaRepository = esperienzaRepository;
    }

    public List<Venditore> visualizzaPosizioneVenditori() {
        //TODO
        return null;
    }

    public List<Esperienza> visualizzaPosizioneEsperienze() {
        //TODO
        return null;
    }

    public List<Venditore> visualizzaPosizione(Venditore v) {
        //TODO
        return null;
    }

    public List<Esperienza> visualizzaPosizione(Esperienza e) {
        //TODO
        return null;
    }

}
