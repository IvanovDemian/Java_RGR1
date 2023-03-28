package com.javafxapplication.controllers;

import com.javafxapplication.DBHandler;
import com.javafxapplication.models.Group;
import com.javafxapplication.models.Student;
import com.javafxapplication.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupController {
  private List<Group> groups = new ArrayList<>();

  public GroupController() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getAllGroups();

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

  public List<Group> getGroups() {
    return groups;
  }

  public void addGroup(Group group) {
    groups.add(group);
  }

  public void removeGroup(Group group) {
    groups.remove(group);
  }
}
