package org.main;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.main.controller.*;
import org.main.entity.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class FxmlMapping {
    public  static void accountLoad(MainSceneController main, Pane current, AnchorPane mainPane, VBox form_input) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FxmlMapping.class.getResource("/accountScene.fxml"));

            main.table_name.setText("Счета клиентов");
            AccountController accountController=new AccountController();

            fxmlLoader.setController(accountController);
            main.creater.setOnAction(accountController::createRow);
            AnchorPane parent = fxmlLoader.load();
            System.out.println(parent);
            TableView table = (TableView) parent.getChildren().get(0);
            HBox node1 = (HBox) parent.getChildren().get(1);
            HBox node2 = (HBox)parent.getChildren().get(2);
            HBox node3 = (HBox)parent.getChildren().get(3);
            HBox node4 = (HBox)parent.getChildren().get(4);
            Button button = (Button)parent.getChildren().get(5);


            System.out.println(current.getChildren());
            current.getChildren().set(0,table);
            System.out.println(form_input.getChildren());
            form_input.getChildren().set(0,node1);
            form_input.getChildren().set(1,node2);
            form_input.getChildren().set(2,node3);
            form_input.getChildren().set(3,node4);
            form_input.getChildren().get(4).setVisible(false);
            form_input.getChildren().get(5).setVisible(false);
            Button node = (Button)form_input.getChildren().get(6);
            form_input.getChildren().set(6,button);
            form_input.getChildren().get(6).setTranslateX(100);
            System.out.println(form_input.getChildren());
            main.table_name.setText("Счета клиентов");
        }catch (Exception E){
            System.out.println("Cant load ");
        }

    }
    public static void clientLoad(Stage baseStage)  throws IOException{
        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = FxmlMapping.class.getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root =loader.load();
        MainSceneController controller = loader.getController();
        List<Node> collect =  controller.form_input.getChildren().stream().filter(t->t instanceof HBox).toList();
        List<TextField> list=new ArrayList<>();
        collect.forEach(
                (Node t)-> {
                    if(t instanceof HBox th){
                        Optional<Node> collect1 = th.getChildren().stream()
                                .filter(tf -> tf instanceof TextField).findFirst();
                        System.out.println("text "+collect1);
                        collect1.ifPresent((a)->list.add((TextField) a));
                    }});

        System.out.println(list);
        list.forEach(
                (a)->a.setOnKeyPressed((event)-> {
                    try {
                        controller.<Client>txtFiled(event,list,controller.getClass());
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                })

        );

        Platform.setImplicitExit(true);
        baseStage.setScene(new Scene(root));
        baseStage.show();

    }
    public  static void cardLoad(MainSceneController main, Pane current, AnchorPane mainPane, VBox form_input) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FxmlMapping.class.getResource("/cardScene.fxml"));
            CardController accountController=new CardController();
            fxmlLoader.setController(accountController);
            main.creater.setOnAction(accountController::createRow);
            main.save_button.setOnAction(accountController::onSaveCard);
            main.table_name.setText("Карты клиентов");
            AnchorPane parent = fxmlLoader.load();

            System.out.println(parent);
            TableView table = (TableView) parent.getChildren().get(0);
            HBox node1 = (HBox) parent.getChildren().get(1);
            HBox node2 = (HBox)parent.getChildren().get(2);
            HBox node3 = (HBox)parent.getChildren().get(3);


            System.out.println(current.getChildren());
            current.getChildren().set(0,table);
            System.out.println(form_input.getChildren());
            form_input.getChildren().set(0,node1);
            form_input.getChildren().set(1,node2);
            form_input.getChildren().set(2,node3);
            form_input.getChildren().get(3).setVisible(false);
            form_input.getChildren().get(4).setVisible(false);
            form_input.getChildren().get(5).setVisible(false);
            System.out.println(form_input.getChildren());

        }catch (Exception E){
            System.out.println("Can`t loaded cardLoad");
        }

    }
    public  static void typeCreditLoad(MainSceneController main, Pane current, AnchorPane mainPane, VBox form_input) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FxmlMapping.class.getResource("/creditype.fxml"));
            CreditTypeController controller=new CreditTypeController();
            fxmlLoader.setController(controller);
            main.creater.setOnAction(controller::createRow);
            main.save_button.setOnAction(controller::onSave);

            AnchorPane parent = fxmlLoader.load();

            main.table_name.setText("Типы кредитов");
            System.out.println(parent);
            TableView table = (TableView) parent.getChildren().get(0);
            HBox node1 = (HBox) parent.getChildren().get(1);
            HBox node2 = (HBox)parent.getChildren().get(2);
            HBox node3 = (HBox)parent.getChildren().get(3);
            HBox node4 = (HBox)parent.getChildren().get(4);


            System.out.println(current.getChildren());
            current.getChildren().set(0,table);
            System.out.println(form_input.getChildren());
            form_input.getChildren().set(0,node1);
            form_input.getChildren().set(1,node2);
            form_input.getChildren().set(2,node3);
            form_input.getChildren().set(2,node4);
            form_input.getChildren().get(4).setVisible(false);
            form_input.getChildren().get(5).setVisible(false);
            System.out.println(form_input.getChildren());

        }catch (Exception E){
            System.out.println("Can`t loaded typeCreditLoad");
        }

    }
    public  static void creditLoad(MainSceneController main, Pane current, AnchorPane mainPane, VBox form_input) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FxmlMapping.class.getResource("/credit.fxml"));
            CreditController controller=new CreditController();
            fxmlLoader.setController(controller);
            main.creater.setOnAction(controller::createRow);
            main.save_button.setOnAction(controller::onSave);

            AnchorPane parent = fxmlLoader.load();

            main.table_name.setText("Список кредитов");
            System.out.println(parent);
            TableView table = (TableView) parent.getChildren().get(0);
            HBox node1 = (HBox) parent.getChildren().get(1);
            HBox node2 = (HBox)parent.getChildren().get(2);
            HBox node3 = (HBox)parent.getChildren().get(3);
            HBox node4 = (HBox)parent.getChildren().get(4);

            System.out.println(current.getChildren());
            current.getChildren().set(0,table);
            System.out.println(form_input.getChildren());
            form_input.getChildren().set(0,node1);
            form_input.getChildren().set(1,node2);
            form_input.getChildren().set(2,node3);
            form_input.getChildren().set(2,node4);
            form_input.getChildren().get(4).setVisible(false);
            form_input.getChildren().get(5).setVisible(false);
            System.out.println(form_input.getChildren());

        }catch (Exception E){
            System.out.println("Can`t loaded creditLoad");
        }

    }

}
