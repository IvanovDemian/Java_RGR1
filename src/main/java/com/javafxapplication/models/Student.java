package com.javafxapplication.models;

import com.javafxapplication.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User {
  private Group group;
  public Student (User user) {
    super(user.getIduser(), user.getfName(), user.getlName(), user.getpName(), user.getEmail(), user.getPassword(), "студент");
    try {
      fillGroup();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Student () {

  }
  public Student (int idUser) {
    super(idUser);

    DBHandler dbHandler = new DBHandler();
    ResultSet resultSet = dbHandler.getStudentById(idUser);

    try {
      while (resultSet.next()) {
        setfName(resultSet.getString("fName"));
        setlName(resultSet.getString("lName"));
        setpName(resultSet.getString("pName"));
        setEmail(resultSet.getString("email"));
        setPassword(resultSet.getString("password"));

      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void fillGroup() throws SQLException{
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getGroupByStudent(getIduser());

    int idGroup = 0;
    String groupName;
    while (resultSet.next()) {
      idGroup = resultSet.getInt(1);

      System.out.println("IN STUDENT IDGROUP IS " + idGroup);
      groupName = resultSet.getString(2);
      this.group = new Group(idGroup);
      this.group.setName(groupName);
    }
  }

//    group = new Group(idGroup);

  public Group getGroup() {
    return group;
  }
}
