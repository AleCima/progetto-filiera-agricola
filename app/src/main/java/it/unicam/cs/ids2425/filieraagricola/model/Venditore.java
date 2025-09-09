package it.unicam.cs.ids2425.filieraagricola.model;

import java.util.List;

public class Venditore extends Account{
    private String PIVA;
    private String ragioneFiscale;
    private String descrizione;

    public Venditore(String email, String password, String PIVA, String ragioneFiscale, String descrizione) {
        super(email, password);
        this.PIVA = PIVA;
        this.ragioneFiscale = ragioneFiscale;
        this.descrizione = descrizione;
    }

    public String getPIVA() {
        return PIVA;
    }

    public void setPIVA(String PIVA) {
        this.PIVA = PIVA;
    }

    public String getRagioneFiscale() {
        return ragioneFiscale;
    }

    public void setRagioneFiscale(String ragioneFiscale) {
        this.ragioneFiscale = ragioneFiscale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
