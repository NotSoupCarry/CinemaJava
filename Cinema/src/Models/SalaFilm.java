package Models;

public class SalaFilm extends Sala {
    private int id;
    private String nomeFilm;
    private String orarioProiezione;

    public SalaFilm(int numeroSala, int posti, int id, String nomeFilm, String orarioProiezione) {
        super(numeroSala, posti);
        this.id = id;
        this.nomeFilm = nomeFilm;
        this.orarioProiezione = orarioProiezione;
    }

    public String getNomeFilm() {
        return nomeFilm;
    }
    
    public int getId() {
        return id;
    }
    public String getOrarioProiezione() {
        return orarioProiezione;
    }

    @Override
    public void mostraInfo() {
        System.out.println("Sala " + getNumeroSala() + " | Film: " + nomeFilm + " | Orario: " + orarioProiezione
                + " | Posti: " + getPosti());
    }
}
