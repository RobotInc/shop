package main;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import main.model.customer;
import main.model.item;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class newinvoice {

    @FXML
    DatePicker setdate;

    @FXML
    ChoiceBox<String> customers1,customers2;

    @FXML
    Button clear, priview, generate;

    @FXML
    TextField invoiceno, transport, transportmode, state1, statecode1,state2,statecode2,phoneNo1,phoneNo2,gstin1,gstin2,address1,address2,exp;

    @FXML
    TableView<item> tableview;

    @FXML
    Label invoicetotal;

    String selectedCustomer = "";
    String selectedProduct = "";


    ArrayList<customer> customerlist = new ArrayList<customer>();
    ArrayList<String> productlist = new ArrayList<String>();

    ObservableList<item> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        customerlist = database.getCustomers();
        productlist = database.getproductList();
        ObservableList<String> customerdata = FXCollections.observableArrayList();
        ObservableList<String> productdata= FXCollections.observableArrayList();

        for(customer c : customerlist){
            customerdata.add(c.getName());
        }
        for(String p : productlist){
            productdata.add(p);
        }

        customers1.setItems(customerdata);
        customers2.setItems(customerdata);


        customers1.getSelectionModel().selectFirst();
        customers2.getSelectionModel().selectFirst();





       setBillto();
       setShipTo();
        customers1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                setBillto();
            }
        });
        customers2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                setShipTo();
            }
        });

        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        setdate.setValue(localDate);



        TableColumn<item,String> productName = new TableColumn("Product");
        TableColumn<item,String> baleNo = new TableColumn("Bale No");
        TableColumn<item,String> quantity = new TableColumn("QTY");
        TableColumn<item,String> price = new TableColumn("Price");
        TableColumn<item,String> total = new TableColumn<>("Total");
        TableColumn<item,String> gsttaxvalue = new TableColumn("GST TAX 5%");
        TableColumn<item,String> totalgst = new TableColumn("Total with GST");


        productName.setCellValueFactory(new PropertyValueFactory<item,String>("product"));
        productName.setCellFactory(ComboBoxTableCell.forTableColumn(productdata));
        productName.setStyle("-fx-alignment: CENTER;");
        baleNo.setCellValueFactory(new PropertyValueFactory<item,String>("baleNo"));
        baleNo.setCellFactory(TextFieldTableCell.<item> forTableColumn());
        baleNo.setStyle("-fx-alignment: CENTER;");
        quantity.setCellValueFactory(new PropertyValueFactory<item,String>("qty"));
        quantity.setCellFactory(TextFieldTableCell.<item> forTableColumn());
        quantity.setStyle("-fx-alignment: CENTER;");
        gsttaxvalue.setCellValueFactory(new PropertyValueFactory<item,String>("gstTax"));
        gsttaxvalue.setStyle("-fx-alignment: CENTER-RIGHT;");
        price.setCellValueFactory(new PropertyValueFactory<item,String>("price"));
        price.setCellFactory(TextFieldTableCell.<item> forTableColumn());
        price.setStyle("-fx-alignment: CENTER;");

        totalgst.setCellValueFactory(new PropertyValueFactory<item,String>("totalgst"));
        totalgst.setStyle("-fx-alignment: CENTER-RIGHT;");
        total.setCellValueFactory(new PropertyValueFactory<item,String>("total"));
        total.setStyle("-fx-alignment: CENTER-RIGHT;");

        quantity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<item, String> event) {
                TablePosition<item, String> pos = event.getTablePosition();

                String qty = event.getNewValue();

                int row = pos.getRow();
                item i = event.getTableView().getItems().get(row);

                i.setQty(qty);
                int q = Integer.parseInt(qty);
                double p = Double.parseDouble(i.getPrice());
                double tax = ((q*p)*5)/100;
                double t = (q*p)+tax;
                i.setGstTax(String.valueOf(tax));
                i.setTotalgst(String.valueOf(t));
                i.setTotal(String.valueOf(q*p));
                double totalinvoice = 0;
                for(item it:items){
                    totalinvoice =totalinvoice + Double.parseDouble(it.getTotalgst());
                    System.out.println(it.getGstTax());
                    System.out.println(it.getTotalgst());
                }

                invoicetotal.setText("Invoice Total: "+String.valueOf(totalinvoice));
                tableview.refresh();
            }
        });
        productName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<item, String> event) {
                TablePosition<item, String> pos = event.getTablePosition();

                String product = event.getNewValue();
                int row = pos.getRow();
                item i = event.getTableView().getItems().get(row);

                i.setProduct(product);
                tableview.refresh();
            }
        });

        price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<item, String> event) {
                TablePosition<item, String> pos = event.getTablePosition();

                String price = event.getNewValue();
                int row = pos.getRow();
                item i = event.getTableView().getItems().get(row);

                i.setPrice(price);
                int q = Integer.parseInt(i.getQty());
                double p = Double.parseDouble(price);
                double tax = ((q*p)*5)/100;
                double t = (q*p)+tax;
                i.setGstTax(String.valueOf(tax));
                i.setTotalgst(String.valueOf(t));
                i.setTotal(String.valueOf(q*p));
                double totalinvoice = 0;
                for(item it:items){
                    totalinvoice =totalinvoice + Double.parseDouble(it.getTotalgst());
                    System.out.println(it.getGstTax());
                    System.out.println(it.getTotalgst());
                }

                invoicetotal.setText("Invoice Total: "+String.valueOf(totalinvoice));
                tableview.refresh();

            }
        });

        baleNo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<item, String> event) {
                TablePosition<item, String> pos = event.getTablePosition();
                String baleno = event.getNewValue();
                int row = pos.getRow();
                item i = event.getTableView().getItems().get(row);
                i.setBaleNo(baleno);
            }
        });

        tableview.setItems(items);
        tableview.getColumns().addAll(productName,baleNo,quantity,price,total,gsttaxvalue,totalgst);
        tableview.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        tableview.setEditable(true);



        tableview.setRowFactory(new Callback<TableView<item>, TableRow<item>>() {
            @Override
            public TableRow<item> call(TableView<item> param) {

                final TableRow<item> row = new TableRow<>();
                ContextMenu menu = new ContextMenu();
                MenuItem add = new MenuItem("Add Item");
                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addNewRow();
                    }
                });
                MenuItem delete = new MenuItem("delete Item");
                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items.remove(row.getIndex());
                        tableview.refresh();
                    }
                });
                menu.getItems().addAll(add,delete);
                if (items.size()<2){
                    delete.setDisable(true);
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu)null)
                                    .otherwise(menu)
                    );
                }else {
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu)null)
                                    .otherwise(menu)
                    );
                }

                return row;
            }
        });

        addNewRow();

    }

    @FXML
    public void handlegenerateinvoice(){
        ArrayList<item> i = new ArrayList<>();
        i.addAll(items);

        createInvoice inv = new createInvoice(setdate.getValue().toString(),customers1.getSelectionModel().getSelectedItem(),gstin1.getText(),address1.getText(),state1.getText(),statecode1.getText(),phoneNo1.getText(),customers2.getSelectionModel().getSelectedItem(),address2.getText(),gstin2.getText(),state2.getText(),statecode2.getText(),phoneNo2.getText(),transport.getText(),transportmode.getText(),invoiceno.getText(),i,exp.getText());
        inv.createPDF();
    }


    private void setBillto(){
        String selectedCustomer = customers1.getSelectionModel().getSelectedItem();
        customer c = database.getCustomerDetails(selectedCustomer);
        address1.setText(c.getAddress());
        state1.setText(c.getState());
        statecode1.setText(helper.getCode(c.getState()));
        phoneNo1.setText(c.getPhone());
        gstin1.setText(c.getGstin());

    }

    private void addNewRow(){
        item i = new item("none","0","0","0","0","0","0");
        items.add(i);
        tableview.refresh();
    }

    private void setShipTo(){
        String selectedCustomer = customers2.getSelectionModel().getSelectedItem();
        customer c = database.getCustomerDetails(selectedCustomer);

        address2.setText(c.getAddress());
        state2.setText(c.getState());
        statecode2.setText(helper.getCode(c.getState()));
        phoneNo2.setText(c.getPhone());
        gstin2.setText(c.getGstin());
    }
}
