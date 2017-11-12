package main;


import com.mysql.cj.core.util.StringUtils;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;


public class helper {

    static String sfile;
    static Stage primaryStage;
    static SortedMap<String,String> statecode = new TreeMap<>();

    public static void setupHelper(){

        statecode.put("Andaman and Nicobar Islands","35");
        statecode.put("Andhra Pradesh","37");
        statecode.put("Arunachal Pradesh","12");
        statecode.put("Assam","18");
        statecode.put("Bihar","10");
        statecode.put("Chandigarh","04");
        statecode.put("Chattisgarh","22");
        statecode.put("Dadra and Nagar Haveli","26");
        statecode.put("Daman and Diu","25");
        statecode.put("Delhi","07");
        statecode.put("Goa","30");
        statecode.put("Gujarat","24");
        statecode.put("Haryana","06");
        statecode.put("Himachal Pradesh","02");
        statecode.put("Jammu Kashmir","01");
        statecode.put("Jharkhand","20");
        statecode.put("Karnataka","29");
        statecode.put("Kerala","32");
        statecode.put("Lakshadweep Islands","31");
        statecode.put("Madhya Pradesh","23");
        statecode.put("Maharastra","27");
        statecode.put("Manipur","14");
        statecode.put("Meghalaya","17");
        statecode.put("Mizoram","15");
        statecode.put("Nagaland","13");
        statecode.put("Odisha","21");
        statecode.put("Pondicherry","34");
        statecode.put("Punjab","03");
        statecode.put("Rajasthan","08");
        statecode.put("Sikkim","11");
        statecode.put("Tamil Nadu","33");
        statecode.put("Telengana","36");
        statecode.put("Tripura","16");
        statecode.put("Uttar Pradesh","09");
        statecode.put("Uttarakhand","05");
        statecode.put("West Bengal","19");
}

    public static int getLength(TextField textField){
        return textField.getText().length();
    }
    public static boolean isEmpty(TextField textField){
        return StringUtils.isNullOrEmpty(textField.getText());
    }
    public static void setCurrentPdf(String file){
        sfile = file;
    }
    public static String getCurrentPdf(){
        return sfile;
    }

    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static SortedMap<String,String> getStatecode(){
        return statecode;
    }

    public static String getCode(String stateName){
        return statecode.get(stateName);
    }



}
