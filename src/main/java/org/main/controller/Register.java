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
import org.main.dao.CardDAO;
import org.main.dao.ClientDAO;
import org.main.dao.CredentialDAO;
import org.main.dao.DefaultDAO;
import org.main.dto.CardData;
import org.main.entity.Account;
import org.main.entity.Card;
import org.main.entity.Client;
import org.main.entity.Credential;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Register {

    /* --- Для Credential---*/
    public TextField login;
    public TextField password;
    public TextField password_check;
    /*          ------      */
    private final ExecutorService pool = Executors.newFixedThreadPool(1);
    private final ExecutorService pool2 = Executors.newFixedThreadPool(1);
    public TextField number_card;
    public TextField number_passport;

    public void checkRegister(ActionEvent actionEvent) throws ExecutionException, InterruptedException, IOException {
        Credential credential=null;
        Client  client=null;
        try {
            credential = credentialPart();
            if (!number_card.getText().isBlank() && !number_passport.getText().isBlank()) {
                client = creditCheckPart(actionEvent);

            }
            else {
                throw new RuntimeException("Empty card number or passport details fields");
            }
            Credential finalCredential = credential;
            Callable<Credential> persist = () -> CredentialDAO.persistEntity(finalCredential);
            Future<Credential> submit = pool.submit(persist);
            client.setCredential(submit.get());

            Client finalClient = client;
            Runnable confirm =()->DefaultDAO.insertOrUpdate(finalClient);
            pool2.submit(confirm);

        } catch (Throwable e) {
            System.out.println(e.getMessage());
            return;
        }

        //todo Terminated part

        LoginLoader(actionEvent);

    }

    private Credential credentialPart() {

        Credential build=null;
        if (!login.getText().isBlank() && password.getText().equals(password_check.getText())
                && !password.getText().isBlank() && !password_check.getText().isBlank()) {
            build = Credential.builder().superU(false).password(password.getText())
                    .login(login.getText()).build();
            System.out.println(build);
                Optional<Credential> credential = CredentialDAO.getCredential(login.getText());
            //todo 3rd part
                if (credential.isPresent()){


                    throw new RuntimeException("User already exist");
                }
            //
        } else {
            throw new RuntimeException("No matches in password or empty login's textfield");
        }
        return build;

    }

    public Client creditCheckPart(ActionEvent actionEvent) throws RuntimeException{
        CardData byData = CardDAO.getByData(CardData.builder().number(number_card.getText()).build());
        Client byPassport = ClientDAO.getByPassport(number_passport.getText());
        System.out.println(byData);
        System.out.println(byPassport);
        DefaultDAO.rcheckMatches(byData,byPassport);
        Object collect = null;

        for (Account account:
        byPassport.getAccounts()) {
            System.out.println("IN EACH ACCS - "+account);

            collect= (Object) account.getCards().stream().filter(card -> card.getNumber().equals(byData.number)).peek(System.out::println).toList() ;

        }
        System.out.println("EXIST "+ collect);
        //collect.stream().filter(Optional::isPresent).forEach(System.out::println);
        return byPassport;

    }
    public void LoginLoader(ActionEvent actionEvent) throws IOException {
        try {
            Button source = (Button) actionEvent.getSource();
            Scene scene = source.getScene();
            Stage window = (Stage) scene.getWindow();

            FXMLLoader loader=new FXMLLoader();
            URL xmlUrl = getClass().getResource("/login.fxml");
            loader.setLocation(xmlUrl);
            Parent root =loader.load();
            Stage stage = new Stage();
            Platform.setImplicitExit(true);
            stage.setScene(new Scene(root));
            stage.show();
            window.close();
        }
        catch (Throwable e){
            e.printStackTrace();
        }
    }


    public void onLogin(ActionEvent actionEvent) throws IOException {
        Button source = (Button) actionEvent.getSource();
        Scene scene = source.getScene();
        Stage window = (Stage) scene.getWindow();
        Stage stage= new Stage();
        FXMLLoader loader=new FXMLLoader();
        URL xmlUrl = getClass().getResource("/login.fxml");
        loader.setLocation(xmlUrl);
        Parent root =loader.load();
        Platform.setImplicitExit(true);
        stage.setScene(new Scene(root));
        stage.show();
        window.close();
    }
}
