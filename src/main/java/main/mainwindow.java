package main;


import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;


import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class mainwindow implements EventHandler<ActionEvent>{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @FXML
    Button newinvoice, newcustomer,viewcustomers,newproduct;
    @FXML
    Label status;

    String ACCESS_TOKEN;

    @FXML
    public void initialize() {

        //getUserToken();
       newcustomer.setOnAction(this);
       viewcustomers.setOnAction(this);
       newproduct.setOnAction(this);
       newinvoice.setOnAction(this);
       if(database.getConnectionStatus()){
          setStatus("Database connection established");
       }else {
           setStatus("Failed to connect to database please check your connection");
       }

    }

   /* private void getUserToken() {

        DbxAppInfo appInfo = new DbxAppInfo("6v36bf0c8pwb2q8", "rr1069jk5ws6vrv");
        DbxRequestConfig requestConfig = new DbxRequestConfig("shopapp-authorize");
        DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo);
        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
                .withNoRedirect()
                .build();

        String authorizeUrl = webAuth.authorize(webAuthRequest);
        WebEngine engine = Webview.getEngine();
        engine.load(authorizeUrl);

    }*/

    public void handle(ActionEvent event) {
        if(((Button)event.getSource()).getId()==newcustomer.getId()){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/newcustomer.fxml"));
                Stage stage = new Stage();
                stage.setTitle("New Customer");
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(new Scene(root));
                stage.showAndWait();
                // Hide this current window (if this is what you want)
                }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else if(((Button)event.getSource()).getId()==viewcustomers.getId()){
            System.out.println("clicked viewcustomers");
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/viewCutomers.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Customers");
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(new Scene(root));
                stage.showAndWait();
                // Hide this current window (if this is what you want)
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else if(((Button)event.getSource()).getId()==newproduct.getId()){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/newproduct.fxml"));
                Stage stage = new Stage();
                stage.setTitle("new Product");
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(new Scene(root));
                stage.showAndWait();
                // Hide this current window (if this is what you want)
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(((Button)event.getSource()).getId()==newinvoice.getId()){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/newinvoice.fxml"));
                Stage stage = new Stage();
                stage.setTitle("new Invoice");
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(new Scene(root));
                stage.showAndWait();
                // Hide this current window (if this is what you want)
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStatus(String message){
        status.setText("Status: "+ message);
    }

    @FXML
    public void viewPdf() {


    }

}
