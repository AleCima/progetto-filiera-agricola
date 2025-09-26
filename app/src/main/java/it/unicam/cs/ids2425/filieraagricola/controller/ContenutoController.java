package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.PacchettoDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.ProdottoDTO;
import it.unicam.cs.ids2425.filieraagricola.controller.DTO.TrasformazioneDTO;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import it.unicam.cs.ids2425.filieraagricola.model.builder.ContenutoBuilder;
import it.unicam.cs.ids2425.filieraagricola.model.builder.PacchettoBuilder;
import it.unicam.cs.ids2425.filieraagricola.model.builder.ProdottoBuilder;
import it.unicam.cs.ids2425.filieraagricola.model.builder.TrasformazioneBuilder;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import it.unicam.cs.ids2425.filieraagricola.service.handler.Handler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.NonNullOrEmptyHandler;
import it.unicam.cs.ids2425.filieraagricola.service.handler.PacchettoContenutiHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contenuto")
public class ContenutoController {
    private final ContenutoService contenutoService;
    private final AccountService accountService;
    private final Handler contenutoDataHandler;

    public ContenutoController(ContenutoService contenutoService, AccountService accountService) {
        this.contenutoService = contenutoService;
        this.accountService = accountService;
        contenutoDataHandler = new NonNullOrEmptyHandler();
    }

    // Aggiungi Prodotto
    @PreAuthorize("#pDTO.getEmailProduttore().equals(authentication.name) or hasRole('GESTORE')")
    @PostMapping("/aggiungi-prodotto")
    public ResponseEntity<Object> addContenuto(@RequestBody ProdottoDTO pDTO) {
        Venditore venditore = accountService.getVenditoreByEmail(pDTO.getEmailProduttore());
        if (venditore == null) return new ResponseEntity<>("Produttore non trovato", HttpStatus.NOT_FOUND);

        ProdottoBuilder prodottoBuilder = new ProdottoBuilder();
        Contenuto nuovoContenuto = prodottoBuilder
                .setCertificazioni(pDTO.getCertificazioni())
                .setDataCaricamento(pDTO.getDataCaricamento())
                .setDataProduzione(pDTO.getDataProduzione())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setPrezzo(pDTO.getPrezzo())
                .setVenditore(venditore)
                .setQuantita(pDTO.getQuantita())
                .setListaTrasformazioni(pDTO.getListaTrasformazioni())
                .setMetodoDiColtivazione(pDTO.getMetodoDiColtivazione())
                .build();

        try {
            contenutoDataHandler.check(nuovoContenuto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        contenutoService.addContenuto(nuovoContenuto);
        return new ResponseEntity<>("Prodotto aggiunto con successo", HttpStatus.OK);
    }

    // Aggiungi Trasformazione
    @PreAuthorize("#tDTO.getEmailTrasformatore().equals(authentication.name) or hasRole('GESTORE')")
    @PostMapping("/aggiungi-trasformazione")
    public ResponseEntity<Object> addContenuto(@RequestBody TrasformazioneDTO tDTO) {
        Venditore venditore = accountService.getVenditoreByEmail(tDTO.getEmailTrasformatore());
        if (venditore == null) return new ResponseEntity<>("Trasformatore non trovato", HttpStatus.NOT_FOUND);

        ContenutoBuilder trasformazioneBuilder = new TrasformazioneBuilder();
        Contenuto nuovoContenuto = trasformazioneBuilder
                .setDataCaricamento(tDTO.getDataCaricamento())
                .setDescrizione(tDTO.getDescrizione())
                .setPrezzo(tDTO.getPrezzo())
                .setVenditore(venditore)
                .setQuantita(tDTO.getQuant())
                .build();

        try {
            contenutoDataHandler.check(nuovoContenuto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        contenutoService.addContenuto(nuovoContenuto);
        return new ResponseEntity<>("Trasformazione aggiunta con successo", HttpStatus.OK);
    }

    // Aggiungi Pacchetto
    @PreAuthorize("#pDTO.getEmailDistributore().equals(authentication.name) or hasRole('GESTORE')")
    @PostMapping("/aggiungi-pacchetto")
    public ResponseEntity<Object> addContenuto(@RequestBody PacchettoDTO pDTO) {
        Venditore venditore = accountService.getVenditoreByEmail(pDTO.getEmailDistributore());
        if (venditore == null) return new ResponseEntity<>("Distributore non trovato", HttpStatus.NOT_FOUND);

        List<Contenuto> contenuti = new ArrayList<>();
        for (int id : pDTO.getListaProdotti()) {
            Contenuto c = contenutoService.getContenutoById(id);
            if (c == null) return new ResponseEntity<>("Prodotto con ID " + id + " non trovato", HttpStatus.NOT_FOUND);
            contenuti.add(c);
        }

        PacchettoBuilder pacchettoBuilder = new PacchettoBuilder();
        Contenuto nuovoContenuto = pacchettoBuilder
                .setDataCaricamento(pDTO.getDataCaricamento())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setQuantita(pDTO.getQuantita())
                .setVenditore(venditore)
                .setListaContenuti(contenuti)
                .setPrezzo(pDTO.getPrezzo())
                .build();

        try {
            contenutoDataHandler.setNext(new PacchettoContenutiHandler());
            contenutoDataHandler.check(nuovoContenuto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        contenutoService.addContenuto(nuovoContenuto);
        return new ResponseEntity<>("Pacchetto aggiunto con successo", HttpStatus.OK);
    }

    // Modifica Prodotto
    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#id, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/modifica-prodotto")
    public ResponseEntity<Object> updateContenuto(@RequestParam int id, @RequestBody ProdottoDTO pDTO) {
        Venditore venditore = accountService.getVenditoreByEmail(pDTO.getEmailProduttore());
        if (venditore == null) return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);

        ProdottoBuilder prodottoBuilder = new ProdottoBuilder();
        Contenuto nuovoContenuto = prodottoBuilder
                .setCertificazioni(pDTO.getCertificazioni())
                .setDataCaricamento(pDTO.getDataCaricamento())
                .setDataProduzione(pDTO.getDataProduzione())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setPrezzo(pDTO.getPrezzo())
                .setVenditore(venditore)
                .setQuantita(pDTO.getQuantita())
                .setListaTrasformazioni(pDTO.getListaTrasformazioni())
                .setMetodoDiColtivazione(pDTO.getMetodoDiColtivazione())
                .build();

        nuovoContenuto.setId(id);

        try {
            contenutoDataHandler.check(nuovoContenuto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        contenutoService.updateContenuto(nuovoContenuto);
        return new ResponseEntity<>("Prodotto modificato con successo", HttpStatus.OK);
    }

    // Modifica Trasformazione
    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#id, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/modifica-trasformazione")
    public ResponseEntity<Object> updateContenuto(@RequestParam int id, @RequestBody TrasformazioneDTO tDTO) {
        Venditore venditore = accountService.getVenditoreByEmail(tDTO.getEmailTrasformatore());
        if (venditore == null) return new ResponseEntity<>("Trasformatore non trovato", HttpStatus.NOT_FOUND);

        ContenutoBuilder trasformazioneBuilder = new TrasformazioneBuilder();
        Contenuto nuovoContenuto = trasformazioneBuilder
                .setDataCaricamento(tDTO.getDataCaricamento())
                .setDescrizione(tDTO.getDescrizione())
                .setPrezzo(tDTO.getPrezzo())
                .setVenditore(venditore)
                .setQuantita(tDTO.getQuant())
                .build();

        nuovoContenuto.setId(id);

        try {
            contenutoDataHandler.check(nuovoContenuto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        contenutoService.updateContenuto(nuovoContenuto);
        return new ResponseEntity<>("Trasformazione modificata con successo", HttpStatus.OK);
    }

    // Rimuovi contenuto
    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#id, authentication.name) or hasRole('GESTORE')")
    @DeleteMapping("rimuovi-contenuto")
    public ResponseEntity<Object> removeContenuto(@RequestParam int id) {
        Contenuto c = contenutoService.getContenutoById(id);
        if (c == null) return new ResponseEntity<>("Contenuto non trovato", HttpStatus.NOT_FOUND);

        contenutoService.removeContenuto(c);
        return new ResponseEntity<>("Contenuto eliminato con successo", HttpStatus.OK);
    }

    // Altri metodi add/remove trasformazioni e pacchetti
    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#idTrasformazione, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/aggiungi-trasformazione")
    public ResponseEntity<Object> addTrasformazioneTo(@RequestParam int idProdotto, @RequestParam int idTrasformazione) {
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Trasformazione trasformazione = (Trasformazione) contenutoService.getContenutoById(idTrasformazione);

        if (prodotto == null) return new ResponseEntity<>("Prodotto non trovato", HttpStatus.NOT_FOUND);
        if (trasformazione == null) return new ResponseEntity<>("Trasformazione non trovata", HttpStatus.NOT_FOUND);

        contenutoService.addTrasformazioneTo(prodotto, trasformazione);
        return new ResponseEntity<>("Trasformazione aggiunta con successo", HttpStatus.OK);
    }

    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#id, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/rimuovi-trasformazione")
    public ResponseEntity<Object> removeTrasformazioneFrom(@RequestParam int idProdotto, @RequestParam int idTrasformazione) {
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Trasformazione trasformazione = (Trasformazione) contenutoService.getContenutoById(idTrasformazione);

        if (prodotto == null) return new ResponseEntity<>("Prodotto non trovato", HttpStatus.NOT_FOUND);
        if (trasformazione == null) return new ResponseEntity<>("Trasformazione non trovata", HttpStatus.NOT_FOUND);

        if (!prodotto.getListaTrasformazioni().contains(trasformazione))
            return new ResponseEntity<>("Trasformazione non presente nel prodotto", HttpStatus.NOT_FOUND);

        contenutoService.removeTrasformazioneFrom(prodotto, trasformazione);
        return new ResponseEntity<>("Trasformazione rimossa con successo", HttpStatus.OK);
    }

    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#idPacchetto, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/aggiungi-al-pacchetto")
    public ResponseEntity<Object> addToPacchetto(@RequestParam int idProdotto, @RequestParam int idPacchetto) {
        Contenuto prodotto = contenutoService.getContenutoById(idProdotto);
        Pacchetto pacchetto = (Pacchetto) contenutoService.getContenutoById(idPacchetto);

        if (prodotto == null) return new ResponseEntity<>("Prodotto non trovato", HttpStatus.NOT_FOUND);
        if (pacchetto == null) return new ResponseEntity<>("Pacchetto non trovato", HttpStatus.NOT_FOUND);

        contenutoService.addContenutoToPacchetto(prodotto, pacchetto);
        return new ResponseEntity<>("Contenuto aggiunto al pacchetto", HttpStatus.OK);
    }

    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#idPacchetto, authentication.name) or hasRole('GESTORE')")
    @PutMapping("/rimuovi-da-pacchetto")
    public ResponseEntity<Object> removeFromPacchetto(@RequestParam int idProdotto, @RequestParam int idPacchetto) {
        Contenuto prodotto = contenutoService.getContenutoById(idProdotto);
        Pacchetto pacchetto = (Pacchetto) contenutoService.getContenutoById(idPacchetto);

        if (prodotto == null) return new ResponseEntity<>("Prodotto non trovato", HttpStatus.NOT_FOUND);
        if (pacchetto == null) return new ResponseEntity<>("Pacchetto non trovato", HttpStatus.NOT_FOUND);

        if (!pacchetto.getListaProdotti().contains(prodotto))
            return new ResponseEntity<>("Prodotto non presente nel pacchetto", HttpStatus.NOT_FOUND);

        contenutoService.removeProdottoFromPacchetto(prodotto, pacchetto);
        return new ResponseEntity<>("Contenuto rimosso dal pacchetto", HttpStatus.OK);
    }

    // GET Contenuti filtrati per stato APPROVATO
    @GetMapping("ottieni-contenuti")
    public ResponseEntity<Object> getContenuti() {
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO);
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-contenuti-venditore")
    public ResponseEntity<Object> getContenutiFromVenditore(@RequestParam String emailVenditore) {
        Venditore venditore = accountService.getVenditoreByEmail(emailVenditore);
        if (venditore == null) return new ResponseEntity<>("Venditore non trovato", HttpStatus.NOT_FOUND);

        List<Contenuto> contenuti = contenutoService.getContenutoByVenditore(emailVenditore);
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO);
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-prodotti")
    public ResponseEntity<Object> getProdotti() {
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO || !(contenuto instanceof Prodotto));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-trasformazioni")
    public ResponseEntity<Object> getTrasformazioni() {
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO || !(contenuto instanceof Trasformazione));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-pacchetti")
    public ResponseEntity<Object> getPacchetti() {
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO || !(contenuto instanceof Pacchetto));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-contenuto")
    public ResponseEntity<Object> getContenuto(@RequestParam int id){
        if (contenutoService.getContenutoById(id) == null){
            return new ResponseEntity<>("Contenuto non esistente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contenutoService.getContenutoById(id), HttpStatus.OK);
    }

    @PreAuthorize("@contenutoService.contenutoVenditoreCheck(#id, authentication.name) or hasRole('GESTORE')")
    @GetMapping("condividi")
    public ResponseEntity<Object> condividiSuSocial(@RequestParam int id, @RequestParam String social){
        if (contenutoService.getContenutoById(id) == null){
            return new ResponseEntity<>("Contenuto non esistente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contenutoService.ottieniLinkSocial(social, id), HttpStatus.OK);
    }
}
