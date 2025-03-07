
import java.util.Scanner;

import Modelli.Utente;

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
        System.out.println("2. Login");
        System.out.println("3. Esci");
        System.out.print("Scelta: ");

        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                GestioneDB.registrazione(scanner);
                break;
            case 2:
                 GestioneDB.login(scanner); // Assegna il risultato di login a una variabile
                // if (utente != null) {
                //     System.out.println("Login effettuato con successo! Benvenuto, " + utente.getNomeUtente());
                //     if(utente.getRuolo().equals("Gestore")){
                //         menuGestoreCinema();
                //     }else{
                //         menuCliente(utente);
                //     }
                    
                // } else {
                //     System.out.println("Login fallito. Riprova.");
                // }
                break;
            case 3:
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
            System.out.println("2. Aggiungi una sala");
            System.out.println("3. Logout");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    GestioneDB.aggiungiFilm(scelta, null, null);
                    break;
                case 2:
                    //GestioneDB.rimuoviFilm(idFilm);
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
