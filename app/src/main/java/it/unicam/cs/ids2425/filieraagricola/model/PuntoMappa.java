package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PuntoMappa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double latitudine;
    private double longitudine;

    public PuntoMappa(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }
}
