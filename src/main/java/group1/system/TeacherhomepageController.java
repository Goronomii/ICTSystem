package group1.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TeacherhomepageController {
    private double MouseX, MouseY = 0;
    
    private AnchorPane parentContainer;
@FXML
private JFXButton sectionICTA;
@FXML
private JFXButton sectionICTB;
@FXML
private JFXButton sectionICTC;
 @FXML
private Text dateTimeText;
 @FXML
    private ImageView logout;
@FXML

    private Button minimizebutton;
    private String username;
    private Stage stage;
    private String teacherUsername;
    @FXML
   private Label namelabel;

    
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
     
     public void initData(String teacherUsername) throws SQLException {
       this.teacherUsername = teacherUsername;
        namelabel.setText(teacherUsername);
        
        
    }
     
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
private void goToSubmission(ActionEvent event) throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher_submission.fxml"));
    Parent root = loader.load();
    TeacherSubmissionController controller = loader.getController();    
    controller.initData(teacherUsername); 
    Scene scene = new Scene(root);
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    movable(scene, currentStage); 
    currentStage.setScene(scene); 
    currentStage.show();
}

   @FXML
    private void initialize() {
        logout.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), logout);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        logout.setOnMouseExited(event -> {  
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), logout);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
         logout.setOnMousePressed(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), logout);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.play();
    });

    logout.setOnMouseReleased(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), logout);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {
            dateTimeText.setText(LocalDateTime.now().format(formatter));
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    }
     
@FXML
private void logout(MouseEvent event) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Are you sure you want to logout?");
    alert.setContentText("Press OK to logout, or Cancel to stay.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);

        // Make the stage movable
        movable(scene, primaryStage);

        primaryStage.show();
    }
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

@FXML
private void sectionICTAPressed(ActionEvent event) throws SQLException {
    if (checkSectionAccess("ICT-A")) {
       switchSceneICTA("/group1/system/ICT-12Cteachermanagement.fxml");
    }
}

@FXML
private void sectionICTBPressed(ActionEvent event) throws SQLException {
    if (checkSectionAccess("ICT-B")) {
        switchSceneICTB("/group1/system/ICT-12Bteachermanagement.fxml");
    }
}
@FXML
private void sectionICTCPressed(ActionEvent event) throws SQLException {
    if (checkSectionAccess("ICT-C")) {
        switchSceneICTC("/group1/system/ICT-12Cteachermanagement.fxml");
    }
}
private void switchSceneICTA(String fxmlResource) throws SQLException {
    try {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/ICT-12Ateachermanagement.fxml"));
        Parent root = loader.load();
        TeacherICTAManage ICTAmanage = loader.getController();
        ICTAmanage.initData(teacherUsername);
        Scene scene = new Scene(new AnchorPane(root));
        Stage stage = (Stage) sectionICTA.getScene().getWindow();
        movable(scene,stage);    
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
        
    }
}   

private void switchSceneICTB(String fxmlResource) throws SQLException {
    try {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/ICT-12Bteachermanagement.fxml"));
        Parent root = loader.load();
        TeacherICTBManage ICTBmanage = loader.getController();
        ICTBmanage.initData(teacherUsername);
        Scene scene = new Scene(new AnchorPane(root));
        Stage stage = (Stage) sectionICTB.getScene().getWindow();
        movable(scene,stage);    
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
        
    }
}   
private void switchSceneICTC(String fxmlResource) throws SQLException {
    try {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/ICT-12Cteachermanagement.fxml"));
        Parent root = loader.load();
        TeacherICTCManage ICTCmanage = loader.getController();
        ICTCmanage.initData(teacherUsername);
        Scene scene = new Scene(new AnchorPane(root));
        Stage stage = (Stage) sectionICTC.getScene().getWindow();
        movable(scene,stage);    
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
        
    }
}   

private boolean checkSectionAccess(String sectionName) {
    System.out.println("Checking section access...");
    String url = "jdbc:mysql://localhost:3306/usersinfo";
    String dbuser = "root";
    String dbpass = "Rootpassword123";
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Access Denied");
    alert.setHeaderText(null);
    alert.setContentText("You don't have access to this section.");

    try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
        String query = "SELECT sectionName FROM TeacherSectionsSubject WHERE teacherUsername = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teacherUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String fetchedSectionName = resultSet.getString("sectionName");
                    if (sectionName.equals(fetchedSectionName)) {
                       
                        return true;
                    }
                }
            }
        }
        // Display alert if the section is not found
        alert.showAndWait();
        return false;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
  }
