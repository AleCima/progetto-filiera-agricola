package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.service.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe contenente tutte le possibili operazione per la conferma del caricamento di un contenuto nel sistema
 * Acceduta solo da Curatori e Gestori
 */
@RestController
@RequestMapping("/autorizzazione")
public class AutorizzazioneController {
    private AutorizzazioneService autorizzazioneService;
    private ContenutoService contenutoService;

    public AutorizzazioneController(AutorizzazioneService autorizzazioneService, ContenutoService contenutoService) {
        this.autorizzazioneService = autorizzazioneService;
        this.contenutoService = contenutoService;
    }

    /**
     * Metodo che mostra tutti i contenuti in attessa
     * @return Una lista dei contenuti in attesa
     */
    @GetMapping("/contenuti-in-attesa")
    public ResponseEntity<Object> getContenutiInAttesa() {

        List<Contenuto> contenutiInAttesa = autorizzazioneService.getContenutiInAttesa();
        if (contenutiInAttesa.isEmpty()) {
            return new ResponseEntity<>("Nessun contenuto in attesa", HttpStatus.NO_CONTENT); // Se non ci sono contenuti
        }
        return new ResponseEntity<>(contenutiInAttesa, HttpStatus.OK); // Restituisce i contenuti in attesa
    }

    /**
     * Metodo per l'autorizzazione di un contenuto
     * @param id Id del contenuto da verificare
     * @return messaggio di successo o insuccesso
     */
    @PutMapping("/autorizza-contenuto")
    public ResponseEntity<String> autorizzaContenuto(@RequestParam int id ) {
        Contenuto contenuto = contenutoService.getContenutoById(id);
        if(contenuto == null){
            return new ResponseEntity<>("Contenuto con id richiesto non trovato", HttpStatus.NOT_FOUND);
        }
        autorizzazioneService.Autorizza(contenuto); // Chiamata al servizio per autorizzare il contenuto
        return new ResponseEntity<>("Contenuto autorizzato con successo", HttpStatus.OK); // Restituisce una risposta positiva
    }

    /**
     * Metodo per il rifiuto di un contenuto
     * @param id Id del contenuto da verificare
     * @return messaggio di successo o insuccesso
     */
    @PutMapping("/rifiuta-contenuto")
    public ResponseEntity<String> rifiutaContenuto(@RequestParam int id ) {
        Contenuto contenuto = contenutoService.getContenutoById(id);
        if(contenuto == null){
            return new ResponseEntity<>("Contenuto con id richiesto non trovato", HttpStatus.NOT_FOUND);
        }
        autorizzazioneService.Rifiuta(contenuto); // Chiamata al servizio per autorizzare il contenuto
        return new ResponseEntity<>("Contenuto rifiutato con successo", HttpStatus.OK); // Restituisce una risposta positiva
    }
}
