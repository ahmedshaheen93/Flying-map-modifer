package com.shaheen.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmMessageViewController extends DialogPane implements Initializable {
    private Stage stage;


    @FXML
    private Label messageTitleLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;


    private ConfirmType confirmType = ConfirmType.CANCEL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void onOkAction(ActionEvent actionEvent) {
        confirmType = ConfirmType.OK;
        stage.close();
    }

    public void onCancelAction(ActionEvent actionEvent) {
        confirmType = ConfirmType.CANCEL;
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessageTitle(String messageTitle) {
        messageTitleLabel.setText(messageTitle);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

}
