package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EsperienzaPosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.repository.EsperienzaRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.EventoRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VenditoreRepository;
import it.unicam.cs.ids2425.filieraagricola.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OSMService {
    private VenditoreRepository venditoreRepository;
    private EsperienzaRepository esperienzaRepository;
    private VisitaRepository visitaRepository;
    private EventoRepository eventoRepository;


    public OSMService(VenditoreRepository venditoreRepository, EsperienzaRepository esperienzaRepository, VisitaRepository visitaRepository, EventoRepository eventoRepository) {
        this.venditoreRepository = venditoreRepository;
        this.esperienzaRepository = esperienzaRepository;
        this.visitaRepository = visitaRepository;
        this.eventoRepository = eventoRepository;
    }

    @Autowired


    public List<VenditorePosizioneDTO> visualizzaPosizioneVenditori() {
        return venditoreRepository.findAllPosizioni(); // restituisce tutti i venditori con la loro posizione
    }

    public List<Object> visualizzaPosizioneEsperienze() {
        List<Object> result = new ArrayList<>();
        result.addAll(visitaRepository.findAllPosizioni());  // ritorna List<VisitaDTO>
        result.addAll(eventoRepository.findAllPosizioni());  // ritorna List<EventoDTO>
        return result;
    }


    public PuntoMappa visualizzaPosizione(int id) {
        return esperienzaRepository.findById(id).orElse(null).getPosizione();
    }

    public List<Esperienza> visualizzaPosizione(Esperienza e) {
        //TODO
        return null;
    }

}
