module com.javafxapplication {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;


  opens com.javafxapplication to javafx.fxml;
  exports com.javafxapplication;
  exports com.javafxapplication.controllers;
  opens com.javafxapplication.controllers to javafx.fxml;
}