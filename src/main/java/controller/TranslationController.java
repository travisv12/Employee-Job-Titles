package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import view.TranslationApp;

public class TranslationController {
    private TranslationModel model;
    private ObservableList<Translation> translations;
    private TranslationApp mainApp;

    @FXML private ComboBox<String> languageCombo;
    @FXML private TableView<Translation> tableView;
    @FXML private TableColumn<Translation, String> keyColumn;
    @FXML private TableColumn<Translation, String> valueColumn;
    @FXML private TextField keyField;
    @FXML private TextField valueField;
    @FXML private Button addButton;

    public TranslationController() {
        model = new TranslationModel();
        translations = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        languageCombo.getItems().addAll("en", "es");
        languageCombo.setValue("en");
        languageCombo.setOnAction(e -> loadTranslations());

        keyColumn.setCellValueFactory(data -> data.getValue().keyProperty());
        valueColumn.setCellValueFactory(data -> data.getValue().valueProperty());
        tableView.setItems(translations);

        addButton.setOnAction(e -> addOrUpdateTranslation());

        loadTranslations();
    }

    private void loadTranslations() {
        translations.setAll(model.getTranslations(languageCombo.getValue()));
    }

    private void addOrUpdateTranslation() {
        model.addOrUpdateTranslation(keyField.getText(), valueField.getText(), languageCombo.getValue());
        loadTranslations();
    }

    public void setMainApp(TranslationApp mainApp) {
        this.mainApp = mainApp;
    }
}