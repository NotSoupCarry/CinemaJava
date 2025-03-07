public class Cliente extends Utente {
    public Cliente(int id, String nomeUtente, String password, String ruolo) {
        super(id, nomeUtente, password, ruolo);
    }

    public void prenotaBiglietto(Film film) {
        System.out.println("Biglietto prenotato per il film: " + film.getTitolo() + " alle " + film.getOrario());
    }
}
