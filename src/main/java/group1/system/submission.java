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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
    import java.net.URL;
    import java.sql.Statement;
    import java.util.ResourceBundle;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
    import javafx.scene.control.TableCell;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.Pane;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.control.cell.TextFieldTableCell;
    import javafx.scene.input.MouseButton;
    import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
    public class submission {
     private double MouseX, MouseY = 0;
     private String[] titles;
    @FXML
      Stage submissionStage;
    @FXML
    private Button insertfilebutton;
    @FXML
    private ChoiceBox teacherChoiceBox;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton SubmitButton;
    @FXML
    private Label submitlabel;
    @FXML
        private Button minimizebutton;
        private String studentUsername;
        private String title;
    @FXML
       private Label namelabel;
    @FXML
       private Label titlename;
    @FXML 
     private Label filenamelabel;
    @FXML 
        public void close() throws IOException {
         submissionStage.close();

        }

        @FXML
        private void minimize_clicked(MouseEvent event) {
            Stage stage = (Stage) minimizebutton.getScene().getWindow();
            stage.setIconified(true);
        }

@FXML
public void initData(String title, String studentUsername) throws SQLException {
    this.title = title;
    this.studentUsername = studentUsername;
    namelabel.setText(studentUsername);
    
    String subjectTitle = getSubjectTitleFromMetadata();
    titlename.setText(title);
}
       public void initialize() {

       populateTeacherChoiceBox();   
    }
@FXML
private void handleInsertFileButton(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose File");
    FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
    FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
    fileChooser.getExtensionFilters().addAll(imageFilter, pdfFilter);
    File selectedFile = fileChooser.showOpenDialog(insertfilebutton.getScene().getWindow());
    if (selectedFile != null) {
        filenamelabel.setText(selectedFile.getAbsolutePath()); 
    }
}
@FXML
private void handleSubmitButton(ActionEvent event) {
    String fileName = filenamelabel.getText();
    if (!fileName.isEmpty() && !teacherChoiceBox.getSelectionModel().isEmpty()) {
        File file = new File(fileName);
        if (file.exists()) {
            String teacherUsername = teacherChoiceBox.getValue().toString();
            saveFileToDatabase(file, studentUsername, teacherUsername);
            submitlabel.setText("Submission Successful");
        } else {
            submitlabel.setText("File not found");
        }
    } else if (teacherChoiceBox.getSelectionModel().isEmpty()) {
        submitlabel.setText("Please select a teacher");
    } else {
        submitlabel.setText("Please insert a file");
    }
    submissionStage.close();
}   
private void saveFileToDatabase(File file, String studentUsername, String teacherUsername) {
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

 String assignmentTitle = null;
String activityTitle = null;
String performanceTitle = null;

String columnType = getColumnTypeFromMetadata(title);

    if (columnType == null) {
        // Handle the case where columnType is null
        System.err.println("Column type is null");
        return; // Return early
    }
    
switch (columnType) {
    case "Assignment":
        assignmentTitle = title;
        break;
    case "Activity":
        activityTitle = title;
        break;
    case "Performance":
        performanceTitle = title;
        break;
    default:
        // Handle default case if needed
        break;
}
    
    String subject = getSubjectFromMetadata(this.title);
    String section = getSectionFromUsername(studentUsername);

    try {
        Connection connection = DriverManager.getConnection(url, dbuser, dbpass);
        int userId = getUserIdFromUsername(connection, studentUsername);

        FileInputStream fis = new FileInputStream(file);
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Submission (user_id, TeacherUsername, subject, Section, file_name, file_data, feedback, assignment_title, activity_title, performance_title, submission_date, submission_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, userId);
        ps.setString(2, teacherUsername);
        if (subject != null) {
            ps.setString(3, subject);
        }
        ps.setString(4, section);
        ps.setString(5, file.getName());
        ps.setBinaryStream(6, fis, (int) file.length());
        ps.setString(7, description.getText());
        ps.setString(8, assignmentTitle);
        ps.setString(9, activityTitle);
        ps.setString(10, performanceTitle);
        ps.setDate(11, java.sql.Date.valueOf(java.time.LocalDate.now())); 
        ps.setTime(12, java.sql.Time.valueOf(java.time.LocalTime.now())); 
        ps.executeUpdate();
        ps.close();
        fis.close();
        connection.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private String getColumnTypeFromMetadata(String title) {
    String columnType = null;
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersinfo", "root", "Rootpassword123")) {
        String query = "SELECT DISTINCT column_type FROM added_columns_metadata WHERE column_title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    columnType = resultSet.getString("column_type");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return columnType;
}
private int getUserIdFromUsername(Connection connection, String studentUsername) throws SQLException {
    int userId = -1; 

    String query = "SELECT UID FROM userprivateinfo WHERE Username = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, studentUsername);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                userId = resultSet.getInt("UID");
            }
        }
    }

    return userId;
}
private String getSectionFromUsername(String studentUsername) {
    String section = null;
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        String query = "SELECT Section FROM userprivateinfo WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    section = resultSet.getString("Section");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return section;
}
      private void populateTeacherChoiceBox() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersinfo", "root", "Rootpassword123")) {
            String query = "SELECT DISTINCT teacherUsername FROM teachersectionssubject";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    ObservableList<String> teacherList = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        teacherList.add(resultSet.getString("teacherUsername"));
                    }   
                    teacherChoiceBox.setItems(teacherList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTeacherSelection() {
        Object selectedTeacherObject = teacherChoiceBox.getValue();
        if (selectedTeacherObject != null) {
            String selectedTeacher = (String) selectedTeacherObject;
            System.out.println("Selected Teacher: " + selectedTeacher);
           
        }
    }

private String getSubjectTitleFromMetadata() {
    String subjectTitle = null;
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersinfo", "root", "Rootpassword123")) {
        String query = "SELECT DISTINCT column_title FROM added_columns_metadata WHERE column_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "Subject");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subjectTitle = resultSet.getString("column_title");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return subjectTitle;
}

private String getSubjectFromMetadata(String subjectTitle) {
    String subject = null;
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersinfo", "root", "Rootpassword123")) {
        String query = "SELECT subject FROM added_columns_metadata WHERE column_title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, subjectTitle);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subject = resultSet.getString("subject");
                    System.out.println("Subject from metadata: " + subject);
                } else {
                    System.out.println("No subject found in metadata for title: " + subjectTitle);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return subject;
}

   }