package com.shaheen;

import com.shaheen.contoller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/templates/MainView.fxml"));
        Parent root = loader.load();

        MainViewController controller = loader.getController();

//        serverController.setServerController(controller);
//        controller.setServerController(serverController);
        controller.setStage(stage);
        stage.setTitle("Flying map modifier");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
