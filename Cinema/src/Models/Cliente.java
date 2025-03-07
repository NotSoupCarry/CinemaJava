package Models;

public class Cliente extends Utente {
    public int idFilm;

    public Cliente(int id, String nomeUtente, String password, String ruolo, int idFilm) {
        super(id, nomeUtente, password, ruolo);
        this.idFilm = idFilm;
    }

    public void prenotaBiglietto(SalaFilm sala, Film film) {
        if (sala.prenotaPosto()) {
            String prenotazione = "Prenotato biglietto per film: \"" + film.getTitolo() + "\" in Sala " + sala.getNumeroSala()
                    + " alle " + sala.getOrarioProiezione();
            System.out.println(prenotazione);
        } else {
            System.out.println("Posti esauriti per il film: " + film);
        }
    }

}
