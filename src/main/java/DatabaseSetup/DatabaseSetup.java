package DatabaseSetup;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseSetup {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_DOMAIN");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        String dbName = "EmployeeJobTitles";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Create Database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            stmt.executeUpdate("USE " + dbName);

            // Create Table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS translations (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Key_name VARCHAR(50) NOT NULL," +
                    "Language_code VARCHAR(10) NOT NULL," +
                    "translation_text VARCHAR(255) NOT NULL," +
                    "UNIQUE KEY unique_translation (Key_name, Language_code)" +
                    ")";
            stmt.executeUpdate(createTableSQL);

            // Insert Sample Data
            stmt.executeUpdate("INSERT IGNORE INTO translations (Key_name, Language_code, translation_text) VALUES " +
                    "('manager', 'en', 'Manager')," +
                    "('developer', 'en', 'Developer')," +
                    "('manager', 'es', 'Gerente')," +
                    "('developer', 'es', 'Desarrollador')");

            System.out.println("Database and table created successfully with sample data.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}