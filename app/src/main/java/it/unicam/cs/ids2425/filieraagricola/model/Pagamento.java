package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.Date;

public class Pagamento {
    int numCarta;
    short CVV;
    Date scadenza;

    public Pagamento(int numCarta, short CVV, Date scadenza) {
        this.numCarta = numCarta;
        this.CVV = CVV;
        this.scadenza = scadenza;
    }

    public int getNumCarta() {
        return numCarta;
    }

    public short getCVV() {
        return CVV;
    }

    public Date getScadenza() {
        return scadenza;
    }
}
