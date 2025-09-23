package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

public class RuoloHandler extends Handler {

    private final AccountService accountService;
    private final Ruolo ruoloRichiesto;

    public RuoloHandler(AccountService accountService, Ruolo ruoloRichiesto) {
        this.accountService = accountService;
        this.ruoloRichiesto = ruoloRichiesto;
    }

    @Override
    public boolean check(HttpServletRequest request) {
        // Recupera il token dall'header
        String token = request.getHeader("Authorization");
        if (token == null) return false;

        // Trova l'email associata al token
        String email = accountService.getEmailByToken(token);
        if (email == null) return false;

        // Recupera l'utente
        Utente utente = accountService.getUtenteByEmail(email);
        if (utente == null) return false;

        // Controlla se l'utente ha il ruolo richiesto
        if (!utente.getRuoli().contains(ruoloRichiesto)) {
            return false;
        }

        // Tutto ok, passa al prossimo handler
        return checkNext(request);
    }
}

