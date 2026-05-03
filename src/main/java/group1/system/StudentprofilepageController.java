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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class StudentprofilepageController {
     private double MouseX, MouseY = 0;
     private Stage stage;
@FXML
private JFXButton ProfileButton;
@FXML 
private JFXButton todobutton;
@FXML
    private Button minimizebutton;
    private String studentUsername;
    
@FXML
   private Label namelabel;
    @FXML
    private JFXButton homebutton;
    @FXML 
    private JFXButton notificationbutton;
  @FXML
private JFXListView<Label> notificationListView;
 
@FXML 
    private void close() throws IOException {
        Platform.exit();
    }
   
    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
    }
     public void initData(String studentUsername) throws SQLException {
        this.studentUsername = studentUsername;
        namelabel.setText(studentUsername);
         populateNotifications();
    }


     public void initialize() {
       populateNotifications();
    }







@FXML
private void goToHomestudents(ActionEvent event) throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/student_home.fxml"));
    Parent root = loader.load();
    StudenthomepageController controllerhome = loader.getController();    
    controllerhome.initData(studentUsername);
    Scene scene = new Scene(root);
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    movable(currentStage, scene); 
    currentStage.setScene(scene); 
    currentStage.show();
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
@FXML
 private void showNotificationDropdown() {
     populateNotifications();
        notificationListView.setVisible(!notificationListView.isVisible());
    }
     private void populateNotifications() {
        ObservableList<Label> notifications = FXCollections.observableArrayList();
String url = "jdbc:mysql://localhost:3306/usersinfo";
        String dbuser = "root";
        String dbpass = "Rootpassword123";
        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        int userId = getUserId(studentUsername);
        String notificationsql = "SELECT * FROM notifications WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(notificationsql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String notificationText = resultSet.getString("notification_text");
                    Label notificationLabel = new Label(notificationText);
                    notifications.add(notificationLabel);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }


        notificationListView.setItems(notifications);
    }
     
   private int getUserId(String studentUsername) throws SQLException {
        int userId = -1;
        String url = "jdbc:mysql://localhost:3306/usersinfo";
        String dbuser = "root";
        String dbpass = "Rootpassword123";

        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
            String sql = "SELECT UID FROM userprivateinfo WHERE Username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, studentUsername);
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