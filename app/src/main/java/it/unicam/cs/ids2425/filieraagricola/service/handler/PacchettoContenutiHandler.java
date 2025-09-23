package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.Pacchetto;
import jakarta.servlet.http.HttpServletRequest;

public class PacchettoContenutiHandler extends Handler {

    private final int minContenuti;

    // Consente impostare il numero minimo di contenuti
    public PacchettoContenutiHandler(int minContenuti) {
        this.minContenuti = minContenuti;
    }

    @Override
    public boolean check(HttpServletRequest request) {
        if (!(request instanceof Pacchetto pacchetto)) {
            // Se non è un pacchetto, passa al prossimo handler
            return checkNext(request);
        }

        if (pacchetto.getListaProdotti() == null || pacchetto.getListaProdotti().size() < minContenuti) {
            throw new CampoNonValidoException(
                    "Il pacchetto deve contenere almeno " + minContenuti + " prodotto/i"
            );
        }

        //Passa al prossimo handler
        return checkNext(request);
    }
}
