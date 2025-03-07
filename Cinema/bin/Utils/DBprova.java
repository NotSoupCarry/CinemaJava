package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBprova {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemaDB";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public DBprova() {
    }

    public static Connection connessioneDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static boolean aggiungiFilm(int id, String titolo, String orario) {
        String query = "INSERT INTO Film (id, titolo, orario) VALUES (?, ?, ?)";
        try (Connection conn = connessioneDatabase(); PreparedStatement stmt = conn.prepareStatement(query)) {
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
        try (Connection conn = connessioneDatabase(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
