package org.main;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.main.entity.Credential;
import org.main.entity.Degree;

import java.time.LocalDate;

public class MainSceneController {
    public Label welcomeText;

    public void onHelloButtonClick(ActionEvent actionEvent) {
        LocalDate birthday =LocalDate.of(2001,1,14);
        Credential credentialNew=Credential.builder().inn("333").full_name("Koresh")
                .address("Skuratovo").date_of_bday(birthday).job("vibe generator")
                .passport_details("2322423").phone("+7912423459").degree(Degree.Non1)
                .build();
        Starter starter= new Starter();
        starter.persistEntity(credentialNew);
        System.out.println("in javafx app ");
    }
}
