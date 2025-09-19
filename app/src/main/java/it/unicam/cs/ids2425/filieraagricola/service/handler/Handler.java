package it.unicam.cs.ids2425.filieraagricola.service.handler;

public abstract class Handler {

    private Handler next;

    public Handler linkWith(Handler next) {
        this.next = next;
        return next;
    }

    // Collega gli handler in una catena di responsabilità (numero variabile di handler)
    public static Handler link(Handler first, Handler... chain) {
        Handler head = first;
        for (Handler nextHandler : chain) {
            head.linkWith(nextHandler);
            head = nextHandler;
        }
        return first;
    }

    /**
     * Metodo da implementare nelle sottoclassi per gestire la richiesta.
     * Se l'handler non riesce a gestirla del tutto, deve chiamare checkNext.
     */
    public abstract boolean check(Object request);

    /**
     * Passa la richiesta al prossimo handler, se presente.
     */
    public boolean checkNext(Object request) {
        if (next == null) {
            return true; // fine catena
        }
        return next.check(request);
    }

}
