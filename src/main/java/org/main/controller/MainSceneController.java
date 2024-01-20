package org.main.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.main.FxmlMapping;
import org.main.dao.ClientDAO;
import org.main.dao.DefaultDAO;
import org.main.entity.Client;
import org.main.entity.Credential;
import org.main.entity.Degree;
import org.main.interation.ValidationTask;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class MainSceneController implements Initializable, Serializable {
    public  Label value;
    public Button creater;

    // instance of elements UI
    public Label welcomeText;
    private final ExecutorService pool = Executors.newFixedThreadPool(1);
    public Button db;
    public TableView<Client> table_client;

    public TableColumn<Client,Integer> hide_id_field;
    public TableColumn<Client,String> name_field;
    public TableColumn<Client,String> lastname_field;
    public TableColumn<Client,String> patron_field;
    public TableColumn<Client,String> dep_filed;
    public TableColumn<Client,String> passport_details_field;
    public TableColumn<Client,String> phone_field;
    public Label table_name;
    public Button save_button;
    public TextField phone_text;
    public TextField passport_details_text;
    public TextField job_text;
    public TextField patronymic_text;
    public TextField lastname_text;

    public TextField first_name_text;
    public MenuButton topdown_menu;


    public MenuItem client_check;
    public MenuItem credential_check;
    public MenuItem account_check;
    public MenuItem credit_check;
    public MenuItem creditype_check;
    public MenuItem card_check;
    public AnchorPane mainPane;
    public Pane table_pane;
    public VBox form_input;

    public MainSceneController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hide_id_field.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_field.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastname_field.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        patron_field.setCellValueFactory(new PropertyValueFactory<>("patronymic_name"));
        dep_filed.setCellValueFactory(new PropertyValueFactory<>("department"));
        passport_details_field.setCellValueFactory(new PropertyValueFactory<>("passport_details"));
        phone_field.setCellValueFactory(new PropertyValueFactory<>("phone"));

        onLoadTable();

    }
    ObservableList<Client> listClient = FXCollections.observableArrayList();
    public void onLoadTable(){
        listClient.clear();
        List<Client> all = ClientDAO.getAll();
        listClient = table_client.getItems();
        listClient.addAll(all);
        table_client.setItems(listClient);

    }
    public void onSave() throws ExecutionException, InterruptedException {

        Client client = this.fromList(List.of(first_name_text.getText(),lastname_text.getText(),patronymic_text.getText(),job_text.getText(),passport_details_text.getText(), phone_text.getText())
                , Optional.ofNullable(value.getText().isBlank()?null:Integer.valueOf(value.getText())));
        Callable<Client> task =()->{
            return DefaultDAO.insertOrUpdate(client);
        };
        String input = "";
        ValidationTask<Client> task2 = new ValidationTask<>(input,client);

        task2.valueProperty().addListener((observable,old,val)-> welcomeText.setText(String.valueOf(val)));
        StringProperty value=new SimpleStringProperty();
        Thread thread = new Thread(task2);
        thread.setDaemon(true);
        thread.start();
//        TODO update or save client
        thread.join();
        String validateMessage = task2.validateMessage;
        System.out.println("Massage is "+validateMessage);

//        if(task2.valueProperty().toString().isBlank()) {
        Future<Client> futureResult = pool.submit(task);
        var client2=futureResult.get();
//            System.out.println("Future result is : "+ client2);
        onLoadTable();
//        }

    }
    public void editContext(){
        Client selectedItem = table_client.getSelectionModel().getSelectedItem();
        value.setText(String.valueOf(selectedItem.getId()));
        first_name_text.setText(!Objects.isNull(selectedItem.getFirst_name())?selectedItem.getFirst_name():"");
        lastname_text.setText(!Objects.isNull(selectedItem.getLast_name())?selectedItem.getLast_name():"");
        patronymic_text.setText(!Objects.isNull(selectedItem.getPatronymic_name())?selectedItem.getPatronymic_name():"");
        job_text.setText(!Objects.isNull(selectedItem.getDepartment())?selectedItem.getDepartment():"");
        passport_details_text.setText(!Objects.isNull(selectedItem.getPassport_details())?selectedItem.getPassport_details():"");
        phone_text.setText(!Objects.isNull(selectedItem.getPhone())?selectedItem.getPhone():"");
        System.out.println(selectedItem);
    }
    public void deleteContext(){

        Client selectedItem = table_client.getSelectionModel().getSelectedItem();
        selectedItem.setDeleted(true);
        System.out.println(selectedItem);
        ObservableList<Client> columns = table_client.getItems();
        columns.remove(selectedItem);

        Runnable task = ()-> DefaultDAO.insertOrUpdate(selectedItem);
        pool.submit(task);
        table_client.setItems(columns);
        System.out.println("deleted");
    }

    public void Validate(ActionEvent actionEvent){
//        LocalDate birthday =LocalDate.of(2001,1,14);
//        Credential credentialNew= Credential.builder().inn("333").full_name("")
//                .address("Skuratovo").date_of_bday(birthday).job("")
//                .passport_details("2322423").phone("+7912423459").degree(Degree.Non)
//                .build();
//        String input = "";
//        System.out.println("In controller "+ Thread.currentThread().getName());
//
//        ValidationTask<Credential> task = new ValidationTask<>(input,credentialNew);
//
//        task.valueProperty().addListener((observable,old,val)-> welcomeText.setText(String.valueOf(val)));
//        Thread thread = new Thread(task);
//        thread.setDaemon(true);
//        thread.start();

    }
    public void createRow(ActionEvent actionEvent){
        value.setText("");
        first_name_text.clear();
        lastname_text.clear();
        patronymic_text.clear();
        job_text.clear();
        passport_details_text.clear();
        phone_text.clear();
    }


    public void topdownInter(ActionEvent actionEvent) throws IOException {

        MenuItem source = ((MenuItem) actionEvent.getSource());
        System.out.println("got the context");
        Scene scene = mainPane.getScene();

        Stage baseStage = (Stage) scene.getWindow();

        switch (source.getId()){
            case "account_check"-> FxmlMapping.accountLoad(this,table_pane,mainPane,form_input);
            case "credential_check"->
                System.out.println("Suspended functionality");
            case "client_check"-> FxmlMapping.clientLoad(baseStage);
            case "credit_check"->FxmlMapping.creditLoad(this,table_pane,mainPane,form_input);
            case "creditype_check"->FxmlMapping.typeCreditLoad(this,table_pane,mainPane,form_input);
            case "card_check"-> FxmlMapping.cardLoad(this,table_pane,mainPane,form_input);
        }


    }

    public <T> void txtFiled(KeyEvent actionEvent,List<TextField> list,Class<? extends Object> controller) throws  Exception {

        List<String> collect = list.stream().map((TextInputControl::getText)).toList();
        System.out.println("in event "+collect);
        Object initializable = controller.getDeclaredConstructor().newInstance();
        Method fromList = controller.getMethod("fromList", List.class,Optional.class);


        Object invoked = fromList.invoke(initializable,collect,Optional.empty());
        System.out.println("Clazz "+invoked);
        TextField source = (TextField) actionEvent.getSource();
        System.out.println(source.getId());
        String input = "";
        ValidationTask<T> task = new ValidationTask<>(input,(T)invoked,source.getId());

        task.valueProperty().addListener((observable,old,val)-> welcomeText.setText(String.valueOf(val)));
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    public Client fromList(List<String> list, Optional<Integer> value){

        return Client.builder().first_name(list.get(0)).last_name(list.get(1))
                        .patronymic_name(list.get(2)).department(list.get(3))
                    .passport_details(list.get(4)).phone(list.get(5)).id(value.orElse(null))
                .build();
    }

}
