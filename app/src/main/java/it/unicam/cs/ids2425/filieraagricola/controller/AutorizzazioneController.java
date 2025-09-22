package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autorizzazione")
public class AutorizzazioneController {
    private AutorizzazioneService autorizzazioneService;
    private ContenutoService contenutoService;
    public AutorizzazioneController(AutorizzazioneService autorizzazioneService, ContenutoService contenutoService) {
        this.autorizzazioneService = autorizzazioneService;
        this.contenutoService = contenutoService;
    }

    @GetMapping("/contenuti-in-attesa")
    public ResponseEntity<Object> getContenutiInAttesa() {

            List<Contenuto> contenutiInAttesa = autorizzazioneService.getContenutiInAttesa();
            if (contenutiInAttesa.isEmpty()) {
                return new ResponseEntity<>("Nessun contenuto in attesa", HttpStatus.NO_CONTENT); // Se non ci sono contenuti
            }
            return new ResponseEntity<>(contenutiInAttesa, HttpStatus.OK); // Restituisce i contenuti in attesa

            // Gestione dell'errore
           // return new ResponseEntity<>("Errore durante il recupero dei contenuti in attesa", HttpStatus.INTERNAL_SERVER_ERROR); // In caso di errore

    }


    @PutMapping("/autorizza-contenuto")
    public ResponseEntity<String> autorizzaContenuto(@RequestParam int id ) {
        autorizzazioneService.Autorizza(contenutoService.getContenutoById(id)); // Chiamata al servizio per autorizzare il contenuto
        return new ResponseEntity<>("Contenuto autorizzato con successo", HttpStatus.OK); // Restituisce una risposta positiva
    }

    @PutMapping("/rifiuta-contenuto")
    public ResponseEntity<String> rifiutaContenuto(@RequestParam int id ) {

        autorizzazioneService.Rifiuta(contenutoService.getContenutoById(id)); // Chiamata al servizio per autorizzare il contenuto
        return new ResponseEntity<>("Contenuto rifiutato con successo", HttpStatus.OK); // Restituisce una risposta positiva

    }
}
