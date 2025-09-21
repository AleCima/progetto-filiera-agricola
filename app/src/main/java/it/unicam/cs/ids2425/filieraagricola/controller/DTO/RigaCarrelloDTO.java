package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

public class RigaCarrelloDTO {
    private int id;
    private int quantita;

    public RigaCarrelloDTO(int id, int quantita) {
        this.id = id;
        this.quantita = quantita;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
