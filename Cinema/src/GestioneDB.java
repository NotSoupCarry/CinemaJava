
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Cliente;
import Models.Film;
import Models.GestoreCinema;
import Models.SalaFilm;
import Models.Utente;
import Utils.Controlli;
import Utils.DBContext;

public class GestioneDB {

    public static boolean aggiungiFilm(int id, String titolo, String orario) {
        String query = "INSERT INTO film (id, titolo, orario) VALUES (?, ?, ?)";
        try (Connection conn = DBContext.connessioneDatabase(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, titolo);
            stmt.setString(3, orario);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean rimuoviFilm(int id) {
        String query = "DELETE FROM Film WHERE id = ?";
        try (Connection conn = DBContext.connessioneDatabase(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void prenotaBiglietto(Scanner scanner, Utente utenteLoggato) {
        System.out.println("\n===== SELEZIONA UN FILM =====");

        // Mostra tutti i film disponibili
        List<Film> films = GestioneDB.getFilms(); // Metodo per ottenere la lista di film dal database
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            System.out.println((i + 1) + ". " + film.getTitolo() + " - Orario: " + film.getOrario());
        }

        System.out.print("Scegli un film (inserisci il numero): ");
        int sceltaFilm = Controlli.controlloInputInteri(scanner);;
        scanner.nextLine();
        Film filmScelto = films.get(sceltaFilm - 1);

        // Mostra le sale disponibili per il film selezionato
        System.out.println("\n===== SELEZIONA UNA SALA =====");
        List<SalaFilm> saleFilm = GestioneDB.getSaleFilm(filmScelto.getId()); // Metodi per ottenere le sale del film
        for (int i = 0; i < saleFilm.size(); i++) {
            SalaFilm sala = saleFilm.get(i);
            sala.mostraInfo(); // Mostra informazioni sulla sala e il film
        }

        System.out.print("Scegli una sala (inserisci il numero): ");
        int sceltaSala = Controlli.controlloInputInteri(scanner);;
        scanner.nextLine();
        SalaFilm salaScelta = saleFilm.get(sceltaSala - 1);

        // Prenota il posto nella sala
        if (salaScelta.prenotaPosto()) {
            // Inserisce la prenotazione nel database
            if (GestioneDB.inserisciPrenotazione(utenteLoggato.getId(), salaScelta.getId())) {
                System.out.println("Prenotazione effettuata con successo per il film: " + filmScelto.getTitolo());
            } else {
                System.out.println("Errore nella prenotazione.");
            }
        } else {
            System.out.println("Spiacenti, non ci sono posti disponibili in questa sala.");
        }
    }

    public static boolean inserisciPrenotazione(int idCliente, int idSalaFilm) {
        String sql = "INSERT INTO Prenotazione (idCliente, idSalaFilm) VALUES (?, ?)";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idSalaFilm);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento della prenotazione: " + e.getMessage());
            return false;
        }
    }

    public static List<Film> getFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, titolo, orario FROM Film";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Film film = new Film(rs.getInt("id"), rs.getString("titolo"), rs.getString("orario"));
                films.add(film);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero dei film: " + e.getMessage());
        }

        return films;
    }

    public static List<SalaFilm> getSaleFilm(int idFilm) {
        List<SalaFilm> saleFilm = new ArrayList<>();
        String sql = "SELECT sf.id, sf.idSala, sf.orarioProiezione, s.numeroSala, s.posti " +
                "FROM SalaFilm sf JOIN Sala s ON sf.idSala = s.id WHERE sf.idFilm = ?";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SalaFilm salaFilm = new SalaFilm(rs.getInt("numeroSala"), rs.getInt("posti"), rs.getInt("id"),
                        rs.getString("orarioProiezione"), rs.getString("orarioProiezione"));
                saleFilm.add(salaFilm);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero delle sale: " + e.getMessage());
        }

        return saleFilm;
    }

    // Metodo per registrare un nuovo utente come Cliente
    public static void registrazione(Scanner scanner) {

        System.out.println("\n===== REGISTRAZIONE =====");
        System.out.print("Inserisci Nome Utente: ");
        String nomeUtente = Controlli.controlloInputStringhe(scanner);
        while (Controlli.checkUtenteEsiste(nomeUtente)) {
            System.out.print("Nome utente già in uso.\nRiprova con un altro: ");
            nomeUtente = Controlli.controlloInputStringhe(scanner);
        }

        System.out.print("Inserisci Password: ");
        String password = Controlli.controlloInputStringhe(scanner);

        // Query SQL per inserire il nuovo utente
        String sql = "INSERT INTO utente (nomeUtente, password, ruolo) VALUES (?, ?, 'Cliente')";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUtente);
            stmt.setString(2, password);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registrazione completata! Ora puoi effettuare il login.");
            }
        } catch (SQLException e) {
            System.out.println("Errore nella registrazione: " + e.getMessage());
        }
    }

    // Metodo per il login
    // public static Utente login(Scanner scanner) {
    //     System.out.println("\n===== LOGIN =====");
    //     System.out.print("Inserisci Nome Utente: ");
    //     String nomeUtente = scanner.nextLine();
    //     System.out.print("Inserisci Password: ");
    //     String password = scanner.nextLine();

    //     String sql = "SELECT id, nomeUtente, ruolo FROM utente WHERE nomeUtente = ? AND password = ?";

    //     try (Connection conn = DBContext.connessioneDatabase();
    //             PreparedStatement stmt = conn.prepareStatement(sql)) {

    //         stmt.setString(1, nomeUtente);
    //         stmt.setString(2, password);
    //         ResultSet resultSet = stmt.executeQuery();

    //         if (resultSet.next()) {
    //             int id = resultSet.getInt("id");
    //             String ruolo = resultSet.getString("ruolo");

    //             System.out.println("Login effettuato con successo! Benvenuto, " + nomeUtente
    //                     + ".");

    //             // Restituisci l'oggetto Cliente o GestoreCinema in base al ruolo
    //             if (ruolo.equals("Cliente")) {
    //                 return new Cliente(id, nomeUtente, password, ruolo);
    //             } else if (ruolo.equals("GestoreCinema")) {
    //                 return new GestoreCinema(id, nomeUtente, password, ruolo);
    //             }
    //         } else {
    //             System.out.println("Credenziali errate. Riprova.");
    //             return null; // Restituisce null se le credenziali sono errate
    //         }
    //     } catch (SQLException e) {
    //         System.out.println("Errore nel login: " + e.getMessage());
    //         return null; // Restituisce null se si verifica un errore di SQL
    //     }
    // }

    public static Cliente loginCliente(Scanner scanner) {
        System.out.println("\n===== LOGIN =====");
        System.out.print("Inserisci Nome Utente: ");
        String nomeUtente = scanner.nextLine();
        System.out.print("Inserisci Password: ");
        String password = scanner.nextLine();

        String sql = "SELECT id, nomeUtente, ruolo FROM utente WHERE nomeUtente = ? AND password = ?";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUtente);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ruolo = resultSet.getString("ruolo");

                // Restituisci l'oggetto Cliente o GestoreCinema in base al ruolo
                if (ruolo.equals("Cliente")) {
                    System.out.println("Login effettuato con successo! Benvenuto, " + nomeUtente + ".");
                    return new Cliente(id, nomeUtente, password, ruolo); // Cliente estende Utente
                } else
                    return null;
            } else {
                System.out.println("Credenziali errate. Riprova.");
                return null; // Restituisce null se le credenziali sono errate
            }
        } catch (SQLException e) {
            System.out.println("Errore nel login: " + e.getMessage());
            return null; // Restituisce null se si verifica un errore di SQL
        }
    }

    public static GestoreCinema loginGestore(Scanner scanner) {
        System.out.println("\n===== LOGIN =====");
        System.out.print("Inserisci Nome Utente: ");
        String nomeUtente = scanner.nextLine();
        System.out.print("Inserisci Password: ");
        String password = scanner.nextLine();

        String sql = "SELECT id, nomeUtente, ruolo FROM utente WHERE nomeUtente = ? AND password = ?";

        try (Connection conn = DBContext.connessioneDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUtente);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ruolo = resultSet.getString("ruolo");

                if (ruolo.equals("Gestore")) {
                    System.out.println("Login effettuato con successo! Benvenuto, " + nomeUtente + ".");
                    return new GestoreCinema(id, nomeUtente, password, ruolo); // GestoreCinema estende Utente
                } else
                    return null;
            } else {
                System.out.println("Credenziali errate. Riprova.");
                return null; // Restituisce null se le credenziali sono errate
            }
        } catch (SQLException e) {
            System.out.println("Errore nel login: " + e.getMessage());
            return null; // Restituisce null se si verifica un errore di SQL
        }
    }

}
