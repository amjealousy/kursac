package org.main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.main.dao.AccountDAO;
import org.main.dto.AccountData;
import org.main.entity.Account;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AccountController implements Initializable, Serializable {


    public  TableView<AccountData> account_table;
    public  TableColumn<AccountData, String> number_column;
    public  TableColumn<AccountData, String> type_column;
    public  TableColumn<AccountData, String> currencies_column;
    public  TableColumn<AccountData, String> client_column;

    public  TextField number_text;
    public  TextField type_text;
    public  TextField currencies_text;
    public  TextField client_text;
    public Label number_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        number_column.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        currencies_column.setCellValueFactory(new PropertyValueFactory<>("currencies"));
        client_column.setCellValueFactory(new PropertyValueFactory<>("client"));
        onLoad();
    }
    ObservableList<Account> listClient = FXCollections.observableArrayList();
    private void onLoad() {
        listClient.clear();
        number_text.setDisable(true);
        number_text.setVisible(false);
        number_label.setVisible(false);
        List<AccountData> all = AccountDAO.getAll();
        ObservableList<AccountData> columns = account_table.getItems();
        columns.addAll(all);
        account_table.setItems(columns);
    }

    public void onSave() throws IOException ,NumberFormatException{

        var accountData = AccountData.builder().type(type_text.getText()).currencies(currencies_text.getText()).
                uuid(number_text.getText()).build();
        var byData = AccountDAO.getByData(accountData);
        ObservableList<AccountData> columns = account_table.getItems();
        System.out.println("++++++++"+ accountData);

        columns.add(byData);
        account_table.setItems(columns);

    }
    public void editContext(){

        number_text.setVisible(true);
        number_label.setVisible(true);
        var selectedItem = account_table.getSelectionModel().getSelectedItem();
        number_text.setText(selectedItem.getUuid());
        type_text.setText(selectedItem.getType());
        currencies_text.setText(selectedItem.getCurrencies());
        client_text.setText(String.valueOf(selectedItem.getId()));
        System.out.println(selectedItem);
    }
    public void createRow(ActionEvent actionEvent){
        number_text.setVisible(false);
        number_label.setVisible(false);
        type_text.clear();
        currencies_text.clear();
        client_text.clear();

    }
    public void deleteContext(){
        var selectedItem = account_table.getSelectionModel().getSelectedItem();
        ObservableList<AccountData> columns = account_table.getItems();
        columns.remove(selectedItem);
        account_table.setItems(columns);
        System.out.println("deleted");
    }
}
