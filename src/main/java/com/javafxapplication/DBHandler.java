package com.javafxapplication;

import com.javafxapplication.models.*;

import java.sql.*;

public class DBHandler extends DBConfig {
  Connection dbConnection;

  public Connection getDbConnection() throws ClassNotFoundException, SQLException {
    String connectionStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    Class.forName("com.mysql.cj.jdbc.Driver");

    dbConnection = DriverManager.getConnection(connectionStr, dbUser, dbPass);

    return dbConnection;
  }

  public ResultSet getUser(User user) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM " + DBConst.TABLE_USERS + " WHERE " + DBConst.USER_EMAIL + "=? AND " + DBConst.USER_PASSWORD + "=?";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setString(1, user.getEmail());
      prSt.setString(2, user.getPassword());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return resultSet;
  }

  public ResultSet getStudentById(int idUser) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM users WHERE iduser = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idUser);
      System.out.println(prSt);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet getRole(User user, String subtable) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM " + DBConst.TABLE_USERS +
      " LEFT JOIN " + subtable + " ON " + DBConst.TABLE_USERS + "." + DBConst.USER_ID + " = " + subtable + "." + DBConst.USER_ID +
      " WHERE " + DBConst.TABLE_USERS + "." + DBConst.USER_ID + " =?";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, user.getIduser());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getGroupByStudent(int idUser) {
    ResultSet resultSet = null;

    String select = "SELECT groups.* FROM users JOIN students ON users.iduser = students.iduser JOIN group_student ON students.idstudent = group_student.idstudent JOIN groups ON group_student.idgroup = groups.idgroup WHERE users.iduser =?;";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idUser);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";

    return resultSet;
  }

  public ResultSet getGroup(int idGroup) {
    ResultSet resultSet = null;

    System.out.println(idGroup);
    String select = "SELECT * FROM users_in_group WHERE idgroup = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idGroup);
      System.out.println(prSt);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet getAllGroups() {
    ResultSet resultSet = null;

    String select = "SELECT * FROM groups";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet getGroupsForTeacher(int idUser) {
    ResultSet resultSet = null;

    String select = "SELECT groups.* FROM groups INNER JOIN teacher_group ON teacher_group.idgroup = groups.idgroup INNER JOIN teachers ON teachers.idteacher = teacher_group.idteacher INNER JOIN users ON users.iduser = teachers.iduser WHERE users.iduser = ?;";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idUser);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";

    return resultSet;
  }
  public ResultSet getDisciplinesForTeacher(int idUser) {
    ResultSet resultSet = null;

    String select = "SELECT discipline.* FROM discipline INNER JOIN teacher_discipline ON teacher_discipline.iddiscipline = discipline.iddiscipline INNER JOIN teachers ON teachers.idteacher = teacher_discipline.idteacher INNER JOIN users ON users.iduser = teachers.iduser WHERE users.iduser = ?;";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idUser);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";

    return resultSet;
  }

  public ResultSet getExams(User user) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM user_exam_grades WHERE iduser = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, user.getIduser());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet getPasses(User user) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM user_pass_grades WHERE iduser = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, user.getIduser());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet addExamGrade(ExamGrade grade, int idStudent) {
    ResultSet resultSet = null;

    String select =
      "INSERT INTO `grade` (`idgrade`, `teacher`, `iddiscipline`, `date`, `semester`, `idstudent`)" + " VALUES (NULL, ?, ?, ?, ?, ?);" +
      "SET @idgrade = LAST_INSERT_ID();" +
      "INSERT INTO `exam_grade`(`id`, `value`, `idgrade`) VALUES (null, ?, @idgrade)";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setString(1, grade.getTeacher());
      prSt.setInt(2, grade.getDiscipline().getIdDiscipline());
      prSt.setString(3, grade.getDate());
      prSt.setString(4, grade.getSemester());
      prSt.setInt(5, idStudent);
      prSt.setString(6, grade.getValue());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

  public ResultSet addPassGrade(PassGrade grade, int idStudent) {
    ResultSet resultSet = null;

    String select =
      "INSERT INTO `grade` (`idgrade`, `teacher`, `iddiscipline`, `date`, `semester`, `idstudent`)" + " VALUES (NULL, ?, ?, ?, ?, ?);" +
        "SET @idgrade = LAST_INSERT_ID();" +
        "INSERT INTO `pass_grade`(`id`, `value`, `idgrade`) VALUES (null, ?, @idgrade)";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setString(1, grade.getTeacher());
      prSt.setInt(2, grade.getDiscipline().getIdDiscipline());
      prSt.setString(3, grade.getDate());
      prSt.setString(4, grade.getSemester());
      prSt.setInt(5, idStudent);
      prSt.setString(6, grade.getValue());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
//    String groupSelect = "SELECT * FROM " + "group_student" + " WHERE " + "idstudent" + " =?";
    return resultSet;
  }

}
