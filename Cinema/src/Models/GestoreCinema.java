package Models;
import Interfaces.IAddFilm;
import Interfaces.IAddRoom;

public class GestoreCinema extends Utente implements IAddFilm, IAddRoom {

    public GestoreCinema(int id, String nomeUtente, String password) {
        super(id, nomeUtente, password, "Gestore");
    }

    @Override
    public void aggiungiFilm(String titolo, String orario) {
        System.out.println("Film aggiunto: " + titolo + " - Orario: " + orario);
    }

    @Override
    public void aggiungiSala(int numeroSala, int posti) {
        System.out.println("Sala aggiunta: " + numeroSala + " - Posti disponibili: " + posti);
    }
}
