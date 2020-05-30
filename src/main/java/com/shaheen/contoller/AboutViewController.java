package com.shaheen.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {
    @FXML
    private TextArea textAreaAbout;

    private final String aboutApp = "\tThis application is under GNU Licence .\n" +
            "This application used to modify xml for x, y, z coordinate \n" +
            "for flying path.\n" +
            "you can find the source code on github.";
    private final String appSrc = "https://github.com/ahmedshaheen93/Flying-map-modifer";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textAreaAbout.setText(aboutApp);
    }

    @FXML
    public void openBrowser(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI(appSrc));
        } catch (IOException | URISyntaxException ioException) {
            ioException.printStackTrace();
        }
    }
}
