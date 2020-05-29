package com.shaheen.contoller;

import com.shaheen.model.Vector3Type;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ModifyAttrViewController extends DialogPane implements Initializable {

    private Stage stage;


    @FXML
    private Label messageTitleLabel;
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField z;
    @FXML
    private TextField type;

    private Vector3Type vector3Type;

    private ConfirmType confirmType = ConfirmType.CANCEL;

    @FXML
    public void onOkAction(ActionEvent actionEvent) {
        vector3Type.setX(Double.parseDouble(x.getText()));
        vector3Type.setY(Double.parseDouble(y.getText()));
        vector3Type.setZ(Double.parseDouble(z.getText()));
        vector3Type.setType(type.getText());
        stage.close();
    }

    @FXML
    public void onCancelAction(ActionEvent actionEvent) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessageTitle(String messageTitle) {
        messageTitleLabel.setText(messageTitle);
    }

    public Vector3Type getVector3Type() {
        return vector3Type;
    }

    public void setVector3Type(Vector3Type vector3Type) {
        this.vector3Type = vector3Type;
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);

        Platform.runLater(() -> {
            if (vector3Type != null) {
                x.setText("" + vector3Type.getX());
                y.setText("" + vector3Type.getY());
                z.setText("" + vector3Type.getZ());
                type.setText(vector3Type.getType());
            }
        });
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTextProp(x);
        setTextProp(y);
        setTextProp(z);

    }

    private void setTextProp(TextField textField) {
        StringConverter<Double> doubleStringConverter = getDoubleStringConverter();
        textField.setTextFormatter(new TextFormatter<>(doubleStringConverter, 0.0, getFilter()));
    }


    private StringConverter<Double> getDoubleStringConverter() {
        return new StringConverter<Double>() {

            @Override
            public String toString(Double object) {
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                if (string.isEmpty() || ".".equals(string) || "-".equals(string) || "-.".equals(string)) {
                    return 0.0;
                } else {
                    return Double.parseDouble(string);
                }
            }

        };

    }

    private UnaryOperator<TextFormatter.Change> getFilter() {
        return t -> {

            String newText = t.getControlNewText();
            if (newText.matches("-?[0-9]*\\.?[0-9]*")) {
                return t;
            }
            return null;
        };

    }

}
