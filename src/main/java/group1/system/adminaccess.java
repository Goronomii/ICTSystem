/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import com.jfoenix.controls.JFXComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Delmark Lusterio
 */
public class adminaccess {
   @FXML private TextField nameteachertextfield;
    @FXML private JFXComboBox<String> sectionCombobox;
    @FXML private JFXComboBox<String> Semestercombobox;
    @FXML private JFXComboBox<String> SubjectCombobox;

    private final String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
    private final String user = "root";
    private final String pass = "Rootpassword123";

    @FXML
    public void initialize() {
        sectionCombobox.getItems().addAll("ICT-A", "ICT-B", "ICT-C");
        Semestercombobox.getItems().addAll("First Semester", "Second Semester");

        // Add a listener to Semestercombobox to populate SubjectCombobox based on selected semester
        Semestercombobox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateSubjectCombobox(newValue);
            }
        });
    }

    private void updateSubjectCombobox(String semester) {
        SubjectCombobox.getItems().clear();
        if ("First Semester".equals(semester)) {
            SubjectCombobox.getItems().addAll(
                "PROGRAMMING", "WORK IMMERSION", "INQUIRIES, INVESTIGATIONS AND IMMERSION",
                "PERSONALITY DEVELOPMENT", "ENGLISH FOR ACADEMIC AND PROFESSIONAL PURPOSES",
                "CONTEMPORARY PHILIPPINE ARTS FROM THE REGION"
            );
        } else if ("Second Semester".equals(semester)) {
            SubjectCombobox.getItems().addAll(
                "COMPUTER PROGRAMMING", "FILIPINO SA PILING LARANG", "PHILOSOPHY",
                "21ST CENTURY LITERATURE FROM THE PHILIPPINES AND THE WORLD", "PRACTICAL RESEARCH"
            );
        }
    }

@FXML
private void insertTeacherSectionSubject() {
    String teacherUsername = nameteachertextfield.getText();
    String sectionName = sectionCombobox.getValue();
    String subject = SubjectCombobox.getValue();

    if (teacherUsername.isEmpty() || sectionName == null || subject == null) {
        // Handle the error, show an alert to the user
        showAlert("Error", "All fields are required.", AlertType.ERROR);
        return;
    }

    // Check if the combination already exists in the database
    String checkQuery = "SELECT COUNT(*) FROM teachersectionssubject WHERE teacherUsername = ? AND sectionName = ? AND subject = ?";
    try (Connection connection = DriverManager.getConnection(sqllink, user, pass);
         PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

        checkStatement.setString(1, teacherUsername);
        checkStatement.setString(2, sectionName);
        checkStatement.setString(3, subject);

        // Execute the query
        try (ResultSet resultSet = checkStatement.executeQuery()) {
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Combination already exists, show an alert message
                showAlert("Error", "Combination already exists.", AlertType.ERROR);
                return;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // If the combination doesn't exist, proceed with insertion
    String insertQuery = "INSERT INTO teachersectionssubject (teacherUsername, sectionName, subject) VALUES (?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(sqllink, user, pass);
         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

        preparedStatement.setString(1, teacherUsername);
        preparedStatement.setString(2, sectionName);
        preparedStatement.setString(3, subject);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            // Show insertion successful alert
            showAlert("Success", "Insertion successful!", AlertType.INFORMATION);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Method to show an alert dialog
private void showAlert(String title, String message, AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
