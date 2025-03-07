package Models;

abstract class Utente {
    private int id;
    private String nomeUtente;
    private String password;
    private String ruolo;

    public Utente(int id, String nomeUtente, String password, String ruolo) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.ruolo = ruolo;
    }

    public int getId() {
        return id;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getRuolo() {
        return ruolo;
    }


    public boolean login(String nomeUtente, String password) {
        return this.nomeUtente.equals(nomeUtente) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("Logout eseguito per: " + nomeUtente);
    }
}
