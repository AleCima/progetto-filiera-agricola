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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/contenuto")
public class ContenutoController {
    ContenutoService contenutoService;
    AccountService accountService; //Probabilmente servira' per controllare chi pubblica cosa(?)

    public ContenutoController(ContenutoService contenutoService, AccountService accountService) {
        this.contenutoService = contenutoService;
        this.accountService = accountService;
    }

    //addProdotto
    @PostMapping("/aggiungi-prodotto")
    public ResponseEntity<Object> addContenuto(@RequestBody ProdottoDTO pDTO) {
        //TODO controlla autorizzazioni solo produttore puo aggiungere
        ProdottoBuilder prodottoBuilder = new ProdottoBuilder();
        Contenuto nuovoContenuto = prodottoBuilder.setCertificazioni(pDTO.getCertificazioni())
                .setDataCaricamento(pDTO.getDataCaricamento())
                .setDataProduzione(pDTO.getDataProduzione())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setPrezzo(pDTO.getPrezzo())
                .setVenditore(accountService.getVenditoreByEmail(pDTO.getEmailProduttore()))
                .setQuantita(pDTO.getQuantita())
                .setListaTrasformazioni(pDTO.getListaTrasformazioni())
                .setMetodoDiColtivazione(pDTO.getMetodoDiColtivazione())
                .build();
        contenutoService.addContenuto(nuovoContenuto);
        return new ResponseEntity<>("Prodotto aggiunto con successo", HttpStatus.OK);
    }

    @PostMapping("/aggiungi-trasformazione")
    public ResponseEntity<Object> addContenuto(@RequestBody TrasformazioneDTO tDTO) {
        ContenutoBuilder trasformazioneBuilder = new TrasformazioneBuilder();
        System.out.println(tDTO.getEmailTrasformatore());
        Contenuto nuovoContenuto = trasformazioneBuilder.setDataCaricamento(tDTO.getDataCaricamento())
                .setDescrizione(tDTO.getDescrizione())
                .setPrezzo(tDTO.getPrezzo())
                .setVenditore(accountService.getVenditoreByEmail(tDTO.getEmailTrasformatore()))
                .setQuantita(tDTO.getQuant()).build();
        contenutoService.addContenuto(nuovoContenuto);

        return new ResponseEntity<>("Contenuto aggiunto al sistema", HttpStatus.OK);
    }

    @PostMapping("/aggiungi-pacchetto")
    public ResponseEntity<Object> addContenuto(@RequestBody PacchettoDTO pDTO) {
        List<Contenuto> contenuti = new ArrayList<>();
        for (int id : pDTO.getListaProdotti()){
            contenuti.add(contenutoService.getContenutoById(id));
            System.out.println(contenuti.getLast());
        }
        PacchettoBuilder pacchettoBuilder = new PacchettoBuilder();
        Contenuto nuovoContenuto = pacchettoBuilder.setDataCaricamento(pDTO.getDataCaricamento())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setQuantita(pDTO.getQuantita())
                .setVenditore(accountService.getVenditoreByEmail(pDTO.getEmailDistributore()))
                .setListaContenuti(contenuti)
                .setPrezzo(pDTO.getPrezzo()).build();
        contenutoService.addContenuto(nuovoContenuto);

        return new ResponseEntity<>("Contenuto aggiunto al sistema", HttpStatus.OK);
    }

    @PutMapping("/modifica-prodotto")
    public ResponseEntity<Object> updateContenuto(@RequestParam int id, @RequestBody ProdottoDTO pDTO) {
        ProdottoBuilder prodottoBuilder = new ProdottoBuilder();
        Contenuto nuovoContenuto = prodottoBuilder.setCertificazioni(pDTO.getCertificazioni())
                .setDataCaricamento(pDTO.getDataCaricamento())
                .setDataProduzione(pDTO.getDataProduzione())
                .setDescrizione(pDTO.getDescrizione())
                .setNome(pDTO.getNome())
                .setPrezzo(pDTO.getPrezzo())
                .setVenditore(accountService.getVenditoreByEmail(pDTO.getEmailProduttore()))
                .setQuantita(pDTO.getQuantita())
                .setListaTrasformazioni(pDTO.getListaTrasformazioni())
                .setMetodoDiColtivazione(pDTO.getMetodoDiColtivazione())
                .build();
        nuovoContenuto.setId(id);
        contenutoService.updateContenuto(nuovoContenuto);
        return new ResponseEntity<>("Contenuto modificato", HttpStatus.OK);
    }

    @PutMapping("/modifica-trasformazione")
    public ResponseEntity<Object> updateContenuto(@RequestParam int id, @RequestBody TrasformazioneDTO tDTO){
        ContenutoBuilder trasformazioneBuilder = new TrasformazioneBuilder();
        System.out.println(tDTO.getEmailTrasformatore());
        Contenuto nuovoContenuto = trasformazioneBuilder.setDataCaricamento(tDTO.getDataCaricamento())
                .setDescrizione(tDTO.getDescrizione())
                .setPrezzo(tDTO.getPrezzo())
                .setVenditore(accountService.getVenditoreByEmail(tDTO.getEmailTrasformatore()))
                .setQuantita(tDTO.getQuant()).build();
        nuovoContenuto.setId(id);
        contenutoService.updateContenuto(nuovoContenuto);
        return new ResponseEntity<>("Contenuto modificato", HttpStatus.OK);
    }

    @DeleteMapping("rimuovi-contenuto")
    public ResponseEntity<Object> removeContenuto(@RequestParam int id) {
        contenutoService.removeContenuto(contenutoService.getContenutoById(id));
        return new ResponseEntity<>("Contenuto eliminato con successo", HttpStatus.OK);
    }

    @PutMapping("/aggiungi-trasformazione")
    public ResponseEntity<Object> addTrasformazioneTo(@RequestParam int idProdotto, @RequestParam int idTrasformazione) {
        //TODO controlli
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Trasformazione trasformazione = (Trasformazione) contenutoService.getContenutoById(idTrasformazione);
        contenutoService.addTrasformazioneTo(prodotto, trasformazione);
        return new ResponseEntity<>("Trasformazione aggiunta con successo", HttpStatus.OK);
    }

    @PutMapping("/rimuovi-trasformazione")
    public ResponseEntity<Object> removeTrasformazioneFrom(@RequestParam int idProdotto, @RequestParam int idTrasformazione) {
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Trasformazione trasformazione = (Trasformazione) contenutoService.getContenutoById(idTrasformazione);
        contenutoService.removeTrasformazioneFrom(prodotto, trasformazione);
        return new ResponseEntity<>("Trasformazione rimossa con successo", HttpStatus.OK);
    }

    @PutMapping("/aggiungi-al-pacchetto")
    public ResponseEntity<Object> addToPacchetto(@RequestParam int idProdotto, @RequestParam int idPacchetto) {
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Pacchetto pacchetto = (Pacchetto) contenutoService.getContenutoById(idPacchetto);
        contenutoService.addContenutoToPacchetto(prodotto, pacchetto);
        return new ResponseEntity<>("Contenuto aggiunto al pacchetto", HttpStatus.OK);
    }

    @PutMapping("/rimuovi-da-pacchetto")
    public ResponseEntity<Object> removeFromPacchetto(@RequestParam int idProdotto, @RequestParam int idPacchetto) {
        Prodotto prodotto = (Prodotto) contenutoService.getContenutoById(idProdotto);
        Pacchetto pacchetto = (Pacchetto) contenutoService.getContenutoById(idPacchetto);
        contenutoService.removeProdottoFromPacchetto(prodotto, pacchetto);
        return new ResponseEntity<>("Contenuto rimosso dal pacchetto", HttpStatus.OK);
    }

    @GetMapping("ottieni-contenuti")
    public ResponseEntity<Object> getContenuti(){
        //TODO Controlli
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO);
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-contenuti-venditore")
    public ResponseEntity<Object> getContenutiFromVenditore(@RequestParam String emailVenditore){
        //TODO Controlli e test
        List<Contenuto> contenuti = contenutoService.getContenutoByVenditore(emailVenditore);
        contenuti.removeIf(contenuto -> contenuto.getStatoConferma() != Conferma.APPROVATO);
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-prodotti")
    public ResponseEntity<Object> getProdotti(){
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> (contenuto.getStatoConferma() != Conferma.APPROVATO) && !(contenuto instanceof Prodotto));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-trasformazioni")
    public ResponseEntity<Object> getTrasformazioni(){
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> (contenuto.getStatoConferma() != Conferma.APPROVATO) && !(contenuto instanceof Trasformazione));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }

    @GetMapping("ottieni-pacchetti")
    public ResponseEntity<Object> getPacchetti(){
        List<Contenuto> contenuti = contenutoService.getContenuti();
        contenuti.removeIf(contenuto -> (contenuto.getStatoConferma() != Conferma.APPROVATO) && !(contenuto instanceof Pacchetto));
        return new ResponseEntity<>(contenuti, HttpStatus.OK);
    }
}
