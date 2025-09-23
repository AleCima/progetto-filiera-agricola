package it.unicam.cs.ids2425.filieraagricola.service;

import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessioneService {
    private Map<String, Utente> sessioni = new ConcurrentHashMap<>();

    public void login(String token, Utente utente) {
        sessioni.put(token, utente);
    }

    public Utente getUtenteByToken(String token) {
        return sessioni.get(token);
    }

    public void logout(String token) {
        sessioni.remove(token);
    }
}
