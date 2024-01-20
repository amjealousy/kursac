package org.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.main.controller.MainSceneController;
import org.main.controller.Register;
import org.main.entity.Client;
import org.main.hibernateconfig.HibernateUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main extends Application {
    public HibernateUtils hibernateUtils;
    @Override
    public void init() throws Exception {
        super.init();
        hibernateUtils= new HibernateUtils();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/registary.fxml");
        loader.setLocation(xmlUrl);

        Parent root =loader.load();
        Platform.setImplicitExit(true);
        loader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}