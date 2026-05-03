package group1.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController {
   
      private double MouseX, MouseY = 0;
      private boolean isSlid = false;
       private boolean isTransitioning = false;
    @FXML
    private Button minimizebutton;
    @FXML 
    private Button exit;
    @FXML
    private TextField userfield;
    @FXML
    private PasswordField passfield;
    @FXML
    private Label PopupLabel;
    @FXML
    private Label PopupLabelregister;
    @FXML
    private ImageView Loginbutton;
    @FXML 
    private JFXButton AboutButton;
    @FXML
    private Label buttonregister;
    @FXML 
    private Label loginregbutton;
    @FXML 
    private ImageView designimage;
    @FXML
    private ImageView logoimage;
    @FXML
    private Text schoolLabel;
    @FXML
    private TextField firstnametextfield;
        @FXML
    private TextField lastnametextfield;
      @FXML
    private TextField agetextfield;
        @FXML
    private TextField lrntextfield;
      @FXML
    private TextField emailtextfield;
    @FXML
    private TextField passwordtextfield;
     @FXML
    private TextField confirmpasswordtextfield;
     @FXML 
     private JFXCheckBox studentcheckbox;
     @FXML
     private JFXCheckBox teachercheckbox;
     @FXML
     private Pane StudentRegisterpane;
     @FXML
     private Pane TeacherRegisterpane;
     @FXML
     private JFXComboBox Sectioncombobox;
     @FXML
     private ImageView completeregisterbutton;
               @FXML
    private TextField firstnametextfieldteacher;
        @FXML
    private TextField lastnametextfieldteacher;     
                     @FXML
    private TextField agetextfieldteacher; 
    @FXML
    private TextField emailtextfieldteacher;
    @FXML
    private TextField passwordtextfieldteacher;
     @FXML
    private TextField confirmpasswordtextfieldteacher;
       @FXML
    private void initialize() {
        Sectioncombobox.getItems().addAll("ICT-A", "ICT-B", "ICT-C");
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
     completeregisterbutton.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), completeregisterbutton);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        completeregisterbutton.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), completeregisterbutton);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
         completeregisterbutton.setOnMousePressed(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), completeregisterbutton);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.play();
    });

    completeregisterbutton.setOnMouseReleased(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), completeregisterbutton);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    buttonregister.setOnMouseClicked(this::toggleSlideElements);
    loginregbutton.setOnMouseClicked(this::toggleSlideElements); 

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
    private void movable(Scene scene, Stage stage) {
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
public void loadadmin(MouseEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/loadadmin.fxml"));
    Parent root = loader.load();
    LoadadminController loadAdminController = loader.getController(); 
    
    // Get the current stage
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Set the stage to LoadadminController
    loadAdminController.setStage(stage);
    
    Scene scene = new Scene(root);
    
    // Ensure the stage is visible before starting the transition
    stage.setScene(scene);
    stage.show();
    
    // Make the scene movable
    movable(scene, stage);
    
    // Start the loading animation
    Timeline timeline = new Timeline();
    for (int i = 0; i <= 50; i++) {
        double progress = i * 0.02;
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(i * 0.1), e -> loadAdminController.updateLoadingStatus(progress))
        );
    }
    
    timeline.setOnFinished(animationEvent -> loadAdminController.updateLoadingStatus(1.0));
    timeline.play();
}
@FXML
public void loadAboutSystem(ActionEvent event) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/Aboutsystem.fxml"));
    Parent root = loader.load();
    aboutSystemController aboutSystemController = loader.getController(); 
    aboutSystemController.setHostServices(App.getHostServicesInstance());
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Ensure the stage is visible before starting the transition
    stage.setScene(scene);
    stage.show();
    
    // Set initial opacity to 0 for fade-in effect
    root.setOpacity(0);
    
    // Fade animation
    FadeTransition transition = new FadeTransition(Duration.seconds(2), root);
    transition.setToValue(1); // Fade to fully visible
    transition.play();
    
    // Make the stage movable
    movable(scene, stage);
}
     @FXML
private void login() throws IOException {
    String username, password;
    String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
    String user = "root";
    String pass = "Rootpassword123";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection(sqllink, user, pass);
        username = userfield.getText();
        password = passfield.getText();

        String sql;
        if (username.matches("\\d{12}")) {
            sql = "SELECT * FROM userprivateinfo WHERE LRN=? AND Password=?";
        } else if (username.matches("^(.+)@(.+)$")) { // Check if username is an email address
            sql = "SELECT * FROM userprivateinfo WHERE Email=? AND Password=?";
        } else {
            sql = "SELECT * FROM userprivateinfo WHERE Username=? AND Password=?";
        }

        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String role = result.getString("Role");
            if (role.equals("Teacher") && (!username.matches("\\d{12}") || username.matches("^(.+)@(.+)$"))) {
                String teacherUsername = result.getString("Username");
                HomeController controller = new HomeController();
                controller.setStage((Stage) userfield.getScene().getWindow());
                controller.loadTeacherHomepage(teacherUsername);
            } else if (role.equals("Student") && username.matches("\\d{12}")) {
                String studentUsername = result.getString("Username");
                HomeController controller = new HomeController();
                controller.setStage((Stage) userfield.getScene().getWindow());
                controller.loadStudentHomepage(studentUsername);
            } else {
                PopupLabel.setText("Invalid username or password");
            }
        } else {
            PopupLabel.setText("Invalid username or password");
        }

    } catch(Exception e) {
        System.out.println("ERROR!" + e.getMessage());
    }
}
 @FXML
    private void slideElements() {
        if (isTransitioning) return; // Prevent new transitions if one is already happening
        isTransitioning = true;

        // Create TranslateTransitions for the elements
        TranslateTransition designImageTransition = new TranslateTransition(Duration.seconds(1), designimage);
        designImageTransition.setByX(553); // Adjust the value to set how far to move

        TranslateTransition logoImageTransition = new TranslateTransition(Duration.seconds(1), logoimage);
        logoImageTransition.setByX(553); // Adjust the value to set how far to move

        TranslateTransition schoolLabelTransition = new TranslateTransition(Duration.seconds(1), schoolLabel);
        schoolLabelTransition.setByX(553); // Adjust the value to set how far to move

        // Play the transitions
        designImageTransition.play();
        logoImageTransition.play();
        schoolLabelTransition.play();

        designImageTransition.setOnFinished(e -> isTransitioning = false);

        minimizebutton.getStyleClass().add("slid");
        exit.getStyleClass().add("slid");
    }

    @FXML
    private void slidebackElements() {
        if (isTransitioning) return; // Prevent new transitions if one is already happening
        isTransitioning = true;

        // Create TranslateTransitions for the elements
        TranslateTransition designImageTransition = new TranslateTransition(Duration.seconds(1), designimage);
        designImageTransition.setByX(-553); // Adjust the value to set how far to move

        TranslateTransition logoImageTransition = new TranslateTransition(Duration.seconds(1), logoimage);
        logoImageTransition.setByX(-553); // Adjust the value to set how far to move

        TranslateTransition schoolLabelTransition = new TranslateTransition(Duration.seconds(1), schoolLabel);
        schoolLabelTransition.setByX(-553); // Adjust the value to set how far to move

        // Play the transitions
        designImageTransition.play();
        logoImageTransition.play();
        schoolLabelTransition.play();

        designImageTransition.setOnFinished(e -> {
            isTransitioning = false;
            minimizebutton.getStyleClass().remove("slid");
            exit.getStyleClass().remove("slid");
        });
    }

    @FXML
    private void toggleSlideElements(MouseEvent event) {
        if (!isTransitioning) {
            if (!isSlid) {
                slideElements();
            } else {
                slidebackElements();
            }
            isSlid = !isSlid; // Toggle the state
        }
    }
    @FXML
    private void toggleStudentRegisterPane() {
        if (studentcheckbox.isSelected()) {
            teachercheckbox.setSelected(false);
            StudentRegisterpane.setVisible(true);
            TeacherRegisterpane.setVisible(false);
        } else {
            StudentRegisterpane.setVisible(false);
        }
    }

    @FXML
    private void toggleTeacherRegisterPane() {
        if (teachercheckbox.isSelected()) {
            studentcheckbox.setSelected(false);
            TeacherRegisterpane.setVisible(true);
            StudentRegisterpane.setVisible(false);
        } else {
            TeacherRegisterpane.setVisible(false);
        }
    }
@FXML
private void registerUser(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        String firstName;
        String lastName;
        String age;
        String email;
        String password;
        String confirmPassword;
        String role = studentcheckbox.isSelected() ? "Student" : (teachercheckbox.isSelected() ? "Teacher" : "");

        // Logging to check the value of role
        System.out.println("Role: " + role);

        if (role.equals("Student")) {
            firstName = firstnametextfield.getText().trim();
            lastName = lastnametextfield.getText().trim();
            age = agetextfield.getText().trim();
            email = emailtextfield.getText().trim();
            password = passwordtextfield.getText().trim();
            confirmPassword = confirmpasswordtextfield.getText().trim();

            String lrn = lrntextfield.getText().trim();
            String section = (String) Sectioncombobox.getSelectionModel().getSelectedItem();
            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || lrn.isEmpty() || section.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all the required fields.");
                return;
            }
        } else if (role.equals("Teacher")) {
            firstName = firstnametextfieldteacher.getText().trim();
            lastName = lastnametextfieldteacher.getText().trim();
            age = agetextfieldteacher.getText().trim();
            email = emailtextfieldteacher.getText().trim();
            password = passwordtextfieldteacher.getText().trim();
            confirmPassword = confirmpasswordtextfieldteacher.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all the required fields.");
                return;
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a role.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match");
            return;
        }

        String username = firstName + " " + lastName;

        String sqllink = "jdbc:mysql://localhost:3306/usersinfo";
        String user = "root";
        String pass = "Rootpassword123";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(sqllink, user, pass);

            // Check if the email already exists
            String checkEmailSql = "SELECT COUNT(*) FROM userprivateinfo WHERE Email = ?";
            statement = connect.prepareStatement(checkEmailSql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Email already exists.");
                return;
            }
            
            // Close the statement and result set for reuse
            statement.close();
            resultSet.close();

            String sql;
            if (role.equals("Student")) {
                String lrn = lrntextfield.getText().trim();
                String section = (String) Sectioncombobox.getSelectionModel().getSelectedItem();
                sql = "INSERT INTO userprivateinfo (Username, Password, Role, LRN, Age, Email, Section) VALUES (?, ?, ?, ?, ?, ?, ?)";
                statement = connect.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, role);
                statement.setString(4, lrn);
                statement.setInt(5, Integer.parseInt(age));
                statement.setString(6, email);
                statement.setString(7, section); 
            } else if (role.equals("Teacher")) {
                sql = "INSERT INTO userprivateinfo (Username, Password, Role, Age, Email) VALUES (?, ?, ?, ?, ?)";
                statement = connect.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, role);
                statement.setInt(4, Integer.parseInt(age));
                statement.setString(5, email);
            }

            statement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful");

            // Clear text fields after successful registration
            firstnametextfield.clear();
            lastnametextfield.clear();
            agetextfield.clear();
            lrntextfield.clear();
            emailtextfield.clear();
            passwordtextfield.clear();
            confirmpasswordtextfield.clear();
            firstnametextfieldteacher.clear();
            lastnametextfieldteacher.clear();
            agetextfieldteacher.clear();
            emailtextfieldteacher.clear();
            passwordtextfieldteacher.clear();
            confirmpasswordtextfieldteacher.clear();

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Registration failed: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Handle the exception
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // Handle the exception
                }
            }
        }
    }
}

private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
}