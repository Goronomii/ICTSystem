package group1.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherICTCManage {
    
    private double MouseX, MouseY = 0;
    @FXML
    private TextField useridtextfield;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField titlecolumntextfield;
    @FXML 
    private TextField gradetextfield;
    @FXML
    private Button gradebutton;
    @FXML
    private JFXCheckBox checked;
    @FXML 
    private TextField textfielddeletecolumn;
    @FXML
    private Button deletetitlecolumn;
@FXML 
private ChoiceBox Semesters;
@FXML 
private ChoiceBox Subject;
@FXML
private Button addAssignmentColumnButton;

@FXML
private Button addActivityColumnButton;

@FXML
private Button addPerformanceColumnButton;
@FXML
private TextField assignmentColumnTitleTextField; // Assuming you have a TextField for entering the column title for assignments
@FXML
private TextField activityColumnTitleTextField; // Assuming you have a TextField for entering the column title for activities
@FXML
private TextField performanceColumnTitleTextField; // Assuming you have a TextField for entering the column title for performance
      @FXML
    private TableView<Student> ictcTable;
@FXML
private TableColumn<Student, Integer> assignmentColumn;

@FXML
private TableColumn<Student, Integer> activityColumn;

@FXML
private TableColumn<Student, Integer> performanceColumn;
    @FXML
    private TableColumn<Student, Integer> idStudentColumn;

    @FXML
    private TableColumn<Student, String> studentNameColumn;
    
    @FXML 
    private TableView<Student> ictcTable2ndsem;
    
    @FXML
private TableColumn<Student, Integer> assignmentColumn2nd;

@FXML
private TableColumn<Student, Integer> activityColumn2nd;

@FXML
private TableColumn<Student, Integer> performanceColumn2nd;
 @FXML
    private TableColumn<Student, Integer> idStudentColumn2nd;

    @FXML
    private TableColumn<Student, String> studentNameColumn2nd;
    @FXML
    private List<String> addedColumnTitles = new ArrayList<>();
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
private int assignmentColumnIndex;
private int activityColumnIndex ;
private int performanceColumnIndex ;
private int assignmentColumnIndex2nd;
private int activityColumnIndex2nd ;
private int performanceColumnIndex2nd ;
    
    private Button minimizebutton;
    private String username;
    private String teacherUsername;
    @FXML
   private Label namelabel;  
     @FXML private TextField searchbar1stsem;
    @FXML private TextField searchbar2ndsem;
@FXML
    private void close() throws IOException {
        Platform.exit();
    }
      @FXML
private void goToHome(ActionEvent event) throws IOException, SQLException { 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher_home.fxml"));
    Parent root = loader.load();
    TeacherhomepageController controller = loader.getController();    
    controller.initData(teacherUsername);
    Scene scene = new Scene(root);
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    movable(currentStage, scene); 
    currentStage.setScene(scene); 
    currentStage.show();
    ictcTable.getColumns().clear();
    ictcTable2ndsem.getColumns().clear();
    studentData.clear(); 
    addedColumnTitles.clear();
}

    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
    
}
            private void initializeSearchBars() {
    searchbar1stsem.textProperty().addListener((observable, oldValue, newValue) -> {
        filterData(ictcTable, newValue);
    });
    
    searchbar2ndsem.textProperty().addListener((observable, oldValue, newValue) -> {
        filterData(ictcTable2ndsem, newValue);
    });
}

// Filter data in TableView based on search text
private void filterData(TableView<Student> table, String searchText) {
    ObservableList<Student> filteredData = FXCollections.observableArrayList();

    if (searchText.isEmpty()) {
        // If search text is empty, display original data
        filteredData.addAll(studentData);
    } else {
        // Filter data based on search text
        for (Student student : table.getItems()) {
            if (student.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredData.add(student);
            }
        }
    }

    table.setItems(filteredData);
}
@FXML
private void handleAddAssignmentColumn(ActionEvent event) {
    String columnTitle = assignmentColumnTitleTextField.getText();
    String semester = (String) Semesters.getValue();
    String subject = (String) Subject.getValue();
    
    if (validateInput(columnTitle, semester, subject)) {
        if (isColumnExists(columnTitle, subject, semester) || addedColumnTitles.contains(columnTitle)) {
            
            System.out.println("ERROR: Column already exists.");
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Column already exists.", ButtonType.OK);
            alert.showAndWait();
        } else {
            addAssignmentColumn(columnTitle, subject, semester, "Assignment");
            addedColumnTitles.add(columnTitle);
        }
    } else {
        System.out.println("ERROR: Please fill in all required fields.");
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Please fill in all required fields.", ButtonType.OK);
        alert.showAndWait();
    }
}


@FXML
private void handleAddActivityColumn(ActionEvent event) {
    String columnTitle = activityColumnTitleTextField.getText();
    String semester = (String) Semesters.getValue();
    String subject = (String) Subject.getValue();
    
    if (validateInput(columnTitle, semester, subject)) {
        if (isColumnExists(columnTitle, subject, semester) || addedColumnTitles.contains(columnTitle)) {
            System.out.println("ERROR: Column already exists.");
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Column already exists.", ButtonType.OK);
            alert.showAndWait();
        } else {
            addActivityColumn(columnTitle, subject, semester, "Activity");
            addedColumnTitles.add(columnTitle);
        }
    } else {
        System.out.println("ERROR: Please fill in all required fields.");
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Please fill in all required fields.", ButtonType.OK);
        alert.showAndWait();
    }
}

@FXML
private void handleAddPerformanceColumn(ActionEvent event) {
    String columnTitle = performanceColumnTitleTextField.getText();
    String semester = (String) Semesters.getValue();
    String subject = (String) Subject.getValue();
    
    if (validateInput(columnTitle, semester, subject)) {
        if (isColumnExists(columnTitle, subject, semester) || addedColumnTitles.contains(columnTitle)) {
            System.out.println("ERROR: Column already exists.");
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Column already exists.", ButtonType.OK);
            alert.showAndWait();
        } else {
            addPerformanceColumn(columnTitle, subject, semester, "Performance");
            addedColumnTitles.add(columnTitle);
        }
    } else {
        System.out.println("ERROR: Please fill in all required fields.");
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR: Please fill in all required fields.", ButtonType.OK);
        alert.showAndWait();
    }
}

    private void addTableSelectionListener(TableView<Student> table) {
    table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            useridtextfield.setText(String.valueOf(newValue.getId()));
            usernameTextField.setText(newValue.getName());
        }
    });
}

@FXML
private void initialize() throws SQLException  {  
    retrieveColumnMetadataFromDatabase();
    initializeSearchBars(); 
  for (Student student : studentData) {
        fetchGradesForStudent(student);
    }
    assignmentColumn.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText();
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn(columnTitle)).asObject();
    });
    activityColumn.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText();
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn(columnTitle)).asObject();
    });
    performanceColumn.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText();
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn(columnTitle)).asObject();
    });
    assignmentColumn2nd.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText(); 
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn2ndSem(columnTitle)).asObject();
    });
    activityColumn2nd.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText();
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn2ndSem(columnTitle)).asObject();
    });
    performanceColumn2nd.setCellValueFactory(cellData -> {
        String columnTitle = cellData.getTableColumn().getText();
        return new SimpleIntegerProperty(cellData.getValue().getGradeForColumn2ndSem(columnTitle)).asObject();
    });

    ObservableList<String> semesters = FXCollections.observableArrayList("1st Semester", "2nd Semester");
    Semesters.setItems(semesters);
    Semesters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        Subject.getItems().clear();
        if ("1st Semester".equals(newValue)) {
            ObservableList<String> firstSemesterSubjects = FXCollections.observableArrayList(
                "COMPUTER PROGRAMMING", "FILIPINO SA PILING LARANG", "PHILOSOPHY",
                "21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD", "PRACTICAL RESEARCH"
            );
            Subject.setItems(firstSemesterSubjects);
        } else if ("2nd Semester".equals(newValue)) {
            ObservableList<String> secondSemesterSubjects = FXCollections.observableArrayList(
                "PROGRAMMING", "WORK IMMERSION", "INQUIRIES, INVESTIGATIONS AND IMMERSION", 
                "PERSONALITY DEVELOPMENT", "ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES",
                "CONTEMPORARY PHILIPPINE ARTS FROM THE REGION"
            );
            Subject.setItems(secondSemesterSubjects);
        }
    });
    
    addAssignmentColumnButton.setOnAction(this::handleAddAssignmentColumn);
    addActivityColumnButton.setOnAction(this::handleAddActivityColumn);
    addPerformanceColumnButton.setOnAction(this::handleAddPerformanceColumn);
        addTableSelectionListener(ictcTable);
    addTableSelectionListener(ictcTable2ndsem);
    populateICTCTable(); 
}
 private void populateICTCTable() throws SQLException {
     String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "SELECT UID, Username FROM userprivateinfo WHERE Section = 'ICT-C'";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        studentData.clear();

        Set<Integer> userIds = new HashSet<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("UID");
            String name = resultSet.getString("Username");

            if (!userIds.contains(id)) {
                Student student = new Student(id, name);
                fetchGradesForStudent(student);
                
                studentData.add(student);
                userIds.add(id);
            }
        }
        ictcTable.setItems(studentData);
        ictcTable2ndsem.setItems(studentData);
        idStudentColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idStudentColumn2nd.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentNameColumn2nd.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
 private void fetchGradesForStudent(Student student) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "SELECT column_title, grade FROM grades WHERE user_id = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, student.getId());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String columnTitle = resultSet.getString("column_title");
                int grade = resultSet.getInt("grade");

                student.setGradeForColumn(columnTitle, grade);
                student.setGradeForColumn2ndSem(columnTitle, grade);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    ictcTable.refresh();
    ictcTable2ndsem.refresh();
}

private void addAssignmentColumn(String columnTitle, String subject, String semester, String columnType) {
    if ("1st Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable, assignmentColumn, "Assignment");
        insertIntoAssignmentMetadata(columnTitle, subject, semester);
        ictcTable.refresh();
    } else if ("2nd Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable2ndsem, assignmentColumn2nd, "Assignment");   
        insertIntoAssignmentMetadata(columnTitle, subject, semester);
        ictcTable2ndsem.refresh();
    }
}

private void addActivityColumn(String columnTitle, String subject, String semester, String columnType) {
    if ("1st Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable, activityColumn, "Activity");
        insertIntoActivityMetadata(columnTitle, subject, semester);
          ictcTable.refresh();
    } else if ("2nd Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable2ndsem, activityColumn2nd, "Activity");
        insertIntoActivityMetadata(columnTitle, subject, semester);
        ictcTable2ndsem.refresh();
    }
}

private void addPerformanceColumn(String columnTitle, String subject, String semester, String columnType) {
    if ("1st Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable, performanceColumn, "Performance");
        insertIntoPerformanceMetadata(columnTitle, subject, semester);
        ictcTable.refresh();
    } else if ("2nd Semester".equals(semester)) {
        addColumnToTable(columnTitle, subject, semester, ictcTable2ndsem, performanceColumn2nd, "Performance");
        insertIntoPerformanceMetadata(columnTitle, subject, semester);
        ictcTable2ndsem.refresh();
    }
}

private void insertIntoAssignmentMetadata(String columnTitle, String subject, String semester) {
    insertIntoMetadata("assignments", columnTitle, subject, semester);
}

private void insertIntoActivityMetadata(String columnTitle, String subject, String semester) {
    insertIntoMetadata("activities", columnTitle, subject, semester);
}

private void insertIntoPerformanceMetadata(String columnTitle, String subject, String semester) {
    insertIntoMetadata("performance", columnTitle, subject, semester);
}

private void insertIntoMetadata(String tableName, String columnTitle, String subject, String semester) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String insertSql = "INSERT INTO " + tableName + " (title, subject, semester) VALUES (?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            insertStatement.setString(1, columnTitle);
            insertStatement.setString(2, subject);
            insertStatement.setString(3, semester);
            insertStatement.executeUpdate();
        }
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Failed to insert data into " + tableName, ButtonType.OK);
        alert.showAndWait();
        e.printStackTrace();
    }
}

private void addColumnToTable(String columnTitle, String subject, String semester, TableView<Student> table, TableColumn<Student, Integer> column, String columnType) {
    String finalColumnTitle = columnTitle.replaceAll("[^a-zA-Z0-9_#]", "_");

    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String insertSql = "INSERT INTO added_columns_metadata (column_title, column_type, subject, semester) VALUES (?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            insertStatement.setString(1, finalColumnTitle);
            insertStatement.setString(2, columnType);
            insertStatement.setString(3, subject);
            insertStatement.setString(4, semester);
            insertStatement.executeUpdate();
        }
        saveColumnMetadataToDatabase(finalColumnTitle, columnType, subject, semester, "ICT-B");

        // Create and configure the new TableColumn
        TableColumn<Student, Integer> newColumn = new TableColumn<>(finalColumnTitle);
        newColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getGradeForColumn(finalColumnTitle)).asObject());

        // Add the new column to the appropriate parent
        if ("Assignment".equals(columnType)) {
            if ("1st Semester".equals(semester)) {
                assignmentColumn.getColumns().add(newColumn);
            } else if ("2nd Semester".equals(semester)) {
                assignmentColumn2nd.getColumns().add(newColumn);
            }
        } else if ("Activity".equals(columnType)) {
            if ("1st Semester".equals(semester)) {
                activityColumn.getColumns().add(newColumn);
            } else if ("2nd Semester".equals(semester)) {
                activityColumn2nd.getColumns().add(newColumn);
            }
        } else if ("Performance".equals(columnType)) {
            if ("1st Semester".equals(semester)) {
                performanceColumn.getColumns().add(newColumn);
            } else if ("2nd Semester".equals(semester)) {
                performanceColumn2nd.getColumns().add(newColumn);
            }
        }
        
        table.refresh();
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Duplicate column title", ButtonType.OK);
        alert.showAndWait();
        e.printStackTrace();
    }
}

public void initData(String teacherUsername) throws SQLException {
    this.teacherUsername = teacherUsername;
    namelabel.setText(teacherUsername);

    
    retrieveColumnMetadataFromDatabase();
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
     
private void retrieveColumnMetadataFromDatabase() {
    addedColumnTitles.clear();
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "SELECT acm.column_title, acm.column_type, acm.subject, acm.semester " +
                 "FROM added_columns_metadata acm " +
                 "INNER JOIN teachersectionssubject tss ON acm.subject = tss.subject " +
                 "WHERE tss.teacherUsername = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, teacherUsername);

        try (ResultSet resultSet = statement.executeQuery()) {
            Set<String> assignmentColumns = new HashSet<>();
            Set<String> activityColumns = new HashSet<>();
            Set<String> performanceColumns = new HashSet<>();

           while (resultSet.next()) {
    String columnTitle = resultSet.getString("column_title");
    String columnType = resultSet.getString("column_type");
    String subject = resultSet.getString("subject");
    String semester = resultSet.getString("semester");

    switch (columnType) {
        case "Assignment":
            if (!assignmentColumns.contains(columnTitle)) {
                addAssignmentColumn(columnTitle, subject, semester, "Assignment");
                assignmentColumns.add(columnTitle);
                addedColumnTitles.add(columnTitle); // Add to addedColumnTitles set
            }
            break;
        case "Activity":
            if (!activityColumns.contains(columnTitle)) {
                addActivityColumn(columnTitle, subject, semester, "Activity");
                activityColumns.add(columnTitle);
                addedColumnTitles.add(columnTitle); // Add to addedColumnTitles set
            }
            break;
        case "Performance":
            if (!performanceColumns.contains(columnTitle)) {
                addPerformanceColumn(columnTitle, subject, semester, "Performance");
                performanceColumns.add(columnTitle);
                addedColumnTitles.add(columnTitle); // Add to addedColumnTitles set
            }
            break;
        default:
            System.out.println("Invalid column type");
    }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private boolean validateInput(String columnTitle, String semester, String subject) {
    if (columnTitle == null || columnTitle.isEmpty() ||
        semester == null || semester.isEmpty() ||
        subject == null || subject.isEmpty()) {
        return false;
    }
        return true;
}
private boolean isColumnExists(String columnTitle, String subject, String semester) {
    return addedColumnTitles.contains(columnTitle);
}
private void saveColumnMetadataToDatabase(String columnTitle, String columnType, String subject, String semester, String section) {
    if (!addedColumnTitles.contains(columnTitle)) {
        String url = "jdbc:mysql://localhost:3306/usersinfo";
        String dbuser = "root";
        String dbpass = "Rootpassword123";
        String sql = "INSERT INTO added_columns_metadata (column_title, column_type, subject, semester, section) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, columnTitle);
            statement.setString(2, columnType);
            statement.setString(3, subject);
            statement.setString(4, semester);
            statement.setString(5, section); 
            statement.executeUpdate();
            
            addedColumnTitles.add(columnTitle); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Column already exists: " + columnTitle);
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Duplicate column title", ButtonType.OK);
        alert.showAndWait();
    }
}
@FXML
private void deleteColumn(ActionEvent event) throws SQLException {
    String columnTitleToDelete = textfielddeletecolumn.getText();
    if (columnTitleToDelete != null && !columnTitleToDelete.isEmpty()) {
        int rowsAffected = deleteColumnDataFromTables(columnTitleToDelete);
        if (rowsAffected == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Column not found in any table.", ButtonType.OK);
            alert.showAndWait();
        } else {
            deleteColumnMetadataFromDatabase(columnTitleToDelete);
            deletegradesfromdatabase(columnTitleToDelete);
            refreshScene(event);
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the column title to delete.", ButtonType.OK);
        alert.showAndWait();
    } 
}
private void deletegradesfromdatabase(String columnTitleToDelete) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    String deletegradessql = "DELETE FROM grades WHERE column_title = ?";
    
    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement deletegradessqlstatement = connection.prepareStatement(deletegradessql)) {
     
        deletegradessqlstatement.setString(1, columnTitleToDelete);

        int rowsAffected = deletegradessqlstatement.executeUpdate();

        System.out.println("Rows affected: " + rowsAffected);
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private void deleteColumnMetadataFromDatabase(String columnTitleToDelete) {
    // Delete the column metadata from the database
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "DELETE FROM added_columns_metadata WHERE column_title = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, columnTitleToDelete);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private int deleteColumnDataFromTables(String columnTitleToDelete) {
    int totalRowsAffected = 0;

    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    String[] tableNames = {"assignments", "activities", "performance"};

    String deleteSql = "DELETE FROM %s WHERE title = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        for (String tableName : tableNames) {
            String formattedSql = String.format(deleteSql, tableName);
            try (PreparedStatement deleteStatement = connection.prepareStatement(formattedSql)) {
                deleteStatement.setString(1, columnTitleToDelete);
                int rowsAffected = deleteStatement.executeUpdate();
                totalRowsAffected += rowsAffected;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRowsAffected;
}
private void refreshScene(ActionEvent event) throws SQLException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/ICT-12Bteachermanagement.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.show();
        movable(currentStage, scene);
        
        // Get controller from the loaded FXML and initialize data
        TeacherICTCManage controller = loader.getController();
        controller.initData(teacherUsername); // Assuming teacherUsername is accessible here
    } catch (IOException ex) {
        Logger.getLogger(TeacherICTCManage.class.getName()).log(Level.SEVERE, null, ex);
    }
}
@FXML
private void handleGradeButton(ActionEvent event) throws SQLException {
    String columnTitle = titlecolumntextfield.getText();
    String grade = gradetextfield.getText();
    
    // Fetch semester and subject from database based on columnTitle
    Map<String, String> metadata = getColumnMetadata(columnTitle);
    if (metadata == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid column title. Metadata not found.", ButtonType.OK);
        alert.showAndWait();
        return;
    }
    String semester = metadata.get("semester");
    String subject = metadata.get("subject");

    System.out.println("Retrieved values:");
    System.out.println("Column Title: " + columnTitle);
    System.out.println("Grade: " + grade);
    System.out.println("Semester: " + semester);
    System.out.println("Subject: " + subject);

    TableView<Student> selectedTable = null;

    if (ictcTable.getSelectionModel().getSelectedItem() != null) {
        selectedTable = ictcTable;
    } else if (ictcTable2ndsem.getSelectionModel().getSelectedItem() != null) {
        selectedTable = ictcTable2ndsem;
    }

    if (selectedTable != null) {
        Student selectedStudent = selectedTable.getSelectionModel().getSelectedItem();
        int studentId = selectedStudent.getId();

        if (isColumnExists(columnTitle, subject, semester)) {
            storeGrade(columnTitle, grade, semester, subject, studentId);
            updateStudentData(selectedTable, studentId, columnTitle, Integer.parseInt(grade));
            selectedTable.refresh();
            useridtextfield.clear();
            usernameTextField.clear();
            titlecolumntextfield.clear();
            gradetextfield.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Column title does not exist or is invalid.", ButtonType.OK);
            alert.showAndWait();
            System.out.println("Column titles fetched from database: " + addedColumnTitles);
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a student.", ButtonType.OK);
        alert.showAndWait();
    }
}

private void displayGrade(TableView<Student> table, String columnTitle, String grade) throws SQLException {
    Student selectedStudent = table.getSelectionModel().getSelectedItem();
    if (selectedStudent != null) {
        int studentId = selectedStudent.getId();
        String semester = (String) Semesters.getValue();
        String subject = (String) Subject.getValue();
        int fetchedGrade = fetchGradeFromDatabase(studentId, columnTitle, subject, semester);
        
        TableView<Student> selectedTable = null;
if (ictcTable.getSelectionModel().getSelectedItem() != null) {
    selectedTable = ictcTable;
} else if (ictcTable2ndsem.getSelectionModel().getSelectedItem() != null) {
    selectedTable = ictcTable2ndsem;
}
        if (fetchedGrade != 0) {  
            if (table == ictcTable) {
                selectedStudent.setGradeForColumn(columnTitle, fetchedGrade);
                updateStudentData(selectedTable, studentId, columnTitle, Integer.parseInt(grade));
                ictcTable.refresh();
            } else if (table == ictcTable2ndsem) {
                selectedStudent.setGradeForColumn2ndSem(columnTitle, fetchedGrade);
                updateStudentData(selectedTable, studentId, columnTitle, Integer.parseInt(grade));
                ictcTable2ndsem.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Grade not found for the selected column.", ButtonType.OK);
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a student.", ButtonType.OK);
        alert.showAndWait();
    }
}
private int fetchGradeFromDatabase(int studentId, String columnTitle, String subject, String semester) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "SELECT grade FROM grades WHERE user_id = ? AND column_title = ? AND subject = ? AND semester = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, studentId);
        statement.setString(2, columnTitle);
        statement.setString(3, subject);
        statement.setString(4, semester);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("grade");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

private void updateStudentData(TableView<Student> table, int studentId, String columnTitle, int grade) {
    for (Student student : table.getItems()) {
        if (student.getId() == studentId) {
            if (table == ictcTable) {
                student.setGradeForColumn(columnTitle, grade);
            } else if (table == ictcTable2ndsem) {
                student.setGradeForColumn2ndSem(columnTitle, grade);
            }
            break;
        }
    }
}
private void storeGrade(String columnTitle, String grade, String semester, String subject, int studentId) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sqlSelect = "SELECT grade FROM grades WHERE user_id = ? AND column_title = ? AND subject = ? AND semester = ?";
    String sqlUpdate = "UPDATE grades SET grade = ? WHERE user_id = ? AND column_title = ? AND subject = ? AND semester = ?";
    String sqlInsert = "INSERT INTO grades (user_id, column_title, grade, subject, semester, checked) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
         PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
         PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

        // Check if the grade exists
        selectStatement.setInt(1, studentId);
        selectStatement.setString(2, columnTitle);
        selectStatement.setString(3, subject);
        selectStatement.setString(4, semester);

        System.out.println("Executing select statement to check for existing grade...");
        System.out.println("SELECT statement: " + sqlSelect);
        System.out.println("Parameters: studentId = " + studentId + ", columnTitle = " + columnTitle + ", subject = " + subject + ", semester = " + semester);

        boolean gradeExists = false;

        try (ResultSet resultSet = selectStatement.executeQuery()) {
            if (resultSet.next()) {
                gradeExists = true;
                System.out.println("Grade exists for student ID: " + studentId + ", column: " + columnTitle + ", subject: " + subject + ", semester: " + semester);
            } else {
                System.out.println("No existing grade found for student ID: " + studentId + ", column: " + columnTitle + ", subject: " + subject + ", semester: " + semester);
            }
        }

        if (gradeExists) {
            // Grade exists, update the existing grade
            System.out.println("Updating existing grade for student ID: " + studentId + ", column: " + columnTitle);
            updateStatement.setInt(1, Integer.parseInt(grade));
            updateStatement.setInt(2, studentId);
            updateStatement.setString(3, columnTitle);
            updateStatement.setString(4, subject);
            updateStatement.setString(5, semester);
            updateStatement.executeUpdate();
        } else {
            // Grade does not exist, insert a new grade
            System.out.println("Inserting new grade for student ID: " + studentId + ", column: " + columnTitle);
            insertStatement.setInt(1, studentId);
            insertStatement.setString(2, columnTitle);
            insertStatement.setInt(3, Integer.parseInt(grade));
            insertStatement.setString(4, subject);
            insertStatement.setString(5, semester);
            insertStatement.setBoolean(6, true); 
            insertStatement.executeUpdate();
        }

        // Update the corresponding Student object with the new grade
        TableView<Student> selectedTable = null;
        if (ictcTable.getSelectionModel().getSelectedItem() != null) {
            selectedTable = ictcTable;
        } else if (ictcTable2ndsem.getSelectionModel().getSelectedItem() != null) {
            selectedTable = ictcTable2ndsem;
        }
        updateStudentData(selectedTable, studentId, columnTitle, Integer.parseInt(grade));

        // Refresh the TableView
        if (selectedTable != null) {
            selectedTable.refresh();
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private Map<String, String> getColumnMetadata(String columnTitle) throws SQLException {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    String sql = "SELECT subject, semester FROM added_columns_metadata WHERE column_title = ?";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, columnTitle);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String subject = resultSet.getString("subject");
                String semester = resultSet.getString("semester");
                Map<String, String> metadata = new HashMap<>();
                metadata.put("subject", subject);
                metadata.put("semester", semester);
                return metadata;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
   private int getUserId(String teacherUsername) throws SQLException {
  int userId = -1;
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        String sql = "SELECT UID FROM userprivateinfo WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacherUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("UID");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userId;
   }
  }
