package Models;

public class Film {
    private int id;
    private String titolo;
    private String orario;

    public Film(int id, String titolo, String orario) {
        this.id = id;
        this.titolo = titolo;
        this.orario = orario;
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getOrario() {
        return orario;
    }
}
