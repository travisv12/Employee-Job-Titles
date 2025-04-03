package model;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import io.github.cdimascio.dotenv.Dotenv;

public class TranslationModel {
    private Connection connection;

    public TranslationModel() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Translation> getTranslations(String language) {
        ObservableList<Translation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT Key_name, translation_text FROM translations WHERE Language_code = ?");
            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Translation(rs.getString("Key_name"), rs.getString("translation_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addOrUpdateTranslation(String key, String value, String language) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO translations (Key_name, Language_code, translation_text) VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE translation_text = VALUES(translation_text)");
            stmt.setString(1, key);
            stmt.setString(2, language);
            stmt.setString(3, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}