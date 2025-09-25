package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EsperienzaPosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OSMService {

    private final AccountService accountService;
    private final EsperienzaService esperienzaService;

    public OSMService(AccountService accountService, EsperienzaService esperienzaService) {
        this.accountService = accountService;
        this.esperienzaService = esperienzaService;
    }

    // ---- Venditori ----

    public List<VenditorePosizioneDTO> visualizzaPosizioneVenditori() {
        List<Venditore> venditori = accountService.getVenditori();
        return venditori.stream()
                // Trasforma ogni oggetto Venditore in un DTO contenente solo i campi necessari
                .map(v -> new VenditorePosizioneDTO(v.getEmail(), v.getRagioneFiscale(), v.getPosizione()))
                // Colleziona tutti i DTO trasformati in una lista
                .collect(Collectors.toList());
    }

    public PuntoMappa visualizzaPosizioneVenditore(String email) {
        return accountService.getVenditoreByEmail(email).getPosizione();
    }

    // ---- Esperienze ----

    public List<EsperienzaPosizioneDTO> visualizzaPosizioneEsperienze() {
        List<Esperienza> esperienze = esperienzaService.getEsperienze();
        return esperienze.stream()
                // Trasforma ogni oggetto Esperienza in un DTO con titolo, descrizione e posizione
                .map(e -> new EsperienzaPosizioneDTO(e.getTitolo(), e.getDescrizione(), e.getPosizione()))
                // Colleziona tutti i DTO in una lista
                .collect(Collectors.toList());
    }

    public PuntoMappa visualizzaPosizione(int idEsperienza) {
        return esperienzaService.getEsperienzaById(idEsperienza).getPosizione();
    }
}
