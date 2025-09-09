package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Ordine {
    Date dataOrdine;
    Carrello carrello;
    Double totale;
    boolean evaso;
    Pagamento cartaDiCredito;
    Indirizzo indirizzoDiFatturazione;

    public Ordine(Date dataOrdine, Carrello carrello, Double totale, boolean evaso, Pagamento cartaDiCredito, Indirizzo indirizzoDiFatturazione) {
        this.dataOrdine = dataOrdine;
        this.carrello = carrello;
        this.totale = totale;
        this.evaso = evaso;
        this.cartaDiCredito = cartaDiCredito;
        this.indirizzoDiFatturazione = indirizzoDiFatturazione;
    }

    public Indirizzo getIndirizzoDiFatturazione() {
        return indirizzoDiFatturazione;
    }

    public Pagamento getCartaDiCredito() {
        return cartaDiCredito;
    }

    public boolean isEvaso() {
        return evaso;
    }

    public void setEvaso(boolean evaso) {
        this.evaso = evaso;
    }

    public Double getTotale() {
        return totale;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }
}
