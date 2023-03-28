package com.javafxapplication.models;

import com.javafxapplication.GradeType;

public class ExamGrade extends Grade {
  public ExamGrade(int idGrade, Discipline discipline, String value) {
    super(idGrade, discipline, value);
    if (discipline.getGradeType() != GradeType.EXAM && !GradeType.EXAM.isValidGrade(value)) {
      throw new IllegalArgumentException("Оценка не соответсвует типу Экзамен");
    }
  }

 }
