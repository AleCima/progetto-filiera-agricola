package it.unicam.cs.ids2425.filieraagricola.controller.security;

import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricola.model.Utente;
import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetails implements UserDetailsService {

    private final AccountService accountService;

    public MyUserDetails(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>> DEBUG: loadUserByUsername chiamato con email: " + email);

        Utente utente = accountService.getUtenteByEmail(email);
        if (utente == null) {
            System.out.println(">>> DEBUG: utente non trovato!");
            throw new UsernameNotFoundException("Utente non trovato con email: " + email);
        }

        System.out.println(">>> DEBUG: utente trovato: " + utente.getEmail());

        List<Ruolo> ruoli = utente.getRuoli();
        if (ruoli == null || ruoli.isEmpty()) {
            System.out.println(">>> DEBUG: utente senza ruoli!");
        } else {
            String ruoliStr = ruoli.stream()
                    .map(Ruolo::name)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("nessuno");
            System.out.println(">>> DEBUG: ruoli utente: " + ruoliStr);
        }

        List<GrantedAuthority> authorities = ruoli.stream()
                .map(r -> {
                    System.out.println(">>> DEBUG: mapping ruolo " + r);
                    return new SimpleGrantedAuthority("ROLE_" + r.name());
                })
                .collect(Collectors.toList());

        System.out.println(">>> DEBUG: authorities pronte: " + authorities);

        UserDetails userDetails = new User(utente.getEmail(), utente.getPassword(), authorities);
        System.out.println(">>> DEBUG: UserDetails creato: " + userDetails);

        return userDetails;
    }
}
