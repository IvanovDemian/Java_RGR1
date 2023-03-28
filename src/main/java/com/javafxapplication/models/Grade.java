package com.javafxapplication.models;

public class Grade {
  private int idGrade;
  private String teacher;
  private Discipline discipline;
  private String disciplineTitle;
  private String date;
  protected String value;
  private String semester;

  protected Grade(int idGrade, Discipline discipline, String value) {
    this.idGrade = idGrade;
    this.discipline = discipline;
    this.value = value;
  }

  public Grade(Discipline discipline, String teacher, String date, String semester, String value) {
    this.discipline = discipline;
    this.teacher = teacher;
    this.date = date;
    this.semester = semester;
    this.value = value;
  }

  public int getIdGrade() {
    return idGrade;
  }

  public void setIdGrade(int idGrade) {
    this.idGrade = idGrade;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }
}
