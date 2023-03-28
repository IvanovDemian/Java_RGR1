package com.javafxapplication;

public enum GradeType {
  PASS {
    @Override
    public boolean isValidGrade(String grade) {
      return Boolean.parseBoolean(grade);
    }
  },
  EXAM {
    @Override
    public boolean isValidGrade(String grade) {
      return grade.matches("[1-5]");
    }
  },
  UNKNOWN {
    @Override
    public boolean isValidGrade(String grade) {
      return Boolean.parseBoolean(grade);
    }
  };
  public abstract boolean isValidGrade(String grade);
}
