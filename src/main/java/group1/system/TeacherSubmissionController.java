/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;


import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
public class TeacherSubmissionController {
     private double MouseX, MouseY = 0;
     @FXML
    private WebView webviewfiles;


    @FXML
    private TableView<submissiondata> submissiontable;

    @FXML
    private TableColumn<submissiondata, String> studentNameColumn;

    @FXML
    private TableColumn<submissiondata, String> sectionColumn;

    @FXML
    private TableColumn<submissiondata, String> subjectColumn;

    @FXML
    private TableColumn<submissiondata, String> titleColumn;

    @FXML
    private TableColumn<submissiondata, String> fileNameColumn;

    @FXML
    private TableColumn<submissiondata, String> dateColumn;

    @FXML
    private TableColumn<submissiondata, String> timeColumn;
    @FXML
         
    private Button minimizebutton;
    private double webzoom;
    private String teacherUsername;
    @FXML 
    private Label namelabel;
        @FXML
    private void initialize() throws SQLException {
    studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
    subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("submissionDate")); 
    timeColumn.setCellValueFactory(new PropertyValueFactory<>("submissionTime")); 
    
    populateTableFromDatabase();
    }

       public void initData(String teacherUsername) {
    this.teacherUsername = teacherUsername;
    namelabel.setText(teacherUsername);
    try {
        populateTableFromDatabase(); // Call populateTableFromDatabase after setting teacherUsername
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
        @FXML
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
@FXML
private void goToHome(ActionEvent event) throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher_home.fxml"));
    Parent root = loader.load();
    TeacherhomepageController controller = loader.getController();    
    controller.initData(teacherUsername); // Pass the teacher's username back
    Scene scene = new Scene(root);
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    movable(currentStage, scene); 
    currentStage.setScene(scene); 
    currentStage.show();
}
 @FXML
    private void close() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit, or Cancel to stay.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
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
private void populateTableFromDatabase() throws SQLException {
    List<submissiondata> submissions = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String username = "root";
    String password = "Rootpassword123";

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
        String query = "SELECT s.*, u.Username AS studentName " +
                       "FROM Submission s " +
                       "INNER JOIN userprivateinfo u ON s.user_id = u.UID " +
                       "WHERE s.TeacherUsername = ?";  // Add condition to filter by teacher's username

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teacherUsername);  // Set the parameter for the teacher's username
            ResultSet resultSet = statement.executeQuery(); // Retrieve the resultSet

            while (resultSet.next()) {
                String studentName = resultSet.getString("studentName");
                String section = resultSet.getString("Section");
                String subject = resultSet.getString("subject");
                String assignmentTitle = resultSet.getString("assignment_title");
                String activityTitle = resultSet.getString("activity_title");
                String performanceTitle = resultSet.getString("performance_title");
                String fileName = resultSet.getString("file_name");
                String submissionDate = resultSet.getString("submission_date");
                String submissionTime = resultSet.getString("submission_time");
                byte[] fileData = resultSet.getBytes("file_data");

                // Determine the appropriate title based on availability
                String title = assignmentTitle != null ? assignmentTitle :
                               activityTitle != null ? activityTitle :
                               performanceTitle != null ? performanceTitle :
                               "No Title Available";

                // Create submissiondata object with all required data
                submissiondata submission = new submissiondata(studentName, section, subject, title, fileName, submissionDate, submissionTime, fileData);
                submissions.add(submission);
            }
        }
    }

    // Set the items of the table with the submissions list
    submissiontable.setItems(FXCollections.observableArrayList(submissions));
}
@FXML
private void handleSubmissionSelection() {
    submissiondata selectedSubmission = submissiontable.getSelectionModel().getSelectedItem();
    if (selectedSubmission != null) {
        displayFileInWebView(selectedSubmission.getFileData(), selectedSubmission.getFileName());
        
    }
}
public void zoomin() {
 
    webviewfiles.setZoom(0.95); 
}
public void zoomout() {
    
    webviewfiles.setZoom(0.50); 
}


private void displayFileInWebView(byte[] fileData, String fileName) {
    if (fileData != null && fileName != null) {
        String base64Data = java.util.Base64.getEncoder().encodeToString(fileData);

        String mimeType = "";
        if (fileName.toLowerCase().endsWith(".pdf")) {
            mimeType = "application/pdf";
        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            mimeType = "image/jpeg";
        } else if (fileName.toLowerCase().endsWith(".png")) {
            mimeType = "image/png";
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            mimeType = "image/gif";
        }

        String html;
        if (mimeType.startsWith("image")) {
            html = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=10.0, minimum-scale=0.1, user-scalable=yes\"></head><body><img src='data:" + mimeType + ";base64," + base64Data + "' style='max-width:100%;height:auto;'></body></html>";
        } else if (mimeType.equals("application/pdf")) {
            html = "<html><body><embed width='100%' height='100%' src='data:" + mimeType + ";base64," + base64Data + "' type='" + mimeType + "'></embed></body></html>";
        } else {
            html = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=10.0, minimum-scale=0.1, user-scalable=yes\"></head><body><embed width='100%' height='100%' src='data:" + mimeType + ";base64," + base64Data + "' type='" + mimeType + "'></embed></body></html>";
        }

        // Print out MIME type and HTML content for debugging
        System.out.println("MIME Type: " + mimeType);
        System.out.println("HTML Content: " + html);

        // Load the HTML content into the WebView
        WebEngine engine = webviewfiles.getEngine();
     
        engine.loadContent(html);
    } else {
        System.out.println("File data or file name is null.");
    }
}

}
