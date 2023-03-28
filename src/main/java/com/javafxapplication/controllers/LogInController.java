package com.javafxapplication.controllers;

import com.javafxapplication.DBHandler;
import com.javafxapplication.models.Group;
import com.javafxapplication.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {
  public static User currentUser;
  @FXML
  private Button btn_login;

  @FXML
  private TextField input_login;

  @FXML
  private PasswordField input_password;

  @FXML
  protected void onLogInButtonClick() {
    String inputLogin = input_login.getText().trim();
    String inputPassword = input_password.getText().trim();
    
    if (!inputLogin.equals("") && !inputPassword.equals("")) {
      logInUser(inputLogin, inputPassword);
    } else {
      System.out.println("Login or Password is empty");
    }
  }

  private void logInUser(String inputLogin, String inputPassword) {
    DBHandler dbHandler = new DBHandler();
    User user = new User();
    user.setEmail(inputLogin);
    user.setPassword(inputPassword);
    ResultSet resultSet = dbHandler.getUser(user);

    User userObj = getUserObj(resultSet);

    if (!userObj.getfName().equals("")) {
      System.out.println("Пользователь найден!");

      RoleController role = new RoleController(userObj);
      currentUser = role.getRoledUser();
      if (currentUser.getRole().equals("студент")) {
        StudentController sC = new StudentController();
        sC.initStudentStage();
      }
      if (currentUser.getRole().equals("преподаватель")) {
        TeacherController tC = new TeacherController();
        tC.initTeacherStage();
      }
    } else {
      System.out.println("Пользователь не найден!");
    }
  }

  private User getUserObj(ResultSet resultSet) {
    User userObj = new User();
    try {
      while (resultSet.next()) {
        userObj.setIduser(resultSet.getInt(1));
        userObj.setfName(resultSet.getString(2));
        userObj.setlName(resultSet.getString(3));
        userObj.setpName(resultSet.getString(4));
        userObj.setEmail(resultSet.getString(5));
        userObj.setPassword(resultSet.getString(6));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return userObj;
  }
}
