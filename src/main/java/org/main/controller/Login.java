package org.main.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.main.dao.CredentialDAO;
import org.main.dao.DefaultDAO;
import org.main.entity.Client;
import org.main.entity.Credential;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.main.dao.CredentialDAO.*;

public class Login {
    public TextField login;
    public TextField password;

    public void onEnter(ActionEvent actionEvent) throws IOException {
        if(!login.getText().isBlank()&&!password.getText().isBlank()){
            Optional<Credential> credential = get(Credential.builder()
                    .login(login.getText())
                    .password(password.getText())
                    .build());
            if (credential.isPresent()){
                if(credential.get().getSuperU()){
                    this.adminContextLoader(actionEvent);
                }else {
                    this.readOnlyContextLoader(actionEvent,credential.get());
                }
            }
        }


    }
    public void readOnlyContextLoader(ActionEvent actionEvent,Credential login) throws IOException {
        Button source = (Button) actionEvent.getSource();
        Scene scene = source.getScene();
        Stage window = (Stage) scene.getWindow();

        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/profile.fxml");
        loader.setLocation(xmlUrl);
        Parent root =loader.load();
        Profile controller = loader.getController();
        controller.welcome.setText(controller.welcome.getText()+login.getLogin());
        Object fullInfo = (Object)getFullInfo(login);
        System.out.println("readOnlyContextLoader ->" +fullInfo);
        controller.info.setText(fullInfo.toString());

        Stage stage = new Stage();

        Platform.setImplicitExit(true);
        stage.setScene(new Scene(root));

        stage.show();
        window.close();
    }
    public void adminContextLoader(ActionEvent actionEvent) throws IOException {
        Button source = (Button) actionEvent.getSource();
        Scene scene = source.getScene();
        Stage window = (Stage) scene.getWindow();

        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
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
        Stage stage = new Stage();
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
        stage.setScene(new Scene(root));

        stage.show();
        window.close();
    }

    public void onRegister(ActionEvent actionEvent) throws IOException {
        Button source = (Button) actionEvent.getSource();
        Scene scene = source.getScene();
        Stage window = (Stage) scene.getWindow();
        Stage stage= new Stage();
        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/registary.fxml");
        loader.setLocation(xmlUrl);
        Parent root =loader.load();
        Platform.setImplicitExit(true);
        stage.setScene(new Scene(root));
        stage.show();
        window.close();
    }
}
