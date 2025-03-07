package Models;

import java.sql.Timestamp;

public class Prenotazione {
    private int id;
    private int idCliente;
    private int idSalaFilm;
    private Timestamp dataPrenotazione;

    public Prenotazione(int id, int idCliente, int idSalaFilm, Timestamp dataPrenotazione) {
        this.id = id;
        this.idCliente = idCliente;
        this.idSalaFilm = idSalaFilm;
        this.dataPrenotazione = dataPrenotazione;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdSalaFilm() {
        return idSalaFilm;
    }

    public Timestamp getDataPrenotazione() {
        return dataPrenotazione;
    }

    // Setters
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdSalaFilm(int idSalaFilm) {
        this.idSalaFilm = idSalaFilm;
    }

    public void setDataPrenotazione(Timestamp dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    // Metodo per stampare i dettagli della prenotazione
    public void mostraDettagli() {
        System.out.println("Prenotazione ID: " + id + 
                           " | Cliente ID: " + idCliente + 
                           " | SalaFilm ID: " + idSalaFilm + 
                           " | Data Prenotazione: " + dataPrenotazione);
    }
}

