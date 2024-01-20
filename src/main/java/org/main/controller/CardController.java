package org.main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import org.main.dao.CardDAO;
import org.main.dto.CardData;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CardController implements Initializable, Serializable {
    public TableView<CardData> card_table;
    public TableColumn<CardData, String> number_column;
    public TableColumn<CardData, String> account_column;
    public TableColumn<CardData, Double> balance_column;
    public TableColumn<CardData, String> code_column;
    public TableColumn<CardData, Date> creating_column;
    public TableColumn<CardData, Date> closing_column;
    public Label number_label;
    public TextField number_text;
    public TextField account_text;
    public TextField balance_text;

    ObservableList<CardData> listClient = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        number_column.setCellValueFactory(new PropertyValueFactory<>("number"));
        account_column.setCellValueFactory(new PropertyValueFactory<>("account"));
        balance_column.setCellValueFactory(new PropertyValueFactory<>("balance"));
        code_column.setCellValueFactory(new PropertyValueFactory<>("code"));
        creating_column.setCellValueFactory(new PropertyValueFactory<>("creating"));
        closing_column.setCellValueFactory(new PropertyValueFactory<>("closing"));
        onLoad();
    }
    private void onLoad() {
        listClient.clear();
        List<CardData> all = CardDAO.getAll();
        ObservableList<CardData> columns = card_table.getItems();
        columns.addAll(all);
        card_table.setItems(columns);
    }
    public void onSaveCard(ActionEvent actionEvent) {

        var data = CardData.builder().number(number_text.getText()).account(account_text.getText()).
                balance(Double.valueOf(balance_text.toString())).build();
        var byData = CardDAO.getByData(data);
        ObservableList<CardData> columns = card_table.getItems();
        columns.add(byData);
        card_table.setItems(columns);

    }
    public void editContext(){
        account_text.setEditable(false);
        number_text.setEditable(false);
        var selectedItem = card_table.getSelectionModel().getSelectedItem();
        number_text.setText(selectedItem.getNumber());
        account_text.setText(selectedItem.getAccount());
        balance_text.setText(String.valueOf(selectedItem.getBalance()));
        System.out.println(selectedItem);
    }
    public void createRow(ActionEvent actionEvent){
        account_text.setEditable(true);
        number_text.setEditable(true);
        number_text.clear();
        account_text.clear();
        balance_text.clear();

    }
    public void deleteContext(){
        var selectedItem = card_table.getSelectionModel().getSelectedItem();
        ObservableList<CardData> columns = card_table.getItems();
        columns.remove(selectedItem);
        card_table.setItems(columns);
        System.out.println("deleted");
    }


}
