package it.unicam.cs.ids2425.filieraagricola.controller.security;

import it.unicam.cs.ids2425.filieraagricola.model.Account;
import it.unicam.cs.ids2425.filieraagricola.model.Ruolo;
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
        Account account = accountService.getAccount(email);
        List<GrantedAuthority> authorities = account.getRuoli().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toList());
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

}
