package Models;
public class SalaFilm extends Sala {
    private String nomeFilm;
    private String orarioProiezione;

    public SalaFilm(int numeroSala, int posti, String nomeFilm, String orarioProiezione) {
        super(numeroSala, posti);
        this.nomeFilm = nomeFilm;
        this.orarioProiezione = orarioProiezione;
    }

    public String getNomeFilm() {
        return nomeFilm;
    }

    public String getOrarioProiezione() {
        return orarioProiezione;
    }

    @Override
    public void mostraInfo() {
        System.out.println("Sala " + getNumeroSala() + " | Film: " + nomeFilm + " | Orario: " + orarioProiezione + " | Posti: " + getPosti());
    }
}
