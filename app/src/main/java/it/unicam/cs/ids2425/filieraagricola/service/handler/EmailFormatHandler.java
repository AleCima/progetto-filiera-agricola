package it.unicam.cs.ids2425.filieraagricola.service.handler;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.model.Venditore;
import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class EmailFormatHandler extends Handler {

    // Regex base per validazione email
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    public boolean check(HttpServletRequest request) {
        String email;
        // Estrae l'email da Utente o Venditore
        if (request instanceof Utente u) {
            email = u.getEmail();
        } else if (request instanceof Venditore v) {
            email = v.getEmail();
        } else {
            // Non è un oggetto con email, passa al prossimo handler
            return checkNext(request);
        }

        // Controllo formato
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new CampoNonValidoException("Email non valida: " + email);
        }

        // Passa al prossimo handler
        return checkNext(request);
    }
}
