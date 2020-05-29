package com.shaheen.contoller;

import com.shaheen.model.Vector3Type;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Spinner<Double> x;
    @FXML
    private Spinner<Double> y;
    @FXML
    private Spinner<Double> z;
    @FXML
    private TextField type;

    private Vector3Type vector3Type;

    private ConfirmType confirmType = ConfirmType.CANCEL;

    @FXML
    public void onOkAction(ActionEvent actionEvent) {
        vector3Type.setX(x.getValue());
        vector3Type.setY(y.getValue());
        vector3Type.setZ(z.getValue());
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
                x.getValueFactory().setValue(vector3Type.getX());
                y.getValueFactory().setValue(vector3Type.getY());
                z.getValueFactory().setValue(vector3Type.getZ());
                type.setText(vector3Type.getType());
            }
        });
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setSpinnerViewProp(x);
        setSpinnerViewProp(y);
        setSpinnerViewProp(z);


    }

    private void setSpinnerViewProp(Spinner<Double> spinner) {
        SpinnerValueFactory<Double> valueFactory = getValueFactory();
        StringConverter<Double> doubleStringConverter = getDoubleStringConverter();
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);
        valueFactory.setConverter(doubleStringConverter);
        spinner.getEditor().setTextFormatter(new TextFormatter<>(doubleStringConverter, 0.0, getFilter()));

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
                    return new Double(string);
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

    private SpinnerValueFactory<Double> getValueFactory() {
        return new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.0, 0.0001);

    }

}
