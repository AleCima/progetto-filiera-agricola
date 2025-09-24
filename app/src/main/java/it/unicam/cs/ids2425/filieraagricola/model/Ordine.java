package it.unicam.cs.ids2425.filieraagricola.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date dataOrdine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrello_id", referencedColumnName = "id")
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private Double totale ;
    private boolean evaso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento cartaDiCredito;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo_id", referencedColumnName = "id")
    private Indirizzo indirizzoDiFatturazione;

    public Ordine( Date dataOrdine, Carrello carrello, Pagamento cartaDiCredito, Indirizzo indirizzoDiFatturazione, Utente utente) {

        this.dataOrdine = dataOrdine;
        this.carrello = carrello;
        this.totale = carrello.getPrezzoTotale();
        this.evaso = false;
        this.cartaDiCredito = cartaDiCredito;
        this.indirizzoDiFatturazione = indirizzoDiFatturazione;
        this.utente = utente;
    }

    public Ordine() {

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

    public int getId() {
        return id;
    }

    public void setDataOrdine(Date dataOrdine){
        this.dataOrdine = dataOrdine;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Utente getUtente() {
        return utente;
    }
}
