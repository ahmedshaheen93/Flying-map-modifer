package com.shaheen.contoller;

import com.shaheen.model.GathererProfileType;
import com.shaheen.model.Vector3Type;
import com.shaheen.utiles.ReadWriteXmlJXB;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class MainViewController implements Initializable {
    @FXML
    public TextField filePathTextField;
    @FXML
    public TableView<Vector3Type> flyingTable;

    @FXML
    public TableColumn x;
    @FXML
    public TableColumn y;
    @FXML
    public TableColumn z;
    @FXML
    public TableColumn type;
    @FXML
    public TextField searchText;


    ObservableList<Vector3Type> vector3TypeList = FXCollections.observableArrayList();
    ListProperty<Vector3Type> vector3TypeListProperty = new SimpleListProperty<>();


    private Stage stage;
    private ReadWriteXmlJXB readWriteXmlJXB = new ReadWriteXmlJXB();
    private GathererProfileType gathererProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void selectFile(ActionEvent actionEvent) {
        final File xmlFile = chooseXmlFile();
        if (xmlFile != null) {
            filePathTextField.setText(xmlFile.getAbsolutePath());
            vector3TypeList.clear();
            new Thread(() -> {
                try {
                    JAXBElement rootElement = readWriteXmlJXB.readXmlFileRootElement(xmlFile);
                    gathererProfile = (GathererProfileType) rootElement.getValue();

                    handelTableView(gathererProfile);
                } catch (JAXBException e) {
                    e.printStackTrace();
                    // view error message for user
                }
            }).start();
        }
    }

    @FXML
    public void saveCurrentFile(ActionEvent actionEvent) {
        AtomicReference<File> saveXmlFile = new AtomicReference<>(saveXmlFile());
        gathererProfile.getVectors3().getVector3().clear();
        vector3TypeList.forEach(vector3Type -> {
            gathererProfile.getVectors3().getVector3().add(vector3Type);
        });
        Platform.runLater(() -> {
                    if (!FilenameUtils.getExtension(saveXmlFile.get().getName()).equalsIgnoreCase("xml")) {
                        saveXmlFile.set(new File(saveXmlFile.toString() + ".xml"));  // append .xml if "foo.jpg.xml" is OK
                        saveXmlFile.set(new File(saveXmlFile.get().getParentFile(), FilenameUtils.getBaseName(saveXmlFile.get().getName()) + ".xml")); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
                    }
                    try {
                        readWriteXmlJXB.writeXmlFile(saveXmlFile.get(), gathererProfile);
                    } catch (JAXBException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void handelTableView(GathererProfileType gathererProfile) {
        List<Vector3Type> vector3 = gathererProfile.getVectors3().getVector3();
        viewTable(vector3);
    }

    private void viewTable(List<Vector3Type> vector3Types) {
        vector3TypeListProperty.set(vector3TypeList);
        flyingTable.itemsProperty().bindBidirectional(vector3TypeListProperty);
        flyingTable.setItems(vector3TypeListProperty);
        Platform.runLater(() -> {
            for (Vector3Type vector3Type : vector3Types) {
                vector3TypeList.add(vector3Type);
            }
            FilteredList<Vector3Type> filteredData = new FilteredList<>(vector3TypeList, p -> true);

            searchTextListener(filteredData);

            SortedList<Vector3Type> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().
                    bind(flyingTable.comparatorProperty());
            flyingTable.setItems(sortedData);

        });
        setDataView();
    }

    private void setDataView() {
        x.setCellValueFactory(new PropertyValueFactory("x"));
        y.setCellValueFactory(new PropertyValueFactory("y"));
        z.setCellValueFactory(new PropertyValueFactory("z"));
        type.setCellValueFactory(new PropertyValueFactory("type"));
    }

    private void searchTextListener(FilteredList<Vector3Type> filteredData) {
        searchText.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(vector3Type -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return ("" + vector3Type.getX()).contains(lowerCaseFilter)
                            ||
                            ("" + vector3Type.getY()).contains(lowerCaseFilter)
                            ||
                            ("" + vector3Type.getZ()).contains(lowerCaseFilter)
                            || (vector3Type.getType() != null && vector3Type.getType().contains(lowerCaseFilter));
                }));
    }

    private File chooseXmlFile() {
        FileChooser fileChooser = createFileChooser("choose xml file");
        return fileChooser.showOpenDialog(stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onTableClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            int selectedIndex = flyingTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Vector3Type vector3Type = vector3TypeList.get(selectedIndex);
                Vector3Type modified = viewSelectedValue(vector3Type);
                if (modified != null) {
                    vector3TypeList.set(selectedIndex, modified);
                    flyingTable.refresh();
                }
            }
        }
    }

    private Vector3Type viewSelectedValue(Vector3Type vector3Type) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/templates/ModifyAttr.fxml"));
        try {
            Parent root = loader.load();
            ModifyAttrViewController controller = loader.getController();
            controller.setMessageTitle("modify current selected");
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.setVector3Type(vector3Type);
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.stage);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.getConfirmType().equals(ConfirmType.OK)) {
                return controller.getVector3Type();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File saveXmlFile() {
        FileChooser fileChooser = createFileChooser("Save xml file");
        return fileChooser.showSaveDialog(stage);
    }

    private FileChooser createFileChooser(String tile) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(tile);
        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        String userHomePath = System.getProperties().getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomePath));
        return fileChooser;
    }


    public void newAction(ActionEvent actionEvent) {

    }

    public void updateAction(ActionEvent actionEvent) {

    }

    public void deleteAction(ActionEvent actionEvent) {

    }

    public void insertAction(ActionEvent actionEvent) {

    }

    public void onAbout(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/templates/About.fxml"));
        try {
            Parent root = loader.load();
            loadStage(new Scene(root), Modality.WINDOW_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStage(Scene scene, Modality modality) {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.initModality(modality);
        stage.initOwner(this.stage);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onAboutMe(ActionEvent actionEvent) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setUserStyleSheetLocation(getClass().getResource("/css/myprofile.css").toString());
        webEngine.load(getClass().getResource("/templates/myCv.html").toString());
        Scene scene = new Scene(webView);
        loadStage(scene, Modality.WINDOW_MODAL);
    }
}
