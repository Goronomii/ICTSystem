/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Delmark Lusterio
 */
public class AdminloginController {
         private Scene scene;
    private Stage stage;
        private double MouseX, MouseY = 0;
            @FXML
    private ImageView Loginbutton;
        @FXML
    private Button minimizebutton;  
                 @FXML
    private TextField userfield;
    @FXML
    private PasswordField passfield;
           private static final String SQL_LINK = "jdbc:mysql://localhost:3306/usersinfo";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Rootpassword123";
         public void setStage(Stage stage) {
        this.stage = stage;
    }
public void setScene(Scene scene) {
    this.scene = scene;
}
    @FXML
    private void close() throws IOException {
        Platform.exit();
    }
   
    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
    }
    
        
        
         @FXML
     public void movable(Scene scene) {
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
    private void initialize() {
        Loginbutton.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), Loginbutton);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        Loginbutton.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), Loginbutton);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
         Loginbutton.setOnMousePressed(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), Loginbutton);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.play();
    });

    Loginbutton.setOnMouseReleased(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), Loginbutton);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    Loginbutton.setOnMouseClicked(this::login);
    }
   @FXML
    private void login(MouseEvent event) {
        String username = userfield.getText();
        String password = passfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter your username and password");
            return;
        }

        // Authenticate user
        if (authenticate(username, password)) {
            // Show a success message
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome " + username);
            // Proceed to the admin panel scene
            switchToAdminPanel();
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Incorrect username or password");
        }
    }

    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM adminprivateinfo WHERE Username = ? AND Password = ?";

        try (Connection conn = DriverManager.getConnection(SQL_LINK, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void switchToAdminPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminpanel.fxml"));
            Parent root = loader.load();
            Scene adminPanelScene = new Scene(root);
            Stage window = (Stage) Loginbutton.getScene().getWindow();
            window.setScene(adminPanelScene);
            window.show();

            // Make the new scene movable
            AdminPanelController controller = loader.getController();
            controller.setStage(window);
            controller.setScene(adminPanelScene);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Scene Switch Error", "Failed to load the admin panel.");
        }
    }
}
