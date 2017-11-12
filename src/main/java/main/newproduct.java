package main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


public class newproduct {
    @FXML
    TextField itemfield;
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleadditem(){
            if(itemfield.getText().toString().length() >1){
                String result = database.addNewProduct(itemfield.getText());
                Alert alert = new Alert(Alert.AlertType.NONE,result, ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid Name", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }

    }
}
