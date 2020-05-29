package com.shaheen.contoller;

import com.shaheen.utiles.ReadWriteXml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    public TextField filePathTextField;

    private Stage stage;
    ReadWriteXml readWriteXml = new ReadWriteXml();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void selectFile(ActionEvent actionEvent) {
        File xmlFile = chooseXmlFile();
        if (xmlFile != null) {
            new Thread(() -> {
                Document document = readWriteXml.readSourceDocument(xmlFile);
            });
        }

    }

    private File chooseXmlFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose xml file");
        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        String userHomePath = System.getProperties().getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomePath));
        return fileChooser.showOpenDialog(stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
