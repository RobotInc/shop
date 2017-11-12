package main;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.customer;

import javax.swing.text.TableView;
import java.util.ArrayList;

public class viewCustomers {
    @FXML
    javafx.scene.control.TableView<customer> cusview;
    @FXML
    public void initialize() {
        ArrayList<customer> list = database.getCustomers();
        ObservableList<customer> data = FXCollections.observableArrayList();
        if(list!=null) {
            for(customer c : list){
                data.add(c);
            }

            TableColumn id = new TableColumn("customerID");
            TableColumn name = new TableColumn("Legal/Trade Name");
            TableColumn gstin = new TableColumn("Gstin");
            TableColumn registered = new TableColumn("Registered");
            TableColumn state = new TableColumn("State");
            TableColumn address = new TableColumn("Address");
            TableColumn phone = new TableColumn("Phone");

            id.setCellValueFactory(new PropertyValueFactory<customer,Integer>("id"));

            name.setCellValueFactory(
                    new PropertyValueFactory<customer, String>("name"));

            gstin.setCellValueFactory(
                    new PropertyValueFactory<customer, String>("gstin"));
            registered.setCellValueFactory(
                    new PropertyValueFactory<customer, String>("registered"));
            state.setCellValueFactory(new PropertyValueFactory<customer,String>("state"));
            address.setCellValueFactory(
                    new PropertyValueFactory<customer, String>("address"));
            phone.setCellValueFactory(
                    new PropertyValueFactory<customer, String>("phone"));

            cusview.setItems(data);

            cusview.getColumns().addAll(id,name, gstin, registered,state, address, phone);
            cusview.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
            cusview.setEditable(true);
        }

    }
}
