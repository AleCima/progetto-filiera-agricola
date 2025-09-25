package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class NonNullOrEmptyHandler extends Handler {

    @Override
    public boolean check(Object request) {
        if (request == null) {
            throw new CampoNonValidoException("Oggetto nullo non consentito");
        }

        switch (request) {
            case Venditore v -> {
                valida(v.getEmail());
                valida(v.getPassword());
                valida(v.getPIVA());
                valida(v.getRagioneFiscale());
                valida(v.getDescrizione());
                valida(v.getPosizione());
                valida(v.getRuoli());
            }
            case Utente u -> {
                valida(u.getEmail());
                valida(u.getPassword());
                valida(u.getNome());
                valida(u.getCognome());
            }
            case Prodotto p -> {
                valida(p.getId());
                valida(p.getCertificazioni());
                valida(p.getNome());
                valida(p.getDataCaricamento());
                valida(p.getDescrizione());
                valida(p.getListaTrasformazioni());
                valida(p.getPrezzo());
                valida(p.getDataProduzione());
                valida(p.getStatoConferma());
            }
            case Pacchetto p -> {
                valida(p.getId());
                valida(p.getDataCaricamento());
                valida(p.getDescrizione());
                valida(p.getPrezzo());
                valida(p.getVenditore());
                valida(p.getNome());
                valida(p.getListaProdotti());
            }
            case Trasformazione t -> {
                valida(t.getId());
                valida(t.getDataCaricamento());
                valida(t.getDescrizione());
                valida(t.getPrezzo());
                valida(t.getVenditore());
                valida(t.getStatoConferma());
            }
            case Autorizzazione a -> {
                valida(a.getCuratore());
                valida(a.getContenutoDaApprovare());
                valida(a.getMotivazione());
            }
            case Carrello c -> {
                valida(c.getId());
                valida(c.getPrezzoTotale());
                valida(c.getContenuti());
            }
            case Evento e -> {
                valida(e.getOrganizzatore());
                valida(e.getDataEsperienza());
                valida(e.getNumMaxPartecipanti());
                valida(e.getPosizione());
            }
            case Visita v -> {
                valida(v.getOrganizzatore());
                valida(v.getDataEsperienza());
                valida(v.getNumMaxPartecipanti());
                valida(v.getPosizione());
                valida(v.getAzienda());
            }
            case Indirizzo i -> {
                valida(i.getVia());
                valida(i.getnCivico());
                valida(i.getComune());
                valida(i.getCAP());
            }
            case Ordine o -> {
                valida(o.getId());
                valida(o.getDataOrdine());
                valida(o.getCarrello());
                valida(o.getTotale());
                valida(o.getCartaDiCredito());
                valida(o.getIndirizzoDiFatturazione());
            }
            case Pagamento p -> {
                valida(p.getNumCarta());
                valida(p.getCVV());
                valida(p.getScadenza());
            }
            case Proposta p -> {
                valida(p.getTitolo());
                valida(p.getDescrizione());
                valida(p.getOrganizzatore());
                valida(p.getDestinatario());
            }
            case PuntoMappa pm -> {
                valida(pm.getLatitudine());
                valida(pm.getLongitudine());
            }
            case RigaCarrello rc -> {
                valida(rc.getContenuto());
                valida(rc.getQuantita());
                valida(rc.getPrezzo());
            }
            default -> throw new CampoNonValidoException(
                    "Classe non gestita: " + request.getClass().getSimpleName()
            );
        }
        return checkNext(request);
    }

    // Metodo generico di validazione
    public void valida(Object valore) {
        if (valore == null) {
            throw new CampoNonValidoException("Campo nullo non consentito");
        }

        if (valore instanceof String s && s.trim().isEmpty()) {
            throw new CampoNonValidoException("Stringa vuota non consentita");
        }

        if (valore instanceof List<?> l && l.isEmpty()) {
            throw new CampoNonValidoException("Lista vuota non consentita");
        }
    }
}
