package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.Pagamento;

import java.util.Date;

public class PagamentoValidHandler extends Handler {

    @Override
    public boolean check(Object request) {
        if (!(request instanceof Pagamento pagamento)) {
            // Se non è un Pagamento, passa al prossimo handler
            return checkNext(request);
        }

        String numCarta = pagamento.getNumCarta();
        if (!numCarta.matches("\\d{13,19}")) {  //una sequenza di cifre lunga da 13 a 19 caratteri
            throw new CampoNonValidoException("Numero carta non valido: " + numCarta);
        }

        int cvv = pagamento.getCVV();
        if (cvv < 100 || cvv > 9999) { // CVV 3 o 4 cifre
            throw new CampoNonValidoException("CVV non valido: " + cvv);
        }

        Date scadenza = pagamento.getScadenza();
        if (scadenza == null || scadenza.before(new Date())) {
            throw new CampoNonValidoException("La data di scadenza della carta non è valida: " + scadenza);
        }

        return checkNext(request);
    }
}
