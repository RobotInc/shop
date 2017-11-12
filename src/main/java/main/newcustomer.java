package main;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class newcustomer implements EventHandler<ActionEvent> {

    @FXML
    TextField name, gstin, address, phone;

    @FXML
    ChoiceBox<String> isregistered;

    @FXML
    ComboBox state;

    @FXML
    Label namestatus, addressstatus, gstinstatus, phonestatus, tradelabel, addresslabel, phonelabel, gstlabel;

    @FXML
    Button clear, addcustomer;

    boolean registered = true;
    boolean f1 = false, f2 = false, f3 = false, f4 = false;

    final int gstinLength = 15;
    final int panlength = 10, phonelength = 10;
    final int aadharlength = 12;

    @FXML
    public void initialize() {
        addcustomer.setDisable(true);
        clear.setOnAction(this);
        addcustomer.setOnAction(this);
        name.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (helper.isEmpty(name)) {
                    f1 = false;
                    setEmptyLabelText(namestatus, tradelabel);

                } else if (helper.getLength(name) < 2) {
                    f1 = false;
                    setLengthError(namestatus, tradelabel);

                } else {

                    f1 = true;
                    setCorrect(namestatus);
                }
                setAddCustomerStatus();
            }
        });
        gstin.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (helper.isEmpty(gstin)) {
                    f2 = false;
                    setEmptyLabelText(gstinstatus, gstlabel);

                } else {
                    if (isregistered.getSelectionModel().getSelectedIndex() == 0) {
                        if (helper.getLength(gstin) != gstinLength) {
                            f2 = false;
                            gstinstatus.setText("GSTIN number lenght should be 15");
                            gstinstatus.setTextFill(Color.web("#ff0019"));
                            gstin.getText().toUpperCase();

                        } else {


                            f2 = true;
                            gstin.getText().toUpperCase();
                            setCorrect(gstinstatus);
                        }
                    } else if (isregistered.getSelectionModel().getSelectedIndex() == 1) {
                        if (helper.getLength(gstin) == aadharlength || helper.getLength(gstin) == panlength) {

                            f2 = true;
                            setCorrect(gstinstatus);
                            gstin.getText().toUpperCase();
                        } else {
                            f2 = false;
                            gstinstatus.setText("Aadhar/pan length should be 12/10");
                            gstinstatus.setTextFill(Color.web("#ff0019"));
                            gstin.getText().toUpperCase();

                        }
                    }
                }
                setAddCustomerStatus();
            }
        });

        address.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(state.getSelectionModel().getSelectedIndex());
                if (helper.isEmpty(address)) {
                    f3 = false;
                    setEmptyLabelText(addressstatus, addresslabel);

                } else if (helper.getLength(address) < 2) {
                    f3 = false;
                    System.out.println("am inside length");
                    setLengthError(addressstatus, addresslabel);

                } else if(state.getSelectionModel().getSelectedIndex()==-1){
                    f3 = false;
                    addressstatus.setText("Please Select the State");
                    addressstatus.setTextFill(Color.web("#ff0019"));
                }
                else {

                    f3 = true;
                    setCorrect(addressstatus);
                }
                setAddCustomerStatus();
            }
        });
        phone.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    phone.setText(newValue.replaceAll("[^\\d]", ""));
                }

                if (helper.isEmpty(phone)) {
                    f4 = false;
                    setEmptyLabelText(phonestatus, phonelabel);

                } else if (helper.getLength(phone) == phonelength) {

                    f4 = true;
                    setCorrect(phonestatus);
                } else {
                    f4 = false;
                    phonestatus.setText("Phone number length should be 10");
                    phonestatus.setTextFill(Color.web("#ff0019"));

                }
                setAddCustomerStatus();
            }
        });
        setEmptyLabelText(namestatus, tradelabel);
        setEmptyLabelText(gstinstatus, gstlabel);
        setEmptyLabelText(addressstatus, addresslabel);
        setEmptyLabelText(phonestatus, phonelabel);
        isregistered.setItems(FXCollections.observableArrayList("Registered", "Un Registered"));
        isregistered.getSelectionModel().selectFirst();
        isregistered.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Registered")) {
                    registered = true;
                    gstlabel.setText("GSTIN Number");
                    gstin.setPromptText("eg 37PMGSTGT36Z1");
                    if (helper.isEmpty(gstin)) {
                        f2 = false;
                        setEmptyLabelText(gstinstatus, gstlabel);
                    }
                    if (helper.getLength(gstin) != gstinLength) {
                        f2 = false;
                        gstinstatus.setText("GSTIN number lenght should be 15");
                        gstinstatus.setTextFill(Color.web("#ff0019"));

                    } else {


                        f2 = true;
                        setCorrect(gstinstatus);
                    }


                } else if (String.valueOf(newValue).equals("Un Registered")) {
                    registered = false;
                    gstlabel.setText("Aadhar/Pan Number");
                    gstin.setPromptText("eg 832152313225/AAAPL1234C");
                    if (helper.isEmpty(gstin)) {
                        f2 = false;
                        setEmptyLabelText(gstinstatus, gstlabel);
                    }
                    if (helper.getLength(gstin) == aadharlength || helper.getLength(gstin) == panlength) {

                        f2 = true;
                        setCorrect(gstinstatus);
                    } else {
                        f2 = false;
                        gstinstatus.setText("Aadhar/pan length should be 12/10");
                        gstinstatus.setTextFill(Color.web("#ff0019"));


                    }
                }
                setAddCustomerStatus();
            }
        });

        ObservableList<String> statecodes = FXCollections.observableArrayList();

        for (String names : helper.getStatecode().keySet()) {
            statecodes.add(names);

        }

        //state.getSelectionModel().select("None");
        state.setPromptText("Select State");
        state.getItems().addAll(statecodes);
        new SelectKeyComboBoxListener(state);
        state.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(state.getSelectionModel().getSelectedIndex());
               if (helper.isEmpty(address)) {
                    f3 = false;
                    setEmptyLabelText(addressstatus, addresslabel);

                } else if (helper.getLength(address) < 2) {
                    f3 = false;
                    setLengthError(addressstatus, addresslabel);

                } else if(state.getSelectionModel().getSelectedIndex()==-1){
                    f3 = false;
                    addressstatus.setText("Please Select the State");
                    addressstatus.setTextFill(Color.web("#ff0019"));

                }
                else {

                    f3 = true;
                    setCorrect(addressstatus);
                }
                setAddCustomerStatus();
            }
        });




    }

    public void handle(ActionEvent event) {
        if (((Button) event.getSource()).getId() == clear.getId()) {
            name.clear();
            gstin.clear();
            address.clear();
            phone.clear();
            setEmptyLabelText(namestatus, tradelabel);
            setEmptyLabelText(gstinstatus, gstlabel);
            setEmptyLabelText(addressstatus, addresslabel);
            setEmptyLabelText(phonestatus, phonelabel);
            state.getSelectionModel().clearSelection();
        } else if (((Button) event.getSource()).getId() == addcustomer.getId()) {
            String status = database.addNewCustomer(name.getText(), registered, gstin.getText(), state.getSelectionModel().getSelectedItem().toString(),address.getText(), phone.getText());
            if (status.contains("Successfully".toLowerCase())) {
                name.clear();
                gstin.clear();
                address.clear();
                phone.clear();
                setEmptyLabelText(namestatus, tradelabel);
                setEmptyLabelText(gstinstatus, gstlabel);
                setEmptyLabelText(addressstatus, addresslabel);
                setEmptyLabelText(phonestatus, phonelabel);
                state.getSelectionModel().clearSelection();
                alert(status);
            } else {
                alert(status);
            }
        }
    }


    private void setEmptyLabelText(Label label, Label actualfield) {
        String error = "Error: " + actualfield.getText() + " cannot be empty";
        label.setText(error);
        label.setTextFill(Color.web("#ff0019"));

    }

    private void setLengthError(Label label, Label actualfield) {
        String error = "Error: " + actualfield.getText() + " check the length";
        label.setText(error);
        label.setTextFill(Color.web("#ff0019"));

    }

    private void setCorrect(Label label) {
        String success = "Perfect";
        label.setText(success);
        label.setTextFill(Color.web("#0cf700"));

    }

    private void setAddCustomerStatus() {
        if (f1 == true && f2 == true && f3 == true && f4 == true) {
            if (addcustomer.isDisabled()) {
                addcustomer.setDisable(false);
            }

        } else {
            addcustomer.setDisable(true);
        }
    }

    private void alert(String Message) {
        Alert alert = new Alert(Alert.AlertType.NONE, Message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }


}
