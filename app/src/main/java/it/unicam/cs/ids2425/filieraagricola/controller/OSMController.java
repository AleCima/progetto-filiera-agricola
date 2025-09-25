package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EsperienzaPosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.OSMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osm")
public class OSMController {

    private final OSMService osmService;
    private final AccountService accountService;

    public OSMController(OSMService osmService, AccountService accountService) {
        this.osmService = osmService;
        this.accountService = accountService;
    }

    // ---- Venditori ----
    @GetMapping("/venditori")
    public ResponseEntity<Object> getPosizioneVenditori() {
        List<VenditorePosizioneDTO> venditori = osmService.visualizzaPosizioneVenditori();
        if (venditori.isEmpty()) {
            return new ResponseEntity<>("Nessun venditore trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(venditori, HttpStatus.OK);
    }

    @GetMapping("/venditore-posizione")
    public ResponseEntity<Object> getPosizioneVenditore(@RequestParam String email) {
        PuntoMappa posizione = osmService.visualizzaPosizioneVenditore(email);
        if (posizione == null) {
            return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posizione, HttpStatus.OK);
    }

    // ---- Esperienze ----
    @GetMapping("/esperienze")
    public ResponseEntity<Object> getPosizioneEsperienze() {
        List<EsperienzaPosizioneDTO> esperienze = osmService.visualizzaPosizioneEsperienze();
        if (esperienze.isEmpty()) {
            return new ResponseEntity<>("Nessuna esperienza trovata", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(esperienze, HttpStatus.OK);
    }

    @GetMapping("/esperienza-posizione")
    public ResponseEntity<Object> getPosizioneEsperienza(@RequestParam int id) {
        PuntoMappa posizione = osmService.visualizzaPosizione(id);
        if (posizione == null) {
            return new ResponseEntity<>("Esperienza non trovata", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posizione, HttpStatus.OK);
    }
}
