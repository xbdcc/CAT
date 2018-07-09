package com.carlos.cat;

import com.carlos.cat.uiautomator.DebugBridge;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/ui/image.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/ui/main.fxml"));

        Button root = new Button("hello");
//
//        MenuBar menuBar = new MenuBar(new Menu("hello"),new Menu("hh"));
//        menuBar.setUseSystemMenuBar(true);


//        Locale locale = Locale.getDefault();
//        String language = locale.getLanguage();

//        Locale locale = new Locale("zh","hk");
//        Locale.setDefault(locale);
//
//        System.out.println("lan:"+locale.getLanguage());
//        System.out.println("con:"+locale.getCountry());
//        root.setText(locale.getLanguage());


        ResourceBundle rb=ResourceBundle.getBundle("locales.content");
//        ResourceBundle rbUS = ResourceBundle.getBundle("locales.content", new Locale("en", "US"));
        String keyValue = new String(rb.getString("Cat").getBytes("ISO-8859-1"), "UTF8");
        System.out.println("k1="+keyValue);


        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();


//        DebugBridge.init();

        System.out.println("hello");


//        ShellUtil2.CommandResult commandResult =ShellUtil2.execCommand("adb devices",false);
//        System.out.println(commandResult.responseMsg);


//        Thread.sleep(3000);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
