package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Ordine {
    private final Date dataOrdine;
    private final Carrello carrello;
    private final Double totale;
    private boolean evaso;
    private Pagamento cartaDiCredito;
    private Indirizzo indirizzoDiFatturazione;

    public Ordine(Date dataOrdine, Carrello carrello, Pagamento cartaDiCredito, Indirizzo indirizzoDiFatturazione) {
        this.dataOrdine = dataOrdine;
        this.carrello = carrello;
        this.totale = carrello.getPrezzoTotale();
        this.evaso = false;
        this.cartaDiCredito = cartaDiCredito;
        this.indirizzoDiFatturazione = indirizzoDiFatturazione;
    }

    public Indirizzo getIndirizzoDiFatturazione() {
        return indirizzoDiFatturazione;
    }

    public void setIndirizzoDiFatturazione(Indirizzo indirizzoDiFatturazione) {
        this.indirizzoDiFatturazione = indirizzoDiFatturazione;
    }

    public Pagamento getCartaDiCredito() {
        return cartaDiCredito;
    }

    public void setCartaDiCredito(Pagamento cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
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
