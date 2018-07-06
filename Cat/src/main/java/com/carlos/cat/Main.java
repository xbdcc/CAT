package com.carlos.cat;

import com.carlos.cat.uiautomator.DebugBridge;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/image.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/ui/main.fxml"));

//        Button button = new Button("hello");
//
//        MenuBar menuBar = new MenuBar(new Menu("hello"),new Menu("hh"));
//        menuBar.setUseSystemMenuBar(true);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        DebugBridge.init();

        System.out.println("hello");


        ShellUtil2.CommandResult commandResult =ShellUtil2.execCommand("adb devices",false);
        System.out.println(commandResult.responseMsg);


//        Thread.sleep(3000);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
