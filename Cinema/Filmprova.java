public class Filmprova {
    private int id;
    private String titolo;
    private String orario;

    public Film(int id, String titolo, String orario) {
       this.id = id;
       this.titolo = titolo;
       this.orario = orario;
    }

    public int getId() {
        return this.id;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public String getOrario() {
        return this.orario;
    }
}

class GestoreCinema {
    private List<Film> filmCatalogo;

    public GestoreCinema() {
        this.filmCatalogo = new ArrayList<>();
    }

    public void aggiungiFilm(int id, String titolo, String orario) {
        Film film = new Film(id, titolo, orario);
        filmCatalogo.add(film);
        System.out.println("Film aggiunto: " + titolo + ", Orario: " + orario);
    }

    public void rimuoviFilm(int id) {
        filmCatalogo.removeIf(film -> film.getId() == id);
        System.out.println("Film con ID " + id + " rimosso dal catalogo.");
    }

    public void gestisciSale() {
        System.out.println("Gestione delle sale in corso...");
    }
}
