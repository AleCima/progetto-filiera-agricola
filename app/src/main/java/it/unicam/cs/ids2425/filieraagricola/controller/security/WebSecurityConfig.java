package it.unicam.cs.ids2425.filieraagricola.controller.security;

import it.unicam.cs.ids2425.filieraagricola.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    /**
     * Configurazione della sicurezza HTTP.
     * Specifica quali endpoint richiedono autenticazione e quali ruoli.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Eventi e visite: solo Animatore
                        .requestMatchers("/esperienza/**").hasRole("ANIMATORE")

                        // Tutto il resto richiede autenticazione
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)                                  // disabilita CSRF per semplicità
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // utile per H2 console
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // niente sessione, token-based
                )
                .httpBasic(Customizer.withDefaults()); // basic auth per semplicità

        return http.build();
    }

    /**
     * PasswordEncoder minimo (password in chiaro).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * UserDetailsService recupera gli utenti dal tuo AccountService.
     */
    @Bean
    public UserDetailsService userDetailsService(AccountService accountService) {
        return new MyUserDetails(accountService);
    }
}
