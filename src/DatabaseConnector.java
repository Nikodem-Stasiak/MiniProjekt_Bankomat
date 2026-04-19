import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlite:bankomat.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
                System.out.println("Błąd SQL: " + e.getMessage());
                return null;
        }
    }

    public static void setupDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS konta (id INTEGER PRIMARY KEY AUTOINCREMENT, numer TEXT, pin TEXT, saldo REAL);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Baza danych gotowa do pracy!");

        } catch (SQLException e) {
            System.out.println("Ups! Coś nie tak z bazą: " + e.getMessage());
        }
    }

    public static void dodajKonto(String numer, String pin, double saldo) {
        String sql = "INSERT INTO konta (numer, pin, saldo) VALUES (?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numer);
            pstmt.setString(2, pin);
            pstmt.setDouble(3, saldo);

            pstmt.executeUpdate();

            System.out.println("Sukces! Konto o numerze " + numer + " zostało zapisane w bazie.");

        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania konta: " + e.getMessage());
        }
    }
}
