package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.QuantitaOutOfBoundException;
import it.unicam.cs.ids2425.filieraagricola.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricola.model.Ordine;
import it.unicam.cs.ids2425.filieraagricola.model.RigaCarrello;

import java.util.List;

public class DisponibilitaProdottiHandler extends Handler{
    @Override
    public boolean check(Object request) {
        if (!(request instanceof Ordine ordine)) {
            // Se non è un Pagamento, passa al prossimo handler
            return checkNext(request);
        }
        List<RigaCarrello> carrello = ordine.getCarrello().getContenuti();
        for (RigaCarrello rigaCarrello : carrello){
            if (rigaCarrello.getQuantita() > rigaCarrello.getContenuto().getQuantita()){
                throw new QuantitaOutOfBoundException("Il prodotto "+ rigaCarrello.getContenuto().getDescrizione() + " non dispone della quantita riciesta");
            }
        }
        return checkNext(request);
    }
}
