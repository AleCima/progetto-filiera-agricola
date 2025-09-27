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

/**
 * Classe contenente tutte le operazioni relative al sistema OSM
 */
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

    /**
     * Metodo per ottenere le posizioni di tutti i venditori
     * @return Lista di posizioni del venditori
     */
    @GetMapping("/venditori")
    public ResponseEntity<Object> getPosizioneVenditori() {
        List<VenditorePosizioneDTO> venditori = osmService.visualizzaPosizioneVenditori();
        if (venditori.isEmpty()) {
            return new ResponseEntity<>("Nessun venditore trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(venditori, HttpStatus.OK);
    }

    /**
     * Metodo per ottenere la posizione di un venditore
     * @param email Email del venditore di cui si vuole conoscere la posizione
     * @return Posizione del venditore
     */
    @GetMapping("/venditore-posizione")
    public ResponseEntity<Object> getPosizioneVenditore(@RequestParam String email) {
        PuntoMappa posizione = osmService.visualizzaPosizioneVenditore(email);
        if (posizione == null) {
            return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posizione, HttpStatus.OK);
    }

    // ---- Esperienze ----

    /**
     * Motodo per ottenere la posizione di tutte le esperienza caricate in sistema
     * @return Lista di posizioni
     */
    @GetMapping("/esperienze")
    public ResponseEntity<Object> getPosizioneEsperienze() {
        List<EsperienzaPosizioneDTO> esperienze = osmService.visualizzaPosizioneEsperienze();
        if (esperienze.isEmpty()) {
            return new ResponseEntity<>("Nessuna esperienza trovata", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(esperienze, HttpStatus.OK);
    }

    /**
     * Metodo per ottenere la posizione di un esperienza
     * @param id Id dell'esperienza di cui si vuole conoscere la posizione
     * @return Posizione dell'esperienza o errore
     */
    @GetMapping("/esperienza-posizione")
    public ResponseEntity<Object> getPosizioneEsperienza(@RequestParam int id) {
        PuntoMappa posizione = osmService.visualizzaPosizione(id);
        if (posizione == null) {
            return new ResponseEntity<>("Esperienza non trovata", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posizione, HttpStatus.OK);
    }
}
