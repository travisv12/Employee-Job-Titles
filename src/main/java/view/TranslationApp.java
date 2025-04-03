package view;

import controller.TranslationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TranslationApp extends Application {
    private TranslationController controller;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.controller = new TranslationController();
        controller.setMainApp(this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TranslationView.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Translation Manager");
        primaryStage.show();
    }
}