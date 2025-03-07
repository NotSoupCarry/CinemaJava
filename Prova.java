import java.util.ArrayList;
import java.util.List;

// Classe astratta Utente
abstract class Utente {
    private String nomeUtente;
    private String password;

    public Utente(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public boolean login(String nomeUtente, String password) {
        return this.nomeUtente.equals(nomeUtente) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("Logout eseguito per: " + nomeUtente);
    }
}

// Classe Cliente che estende Utente
class Cliente extends Utente {
    private List<String> prenotazioni;

    public Cliente(String nomeUtente, String password) {
        super(nomeUtente, password);
        this.prenotazioni = new ArrayList<>();
    }

    public void prenotaBiglietto(SalaFilm sala, String film) {
        if (sala.prenotaPosto()) {
            String prenotazione = "Prenotato biglietto per film: \"" + film + "\" in Sala " + sala.getNumeroSala()
                    + " alle " + sala.getOrarioProiezione();
            prenotazioni.add(prenotazione);
            System.out.println(prenotazione);
        } else {
            System.out.println("Posti esauriti per il film: " + film);
        }
    }
}

// Classe SalaFilm per gestire la prenotazione dei biglietti
class SalaFilm {
    private int numeroSala;
    private int postiDisponibili;
    private String nomeFilm;
    private String orarioProiezione;

    public SalaFilm(int numeroSala, int postiDisponibili, String nomeFilm, String orarioProiezione) {
        this.numeroSala = numeroSala;
        this.postiDisponibili = postiDisponibili;
        this.nomeFilm = nomeFilm;
        this.orarioProiezione = orarioProiezione;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public String getOrarioProiezione() {
        return orarioProiezione;
    }

    public boolean prenotaPosto() {
        if (postiDisponibili > 0) {
            postiDisponibili--;
            return true;
        }
        return false;
    }
}
