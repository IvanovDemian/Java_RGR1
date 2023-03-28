package com.javafxapplication.controllers;

import com.javafxapplication.DBHandler;
import com.javafxapplication.GradeType;
import com.javafxapplication.Parser;
import com.javafxapplication.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GradeController {
  public List<Grade> grades;

  public User user;

  public GradeController(User user) {
    this.user = user;
    grades = new ArrayList<>();
  }

  public void addGrade(Teacher teacher, Student student, Grade grade) {
      if (grade.getDiscipline().getGradeType() == GradeType.PASS) {
        PassGrade passGrade = new PassGrade(ThreadLocalRandom.current().nextInt(1, 1000 + 1), grade.getDiscipline(), grade.getValue());
        DBHandler dbHandler = new DBHandler();
        dbHandler.addPassGrade(passGrade, user.getIduser());

      }
      if (grade.getDiscipline().getGradeType() == GradeType.EXAM){
        ExamGrade examGrade = new ExamGrade(ThreadLocalRandom.current().nextInt(1, 1000 + 1), grade.getDiscipline(), grade.getValue());
        DBHandler dbHandler = new DBHandler();
        dbHandler.addExamGrade(examGrade, user.getIduser());
      }

    grades.add(grade);
  }

  public List<Grade> getAllGrades() {
//    getExams();
//    getPasses();

    return grades;
  }

  public List<Grade> getExams() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getExams(user);

    try {
      while (resultSet.next()) {
        int iddiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");
        String teacher = resultSet.getString("teacher");
        String date = resultSet.getString("date");
        String value = resultSet.getString("value");
        String semester = resultSet.getString("semester");
//        System.out.println(title + " " + gradeType + " " + teacher + " " + date + " " + value);
        Grade grade = new Grade(new Discipline(iddiscipline, title, Parser.stringToGradeType(gradeType)), teacher, date, semester, value);
        grades.add(grade);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return grades;
  }

  public List<Grade> getPasses() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getPasses(user);

    try {
      while (resultSet.next()) {
        int iddiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");
        String teacher = resultSet.getString("teacher");
        String date = resultSet.getString("date");
        String value = resultSet.getString("value");
        String semester = resultSet.getString("semester");
//        System.out.println(title + " " + gradeType + " " + teacher + " " + date + " " + value);
        Grade grade = new Grade(new Discipline(iddiscipline, title, Parser.stringToGradeType(gradeType)), teacher, date, semester, value);
        grades.add(grade);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return grades;
  }
}
