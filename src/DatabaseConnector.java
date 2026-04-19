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
}
