package com.javafxapplication.models;

import com.javafxapplication.GradeType;

public class PassGrade extends Grade {
  public PassGrade(int idGrade, Discipline discipline, String value) {
    super(idGrade, discipline, value);
    if (discipline.getGradeType() != GradeType.PASS && !GradeType.PASS.isValidGrade(value)) {
      throw new IllegalArgumentException("Оценка не соответсвует типу Зачет");
    }
  }
}
