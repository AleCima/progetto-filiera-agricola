package it.unicam.cs.ids2425.filieraagricola.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MyUserDetails userDetailsService) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // ACCOUNT
                        .requestMatchers("/account/modifica-utente").hasAnyRole("GESTORE", "ACQUIRENTE")
                        .requestMatchers("/account/modifica-venditore").hasAnyRole("GESTORE", "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")
                        .requestMatchers("/account/elimina-venditore").hasAnyRole("GESTORE", "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")
                        .requestMatchers("/account/elimina-utente").hasAnyRole("GESTORE", "ACQUIRENTE")
                        .requestMatchers("/account/assegna-ruolo").hasAnyRole("GESTORE")
                        .requestMatchers("/account/assegna-ruoli").hasAnyRole("GESTORE")
                        .requestMatchers("/account/**").permitAll() // generica, viene dopo quelle specifiche

                        // AUTORIZZAZIONE
                        .requestMatchers("/autorizzazione/**").hasAnyRole("GESTORE", "CURATORE")

                        // CARRELLO
                        .requestMatchers("/carrello/**").hasAnyRole("GESTORE", "ACQUIRENTE")

                        // CONTENUTO
                        .requestMatchers("/contenuto/aggiungi-prodotto").hasAnyRole("GESTORE", "PRODUTTORE")
                        .requestMatchers("/contenuto/aggiungi-trasformazione").hasAnyRole("GESTORE", "TRASFORMATORE")
                        .requestMatchers("/contenuto/aggiungi-pacchetto").hasAnyRole("GESTORE", "DISTRIBUTORE")
                        .requestMatchers("/contenuto/**").permitAll() // generica, viene dopo quelle specifiche

                        // ESPERIENZA
                        .requestMatchers("/esperienza/accetta-proposta").hasAnyRole("GESTORE", "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")
                        .requestMatchers("/esperienza/**").hasAnyRole("GESTORE", "ANIMATORE")

                        // ORDINE
                        .requestMatchers("/ordine/**").hasAnyRole("GESTORE", "ACQUIRENTE", "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")

                        // OSM
                        .requestMatchers("/osm/**").permitAll()

                        // H2 CONSOLE
                        .requestMatchers("/h2-console/**").permitAll()
                )

                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
