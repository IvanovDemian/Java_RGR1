package com.javafxapplication.controllers;

import com.javafxapplication.DBConst;
import com.javafxapplication.DBHandler;
import com.javafxapplication.MainApp;
import com.javafxapplication.models.Student;
import com.javafxapplication.models.Teacher;
import com.javafxapplication.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleController {
  private User user;

  public RoleController(User user) {
    this.user = user;
  }

  private Boolean isStudent() {
    DBHandler dbHandler = new DBHandler();

    boolean isStudent = false;
    ResultSet resultSet = dbHandler.getRole(user, "students");

    try {
      while (resultSet.next()) {
        isStudent = resultSet.getBoolean(7);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return isStudent;
  }

  private Boolean isTeacher() {
    DBHandler dbHandler = new DBHandler();

    boolean isTeacher = false;
    ResultSet resultSet = dbHandler.getRole(user, "teachers");

    try {
      while (resultSet.next()) {
        isTeacher = resultSet.getBoolean(7);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return isTeacher;
  }

//  private Boolean isAdmin() {
//    DBHandler dbHandler = new DBHandler();
//
//    boolean isAdmin = false;
//    ResultSet resultSet = dbHandler.getRole(user, "admins");
//
//    try {
//      while (resultSet.next()) {
//        isAdmin = resultSet.getBoolean(7);
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//
//    return isAdmin;
//  }

  public User getRoledUser() {
    if (isStudent()) {
      Student student = new Student(this.user);
      return student;
    } else if (isTeacher()) {
      Teacher teacher = new Teacher(this.user);
      return teacher;
    }
//    else if (isAdmin()) {
//      System.out.println("is admin");
//      return user;
//    }
    else {
      return user;
    }
  }
}
