package it.unicam.cs.ids2425.filieraagricola.controller.DTO;

public class AutenticazioneAccountDTO {
    private String email;
    private String password;

    public AutenticazioneAccountDTO() {
    }

    public AutenticazioneAccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
