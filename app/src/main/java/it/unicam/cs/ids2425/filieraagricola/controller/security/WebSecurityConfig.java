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
                        //ACCOUNT
                        .requestMatchers("/account/registrazione").permitAll()
                        .requestMatchers("/account/modifica-utente").hasAnyRole("GESTORE", "ACQUIRENTE")
                        .requestMatchers("/account/registrazione-venditore").permitAll()
                        .requestMatchers("/account/modifica-venditore").hasAnyRole("GESTORE", "VENDITORE")
                        .requestMatchers("/account/elimina-venditore").hasAnyRole("GESTORE", "VENDITORE")
                        .requestMatchers("/account/ricerca-utente").permitAll()
                        .requestMatchers("/account/ricerca-venditore").permitAll()
                        .requestMatchers("/account/assegna-ruolo").hasAnyRole("GESTORE")
                        .requestMatchers("/account/assegna-ruoli").hasAnyRole("GESTORE")
                        //AUTORIZZAZIONE
                        .requestMatchers("/autorizzazione/**").hasAnyRole("GESTORE", "CURATORE")
                        //CARRELLO
                        .requestMatchers("/carrello/**").hasAnyRole("GESTORE", "ACQUIRENTE")
                        //CONTENUTO
                        .requestMatchers("/contenuto/aggiungi-prodotto").hasAnyRole("GESTORE", "PRODUTTORE")
                        .requestMatchers("/contenuto/aggiungi-trasformazione").hasAnyRole("GESTORE", "TRASFORMATORE")
                        .requestMatchers("/contenuto/aggiungi-pacchetto").hasAnyRole("GESTORE", "DISTRIBUTORE")
                        .requestMatchers("/contenuto/ottieni-contenuti").permitAll()
                        .requestMatchers("/contenuto/ottieni-contenuti-venditore").permitAll()
                        .requestMatchers("/contenuto/ottieni-prodotti").permitAll()
                        .requestMatchers("/contenuto/ottieni-trasformazione").permitAll()
                        .requestMatchers("/contenuto/ottieni-pacchetti").permitAll()
                        //ESPERIENZA
                        .requestMatchers("/esperienza/**").hasAnyRole("GESTORE", "ANIMATORE")
                        //ORDINE
                        .requestMatchers("/ordine/**").hasAnyRole("GESTORE", "ACQUIRENTE", "VENDITORE")
                        //OSM
                        .requestMatchers("/osm/**").permitAll()

                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
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
