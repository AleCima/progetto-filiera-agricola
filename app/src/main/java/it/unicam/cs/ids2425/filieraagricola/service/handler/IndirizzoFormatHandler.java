package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import jakarta.servlet.http.HttpServletRequest;

public class IndirizzoFormatHandler extends Handler {

    @Override
    public boolean check(Object request) {
        if (!(request instanceof Ordine ordine)) {
            // Se non è un Ordine, passa al prossimo handler
            return checkNext(request);
        }

        Indirizzo indirizzo = ordine.getIndirizzoDiFatturazione();

        // Controllo numero civico positivo
        if (indirizzo.getnCivico() <= 0) {
            throw new CampoNonValidoException("Numero civico non valido: " + indirizzo.getnCivico());
        }

        // Controllo CAP a 5 cifre (Italia)
        int cap = indirizzo.getCAP();
        if (cap < 10000 || cap > 99999) {
            throw new CampoNonValidoException("CAP non valido: " + cap);
        }

        //Via e comune non devono contenere numeri strani o simboli
        if (!indirizzo.getVia().matches("[a-zA-Z0-9\\s]+")) {
            throw new CampoNonValidoException("Via contiene caratteri non validi: " + indirizzo.getVia());
        }

        if (!indirizzo.getComune().matches("[a-zA-Z\\s]+")) {
            throw new CampoNonValidoException("Comune contiene caratteri non validi: " + indirizzo.getComune());
        }

        return checkNext(request);
    }
}
