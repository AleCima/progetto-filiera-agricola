package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;

public class LoginHandler extends Handler {

    private final AccountService accountService;

    public LoginHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean check(HttpServletRequest request) {
        // Recupera il token dall'header "Authorization"
        String token = request.getHeader("Authorization");
        if (token == null || accountService.getEmailByToken(token) == null) {
            return false; // token assente o non valido
        }

        // Token valido, passa al prossimo handler
        return checkNext(request);
    }
}