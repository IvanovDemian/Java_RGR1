package com.javafxapplication.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.javafxapplication.GradeType;
import com.javafxapplication.MainApp;
import com.javafxapplication.Parser;
import com.javafxapplication.UserStage;
import com.javafxapplication.models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TeacherController {
  private final Teacher user = new Teacher(LogInController.currentUser);

  private static GradeController gradeController;
  private static Grade addedGrade;
  private static List<Grade> allGrades;
  private static List<Grade> exams;
  private static List<Grade> passes;

  String[] selectedSemester = new String[1];
  String[] selectedGradeType = new String[1];

  private static Student selectedStudent;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Text text_hello;

  @FXML
  private ChoiceBox<String> choice_group;

  @FXML
  private ChoiceBox<String> choice_teachergroup;
  @FXML
  private Label label_alert1;

  @FXML
  private ListView<String> list_group;

  @FXML
  private Label label_department;

  @FXML
  private Label label_initials;

  @FXML
  private Label label_role;

  @FXML
  private Button btn_showlist;

  @FXML
  private ChoiceBox<String> choice_gradetype;

  @FXML
  private ChoiceBox<String> choice_semester;

  @FXML
  private ListView<String> list_grades;

  @FXML
  private Label label_studentinitials;

  @FXML
  private Button btn_addGrade;

  @FXML
  private ChoiceBox<String> choice_adddiscipline;
  @FXML
  private Label label_iddiscipline;

  @FXML
  private ChoiceBox<String> choice_addvalue;

  @FXML
  private DatePicker date_adddate;
  @FXML
  private TextField input_semester;

  @FXML
  void initialize() {
    if (text_hello != null) {
      text_hello.setText("Добрый день, " + user.getfName() + " " + user.getpName() + "!");
    }
    if (choice_teachergroup != null) {
      GroupController gC = new GroupController();
      List<Group> allGroups = user.getGroups();
      choice_teachergroup.setValue("Группа");
      for(Group group : allGroups){
        choice_teachergroup.getItems().add(group.getName());
      }

      choice_teachergroup.setOnAction(actionEvent -> {
        String selectedItem = choice_teachergroup.getSelectionModel().getSelectedItem();

        for(Group group : allGroups){
          label_alert1.setVisible(false);
          if (group.getName() == selectedItem) {
            list_group.getItems().clear();
            for(Student student : group.getStudents()){
              list_group.getItems().add(student.getIduser() + " " + student.getlName() + " " + student.getfName() + " " + student.getpName());
            }
          }
        }
        if (list_group.getItems().size() == 0) {
          label_alert1.setText("В данной группе нет студентов");
          label_alert1.setVisible(true);
        }
      });

      list_group.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          String selectedItem = list_group.getSelectionModel().getSelectedItem();
          String[] words = selectedItem.split(" ");

          selectedStudent = new Student(Integer.parseInt(words[0]));
          gradeController = new GradeController(selectedStudent);
//          allGrades = gradeController.getAllGrades();
//          exams = gradeController.getExams();
//          passes = gradeController.getPasses();
          System.out.println("selectedstudent " + selectedStudent.getlName());

          showStudentProfile(selectedStudent);

          System.out.println("Вы выбрали: " + selectedItem);
        }
      });
    }
    if (label_initials != null) {
      label_initials.setText(user.getlName() + " " + user.getfName() + " " + user.getpName());
    }
    if (label_role != null) {
      label_role.setText(user.getRole());
    }
    if (label_department != null) {
      label_department.setText("Кафедра: " + user.getDepartment());
    }
    if (choice_group != null && list_group != null) {
      GroupController gC = new GroupController();
      List<Group> allGroups = gC.getGroups();
      choice_group.setValue("Группа");
      for(Group group : allGroups){
        choice_group.getItems().add(group.getName());
      }

      choice_group.setOnAction(actionEvent -> {
        String selectedItem = choice_group.getSelectionModel().getSelectedItem();

        for(Group group : allGroups){
          label_alert1.setVisible(false);
          if (group.getName() == selectedItem) {
            list_group.getItems().clear();
            for(Student student : group.getStudents()){
              list_group.getItems().add(student.getlName() + " " + student.getfName() + " " + student.getpName());
            }
          }
        }
        if (list_group.getItems().size() == 0) {
          label_alert1.setText("В данной группе нет студентов");
          label_alert1.setVisible(true);
        }
      });
    }
    if (label_studentinitials != null) {
      label_studentinitials.setText(selectedStudent.getlName() + " " + selectedStudent.getfName() + " " + selectedStudent.getpName());
    }
    if (choice_adddiscipline != null && choice_addvalue != null) {
      List<Discipline> disciplines = user.getDisciplines();
      List<Integer> iddisciplines = new ArrayList<>();

      final Discipline[] selectedDiscipline = new Discipline[1];
      choice_adddiscipline.setValue("Дисциплина");
      choice_addvalue.setValue("Оценка");
      for(Discipline discipline : disciplines){
        choice_adddiscipline.getItems().add(discipline.getTitle());
        iddisciplines.add(discipline.getIdDiscipline());
        if (discipline.getGradeType() == GradeType.EXAM) {
          choice_addvalue.getItems().clear();
          choice_addvalue.getItems().add("Отлично");
          choice_addvalue.getItems().add("Хорошо");
          choice_addvalue.getItems().add("Удовлетворительно");
          choice_addvalue.getItems().add("Неудовлетворительно");
        }
        if (discipline.getGradeType() == GradeType.PASS) {
          choice_addvalue.getItems().clear();
          choice_addvalue.getItems().add("Зачтено");
          choice_addvalue.getItems().add("Не зачтено");
        }
      }

      choice_adddiscipline.setOnAction(actionEvent -> {
        int selectedIndex = choice_adddiscipline.getSelectionModel().getSelectedIndex();

        for (int i = 0; i < iddisciplines.size(); i++) {
          if (i == selectedIndex) {
            selectedDiscipline[0] = disciplines.get(i);
          }
        }

        System.out.println(selectedDiscipline[0]);
      });

      final String[] selectedValue = new String[1];
      choice_addvalue.setOnAction(actionEvent -> {
        selectedValue[0] = choice_addvalue.getSelectionModel().getSelectedItem();
      });

      String teacherInitials = user.getlName() + user.getfName() + user.getpName();
      addedGrade = new Grade(selectedDiscipline[0], teacherInitials, date_adddate.toString(), input_semester.getText(), selectedValue[0]);
    }
//    if (list_grades != null) {
//      choice_semester.setValue("Семестр");
//      List<String> semesters = new ArrayList<>();
//      for(Grade grade : allGrades){
//        if (!semesters.contains(grade.getSemester())) {
//          choice_semester.getItems().add(Parser.semesterToString(grade.getSemester()));
//          semesters.add(grade.getSemester());
//        }
//      }
//
//      choice_gradetype.setValue("Оценка");
//      choice_gradetype.getItems().add("Экзамены");
//      choice_gradetype.getItems().add("Зачёты");
//
//      List<Grade> grades = new ArrayList<>();
//
//      choice_semester.setOnAction(actionEvent -> {
//        selectedSemester[0] = choice_semester.getSelectionModel().getSelectedItem();
//      });
//
//      choice_gradetype.setOnAction(actionEvent -> {
//        selectedGradeType[0] = choice_gradetype.getSelectionModel().getSelectedItem();
//      });
//    }
  }

  public void initTeacherStage() {
    System.out.println("init stage");
    UserStage.stage.setTitle("ЗачётКом");
    UserStage.stage.setResizable(false);
    showHomeScene();
    MainApp.getPrimaryStage().close();
    UserStage.stage.showAndWait();
  }

  void showHomeScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("teacher-home-view.fxml"));
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Home scene");
  }

  void showGroupsScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("teacher-groups-view.fxml"));
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Groups scene");
  }

  void showProfileScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("teacher-profile-view.fxml"));
//    fxmlLoader.setController(new StudentController());
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Profile scene");
  }

  void showStudentProfile(Student student) {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("teacher-studentprofile-view.fxml"));
//    fxmlLoader.setController(new StudentController());
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Student scene");
    System.out.println(student.getlName());
  }

  void showAddGrade(Student student) {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("teacher-addgrade-view.fxml"));
//    fxmlLoader.setController(new StudentController());
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Student scene");
    System.out.println(student.getlName());
  }

  @FXML
  void onHomeLinkClicked(MouseEvent event) {
    showHomeScene();
  }

  @FXML
  void onGroupsLinkClicked(MouseEvent event) {
    showGroupsScene();
  }

  @FXML
  void onProfileLinkClicked(MouseEvent event) {
    showProfileScene();
  }

  void setListGrades() {
    list_grades.getItems().clear();
    if (selectedGradeType[0] == "Зачёты") {
      for(Grade grade : passes){
        System.out.println("Зачёты");
        if (Parser.semesterToString(grade.getSemester()) == selectedSemester[0] && grade.getDiscipline().getGradeType() == GradeType.PASS) {
          list_grades.getItems().add(grade.getDiscipline().getTitle() + "\t\t" + grade.getTeacher() + "\t\t" + grade.getDate() + "\t\t" + grade.getValue());
        }
      }
    }
    if (selectedGradeType[0] == "Экзамены") {
      for(Grade grade : exams){
        System.out.println("Экзамены");
        if (Parser.semesterToString(grade.getSemester()) == selectedSemester[0]) {
          System.out.println(grade.getDiscipline().getTitle() + "\t\t" + grade.getTeacher() + "\t\t" + grade.getDate() + "\t\t" + grade.getValue());
          list_grades.getItems().add(grade.getDiscipline().getTitle() + "\t\t" + grade.getTeacher() + "\t\t" + grade.getDate() + "\t\t" + grade.getValue());
        }
      }
    }
  }

  @FXML
  void onShowClicked(ActionEvent event) {
    setListGrades();
  }

  @FXML
  void onAddGradeClicked(ActionEvent event) {
    showAddGrade(selectedStudent);
    System.out.println("clicked");
  }

  @FXML
  void onAddClick(ActionEvent event) {
    gradeController.addGrade(user, selectedStudent, addedGrade);
  }
}