package it.unicam.cs.ids2425.filieraagricola.controller.DTO;
import it.unicam.cs.ids2425.filieraagricola.model.Carrello;
import it.unicam.cs.ids2425.filieraagricola.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricola.model.Pagamento;

import java.util.Date;

public class OrdineDTO {

    private final Date dataOrdine;

    private Pagamento cartaDiCredito;
    private Indirizzo indirizzoDiFatturazione;

    public OrdineDTO(Date dataOrdine, Pagamento cartaDiCredito, Indirizzo indirizzoDiFatturazione) {
        this.dataOrdine = dataOrdine;
        this.cartaDiCredito = cartaDiCredito;
        this.indirizzoDiFatturazione = indirizzoDiFatturazione;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }




    public Pagamento getCartaDiCredito() {
        return cartaDiCredito;
    }


    public Indirizzo getIndirizzoDiFatturazione() {
        return indirizzoDiFatturazione;
    }


}
