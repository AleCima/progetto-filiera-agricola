package it.unicam.cs.ids2425.filieraagricola.model;

public class PuntoMappa {
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
