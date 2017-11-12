package main;

import main.model.customer;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class database {

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;
    public static int result = 0;

    public static void connect(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/shop?"
                                    + "user=mohan&password=1990");

            // Statements allow to issue SQL queries to the database
            String customers = "CREATE TABLE IF NOT EXISTS CUSTOMERS ("+
                    "customerID int NOT NULL AUTO_INCREMENT,"+
                    "name varchar(250) NOT NULL UNIQUE,"+
                    "gstin varchar(250) NOT NULL UNIQUE,"+
                    "registered  TINYINT(1) DEFAULT TRUE,"+
                    "state varchar(250) NOT NULL,"+
                    "address varchar(250) NOT NULL,"+
                    "phone varchar(250) NOT NULL,"+
                    "PRIMARY KEY (customerID))";
            statement = connect.createStatement();
            result = statement.executeUpdate(customers);

            String products = "CREATE TABLE IF NOT EXISTS PRODUCTS ("+
                    "productID int NOT NULL AUTO_INCREMENT,"+
                    "name varchar(250) NOT NULL UNIQUE,"+
                    "PRIMARY KEY (productID))";
            result = statement.executeUpdate(products);


            String invoice = "CREATE TABLE IF NOT EXISTS INVOICE("+
                    "invoiceID int NOT NULL AUTO_INCREMENT,"+
                    "invoice_no int NOT NULL UNIQUE,"+
                    "customerID int NOT NULL,"+
                    "FOREIGN KEY (customerID) REFERENCES CUSTOMERS(customerID),"+
                    "invoice_date DATE NOT NULL,"+
                    "transport varchar(250),"+
                    "trasport_mode varchar(250),"+
                    "PRIMARY KEY (invoiceID))";

            result = statement.executeUpdate(invoice);

            String invoiceItem = "CREATE TABLE IF NOT EXISTS INVOICEITEM("+
                    "invoiceID int NOT NULL,"+
                    "FOREIGN KEY (invoiceID) REFERENCES INVOICE(invoiceID),"+
                    "productID int NOT NULL,"+
                    "FOREIGN KEY (productID) REFERENCES PRODUCTS(productID),"+
                    "qty int NOT NULL,"+
                    "price int NOT NULL)";

            result = statement.executeUpdate(invoiceItem);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean getConnectionStatus(){
        if(connect != null){
            try {
                if(connect.isClosed()){
                    return false;
                }else {
                    return true;
                }
            } catch (SQLException e) {
              return false;
            }
        }else return false;
    }

    public static String addNewCustomer(String name,boolean registered, String gstin,String state,String address, String phone) {

        String query = "INSERT INTO CUSTOMERS(name, gstin, registered,state, address, phone)" +
                "VALUES (?, ?, ?,?,?,?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gstin);
            preparedStatement.setBoolean(3, registered);
            preparedStatement.setString(4,state);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, phone);

            preparedStatement.executeUpdate();
            return "Successcully added customer";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public static String addNewProduct(String name){
        String query = "INSERT INTO PRODUCTS(name)" +
                "VALUES (?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return "Successcully added new product";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }



    public static ArrayList<customer> getCustomers(){
        Statement statement = null;
        ArrayList<customer> list = new ArrayList<customer>();
        try {
            statement = connect.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from CUSTOMERS");
            while(resultSet.next()){
                int id  = resultSet.getInt("customerID");
                String name = resultSet.getString("name").toUpperCase();
                String gstin = resultSet.getString("gstin").toUpperCase();
                boolean v = resultSet.getBoolean("registered");
                String state = resultSet.getString("state");
                String registered = "true";
                if(!v){
                    registered = "false";
                }
                String address = resultSet.getString("address").toUpperCase();
                String phone = resultSet.getString("phone");
                customer c = new customer(id,name,gstin,registered,state,address,phone);
                list.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
        // Result set get the result of the SQL query

    }

    public static ArrayList<String> getproductList(){
        Statement statement = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            statement = connect.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from products");
            while(resultSet.next()){

                String name = resultSet.getString("name").toUpperCase();

                list.add(name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
        // Result set get the result of the SQL query


    }

    public static customer getCustomerDetails(String name){
        customer c = new customer();
        Statement statement = null;
        try {
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customers  WHERE name = '"+name+"'");
            resultSet.next();
            int id  = resultSet.getInt("customerID");
            String cusName = resultSet.getString("name").toUpperCase();
            String gstin = resultSet.getString("gstin").toUpperCase();
            boolean v = resultSet.getBoolean("registered");
            String state = resultSet.getString("state");
            String registered = "true";
            if(!v){
                registered = "false";
            }
            String address = resultSet.getString("address").toUpperCase();
            String phone = resultSet.getString("phone");
            c = new customer(id,cusName,gstin,registered,state,address,phone);
            System.out.println(id);
            System.out.println(cusName);
            System.out.println(gstin);
            System.out.println(registered);
            System.out.println(state);
            System.out.println(address);
            System.out.println(phone);


            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
