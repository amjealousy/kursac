package org.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root =loader.load();
//        Button button =new Button();
//
//        button.setText("context");
//        button.setPrefWidth(80);
//        Pane pane = new Pane();
//        pane.getChildren().add(button);
//        Scene scene= new Scene(pane);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}