package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.controller.DTO.ProdottoDTO;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.model.builder.ProdottoBuilder;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    //addTrasformazione
    public void addContenuto() {
     }

    //addPacchetto
    public void addContenuto(int id, Date dataCaricamento, String descrizione, String nome, double prezzo, Venditore distributore) {
    }

    public void updateContenuto(int id) {
        contenutoService.updateContenuto(id);
    }

    public void removeContenuto(int id) {
        contenutoService.removeContenuto(id);
    }

    public void addTrasformazioneTo(int idProdotto, int idTrasformazione) {
    }

    public void removeTrasformazioneFrom(int idProdotto, int idTrasformazione) {
        contenutoService.removeTrasformazioneFrom(idProdotto, idTrasformazione);
    }

}
