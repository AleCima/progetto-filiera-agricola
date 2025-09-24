package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.RigaCarrello;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.repository.ContenutoRepository;
import it.unicam.cs.ids2425.filieraagricola.service.ContenutoService;
import jakarta.servlet.http.HttpServletRequest;

public class DisponibilitaRigaCarrelloHandler extends Handler {

    public DisponibilitaRigaCarrelloHandler() {
    }

    @Override
    public boolean check(Object request) {
        if (!(request instanceof RigaCarrello riga)) {
            // Non è una riga carrello, passa al prossimo handler
            return checkNext(request);
        }

        Contenuto contenuto = riga.getContenuto();
        if (contenuto == null) {
            throw new CampoNonValidoException("Il contenuto della riga carrello non può essere nullo");
        }

        int quantitaRichiesta = riga.getQuantita();
        int disponibilita = contenuto.getQuantita();

        if (quantitaRichiesta > disponibilita) {
            throw new CampoNonValidoException(
                    "Quantità richiesta (" + quantitaRichiesta + ") maggiore della disponibilità (" + disponibilita + ") per il contenuto: " + contenuto.getDescrizione()
            );
        }

        // Tutto ok, passa al prossimo handler
        return checkNext(request);
    }
}
