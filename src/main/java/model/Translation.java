package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Translation {
    private final StringProperty key;
    private final StringProperty value;

    public Translation(String key, String value) {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty(value);
    }

    public StringProperty keyProperty() { return key; }
    public StringProperty valueProperty() { return value; }
}
