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
    public class firstsemesterICTSubjectController {
     private double MouseX, MouseY = 0;
    @FXML
     private Stage semesterStage;
     @FXML 
     private TableView<Assignment> assignmentsTablefilipino;
     @FXML 
     private TableColumn<Assignment, String> assignmentTitleColumnfilipino;
     @FXML
        private TableColumn<Assignment, Boolean> assignmentCheckedColumnfilipino;

     @FXML 
     private TableView<Activity> activitiesTablefilipino;
      @FXML
        private TableColumn<Activity, String> activityTitleColumnfilipino;
        @FXML
        private TableColumn<Activity, Boolean> activityCheckedColumnfilipino;
     @FXML 
     private TableView<Performance> performanceTablefilipino;
     @FXML
        private TableColumn<Performance, String> performanceTitleColumnfilipino;
        @FXML
        private TableColumn<Performance, Boolean> performanceCheckedColumnfilipino;

         @FXML 
     private TableView<Assignment> assignmentsTablepracresearch;
     @FXML 
     private TableColumn<Assignment, String> assignmentTitleColumnpracresearch;
     @FXML
        private TableColumn<Assignment, Boolean> assignmentCheckedColumnpracresearch;

     @FXML 
     private TableView<Activity> activitiesTablepracresearch;
      @FXML
        private TableColumn<Activity, String> activityTitleColumnpracresearch;
        @FXML
        private TableColumn<Activity, Boolean> activityCheckedColumnpracresearch;
     @FXML 
     private TableView<Performance> performanceTablepracresearch;
     @FXML
        private TableColumn<Performance, String> performanceTitleColumnpracresearch;
        @FXML
        private TableColumn<Performance, Boolean> performanceCheckedColumnpracresearch;
        @FXML 
     private TableView<Assignment> assignmentsTablephilosophy;
     @FXML 
     private TableColumn<Assignment, String> assignmentTitleColumnphilosophy;
     @FXML
        private TableColumn<Assignment, Boolean> assignmentCheckedColumnphilosophy;

     @FXML 
     private TableView<Activity> activitiesTablephilosophy;
      @FXML
        private TableColumn<Activity, String> activityTitleColumnphilosophy;
        @FXML
        private TableColumn<Activity, Boolean> activityCheckedColumnphilosophy;
     @FXML 
     private TableView<Performance> performanceTablephilosophy;
     @FXML
        private TableColumn<Performance, String> performanceTitleColumnphilosophy;
        @FXML
        private TableColumn<Performance, Boolean> performanceCheckedColumnphilosophy;
           @FXML 
     private TableView<Assignment> assignmentsTableliterature;
     @FXML 
     private TableColumn<Assignment, String> assignmentTitleColumnliterature;
     @FXML
        private TableColumn<Assignment, Boolean> assignmentCheckedColumnliterature;

     @FXML 
     private TableView<Activity> activitiesTableliterature;
      @FXML
        private TableColumn<Activity, String> activityTitleColumnliterature;
        @FXML
        private TableColumn<Activity, Boolean> activityCheckedColumnliterature;
     @FXML 
     private TableView<Performance> performanceTableliterature;
     @FXML
        private TableColumn<Performance, String> performanceTitleColumnliterature;
        @FXML
        private TableColumn<Performance, Boolean> performanceCheckedColumnliterature;

       @FXML
        private TableView<Assignment> assignmentsTablecomprog;
        @FXML
        private TableColumn<Assignment, String> assignmentTitleColumncomprog;
        @FXML
        private TableColumn<Assignment, Boolean> assignmentCheckedColumncomprog;

        @FXML
        private TableView<Activity> activitiesTablecomprog;
        @FXML
        private TableColumn<Activity, String> activityTitleColumncomprog;
        @FXML
        private TableColumn<Activity, Boolean> activityCheckedColumncomprog;

        @FXML
        private TableView<Performance> performanceTablecomprog;
        @FXML
        private TableColumn<Performance, String> performanceTitleColumncomprog;
        @FXML
        private TableColumn<Performance, Boolean> performanceCheckedColumncomprog;
        @FXML
    private Pane programmingPane;
    @FXML
    private Pane filipinoPane;
      @FXML
    private Pane researchpane;
    @FXML
    private Pane philosophypane;
      @FXML
    private Pane literaturepane;




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
        
        // Set the submissionStage field
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
                loadSubjectprogramming(connect, studentUsername, "COMPUTER PROGRAMMING", assignmentsTablecomprog, activitiesTablecomprog, performanceTablecomprog);
                loadSubjectworkfilipino(connect, studentUsername, "FILIPINO SA PILING LARANG", assignmentsTablefilipino, activitiesTablefilipino, performanceTablefilipino);
                  loadSubjectresearch(connect, studentUsername, "PRACTICAL RESEARCH", assignmentsTablepracresearch, activitiesTablepracresearch, performanceTablepracresearch);
                    loadSubjectphilosophy(connect, studentUsername, "PHILOSOPHY", assignmentsTablephilosophy, activitiesTablephilosophy, performanceTablephilosophy);
                      loadSubjectliterature(connect, studentUsername, "21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD", assignmentsTableliterature, activitiesTableliterature, performanceTableliterature);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    private void loadSubjectprogramming(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTablecomprog, TableView<Activity> activitiesTablecomprog, TableView<Performance> performanceTablecomprog) throws SQLException {
            loadAssignments(connection, studentUsername, subject, assignmentsTablecomprog);
            loadActivities(connection, studentUsername, subject, activitiesTablecomprog);
            loadPerformance(connection, studentUsername, subject, performanceTablecomprog);
        }
    private void loadSubjectworkfilipino(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTablefilipino, TableView<Activity> activitiesTablefilipino, TableView<Performance> performanceTablefilipino) throws SQLException {
            loadAssignments(connection, studentUsername, subject, assignmentsTablefilipino);
            loadActivities(connection, studentUsername, subject, activitiesTablefilipino);
            loadPerformance(connection, studentUsername, subject, performanceTablefilipino);
        }
    private void loadSubjectresearch(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTablepracresearch, TableView<Activity> activitiesTablepracresearch, TableView<Performance> performanceTablepracresearch) throws SQLException {
            loadAssignments(connection, studentUsername, subject, assignmentsTablepracresearch);
            loadActivities(connection, studentUsername, subject, activitiesTablepracresearch);
            loadPerformance(connection, studentUsername, subject, performanceTablepracresearch);
        }
    private void loadSubjectphilosophy(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTablephilosophy, TableView<Activity> activitiesTablephilosophy, TableView<Performance> performanceTablephilosophy) throws SQLException {
            loadAssignments(connection, studentUsername, subject, assignmentsTablephilosophy);
            loadActivities(connection, studentUsername, subject, activitiesTablephilosophy);
            loadPerformance(connection, studentUsername, subject, performanceTablephilosophy);
        }
    private void loadSubjectliterature(Connection connection, String studentUsername, String subject, TableView<Assignment> assignmentsTableliterature, TableView<Activity> activitiesTableliterature, TableView<Performance> performanceTableliterature) throws SQLException {
            loadAssignments(connection, studentUsername, subject, assignmentsTableliterature);
            loadActivities(connection, studentUsername, subject, activitiesTableliterature);
            loadPerformance(connection, studentUsername, subject, performanceTableliterature);
        }


         public void initialize() {

        setupColumns(assignmentTitleColumncomprog, assignmentCheckedColumncomprog, Assignment.class);
        setupColumns(activityTitleColumncomprog, activityCheckedColumncomprog, Activity.class);
        setupColumns(performanceTitleColumncomprog, performanceCheckedColumncomprog, Performance.class);

        setupColumns(assignmentTitleColumnfilipino, assignmentCheckedColumnfilipino, Assignment.class);
        setupColumns(activityTitleColumnfilipino, activityCheckedColumnfilipino, Activity.class);
        setupColumns(performanceTitleColumnfilipino, performanceCheckedColumnfilipino, Performance.class);

        setupColumns(assignmentTitleColumnpracresearch, assignmentCheckedColumnpracresearch, Assignment.class);
        setupColumns(activityTitleColumnpracresearch, activityCheckedColumnpracresearch, Activity.class);
        setupColumns(performanceTitleColumnpracresearch, performanceCheckedColumnpracresearch, Performance.class);

        setupColumns(assignmentTitleColumnphilosophy, assignmentCheckedColumnphilosophy, Assignment.class);
        setupColumns(activityTitleColumnphilosophy, activityCheckedColumnphilosophy, Activity.class);
        setupColumns(performanceTitleColumnphilosophy, performanceCheckedColumnphilosophy, Performance.class);

        setupColumns(assignmentTitleColumnliterature, assignmentCheckedColumnliterature, Assignment.class);
        setupColumns(activityTitleColumnliterature, activityCheckedColumnliterature, Activity.class);
        setupColumns(performanceTitleColumnliterature, performanceCheckedColumnliterature, Performance.class);



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
                // Check if the item is false (unchecked)
                if (!item) {
                    setText("Incomplete");
                    setStyle("-fx-background-color: #db5c5c;");
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
                            // Handling click events based on row data type
                            if (rowData instanceof Assignment) {
                                Assignment assignment = (Assignment) rowData;
                                String title = assignment.getTitle(); 
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(firstsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            } else if (rowData instanceof Activity) {
                                Activity activity = (Activity) rowData;
                                String title = activity.getTitle();
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(firstsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            } else if (rowData instanceof Performance) {
                                Performance performance = (Performance) rowData;
                                String title = performance.getTitle();
                                try {
                                    openSubmissionScene(title, studentUsername);
                                } catch (SQLException ex) {
                                    Logger.getLogger(firstsemesterICTSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        }
                    });
                } else {
                    setText("Completed");
                    setStyle("-fx-background-color: #5fc257;");
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
            assignmentsStatement.setString(3, "1st Semester"); 
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
            activityStatement.setString(4, "1st Semester"); 
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
            performanceStatement.setString(4, "1st Semester"); 
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
            if (subject.equals("COMPUTER PROGRAMMING")) {
                loadSubjectprogramming(connect, studentUsername, "COMPUTER PROGRAMMING", assignmentsTablecomprog, activitiesTablecomprog, performanceTablecomprog);
            } else if (subject.equals("FILIPINO SA PILING LARANG")) {
                loadSubjectworkfilipino(connect, studentUsername, "FILIPINO SA PILING LARANG", assignmentsTablefilipino, activitiesTablefilipino, performanceTablefilipino);
            } else if (subject.equals("PRACTICAL RESEARCH")) {
                loadSubjectresearch(connect, studentUsername, "PRACTICAL RESEARCH", assignmentsTablepracresearch, activitiesTablepracresearch, performanceTablepracresearch);
            } else if (subject.equals("PHILOSOPHY")) {
                loadSubjectphilosophy(connect, studentUsername, "PHILOSOPHY", assignmentsTablephilosophy, activitiesTablephilosophy, performanceTablephilosophy);
            } else if (subject.equals("21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD")) {
                loadSubjectliterature(connect, studentUsername, "21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD", assignmentsTableliterature, activitiesTableliterature, performanceTableliterature);
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        programmingPane.setVisible(subject.equals("COMPUTER PROGRAMMING"));
        filipinoPane.setVisible(subject.equals("FILIPINO SA PILING LARANG"));
        researchpane.setVisible(subject.equals("PRACTICAL RESEARCH"));
        philosophypane.setVisible(subject.equals("PHILOSOPHY"));
        literaturepane.setVisible(subject.equals("21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD"));

    }
     @FXML
    private void loadProgramming(ActionEvent event) {
        String subject = "COMPUTER PROGRAMMING";
        loadDataAndTogglePane(subject);
    }

    @FXML
    private void loadfilipino(ActionEvent event) {
        String subject = "FILIPINO SA PILING LARANG";
        loadDataAndTogglePane(subject);
    }

    @FXML
    private void loadResearch(ActionEvent event) {
        String subject = "PRACTICAL RESEARCH";
        loadDataAndTogglePane(subject);
    }

    @FXML
    private void loadphilosophy(ActionEvent event) {
        String subject = "PHILOSOPHY";
        loadDataAndTogglePane(subject);
    }

    @FXML
    private void loadliterature(ActionEvent event) {
        String subject = "21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD";
        loadDataAndTogglePane(subject);
    }


    @FXML 
    private void closetables(ActionEvent event) {
        programmingPane.setVisible(false);
       filipinoPane.setVisible(false);
       researchpane.setVisible(false);
       philosophypane.setVisible(false);
       literaturepane.setVisible(false);
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
