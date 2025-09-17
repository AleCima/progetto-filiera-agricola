package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int numCarta;
    private short CVV;
    private Date scadenza;

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
