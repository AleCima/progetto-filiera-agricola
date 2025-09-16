package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Esperienza;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsperienzaService {

    //Necessaria repository "Esperienza"

    public EsperienzaService() {
    }

    public List<Esperienza> getEsperienze() {
        //TODO
        return null;
    }

    public List<Esperienza> getEsperienzeByAnimatore(Utente utente) {
        //TODO
        return null;
    }

    public Esperienza getEsperienzaById(int id) {
        //TODO
        return null;
    }

    public void addPartecipante(Esperienza esperienza, Utente utente) {
        //TODO aggiungo partecipante all' esperienza passata come parametro
    }

    public void removePartecipante(Esperienza esperienza, Utente utente) {
        //TODO rimuovo partecipante all' esperienza passata come parametro
    }

    public void addEsperienza(Esperienza esperienza) {
        //TODO aggiungo alla repo esperienza
    }

    public void updateEsperienza(Esperienza esperienza) {
        //TODO modifico l'esperienza passata nella repo
    }

    public void removeEsperienza(Esperienza esperienza) {
        //TODO rimuovo l'esperienza passata nella repo
    }

}
