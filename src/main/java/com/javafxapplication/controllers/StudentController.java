package com.javafxapplication.controllers;

import com.javafxapplication.GradeType;
import com.javafxapplication.MainApp;
import com.javafxapplication.Parser;
import com.javafxapplication.UserStage;
import com.javafxapplication.models.Grade;
import com.javafxapplication.models.Group;
import com.javafxapplication.models.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController {
  private Stage studentStage;

  private final Student user = new Student(LogInController.currentUser);
  private Group group = user.getGroup();

  GradeController gC = new GradeController(user);
  List<Grade> allGrades = gC.getAllGrades();
  List<Grade> exams = gC.getExams();
  List<Grade> passes = gC.getPasses();

  String[] selectedSemester = new String[1];
  String[] selectedGradeType = new String[1];

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Text text_hello;

  @FXML
  private Label label_initials;

  @FXML
  private Label label_role;

  @FXML
  private Label label_group;

  @FXML
  private Label label_groupname;

  @FXML
  private ListView<String> list_group;

  private TableView<String> table;

  @FXML
  private TableView<Grade> table_grades;

  @FXML
  private ChoiceBox<String> choice_gradetype;

  @FXML
  private ChoiceBox<String> choice_semester;

  @FXML
  private ListView<String> list_grades;

  @FXML
  private Button btn_showlist;

  @FXML
  public void initialize() {
    if (text_hello != null) {
      text_hello.setText("Добрый день, " + user.getfName() + "!");
    }
    if (label_initials != null) {
      label_initials.setText(user.getlName() + " " + user.getfName() + " " + user.getpName());
    }
    if (label_role != null) {
      label_role.setText(user.getRole());
    }
    if (label_groupname != null) {
      label_groupname.setText(group.getName());
    }
    if (label_group != null) {
      label_group.setText("Группа: " + group.getName());
    }
    if (list_group != null) {
      System.out.println(group.getIdGroup());
      System.out.println(group.getName());
      for(Student student : group.getStudents()){
        System.out.println(student.getlName() + " " + student.getfName() + " " + student.getpName());
        list_group.getItems().add(student.getlName() + " " + student.getfName() + " " + student.getpName());
      }
    }
//    if (table_grades != null) {
//      TableColumn title = new TableColumn<>("Дисциплина");
//      title.setPrefWidth(400);
//      TableColumn teacher = new TableColumn<>("Преподаватель");
//      teacher.setPrefWidth(200);
//      TableColumn date = new TableColumn<>("Дата сдачи");
//      date.setPrefWidth(120);
//      TableColumn value = new TableColumn<>("Оценка");
//      value.setPrefWidth(120);
//
//      // add the columns to the tableView
//      table_grades.getColumns().addAll(title, teacher, date, value);
//
//
//
//      GradeController gC = new GradeController(user);
//      ObservableList<Grade> observableList = FXCollections.observableArrayList();
//      observableList.addAll(gC.getExams());
//      System.out.println(Arrays.toString(gC.getExams()));
//      List<Grade> exams = gC.getExams();
//      for (Grade grade : exams) {
//        list_grades.getItems().add(grade.getDiscipline().getTitle() + "\t\t" + grade.getTeacher() + "\t\t" + grade.getDate() + "\t\t" + grade.getValue());
//      }
//      ObservableList<Grade> observableList = FXCollections.observableArrayList();
//      observableList.addAll(exams);
//      table_grades.setItems(observableList);
//
//      title.setCellValueFactory(new PropertyValueFactory<>("title"));
//      teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
//      date.setCellValueFactory(new PropertyValueFactory<>("date"));
//      value.setCellValueFactory(new PropertyValueFactory<>("value"));
//    }
    if (list_grades != null) {
      choice_semester.setValue("Семестр");
      List<String> semesters = new ArrayList<>();
      for(Grade grade : allGrades){
        if (!semesters.contains(grade.getSemester())) {
          choice_semester.getItems().add(Parser.semesterToString(grade.getSemester()));
          semesters.add(grade.getSemester());
        }
      }

      choice_gradetype.setValue("Оценка");
      choice_gradetype.getItems().add("Экзамены");
      choice_gradetype.getItems().add("Зачёты");

      List<Grade> grades = new ArrayList<>();

      choice_semester.setOnAction(actionEvent -> {
        selectedSemester[0] = choice_semester.getSelectionModel().getSelectedItem();
      });

      choice_gradetype.setOnAction(actionEvent -> {
        selectedGradeType[0] = choice_gradetype.getSelectionModel().getSelectedItem();
      });
    }
  }

  public void initStudentStage() {
    System.out.println("init stage");
    UserStage.stage.setTitle("ЗачётКом");
    UserStage.stage.setResizable(false);
    showHomeScene();
    MainApp.getPrimaryStage().close();
    UserStage.stage.showAndWait();
  }

  void showHomeScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("student-home-view.fxml"));
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Home scene");
  }

  void showGroupsScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("student-groups-view.fxml"));
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Groups scene");
  }

  void showProfileScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("student-profile-view.fxml"));
//    fxmlLoader.setController(new StudentController());
    try {
      UserStage.stage.setScene(new Scene(fxmlLoader.load()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Profile scene");
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

}
