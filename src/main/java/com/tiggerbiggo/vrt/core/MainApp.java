package com.tiggerbiggo.vrt.core;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import old.Main;

public class MainApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
    primaryStage.setTitle("Hello World");

    primaryStage.setScene(new Scene(root, 600, 500));
    primaryStage.show();
  }
}
