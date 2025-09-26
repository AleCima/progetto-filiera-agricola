Filiera Agricola - Piattaforma di Digitalizzazione e Valorizzazione
===================================================================

Descrizione
-----------
La piattaforma permette la gestione, valorizzazione e tracciabilità dei prodotti agricoli locali.
Consente di caricare, visualizzare e condividere informazioni legate alla filiera agricola, includendo dati relativi a produzione, trasformazione e vendita dei prodotti tipici. 
Supporta l'organizzazione di eventi locali, fiere, visite guidate alle aziende e la creazione di pacchetti esperienziali.

Tecnologie
----------
- Java 17
- Spring Boot
- Spring Security
- H2 Database (default)
- Maven
- API REST

Attori principali
-----------------
1. Produttore: carica informazioni sui prodotti, certificazioni, metodi di coltivazione e vende sul marketplace.
2. Trasformatore: carica dati sui processi di trasformazione e certificazioni di qualità, collega le fasi ai produttori.
3. Distributore di Tipicità: gestisce prodotti e pacchetti di prodotti per esperienze gastronomiche.
4. Curatore: approva e verifica la validità dei contenuti caricati dagli altri attori.
5. Animatore della Filiera: organizza eventi, fiere e visite guidate alle aziende.
6. Acquirente: accede alle informazioni e acquista prodotti o prenota eventi/fiere.
7. Utente Generico: consulta contenuti per informarsi sulla provenienza e qualità dei prodotti.
8. Gestore della Piattaforma: gestisce aspetti amministrativi, autorizzazioni e accrediti.
9. Sistemi Social: destinatari dei contenuti condivisi dagli utenti.
10. Sistema OSM: fornisce mappe e visualizza punti della filiera agricola.

Funzionalità
------------
- Registrazione, modifica, eliminazione di utenti e venditori
- Assegnazione di ruoli singoli o multipli
- Gestione contenuti: prodotti, trasformazioni, pacchetti
- Gestione ordini e carrello con controllo dei ruoli
- Organizzazione e prenotazione eventi, fiere e visite guidate
- Tracciabilità completa dei prodotti lungo la filiera
- Visualizzazione dei punti della filiera su mappa interattiva
- Condivisione dei contenuti sui social
- Sicurezza e validazione dati tramite handler personalizzati

Endpoint principali
------------------
- **Account**
  - Registrazione/modifica/eliminazione utenti e venditori
  - Assegnazione di ruoli
  - Ricerca utenti e venditori
- **Contenuto**
  - Aggiunta prodotti, trasformazioni e pacchetti
- **Esperienza/Eventi**
  - Organizzazione, accettazione e prenotazione di eventi e visite guidate
- **Carrello**
  - Gestione acquisti e prenotazioni
- **Ordine**
  - Gestione ordini dei prodotti
- **Autorizzazione**
  - Gestione delle approvazioni dei contenuti
- **OSM**
  - Visualizzazione punti della filiera sulla mappa
- **H2 Console**
  - Accessibile senza autenticazione, utilizzato per test 

Sicurezza
---------
- Controllo accesso basato sui ruoli (`ROLE_` + nome ruolo)
- Basic Auth con sessione stateless
- Password di default non criptate (NoOpPasswordEncoder)
- Validazioni su email e campi non nulli tramite handler personalizzati

Note
----
- Endpoints protetti secondo ruolo dell’utente
- Supporto a design pattern per gestione validazioni e flussi di autorizzazione
- La piattaforma promuove la tracciabilità e la valorizzazione del territorio
