package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.EsperienzaPosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.VenditorePosizioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.OSMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/osm")
public class OSMController {
    private OSMService osmService;
    private AccountService accountService;

    public OSMController(OSMService osmService, AccountService accountService) {
        this.osmService = osmService;
        this.accountService = accountService;
    }

    public void getMappa(){
        //TODO
    }

    @GetMapping("/venditori")
    public ResponseEntity<List<VenditorePosizioneDTO>> getPosizioneVenditori() {
        List<VenditorePosizioneDTO> venditori = osmService.visualizzaPosizioneVenditori();
        return ResponseEntity.ok(venditori);
    }

    @GetMapping("/esperienze")
    public ResponseEntity<Object> getPosizioneEsperienze() {
        return ResponseEntity.ok(osmService.visualizzaPosizioneEsperienze());
    }

    @GetMapping("/esperienza-posizione")
    public ResponseEntity<Object> getPosizioneEsperienza(@RequestParam int id){
        PuntoMappa posizione = osmService.visualizzaPosizione(id);
        return ResponseEntity.ok(posizione);
    }
}
