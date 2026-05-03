/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Delmark Lusterio
 */
public class Deleteuser {
     private Stage stage;
    private double MouseX, MouseY = 0;
   @FXML
    private TableView<User> StudentTableView;
    @FXML
    private TableColumn<User, Integer> UIDstudents;
    @FXML
    private TableColumn<User, String> Namestudents;
    @FXML
    private TableColumn<User, String> LRNstudents;
    @FXML
    private TableColumn<User, String> Emailstudents;
    @FXML
    private TableColumn<User, Integer> agestudents;
    @FXML
    private TableColumn<User, String> sectionsStudents;
   @FXML
    private TableView<User> TeacherTableView;
    @FXML
    private TableColumn<User, Integer> UIDteachers;
    @FXML
    private TableColumn<User, String> Nameteachers;
    @FXML
    private TableColumn<User, String> Emailteachers;
@FXML
private TextField Searchfield;
@FXML
private Button Deleteuser;
 

    @FXML
    public void initialize() {
       UIDstudents.setCellValueFactory(new PropertyValueFactory<>("UID"));
Namestudents.setCellValueFactory(new PropertyValueFactory<>("Username"));
LRNstudents.setCellValueFactory(new PropertyValueFactory<>("LRN"));
Emailstudents.setCellValueFactory(new PropertyValueFactory<>("Email"));
agestudents.setCellValueFactory(new PropertyValueFactory<>("Age"));
sectionsStudents.setCellValueFactory(new PropertyValueFactory<>("Section"));

UIDteachers.setCellValueFactory(new PropertyValueFactory<>("UID"));
Nameteachers.setCellValueFactory(new PropertyValueFactory<>("Username"));
Emailteachers.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        // Fetch and display data for both students and teachers
        fetchAndDisplayStudentData();
        fetchAndDisplayTeacherData();
        
          Searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
        filterStudentTable(newValue);
    });

    // Add event listener to search field for teachers
    Searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
        filterTeacherTable(newValue);
    });

    }
private void filterStudentTable(String keyword) {
    ObservableList<User> filteredList = FXCollections.observableArrayList();
    if (keyword == null || keyword.trim().isEmpty()) {
        fetchAndDisplayStudentData(); // Reset the table to display all data
        return;
    }
    for (User user : StudentTableView.getItems()) {
        if (userMatchesKeyword(user, keyword)) {
            filteredList.add(user);
        }
    }
    StudentTableView.setItems(filteredList);
}

private void filterTeacherTable(String keyword) {
    ObservableList<User> filteredList = FXCollections.observableArrayList();
    if (keyword == null || keyword.trim().isEmpty()) {
        fetchAndDisplayTeacherData(); // Reset the table to display all data
        return;
    }
    for (User user : TeacherTableView.getItems()) {
        if (userMatchesKeyword(user, keyword)) {
            filteredList.add(user);
        }
    }
    TeacherTableView.setItems(filteredList);
}

private boolean userMatchesKeyword(User user, String keyword) {
    String lowerCaseKeyword = keyword.toLowerCase();
    String lrn = user.getLRN();
    String email = user.getEmail();
    String section = user.getSection();

    return (lrn != null && lrn.toLowerCase().contains(lowerCaseKeyword)) ||
           (email != null && email.toLowerCase().contains(lowerCaseKeyword)) ||
           (section != null && section.toLowerCase().contains(lowerCaseKeyword));
}   
private void fetchAndDisplayStudentData() {
    String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
    String user = "root";
    String pass = "Rootpassword123";
    try {
        Connection conn = DriverManager.getConnection(sqllink, user, pass);
        String query = "SELECT UID, Username, LRN, Email, Age, Section FROM userprivateinfo WHERE Role = 'Student'";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        ObservableList<User> userList = FXCollections.observableArrayList();
        while (rs.next()) {
    User student = new User(
            rs.getInt("UID"),
            rs.getString("Username"),
            rs.getString("LRN"),
            rs.getString("Email"),
            rs.getInt("Age"),
            rs.getString("Section")
    );
    userList.add(student);
}
StudentTableView.setItems(userList);
StudentTableView.refresh();

        rs.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void fetchAndDisplayTeacherData() {
         String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
    String user = "root";
    String pass = "Rootpassword123";
        try {
            Connection conn = DriverManager.getConnection(sqllink, user, pass);
            String query = "SELECT UID, Username, Email FROM userprivateinfo WHERE Role = 'Teacher'";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            ObservableList<User> teacherList = FXCollections.observableArrayList();
          while (rs.next()) {
    User teacher = new User(
            rs.getInt("UID"),
            rs.getString("Username"),
            null, 
            rs.getString("Email"),
            0,
            null
    );
    teacherList.add(teacher);
}
TeacherTableView.setItems(teacherList);
TeacherTableView.refresh();

            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@FXML
private void deleteUser() {
    // Get the selected user from either the StudentTableView or TeacherTableView
    User selectedUser = null;
    TableView<User> selectedTableView = null;
    
    if (StudentTableView.getSelectionModel().getSelectedItem() != null) {
        selectedUser = StudentTableView.getSelectionModel().getSelectedItem();
        selectedTableView = StudentTableView;
    } else if (TeacherTableView.getSelectionModel().getSelectedItem() != null) {
        selectedUser = TeacherTableView.getSelectionModel().getSelectedItem();
        selectedTableView = TeacherTableView;
    }
    
    if (selectedUser == null) {
        // No user selected, show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a user to delete.");
        alert.showAndWait();
        return;
    }

    // Prompt the user for confirmation
    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("Are you sure you want to delete this user?");
    Optional<ButtonType> result = confirmationAlert.showAndWait();

    // If the user confirms the deletion, proceed with deleting the user
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Execute SQL delete statement
        String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
        String user = "root";
        String pass = "Rootpassword123";
        try (Connection conn = DriverManager.getConnection(sqllink, user, pass)) {
            String query = "DELETE FROM userprivateinfo WHERE UID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, selectedUser.getUID());
            statement.executeUpdate();

            // Remove the user from the appropriate TableView
            if (selectedTableView != null) {
                ObservableList<User> items = selectedTableView.getItems();
                items.remove(selectedUser);
                selectedTableView.setItems(items);
            }

            // Show success message
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User deleted successfully.");
            successAlert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error message if deletion fails
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Failed to delete user.");
            errorAlert.showAndWait();
        }
    }
}
}
