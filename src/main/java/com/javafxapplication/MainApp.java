package com.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
  static private Stage primaryStage;
  static private Stage userStage;

  @Override
  public void start(Stage primaryStage) throws IOException {
    MainApp.primaryStage = primaryStage;


    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
    MainApp.primaryStage.setTitle("ЗачётКом");
    MainApp.primaryStage.setScene(scene);
    MainApp.primaryStage.setResizable(false);
    MainApp.primaryStage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  static public Stage getPrimaryStage() {
    return primaryStage;
  }

  public void setPrimaryStage(Stage primaryStage) {
    MainApp.primaryStage = primaryStage;
  }

  public static Stage getUserStage() {
    return userStage;
  }

  public static void setUserStage(Stage userStage) {
    MainApp.userStage = userStage;
  }
}