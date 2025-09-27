package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Venditore implements Account {
    @Id
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Ruolo.class)
    @CollectionTable(name = "ruoliVenditore", joinColumns = @JoinColumn(name = "email"))
    @Column(name = "ruolo")
    @Enumerated(EnumType.STRING)
    private Set<Ruolo> ruoli;

    private String PIVA;
    private String ragioneFiscale;
    private String descrizione;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posizione_id", referencedColumnName = "id")
    private PuntoMappa posizione;

    public Venditore() {
    }

    public Venditore(String email, String password, String PIVA, String ragioneFiscale, String descrizione, PuntoMappa posizione, Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
        this.email = email;
        this.password = password;
        this.PIVA = PIVA;
        this.ragioneFiscale = ragioneFiscale;
        this.descrizione = descrizione;
        this.posizione = posizione;
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

    public PuntoMappa getPosizione() {
        return posizione;
    }

    public void setPosizione(PuntoMappa posizione) {
        this.posizione = posizione;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Set<Ruolo> getRuoli() {
        return ruoli;
    }

    @Override
    public void addRuolo(Ruolo ruolo) {
        ruoli.add(ruolo);
    }

    @Override
    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void removeRuolo(Ruolo ruolo) {
        ruoli.remove(ruolo);
    }
}
