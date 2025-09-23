package it.unicam.cs.ids2425.filieraagricola.service.handler;

import jakarta.servlet.http.HttpServletRequest;

public abstract class Handler {

    private Handler next; // prossimo handler della catena

    /**
     * Collega questo handler al prossimo.
     */
    public Handler setNext(Handler next) {
        this.next = next;
        return next; // così puoi concatenare i setNext()
    }

    /**
     * Metodo che ogni handler concreto deve implementare
     * per eseguire il proprio controllo.
     */
    public abstract boolean check(HttpServletRequest request);

    /**
     * Chiama il prossimo handler nella catena (se esiste).
     */
    protected boolean checkNext(HttpServletRequest request) {
        if (next == null) {
            return true; // fine catena, nessun problema
        }
        return next.check(request);
    }

//                          ESEMPIO
//       Handler chain = new NonNullOrEmptyHandler()
//            .setNext(new EmailFormatHandler())
//            .setNext(new RuoloHandler(Ruolo.CURATORE))
//            .setNext(new IndirizzoHandler());

}
