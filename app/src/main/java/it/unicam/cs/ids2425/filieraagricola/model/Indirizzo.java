package it.unicam.cs.ids2425.filieraagricola.model;

public class Indirizzo {
    private String Via;
    private short nCivico;
    private String comune;
    private int CAP;

    public Indirizzo(String via, short nCivico, String comune, int CAP) {
        Via = via;
        this.nCivico = nCivico;
        this.comune = comune;
        this.CAP = CAP;
    }

    public String getVia() {
        return Via;
    }

    public short getnCivico() {
        return nCivico;
    }

    public String getComune() {
        return comune;
    }

    public int getCAP() {
        return CAP;
    }
}

