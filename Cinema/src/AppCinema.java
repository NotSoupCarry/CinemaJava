
import Models.Utente;
import Utils.Controlli;

import java.util.Scanner;

public class AppCinema {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            exit = menuPrincipale();
        }
        scanner.close();
    }

    // MENU PRINCIPALE (Registrazione / Login)
    private static boolean menuPrincipale() {
        System.out.println("\n===== CINEMA SYSTEM =====");
        System.out.println("1. Registrati");
        System.out.println("2. Login utente");
        System.out.println("3. Login gestore");
        System.out.println("4. Esci");
        System.out.print("Scelta: ");

        int scelta = Controlli.controlloInputInteri(scanner);
        scanner.nextLine();

        switch (scelta) {
            case 1:
                GestioneDB.registrazione(scanner);
                break;
            case 2:
                Utente utenteCliente = GestioneDB.loginCliente(scanner);
                if (utenteCliente != null) {
                    if (utenteCliente.getRuolo().equals("Gestore")) {
                        menuGestoreCinema();
                    } else {
                        menuCliente(utenteCliente);
                    }

                } else {
                    System.out.println("Login fallito. Riprova.");
                }
                break;
            case 3:
                Utente utenteGestore = GestioneDB.loginGestore(scanner);
                if (utenteGestore != null) {
                    if (utenteGestore.getRuolo().equals("Gestore")) {
                        menuGestoreCinema();
                    } else {
                        menuGestoreCinema();
                    }

                } else {
                    System.out.println("Login fallito. Riprova.");
                }
                break;
            case 4:
                System.out.println("Chiusura del sistema...");
                return true;
            default:
                System.out.println("Scelta non valida, riprova.");
        }
        return false;
    }

    // MENU CLIENTE (Prenotazioni)
    private static void menuCliente(Utente utente) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("1. Prenota un biglietto");
            System.out.println("2. Logout");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    GestioneDB.prenotaBiglietto(scanner, utente);
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
    }

    // MENU GESTORE CINEMA (Gestione Film e Sale)
    private static void menuGestoreCinema() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== MENU GESTORE CINEMA =====");
            System.out.println("1. Aggiungi un film");
            System.out.println("2. rimuvuovi un film");
            System.out.println("3. Logout");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    System.out.println("inserisci il titolo del film");
                    String titolo = Controlli.controlloInputStringhe(scanner);
                    System.out.println("inserisci l'orario del film");
                    String orario = Controlli.controlloInputStringhe(scanner);
                    GestioneDB.aggiungiFilm(scelta, titolo, orario);
                    break;
                case 2:
                    GestioneDB.getFilms();
                    System.out.println("inserisci l'id del film da rimuovere");
                    int idFilm = Controlli.controlloInputInteri(scanner);
                    GestioneDB.rimuoviFilm(idFilm);
                    break;
                case 3:
                    System.out.println("Logout effettuato.");
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
    }
}
