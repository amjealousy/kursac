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
import org.main.dao.AccountDAO;
import org.main.dao.TypeCreditDAO;
import org.main.dto.AccountData;
import org.main.entity.Account;
import org.main.entity.TypeCredit;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreditTypeController implements Initializable, Serializable {
    public TableView<TypeCredit> creditype_table;
    public TableColumn<TypeCredit,String> name_column;
    public TableColumn<TypeCredit,String> condition_column;
    public TableColumn<TypeCredit,Double> interest_column;
    public TableColumn<TypeCredit,Integer> repay_column;

    public TextField name_text;
    public TextField condition_text;
    public TextField interest_text;
    public TextField repay_text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        condition_column.setCellValueFactory(new PropertyValueFactory<>("condition"));
        interest_column.setCellValueFactory(new PropertyValueFactory<>("interest_rate"));
        repay_column.setCellValueFactory(new PropertyValueFactory<>("repayment_period"));
        onLoad();
    }
    ObservableList<TypeCredit> listClient = FXCollections.observableArrayList();
    private void onLoad() {
        listClient.clear();
        List<TypeCredit> all = TypeCreditDAO.getAll();
        ObservableList<TypeCredit> columns = creditype_table.getItems();
        columns.addAll(all);
        creditype_table.setItems(columns);
    }
    public void onSave(ActionEvent actionEvent) {

//        var accountData = AccountData.builder().type(type_text.getText()).currencies(currencies_text.getText()).
//                uuid(number_text.getText()).build();
//        var byData = AccountDAO.getByData(accountData);
//        ObservableList<AccountData> columns = account_table.getItems();
//        System.out.println("++++++++"+ accountData);
//
//        columns.add(byData);
//        account_table.setItems(columns);

    }
    public void editContext(ActionEvent actionEvent) {
        System.out.println("editContext");
    }


    public void deleteContext(ActionEvent actionEvent) {
    }

    public void createRow(ActionEvent actionEvent) {
    }
}
