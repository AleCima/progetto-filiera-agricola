package it.unicam.cs.ids2425.filieraagricola.model;

import it.unicam.cs.ids2425.filieraagricola.exception.CampoNonValidoException;

public class SistemaSocial {

    public static final String paginaContenuto = "localhost:8080/contenuto/ottieni-contenuto?id=";

    public static String generateLink(String social, int idContenuto) {
        if (social.equalsIgnoreCase("Twitter") || social.equalsIgnoreCase("X")) {
            return "https://x.com/intent/tweet?url=" + paginaContenuto + idContenuto;
        } else if (social.equalsIgnoreCase("Facebook")) {
            return "https://www.facebook.com/sharer/sharer.php?u=" + paginaContenuto + idContenuto;
        } else if (social.equalsIgnoreCase("Whatsapp")) {
            return "https://api.whatsapp.com/send?text=" + paginaContenuto + idContenuto;
        } else {
            throw new CampoNonValidoException("Non è stato implementato il link per " + social);
        }
    }
}
