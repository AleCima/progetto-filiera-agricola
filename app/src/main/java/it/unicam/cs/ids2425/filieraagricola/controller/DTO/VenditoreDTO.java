package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

import it.unicam.cs.ids2425.filieraagricola.model.PuntoMappa;
import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;

import java.util.List;
import java.util.Set;

public class VenditoreDTO {

    private String PIVA;
    private Set<Ruolo> ruoli;
    private String email;
    private String password;
    private String ragioneFiscale;
    private String descrizione;
    private PuntoMappa posizione;

    public VenditoreDTO(String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione, Set<Ruolo> ruoli) {
        this.email = email;
        this.password = password;
        this.PIVA = PIVA;
        this.ragioneFiscale = ragioneFiscale;
        this.ruoli = ruoli;
        this.descrizione = descrizione;
        this.posizione = posizione;
    }

    public String getPIVA() {
        return PIVA;
    }

    public void setPIVA(String PIVA) {
        this.PIVA = PIVA;
    }

    public Set<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }
}
