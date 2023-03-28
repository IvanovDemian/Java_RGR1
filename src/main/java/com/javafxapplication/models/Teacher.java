package com.javafxapplication.models;

import com.javafxapplication.DBConst;
import com.javafxapplication.DBHandler;
import com.javafxapplication.Parser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
  private String department;
  private List<Group> groups = new ArrayList<>();
  private List<Discipline> disciplines = new ArrayList<>();

  public Teacher (User user) {
    super(user.getIduser(), user.getfName(), user.getlName(), user.getpName(), user.getEmail(), user.getPassword(), "преподаватель");

//    System.out.println("id " + getIduser());
    fillGroups();
    fillDisciplines();

    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = null;

    String select = "SELECT * FROM " + DBConst.TABLE_USERS +
      " LEFT JOIN " + "teachers" + " ON " + DBConst.TABLE_USERS + "." + DBConst.USER_ID + " = " + "teachers" + "." + DBConst.USER_ID +
      " WHERE " + DBConst.TABLE_USERS + "." + DBConst.USER_ID + " =?";

    try {
      PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(select);
      prSt.setInt(1, user.getIduser());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      if (resultSet != null) {
        while (resultSet.next()) {
          department = resultSet.getString("department");
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void fillGroups() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getGroupsForTeacher(getIduser());

    try {
      while (resultSet.next()) {
        int idGroup = resultSet.getInt("idgroup");
        String name = resultSet.getString("name");
        Group group = new Group(idGroup, name);
        groups.add(group);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void fillDisciplines() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getDisciplinesForTeacher(getIduser());

    try {
      while (resultSet.next()) {
        int idDiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");
        Discipline discipline = new Discipline(idDiscipline, title, Parser.stringToGradeType(gradeType));
        disciplines.add(discipline);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<Discipline> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(List<Discipline> disciplines) {
    this.disciplines = disciplines;
  }
}
