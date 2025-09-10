package it.unicam.cs.ids2425.filieraagricola.controller;

import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import it.unicam.cs.ids2425.filieraagricola.service.OSMService;

import java.util.List;

public class OSMController {
    private OSMService servizioOsm;
    private List<Venditore> venditori;
    private List<Esperienza> esperienza;

    public void getMappa(){
        //TODO
    }
    public List<Venditore> getPosizioneVenditori(){
        return servizioOsm.visualizzaPosizioneVenditori();
    }

    public List<Esperienza> getPosizioneEsperienze(){
        return servizioOsm.visualizzaPosizioneEsperienze();
    }

    public List<Venditore> getPosizoneVenditore(Venditore v){
        return servizioOsm.visualizzaPosizione(v);
    }

    public List<Esperienza> getPosizioneEsperienza(Esperienza e){
        return servizioOsm.visualizzaPosizione(e);
    }
}
