public abstract class Sala {
    private int numeroSala;
    private int posti;

    public Sala(int numeroSala, int posti) {
        this.numeroSala = numeroSala;
        this.posti = posti;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public int getPosti() {
        return posti;
    }

    public abstract void mostraInfo();
}
