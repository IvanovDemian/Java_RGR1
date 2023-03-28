package com.javafxapplication.models;

import com.javafxapplication.GradeType;

public class Discipline {
  private int idDiscipline;
  private String title;
  private GradeType gradeType;

  public Discipline(int idDiscipline, String title, GradeType gradeType) {
    this.idDiscipline = idDiscipline;
    this.title = title;
    this.gradeType = gradeType;
  }

  public int getIdDiscipline() {
    return idDiscipline;
  }

  public void setIdDiscipline(int idDiscipline) {
    this.idDiscipline = idDiscipline;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public GradeType getGradeType() {
    return gradeType;
  }

  public void setGradeType(GradeType gradeType) {
    this.gradeType = gradeType;
  }
}
