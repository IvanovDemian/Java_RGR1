package com.javafxapplication;

public class Parser {
  public static String semesterToString(String semester) {
    return switch (semester) {
      case ("1") -> "Курс 01 / Семестр 01";
      case ("2") -> "Курс 01 / Семестр 02";
      case ("3") -> "Курс 02 / Семестр 01";
      case ("4") -> "Курс 02 / Семестр 02";
      case ("5") -> "Курс 03 / Семестр 01";
      case ("6") -> "Курс 03 / Семестр 02";
      case ("7") -> "Курс 04 / Семестр 01";
      case ("8") -> "Курс 04 / Семестр 02";
      default -> "Семестр " + semester;
    };
  }

  public static String stringToSemestr(String string) {
    return switch (string) {
      case ("Курс 01 / Семестр 01") -> "1";
      case ("Курс 01 / Семестр 02") -> "2";
      case ("Курс 02 / Семестр 01") -> "3";
      case ("Курс 02 / Семестр 02") -> "4";
      case ("Курс 03 / Семестр 01") -> "5";
      case ("Курс 03 / Семестр 02") -> "6";
      case ("Курс 04 / Семестр 01") -> "7";
      case ("Курс 04 / Семестр 02") -> "8";
      default -> "1";
    };
  }

  public static GradeType stringToGradeType(String gradeType) {
    return switch (gradeType) {
      case ("Экзамен") -> GradeType.EXAM;
      case ("Зачёт") -> GradeType.PASS;
      default -> GradeType.UNKNOWN;
    };
  }
}
