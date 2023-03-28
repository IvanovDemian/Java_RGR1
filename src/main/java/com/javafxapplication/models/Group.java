package com.javafxapplication.models;

import com.javafxapplication.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group {
  private int idGroup;
  private String name;
  private List<Student> students = new ArrayList<>();

  public Group(int idGroup) {
    DBHandler dbHandler = new DBHandler();
    this.idGroup = idGroup;

    ResultSet resultSet = dbHandler.getGroup(this.idGroup);

//    List<String> groupArray = new ArrayList<>();

    try {
      while (resultSet.next()) {
        int iduser = resultSet.findColumn("iduser");
//        int id = resultSet.getInt("iduser");
        String fName = resultSet.getString("fName");
        String lName = resultSet.getString("lName");
        String pName = resultSet.getString("pName");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
//        System.out.println(iduser + fName + lName);
        Student student = new Student(new User(iduser, fName, lName, pName, email, password, "студент"));
        students.add(student);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Group(int idGroup, String name) {
    this.idGroup = idGroup;
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getGroup(this.idGroup);

//    List<String> groupArray = new ArrayList<>();

    try {
      while (resultSet.next()) {
//        int id = resultSet.findColumn("iduser");
        int iduser = resultSet.getInt("iduser");
        String fName = resultSet.getString("fName");
        String lName = resultSet.getString("lName");
        String pName = resultSet.getString("pName");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
//        System.out.println(iduser + fName + lName);
        Student student = new Student(new User(iduser, fName, lName, pName, email, password, "студент"));
        students.add(student);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    this.name = name;
  }

  public Integer getIdGroup() {
    return idGroup;
  }

  public void setIdGroup(Integer idGroup) {
    this.idGroup = idGroup;
  }

  public synchronized void addStudent(Student student) {
    this.students.add(student);
  }

  public List<Student> getStudents() {
    return this.students;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
