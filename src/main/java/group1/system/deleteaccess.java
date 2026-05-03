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
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Delmark Lusterio
 */
public class deleteaccess {
    @FXML
    private TableView<teacher > Teacheraccess;
    @FXML
    private TableColumn<teacher, String> Teachername;
     @FXML
    private TableColumn<teacher, String> Sectioncolumn;
      @FXML
    private TableColumn<teacher, String> Subjectcolumn;
  @FXML
  private Button deletebutton;
       
       
     private static final String SQL_LINK = "jdbc:mysql://localhost:3306/usersinfo";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Rootpassword123";

    // Method to initialize the TableView with data
public void initialize() {
      // Assuming your TableView is named Teacheraccess

      Teachername.setCellValueFactory(new PropertyValueFactory<>("teacherUsername"));
    Sectioncolumn.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
    Subjectcolumn.setCellValueFactory(new PropertyValueFactory<>("subject"));

     
        
    try {
        Connection connection = DriverManager.getConnection(SQL_LINK, DB_USER, DB_PASS);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM teachersectionssubject");

        while (resultSet.next()) {
            String teacherUsername = resultSet.getString("teacherUsername");
            String section = resultSet.getString("sectionName");
            String subject = resultSet.getString("subject");

            // Debugging: Print retrieved data to console
            System.out.println("Teacher Username: " + teacherUsername + ", Section: " + section + ", Subject: " + subject);

            Teacheraccess.getItems().add(new teacher(teacherUsername, section, subject));
            
        }

        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}
@FXML
private void deleteEntry() {
    // Get the selected item from the TableView
    teacher selectedTeacher = Teacheraccess.getSelectionModel().getSelectedItem();
    if (selectedTeacher == null) {
        // No item selected, do nothing
        return;
    }

    try {
        Connection connection = DriverManager.getConnection(SQL_LINK, DB_USER, DB_PASS);
        String deleteQuery = "DELETE FROM teachersectionssubject WHERE teacherUsername = ? AND subject = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, selectedTeacher.getTeacherUsername());
        preparedStatement.setString(2, selectedTeacher.getSubject());
        preparedStatement.executeUpdate();

        // Remove the item from the TableView
        Teacheraccess.getItems().remove(selectedTeacher);

        connection.close();
        Teacheraccess.refresh();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
