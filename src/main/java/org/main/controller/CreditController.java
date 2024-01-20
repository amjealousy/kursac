package org.main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.main.dao.CreditDAO;
import org.main.dto.CreditData;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class CreditController implements Initializable, Serializable {

    public TableView<CreditData> credit_table;
    public TableColumn<CreditData, UUID> id_column;
    public TableColumn<CreditData, Double> value_column;
    public TableColumn<CreditData, UUID> account_column;
    public TableColumn<CreditData, Integer> type_column;

    public TextField id_text;
    public TextField value_text;
    public TextField account_text;
    public TextField type_text;
    public Label name_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id_column.setCellValueFactory(new PropertyValueFactory<>("key_id"));
        value_column.setCellValueFactory(new PropertyValueFactory<>("value"));
        account_column.setCellValueFactory(new PropertyValueFactory<>("accountCredit"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        onLoad();
    }
    ObservableList<CreditData> listClient = FXCollections.observableArrayList();
    private void onLoad() {
        listClient.clear();
        id_text.setDisable(true);
        id_text.setVisible(false);
        name_label.setVisible(false);
        List<CreditData> all = CreditDAO.getAll();
        ObservableList<CreditData> columns = credit_table.getItems();
        columns.addAll(all);
        credit_table.setItems(columns);
    }
    public void onSave(ActionEvent actionEvent){


    }
    public void createRow(ActionEvent actionEvent){
    System.out.println("createRow on CreditController");

    }
    public void editContext(ActionEvent actionEvent) {
    }

    public void deleteContext(ActionEvent actionEvent) {
    }
}
