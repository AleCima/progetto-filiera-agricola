package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.AutorizzazioneException;
import it.unicam.cs.ids2425.filieraagricola.model.*;

import java.util.List;

public class RuoloHandler extends Handler {

    //TODO
    //TODO
    //TODO
    //TODO

    private final Ruolo ruoloRichiesto;

    public RuoloHandler(Ruolo ruoloRichiesto) {
        this.ruoloRichiesto = ruoloRichiesto;
    }

    @Override
    public boolean check(Object request) {
        Utente utente = switch (request) {
            case Proposta proposta -> proposta.getOrganizzatore();
            case Autorizzazione autorizzazione -> autorizzazione.getCuratore();
            case Evento evento -> evento.getOrganizzatore();
            case Visita visita -> visita.getOrganizzatore();
            default -> null; // altri oggetti non gestiti
        };

        if (utente == null) {
            return checkNext(request); // passa al prossimo handler se oggetto non gestito
        }

        List<Ruolo> ruoli = utente.getRuoli();
        if (ruoli == null || !ruoli.contains(ruoloRichiesto)) {
            throw new AutorizzazioneException(
                    "Utente " + utente.getEmail() + " non ha il ruolo necessario: " + ruoloRichiesto
            );
        }

        return checkNext(request); // tutto ok, passa al prossimo handler
    }

    //TODO
    //TODO
    //TODO
    //TODO

}
