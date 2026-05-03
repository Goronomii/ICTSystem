package group1.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;
public class secondsemesterICTSubjectController {
 private double MouseX, MouseY = 0;
@FXML
 private Stage semesterStage;
 @FXML 
 private TableView<Assignment> assignmentsTableimmersion;
 @FXML 
 private TableColumn<Assignment, String> assignmentTitleColumnimmersion;
 @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumnimmersion;
    
 @FXML 
 private TableView<Activity> activitiesTableimmersion;
  @FXML
    private TableColumn<Activity, String> activityTitleColumnimmersion;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumnimmersion;
 @FXML 
 private TableView<Performance> performanceTableimmersion;
 @FXML
    private TableColumn<Performance, String> performanceTitleColumnimmersion;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumnimmersion;

     @FXML 
 private TableView<Assignment> assignmentsTableresearch;
 @FXML 
 private TableColumn<Assignment, String> assignmentTitleColumnresearch;
 @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumnresearch;
    
 @FXML 
 private TableView<Activity> activitiesTableresearch;
  @FXML
    private TableColumn<Activity, String> activityTitleColumnresearch;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumnresearch;
 @FXML 
 private TableView<Performance> performanceTableresearch;
 @FXML
    private TableColumn<Performance, String> performanceTitleColumnresearch;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumnresearch;
    @FXML 
 private TableView<Assignment> assignmentsTablepersonality;
 @FXML 
 private TableColumn<Assignment, String> assignmentTitleColumnpersonality;
 @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumnpersonality;
    
 @FXML 
 private TableView<Activity> activitiesTablepersonality;
  @FXML
    private TableColumn<Activity, String> activityTitleColumnpersonality;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumnpersonality;
 @FXML 
 private TableView<Performance> performanceTablepersonality;
 @FXML
    private TableColumn<Performance, String> performanceTitleColumnpersonality;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumnpersonality;
       @FXML 
 private TableView<Assignment> assignmentsTableenglish;
 @FXML 
 private TableColumn<Assignment, String> assignmentTitleColumnenglish;
 @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumnenglish;
    
 @FXML 
 private TableView<Activity> activitiesTableenglish;
  @FXML
    private TableColumn<Activity, String> activityTitleColumnenglish;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumnenglish;
 @FXML 
 private TableView<Performance> performanceTableenglish;
 @FXML
    private TableColumn<Performance, String> performanceTitleColumnenglish;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumnenglish;
  @FXML 
 private TableView<Assignment> assignmentsTableart;
 @FXML 
 private TableColumn<Assignment, String> assignmentTitleColumnart;
 @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumnart;
    
 @FXML 
 private TableView<Activity> activitiesTableart;
  @FXML
    private TableColumn<Activity, String> activityTitleColumnart;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumnart;
 @FXML 
 private TableView<Performance> performanceTableart;
 @FXML
    private TableColumn<Performance, String> performanceTitleColumnart;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumnart;

   @FXML
    private TableView<Assignment> assignmentsTable;
    @FXML
    private TableColumn<Assignment, String> assignmentTitleColumn;
    @FXML
    private TableColumn<Assignment, Boolean> assignmentCheckedColumn;
    
    @FXML
    private TableView<Activity> activitiesTable;
    @FXML
    private TableColumn<Activity, String> activityTitleColumn;
    @FXML
    private TableColumn<Activity, Boolean> activityCheckedColumn;
    
    @FXML
    private TableView<Performance> performanceTable;
    @FXML
    private TableColumn<Performance, String> performanceTitleColumn;
    @FXML
    private TableColumn<Performance, Boolean> performanceCheckedColumn;
    @FXML
private Pane programmingPane;
@FXML
private Pane workImmersionPane;
  @FXML
private Pane researchpane;
@FXML
private Pane personalitypane;
  @FXML
private Pane englishpane;
@FXML
private Pane contemporarypane;



@FXML 
private JFXButton todobutton;
@FXML 
    private JFXButton notificationbutton;
@FXML
    private Button minimizebutton;
    private String studentUsername;
    
@FXML
   private Label namelabel;

 
@FXML 
    private void close() throws IOException {
     semesterStage.close();
    
    }
   
    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
    }
    private void openSubmissionScene(String title, String studentUsername) throws SQLException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Submission.fxml"));
        Parent root = loader.load();
        submission submission = loader.getController();
        submission.initData(title, studentUsername);
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) minimizebutton.getScene().getWindow(); 
        Stage submissionStage = new Stage();
        submissionStage.setScene(scene);
        submissionStage.initStyle(StageStyle.UNDECORATED);
        submissionStage.initOwner(currentStage); 
        movable(submissionStage, scene);

        submission.submissionStage = submissionStage;
        
        submissionStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
     public void initData(String studentUsername) throws SQLException {
       this.studentUsername = studentUsername;
        namelabel.setText(studentUsername);
        loadDataFromDatabase(studentUsername);
        
        
    }
private void loadDataFromDatabase(String studentUsername) throws SQLException {
      String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

   try (Connection connect = DriverManager.getConnection(url, dbuser, dbpass)) {
            loadSubjectprogramming(connect, studentUsername, "PROGRAMMING", assignmentsTable, activitiesTable, performanceTable);
            loadSubjectworkimmersion(connect, studentUsername, "WORK IMMERSION", assignmentsTableimmersion, activitiesTableimmersion, performanceTableimmersion);
              loadSubjectresearch(connect, studentUsername, "INQUIRIES,INVESTIGATIONS AND IMMERSION", assignmentsTableresearch, activitiesTableresearch, performanceTableresearch);
                loadSubjectpersonality(connect, studentUsername, "PERSONALITY DEVELOPMENT", assignmentsTablepersonality, activitiesTablepersonality, performanceTablepersonality);
                  loadSubjectenglish(connect, studentUsername, "ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES", assignmentsTableenglish, activitiesTableenglish, performanceTableenglish);
                    loadSubjectart(connect, studentUsername, "CONTEMPORARY PHILIPPINE ARTS FROM THE REGION", assignmentsTableart, activitiesTableart, performanceTableart);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

private void loadSubjectprogramming(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentTable, TableView<Activity> activityTable, TableView<Performance> performanceTable) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentTable);
        loadActivities(connection, studentUsername, subject, activityTable);
        loadPerformance(connection, studentUsername, subject, performanceTable);
    }
private void loadSubjectworkimmersion(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTableimmersion, TableView<Activity> activitiesTableimmersion, TableView<Performance> performanceTableimmersion) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentsTableimmersion);
        loadActivities(connection, studentUsername, subject, activitiesTableimmersion);
        loadPerformance(connection, studentUsername, subject, performanceTableimmersion);
    }
private void loadSubjectresearch(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTableresearch, TableView<Activity> activitiesTableresearch, TableView<Performance> performanceTableresearch) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentsTableresearch);
        loadActivities(connection, studentUsername, subject, activitiesTableresearch);
        loadPerformance(connection, studentUsername, subject, performanceTableresearch);
    }
private void loadSubjectpersonality(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTablepersonality, TableView<Activity> activitiesTablepersonality, TableView<Performance> performanceTablepersonality) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentsTablepersonality);
        loadActivities(connection, studentUsername, subject, activitiesTablepersonality);
        loadPerformance(connection, studentUsername, subject, performanceTablepersonality);
    }
private void loadSubjectenglish(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTableenglish, TableView<Activity> activitiesTableenglish, TableView<Performance> performanceTableenglish) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentsTableenglish);
        loadActivities(connection, studentUsername, subject, activitiesTableenglish);
        loadPerformance(connection, studentUsername, subject, performanceTableenglish);
    }
private void loadSubjectart(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTableart, TableView<Activity> activitiesTableart, TableView<Performance> performanceTableart) throws SQLException {
        loadAssignments(connection, studentUsername, subject, assignmentsTableart);
        loadActivities(connection, studentUsername, subject, activitiesTableart);
        loadPerformance(connection, studentUsername, subject, performanceTableart);
    }

     public void initialize() {

    setupColumns(assignmentTitleColumn, assignmentCheckedColumn, Assignment.class);
    setupColumns(activityTitleColumn, activityCheckedColumn, Activity.class);
    setupColumns(performanceTitleColumn, performanceCheckedColumn, Performance.class);
    
    setupColumns(assignmentTitleColumnimmersion, assignmentCheckedColumnimmersion, Assignment.class);
    setupColumns(activityTitleColumnimmersion, activityCheckedColumnimmersion, Activity.class);
    setupColumns(performanceTitleColumnimmersion, performanceCheckedColumnimmersion, Performance.class);
    
    setupColumns(assignmentTitleColumnresearch, assignmentCheckedColumnresearch, Assignment.class);
    setupColumns(activityTitleColumnresearch, activityCheckedColumnresearch, Activity.class);
    setupColumns(performanceTitleColumnresearch, performanceCheckedColumnresearch, Performance.class);
    
    setupColumns(assignmentTitleColumnpersonality, assignmentCheckedColumnpersonality, Assignment.class);
    setupColumns(activityTitleColumnpersonality, activityCheckedColumnpersonality, Activity.class);
    setupColumns(performanceTitleColumnpersonality, performanceCheckedColumnpersonality, Performance.class);
    
    setupColumns(assignmentTitleColumnenglish, assignmentCheckedColumnenglish, Assignment.class);
    setupColumns(activityTitleColumnenglish, activityCheckedColumnenglish, Activity.class);
    setupColumns(performanceTitleColumnenglish, performanceCheckedColumnenglish, Performance.class);
    
    setupColumns(assignmentTitleColumnart, assignmentCheckedColumnart, Assignment.class);
    setupColumns(activityTitleColumnart, activityCheckedColumnart, Activity.class);
    setupColumns(performanceTitleColumnart, performanceCheckedColumnart, Performance.class);

    try {
        initData(studentUsername);
    } catch (SQLException ex) {
        Logger.getLogger(secondsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private <T> void setupColumns(TableColumn<T, String> titleColumn, 
                              TableColumn<T, Boolean> checkedColumn, 
                              Class<T> clazz) {
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    checkedColumn.setCellValueFactory(new PropertyValueFactory<>("checked"));
    checkedColumn.setCellFactory(column -> new TableCell<T, Boolean>() {
         @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle("");
            } else {
                setText(item ? "Completed" : "Incomplete");
                setStyle(item ? "-fx-background-color: #5fc257;" : "-fx-background-color: #db5c5c;");
                if (!item) {
                    setCursor(Cursor.HAND); 
                    setOnMouseEntered(event -> {
                        setStyle("-fx-background-color: #a34141;"); 
                    });
                    setOnMouseExited(event -> {
                        setPrefHeight(Region.USE_COMPUTED_SIZE);
                        setStyle("-fx-background-color: #db5c5c;");
                    });
                    setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                            T rowData = getTableView().getItems().get(getIndex());
                            if (rowData instanceof Assignment) {
                                Assignment assignment = (Assignment) rowData;
                                String title = assignment.getTitle(); 
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(secondsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            } else if (rowData instanceof Activity) {
                                Activity activity = (Activity) rowData;
                                String title = activity.getTitle();
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(secondsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            } else if (rowData instanceof Performance) {
                                Performance performance = (Performance) rowData;
                                String title = performance.getTitle();
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(secondsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        }
                    });
                }
            }
        }
    });
}
private void movable(Stage stage, Scene scene) { 
    scene.setOnMousePressed(event ->{
        MouseX = event.getSceneX();
        MouseY = event.getSceneY();
    });
    scene.setOnMouseDragged(event -> {
        stage.setX(event.getScreenX() - MouseX);
        stage.setY(event.getScreenY() - MouseY); 
    });
}
     
     
     
     
     
private void loadAssignments(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTable) throws SQLException {
    String assignmentsql = "SELECT DISTINCT m.column_title, COALESCE(g.checked, false) AS checked " +
                           "FROM added_columns_metadata m " +
                           "LEFT JOIN grades g ON m.column_title = g.column_title " +
                           "AND g.user_id = (SELECT UID FROM userprivateinfo WHERE Username = ?) " +
                           "AND g.subject = ? " +
                           "AND g.semester = ? " +
                           "WHERE m.subject = ? " +
                           "AND m.column_type = 'Assignment'";
    try (PreparedStatement assignmentsStatement = connection.prepareStatement(assignmentsql)) {
        assignmentsStatement.setString(1, studentUsername);
        assignmentsStatement.setString(2, subject);
        assignmentsStatement.setString(3, "2nd Semester"); // Adjust semester here
        assignmentsStatement.setString(4, subject);
        try (ResultSet assignmentResult = assignmentsStatement.executeQuery()) {
            assignmentsTable.getItems().clear();
            while (assignmentResult.next()) {
                String title = assignmentResult.getString("column_title");
                boolean checked = assignmentResult.getBoolean("checked");
                Assignment assignment = new Assignment(title, checked);
                assignmentsTable.getItems().add(assignment);
            }
        }
    }
}
private void loadActivities(Connection connection, String studentUsername, String subject, TableView<Activity> activitiesTable) throws SQLException {
    String activitySql = "SELECT DISTINCT m.column_title, COALESCE(g.checked, false) AS checked " +
                         "FROM added_columns_metadata m " +
                         "LEFT JOIN grades g ON m.column_title = g.column_title " +
                         "AND g.user_id = (SELECT UID FROM userprivateinfo WHERE Username = ?) " +
                         "AND g.subject = ? " +
                         "AND g.semester = ? " +
                         "WHERE m.subject = ? " +
                         "AND m.column_type = 'Activity'";
    try (PreparedStatement activityStatement = connection.prepareStatement(activitySql)) {
        activityStatement.setString(1, studentUsername);
        activityStatement.setString(2, subject);
        activityStatement.setString(3, subject);
        activityStatement.setString(4, "2nd Semester"); // Adjust semester here
        try (ResultSet activityResult = activityStatement.executeQuery()) {
            activitiesTable.getItems().clear();
            while (activityResult.next()) {
                String title = activityResult.getString("column_title");
                boolean checked = activityResult.getBoolean("checked");
                Activity activity = new Activity(title, checked);
                activitiesTable.getItems().add(activity);
            }
        }
    }
}

private void loadPerformance(Connection connection, String studentUsername, String subject, TableView<Performance> performanceTable) throws SQLException {
    String performanceSql = "SELECT DISTINCT m.column_title, COALESCE(g.checked, false) AS checked " +
                             "FROM added_columns_metadata m " +
                             "LEFT JOIN grades g ON m.column_title = g.column_title " +
                             "AND g.user_id = (SELECT UID FROM userprivateinfo WHERE Username = ?) " +
                             "AND g.subject = ? " +
                             "AND g.semester = ? " +
                             "WHERE m.subject = ? " +
                             "AND m.column_type = 'Performance'";
    try (PreparedStatement performanceStatement = connection.prepareStatement(performanceSql)) {
        performanceStatement.setString(1, studentUsername);
        performanceStatement.setString(2, subject);
        performanceStatement.setString(3, subject);
        performanceStatement.setString(4, "2nd Semester"); // Adjust semester here
        try (ResultSet performanceResult = performanceStatement.executeQuery()) {
            performanceTable.getItems().clear();
            while (performanceResult.next()) {
                String title = performanceResult.getString("column_title");
                boolean checked = performanceResult.getBoolean("checked");
                Performance performance = new Performance(title, checked);
                performanceTable.getItems().add(performance);
            }
        }
    }
}
private void loadDataAndTogglePane(String subject) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    try (Connection connect = DriverManager.getConnection(url, dbuser, dbpass)) {
        if (subject.equals("PROGRAMMING")) {
            loadSubjectprogramming(connect, studentUsername, "PROGRAMMING", assignmentsTable, activitiesTable, performanceTable);
        } else if (subject.equals("WORK IMMERSION")) {
            loadSubjectworkimmersion(connect, studentUsername, "WORK IMMERSION", assignmentsTableimmersion, activitiesTableimmersion, performanceTableimmersion);
        } else if (subject.equals("INQUIRIES, INVESTIGATIONS AND IMMERSION")) {
            loadSubjectresearch(connect, studentUsername, "INQUIRIES, INVESTIGATIONS AND IMMERSION", assignmentsTableresearch, activitiesTableresearch, performanceTableresearch);
        } else if (subject.equals("PERSONALITY DEVELOPMENT")) {
            loadSubjectpersonality(connect, studentUsername, "PERSONALITY DEVELOPMENT", assignmentsTablepersonality, activitiesTablepersonality, performanceTablepersonality);
        } else if (subject.equals("ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES")) {
            loadSubjectenglish(connect, studentUsername, "ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES", assignmentsTableenglish, activitiesTableenglish, performanceTableenglish);
        } else if (subject.equals("CONTEMPORARY PHILIPPINE ARTS FROM THE REGION")) {
            loadSubjectart(connect, studentUsername, "CONTEMPORARY PHILIPPINE ARTS FROM THE REGION", assignmentsTableart, activitiesTableart, performanceTableart);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    programmingPane.setVisible(subject.equals("PROGRAMMING"));
    workImmersionPane.setVisible(subject.equals("WORK IMMERSION"));
    researchpane.setVisible(subject.equals("INQUIRIES, INVESTIGATIONS AND IMMERSION"));
    personalitypane.setVisible(subject.equals("PERSONALITY DEVELOPMENT"));
    englishpane.setVisible(subject.equals("ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES"));
    contemporarypane.setVisible(subject.equals("CONTEMPORARY PHILIPPINE ARTS FROM THE REGION"));
}
 @FXML
private void loadProgramming(ActionEvent event) {
    String subject = "PROGRAMMING";
    loadDataAndTogglePane(subject);
}

@FXML
private void loadWorkImmersion(ActionEvent event) {
    String subject = "WORK IMMERSION";
    loadDataAndTogglePane(subject);
}

@FXML
private void loadResearch(ActionEvent event) {
    String subject = "INQUIRIES, INVESTIGATIONS AND IMMERSION";
    loadDataAndTogglePane(subject);
}

@FXML
private void loadPersonality(ActionEvent event) {
    String subject = "PERSONALITY DEVELOPMENT";
    loadDataAndTogglePane(subject);
}

@FXML
private void loadEnglish(ActionEvent event) {
    String subject = "ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES";
    loadDataAndTogglePane(subject);
}

@FXML
private void loadContemporary(ActionEvent event) {
    String subject = "CONTEMPORARY PHILIPPINE ARTS FROM THE REGION";
    loadDataAndTogglePane(subject);
}
@FXML 
private void closetables(ActionEvent event) {
    programmingPane.setVisible(false);
   workImmersionPane.setVisible(false);
   researchpane.setVisible(false);
   personalitypane.setVisible(false);
   englishpane.setVisible(false);
   contemporarypane.setVisible(false);
}
     
     
     
     
     
     
     
     public void setSemesterStage(Stage semesterStage) {
        this.semesterStage = semesterStage;
    }
     
     //getter
private UserInfo getUserInfo(String studentUsername) throws SQLException {
    UserInfo userInfo = null;
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        String sql = "SELECT UID, Section FROM userprivateinfo WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("UID");
                    String section = resultSet.getString("Section");
                    userInfo = new UserInfo(userId, section);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userInfo;
}
    private static class semesterStage {

       
    }

    }
   